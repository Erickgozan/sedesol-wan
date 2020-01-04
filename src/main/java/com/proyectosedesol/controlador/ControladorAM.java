package com.proyectosedesol.controlador;

import com.proyectosedesol.modelo.ModeloProcesos;
import com.proyectosedesol.entidades.Estancia;
import com.proyectosedesol.entidades.EstanciaAm;
import com.proyectosedesol.entidades.RevisarAm;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ControladorAM extends HttpServlet {

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
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String proceso = request.getParameter("proceso");
        //String paginacion = request.getParameter("pagina");
        if (proceso == null) {
            proceso = "subirDoc";
        }

        switch (proceso) {
            case "idEstancia":
                getDatosEstancia(request, response);
                break;
            case "idRevisarDoc":
                getDatosRev(request, response);
                break;
            case "subirDoc":
                insertarDatosEstancia(request, response);
                break;
            case "subirDatosCD":
                insertarDatosEstanciaD(request, response);
                break;
            case "revisarDoc":
                insertarDatosRev(request, response);
                break;
            case "listado":
                listadoDatos(request, response);
                break;
            case "idBuscarRev":
                listadoDatos(request, response);
                break;
            case "paginacion":
                listadoDatos(request, response);
                break;
            case "eliminar":
                eliminarRegistro(request, response);
            case "navegacion":
                navegar(request, response);
                break;
        }

    }

    private void getDatosEstancia(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            idEstancia = Integer.parseInt(request.getParameter("txtIdEstancia"));
            String buscar = request.getParameter("buscar");

            Estancia datos = procesos.getDatosEstacia(idEstancia);

            if (datos == null) {
                request.setAttribute("noEncontrado", "LA ESTANCIA NO SE ENCUENTRA EN LA BASE DE DATOS");
                RequestDispatcher dispatcherSubir = request.getRequestDispatcher("/sesionIniciada/SubirDocumentacion.jsp");
                dispatcherSubir.forward(request, response);

            } else {

                request.setAttribute("DatosDoc", datos);

                switch (buscar) {
                    case "idRevisar": {
                        getDatosRev(request, response);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/sesionIniciada/RevisarDocumentacion.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                    case "idSubir": {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/sesionIniciada/SubirDocumentacion.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                    case "idCambioDomicilio": {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/sesionIniciada/CambioDomicilio.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                    case "idCambioResponsable": {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/sesionIniciada/CambioResponsable.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                    default:
                        break;
                }
            }
        } catch (SQLException | ServletException ex) {
            response.getWriter().print("Error-> en el metodo getDatosDoc: " + ex.getLocalizedMessage());
        } catch (NumberFormatException e) {
            request.setAttribute("formatoInvalido", "SE INGRESO UN VALOR INVALIDO");
            RequestDispatcher dispatcherSubir = request.getRequestDispatcher("/sesionIniciada/SubirDocumentacion.jsp");
            dispatcherSubir.forward(request, response);
        }

    }

    private void getDatosRev(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RevisarAm revDoc;
        try {
            revDoc = procesos.getDatosRevisar(idEstancia);
            request.setAttribute("DatosRevision", revDoc);

        } catch (SQLException ex) {
            response.getWriter().print("Error-> en el metodo getDatosDoc: " + ex.getLocalizedMessage());
        }
    }

    private void insertarDatosEstancia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        FileItemFactory file_factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(file_factory);

        ArrayList<String> campos = new ArrayList<>();
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("documentos/AM/" + idEstancia);

        File archivo = null;
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
            List items = upload.parseRequest(request);
            for (Object item : items) {
                FileItem uploaded = (FileItem) item;
                if (!uploaded.isFormField()) {
                     String ruta = path + "/" + uploaded.getName();
                     archivo = new File(ruta);
                    uploaded.write(archivo);
                } else {
                    campos.add(uploaded.getString());
                }
            }
            Estancia datos = new Estancia();
            datos.setId_estancia(idEstancia);
            datos.setFecha_inicio(campos.get(1));
            datos.setRuta_doc(archivo.getAbsolutePath());
            procesos.actualizarDatosEstancia(datos);
            out.print("EL REGISTRO SE REALIZÓ CORRECTAMENTE");
        } catch (Exception e) {
            out.print("Error-> en el metodo insertarDatosDoc: " + e.getLocalizedMessage());
        }

    }

    private void insertarDatosEstanciaD(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.getWriter().print("funciona");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertarDatosRev(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        String selNombreEstancia = request.getParameter("selectNombreEstancia");
        String selectNombreRespon = request.getParameter("selectNombreRespon");
        String selectDireccion = request.getParameter("selectDireccion");
        String selectEstado = request.getParameter("selectEstado");
        String selectFechaInicio = request.getParameter("selectFechaInicio");
        String selectFechaFin = request.getParameter("selectFechaFin");
        String txtUsuarioRev = request.getParameter("txtUsuarioRev");
        String selectEstatus = request.getParameter("selectEstatus");
        String comentariosAm = request.getParameter("comentariosAm");

        try {
            RevisarAm rev = new RevisarAm();
            rev.setId_revision(idEstancia);
            rev.setR_nombre_estancia(selNombreEstancia);
            rev.setR_nombre_responsable(selectNombreRespon);
            rev.setR_direccion(selectDireccion);
            rev.setR_estado(selectEstado);
            rev.setR_fecha_inicio(selectFechaInicio);
            rev.setR_fecha_fin(selectFechaFin);
            rev.setUsuario_rev(txtUsuarioRev);
            rev.setEstatus(selectEstatus);
            rev.setComentarios(comentariosAm);
            procesos.actualizarDatosRev(rev);

            out.print("EL DOCUMENTO SE HA REVISADO CORRECTAMENTE");
        } catch (SQLException ex) {
            out.print("Error-> en el metodo insertarDatosRev: " + ex.getLocalizedMessage());
        }

    }

    private void listadoDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Object> estancia;
        List<EstanciaAm> listado;
        PrintWriter out = response.getWriter();
        String btnBuscarRev = request.getParameter("btnBuscarRev");
        String estado = request.getParameter("estado");
        String paginaString = request.getParameter("pagina");
        int pagina;

        try {
            if (paginaString == null) {
                pagina = 0;
            } else {
                pagina = Integer.parseInt(paginaString);
            }
            int finaliza = 500;

            if (btnBuscarRev != null) {
                //codigo de la busqueda de la estancia por su ID
                estancia = procesos.listarDatos(Integer.parseInt(btnBuscarRev), estado);
                request.setAttribute("listadoDatos", estancia.get(0));
                request.setAttribute("listadoRev", estancia.get(1));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/sesionIniciada/ListadoEstancias.jsp");
                dispatcher.forward(request, response);
            } else {
                //Codigo que muestre el lsitado completo de estancias              
                int numeroRegistros = procesos.getNumRegistros(estado);
                int numeroPaginas = numeroRegistros / (finaliza - 1);
                request.setAttribute("numeroPaginas", numeroPaginas);
                request.setAttribute("pagina", pagina);
                listado = procesos.getListadoJoin(pagina * (finaliza - 1), finaliza, estado);
                request.setAttribute("listado", listado);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/sesionIniciada/ListadoEstancias.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException ex) {
            out.print("Error en el metodo listadoDatos -> " + ex.getLocalizedMessage());
        } catch (IndexOutOfBoundsException ex) {
            request.setAttribute("noEncontrado", "LA ESTANCIA NO SE ENCUENTRA EN LA BASE DE DATOS O NO PERTENECE A TU REGIÓN");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/sesionIniciada/ListadoEstancias.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void eliminarRegistro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        //variables de la vista
        int id = Integer.parseInt(request.getParameter("id"));
        String ruta = request.getParameter("ruta");
        //entidades y/u objetos
        File archivo = new File(ruta);
        Estancia doc;
        RevisarAm docRev;
        RequestDispatcher dispatcher;
        if (archivo.delete()) {
            doc = new Estancia();
            doc.setFecha_inicio(null);
            doc.setRuta_doc(null);
            doc.setId_estancia(id);
            docRev = new RevisarAm(id, id, null, null, null, null, null, null, null, null, null);
            try {
                procesos.actualizarDatosEstancia(doc);
                procesos.actualizarDatosRev(docRev);

            } catch (SQLException ex) {
                out.print("Error en eliminar el registro: " + ex.getLocalizedMessage());
            }
            request.setAttribute("eliminarS", "EL REGISTRO SE ELIMINO CORRECTAMENTE");
            dispatcher = request.getRequestDispatcher("/sesionIniciada/ListadoEstancias.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("eliminarE", "EL REGISTRO NO SE PUDO ELIMINAR!");
            dispatcher = request.getRequestDispatcher("/sesionIniciada/ListadoEstancias.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void navegar(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String pg = request.getParameter("pg");
        RequestDispatcher rd;
        try {
            switch (pg) {
                 case "inicio":
                    rd = request.getRequestDispatcher("/sesionIniciada/Inicio.jsp");
                    rd.forward(request, response);
                    break;
                case "subirAm":
                    rd = request.getRequestDispatcher("/sesionIniciada/SubirDocumentacion.jsp");
                    rd.forward(request, response);
                    break;
                case "revisarAm":
                    rd = request.getRequestDispatcher("/sesionIniciada/RevisarDocumentacion.jsp");
                    rd.forward(request, response);
                    break;
                case "subirCd":
                    rd = request.getRequestDispatcher("/sesionIniciada/CambioDomicilio.jsp");
                    rd.forward(request, response);
                    break;
                    case "revisarCd":
                    rd = request.getRequestDispatcher("/sesionIniciada/CambioDomicilioP.jsp");
                    rd.forward(request, response);
                    break;
                    case "subirCr":
                    rd = request.getRequestDispatcher("/sesionIniciada/CambioResponsable.jsp");
                    rd.forward(request, response);
                    break;
                    case "revisarCr":
                    rd = request.getRequestDispatcher("/sesionIniciada/CambioResponsableP.jsp");
                    rd.forward(request, response);
                    break;
                    case "actualizarDB":
                    rd = request.getRequestDispatcher("/sesionIniciada/ActualizarDB.jsp");
                    rd.forward(request, response);
                    break;
                   
                default:
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorAM.class.getName()).log(Level.SEVERE, null, ex);
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
