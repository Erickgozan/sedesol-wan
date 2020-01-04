package com.proyectosedesol.controlador;

import com.proyectosedesol.entidades.Estancia;
import com.proyectosedesol.entidades.EstanciaCr;
import com.proyectosedesol.entidades.RevisarCr;
import com.proyectosedesol.modelo.ModeloProcesos;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ControladorCR extends HttpServlet {

    private ModeloProcesos procesos;
    @Resource(name = "jdbc/sedesol")
    private DataSource pool;
    private int idEstancia;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            procesos = new ModeloProcesos(pool);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String proceso = request.getParameter("proceso");

        if (proceso == null) {
            proceso = "SubirDatosCR";
        }

        switch (proceso) {
            case "CambioResponsable":
                getDatosEstancia(request, response);
                break;
            case "SubirDatosCR":
                insertarDatosEstancia(request, response);
                break;
            case "insertarCR":
                insertarDatosRevisar_cr(request, response);
                break;
            case "actualizarCR":
                actualizarDatosRevisar_cr(request, response);
                break;
            case "listarCR":
                listarCR(request, response);
                break;
            case "idBuscarEstanciaCr":
                listarCR(request, response);
                break;
            case "eliminar":
                eliminarCR(request, response);
                break;

        }
    }

    private void getDatosEstancia(HttpServletRequest request, HttpServletResponse response) throws IOException {

        idEstancia = Integer.parseInt(request.getParameter("txtIdEstancia"));
        String buscar = request.getParameter("buscar");
        RequestDispatcher rd;
        Estancia estancia;
        RevisarCr revisar;
        try {
            estancia = procesos.getDatosEstacia(idEstancia);
            request.setAttribute("datosEstancia", estancia);

            if (buscar.equals("idBuscar")) {
                rd = request.getRequestDispatcher("/sesionIniciada/CambioResponsable.jsp");
                rd.forward(request, response);
            } else if (buscar.equals("idRevisar")) {
                revisar = procesos.getDatosCr(idEstancia);
                request.setAttribute("revisionCR", revisar);
                rd = request.getRequestDispatcher("/sesionIniciada/CambioResponsableP.jsp");
                rd.forward(request, response);
            }

        } catch (IOException | SQLException | ServletException e) {
            response.getWriter().print("Error en el metodo getDatosEstancia: " + e.getLocalizedMessage());

        }

    }

    private void insertarDatosEstancia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        /*FileItemFactory es una interfaz para crear FileItem*/
        FileItemFactory file = new DiskFileItemFactory();
        /*ServletFileUpload esta clase convierte los input file a FileItem*/
        ServletFileUpload componentesForm = new ServletFileUpload(file);
        //Array que guarda los componentes que no sean de tipo File
        ArrayList<String> inputsText = new ArrayList<>();
        //ruta servlet
        ServletContext context = request.getServletContext();
        //Ruta real del servidor
        String rutaCarpta = context.getRealPath("documentos/CR/" + idEstancia);
        //Array que guarda las rutas de las carpetas
        ArrayList<String> rutaArchivos = new ArrayList<>();

        Estancia estancia = new Estancia();
        try {
            /*Codigo para crear la carpeta en el servidor*/
            File carpeta = new File(rutaCarpta);
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }
            /*sacando los FileItem del ServletFileUpload en una lista */
            List items = componentesForm.parseRequest(request);
            for (Object input : items) {
                /*FileItem representa un archivo en memoria que puede ser pasado al disco duro*/
                FileItem archivosSubidos = (FileItem) input;
                if (!archivosSubidos.isFormField()) {
                    String rutaCompleta = rutaCarpta + "/" + archivosSubidos.getName();
                    File archivo = new File(rutaCompleta);
                    archivosSubidos.write(archivo);
                    //Arreglo de file para gusardar el total de archivos dentro de la carpeta
                    File[] ficheros = carpeta.listFiles();
                    for (int i = 0; i < ficheros.length; i++) {
                        rutaArchivos.add(rutaCarpta + "/" + ficheros[i].getName());
                        if (i == 0) {
                            estancia.setRuta_docR1(rutaArchivos.get(0));
                        } else if (i == 1) {
                            estancia.setRuta_docR2(rutaArchivos.get(1));
                        } else if (i == 2) {
                            estancia.setRuta_docR3(rutaArchivos.get(2));
                        } else if (i > 2) {
                            out.println("Se supero el limite de archivos");
                        }
                    }

                } else {
                    inputsText.add(archivosSubidos.getString());
                }
            }

            estancia.setId_estancia(idEstancia);
            estancia.setEstatus_responsable(inputsText.get(0));
            estancia.setNombre_responsable(inputsText.get(1));

            procesos.insertarDatosCR(estancia);

            out.println("<br>La informacion se actualizo correctamente");
        } catch (FileUploadException e) {
            response.getWriter().print("Error al subir el archivo " + e.getLocalizedMessage());
        } catch (Exception e) {
            response.getWriter().print("Error al subir el archivo, el archivo ya existe. ");
        }
    }

    private void insertarDatosRevisar_cr(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        String selectDoc1 = request.getParameter("selectDoc1");
        String selectDoc2 = request.getParameter("selectDoc2");
        String selectDoc3 = request.getParameter("selectDoc3");

        String estatus = request.getParameter("estatus");
        String txtAreacomentarios = request.getParameter("txtAreacomentarios");
        String txtUsuarioRev = request.getParameter("txtUsuarioRev");

        try {
            out = response.getWriter();

            RevisarCr revisar = new RevisarCr(idEstancia, selectDoc1, selectDoc2, selectDoc3, txtAreacomentarios, estatus, txtUsuarioRev);
            procesos.insertarDatosRevisar_cr(revisar);

            out.print("LA REVICIÓN SE REALIZÓ CORRECTAMENTE");
        } catch (IOException | SQLException ex) {
            out.println("Error en el metodo insertarDatosRevisar_cd: " + ex.getLocalizedMessage());
        }
    }

    private void actualizarDatosRevisar_cr(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        String selectDoc1 = request.getParameter("selectDoc1");
        String selectDoc2 = request.getParameter("selectDoc2");
        String selectDoc3 = request.getParameter("selectDoc3");

        String estatus = request.getParameter("estatus");
        String txtAreacomentarios = request.getParameter("txtAreacomentarios");
        String txtUsuarioRev = request.getParameter("txtUsuarioRev");

        try {
            out = response.getWriter();

            RevisarCr revisar = new RevisarCr(idEstancia, selectDoc1, selectDoc2, selectDoc3, txtAreacomentarios, estatus, txtUsuarioRev);
            procesos.actualizarDatosRevisar_cr(revisar);

            out.print("LA REVICIÓN SE ACTUALIZÓ CORRECTAMENTE");
        } catch (IOException | SQLException ex) {
            out.println("Error en el metodo insertarDatosRevisar_cd: " + ex.getLocalizedMessage());
        }
    }

    private void listarCR(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = null;
        List<EstanciaCr> listaCr;
        RequestDispatcher dispatcher;
        String parametro = request.getParameter("BuscarEstanciaCr");
        String estado = request.getParameter("estado");

        try {
            out = response.getWriter();

            if (parametro != null) {
                listaCr = procesos.getEstanciaCR(parametro, estado);
                request.setAttribute("estanciaCr", listaCr);
                if (listaCr.size() < 1) {
                    request.setAttribute("noEncontrado", "LA ESTANCIA NO HA INICIADO EL PROCESO O NO PERTENCE A TU REGIÓN ");
                }
            } else {
                listaCr = procesos.getListadoCR(estado);
                request.setAttribute("estanciaCr", listaCr);
                if (listaCr.size() < 1) {
                    request.setAttribute("noEncontrado", "NO HAY PROCESOS DE CAMBIO DE DOMICILIO REVISADOS");
                }
            }

            dispatcher = request.getRequestDispatcher("/sesionIniciada/ListadoCR.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | IOException ex) {
            out.print("Error en el contolador. metodo-listarCD. Error: " + ex.getLocalizedMessage());
        }
    }

    private void eliminarCR(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = null;
        try {
            out = response.getWriter();
            int id_estancia = Integer.parseInt(request.getParameter("id"));
            String rutaDocCr = request.getParameter("ruta");
            String numDocumento = request.getParameter("documento");
            RequestDispatcher rd;
            //out.print(id_estancia + "<br>" + rutaDocCd1 );
            File docCr = new File(rutaDocCr);

            if (docCr.delete()) {
                procesos.eliminarCR(id_estancia, numDocumento);
                request.setAttribute("eliminarCr", "EL DOCUMENTO SE ELIMINO CORRECTAMENTE");
                rd = request.getRequestDispatcher("sesionIniciada/ListadoCR.jsp");
                rd.forward(request, response);
            } else {
                out.println("El documento no se pudo eliminar");
            }

        } catch (IOException | SQLException | ServletException ex) {
            out.println("Error " + ex.getLocalizedMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
