package com.proyectosedesol.excel;

import com.proyectosedesol.entidades.Estancia;
import com.proyectosedesol.entidades.RevisarAm;
import com.proyectosedesol.modelo.ModeloProcesos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportarExcel extends HttpServlet {

    private ModeloProcesos porcesos;
    @Resource(name = "jdbc/sedesol")
    private DataSource pool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            porcesos = new ModeloProcesos(pool);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Codico que permite subir el documento al servidor
        FileItemFactory file_factory = new DiskFileItemFactory(); //Interface que permite guardar el archivo en el disco
        ServletFileUpload upload = new ServletFileUpload(file_factory); //Clase que permite almacenar los datos(HTML) por piezas individuales.
        //está determinada por la fábrica utilizada para crearlos; una parte dada puede estar en la memoria, en el disco o en otro lugar.
        String path = "documentos/archivosExcel";
        String ruta = "";
        String input = "";
        try {
            File carpeta = new File(path);
            if(!carpeta.exists()){
                carpeta.mkdir();
            }
            List items = upload.parseRequest(request);//Parsea los parametros(name) del formulario
            for (Object item : items) { //Recorremos todos los items del formulario
                FileItem uploaded = (FileItem) item;// Se crea un variable para guardar los items(input) del formulario
                if (!uploaded.isFormField()) { // Evaluamos si los input no son de tipo File
                    File archivo = new File(path + "/" + uploaded.getName());//Creamos un objeto de tipo File, con la ruta y nombre por parametro
                    uploaded.write(archivo); // Creamos/guardamos el archivo 
                    ruta = archivo.toString(); // Imprimir la ruta completa
                } else {
                    input = uploaded.getString();// Se guardan los inputs que no son de tipo File.
                }
            }
            
           //Codigo para copiar el excel(xlsx) a la base de datos 

            FileInputStream file = new FileInputStream(new File(ruta));//Objeto para recuperar el archivo guardado
            //Paso 1 crear el libro
            XSSFWorkbook wb = new XSSFWorkbook(file);//Se crea el excel con el archivo recuperado
            //Paso 2 se crea la hoja
            XSSFSheet sheet = wb.getSheetAt(0);// Retorna la hoja del libro de  excel que se creo previamente
            
            //Paso 3 obtenemos el numero de filas que tendra el archivo subido
            int numfilas = sheet.getLastRowNum();//Devuelve el nuero total de filas que contiene la hoja

            //Paso 4 recorremos el las filas del archivo excel empezando por la 2
            for (int i = 1; i <= numfilas; i++) { // recorremos las filas de la hoja de excel
               //Paso 5 guardamos en la variable de tipo Row fila por fila de la hoja creada  
                Row fila = sheet.getRow(i); // Se guardan las filas en el objeto Row en el indice i
                //paso 6 declaramon el objeto donde se guargaran los valores 
                Estancia doc;
                /*fila.getCell(0)-metodo para obtener la posicion de la celda del archivo Excel
                y getNumericCellValue()-metodo para obtener el valor*/ 
                switch (input) {
                    //paso 7 insertamos/actualizamos los datos en la base de datos
                    case "insertDocumento":
                        doc = new Estancia();
                        doc.setId_estancia((int) fila.getCell(0).getNumericCellValue());//Fila ID
                        doc.setNombre_estancia(fila.getCell(1).getStringCellValue());//Fila NOMBRE DE LA ESTANCIA
                        doc.setNombre_responsable(fila.getCell(2).getStringCellValue());//Fila NOMBRE DE LA RESPONSEBLE
                        doc.setDireccion(fila.getCell(3).getStringCellValue());//Fila DIRECCION
                        doc.setEstado(fila.getCell(4).getStringCellValue());//Fila ESTADO
                        doc.setFehca_fin(fila.getCell(5).getDateCellValue());//Fila FECHA DE FIN
                        porcesos.insertarDatosDoc(doc);
                        break;
                    case "ActualizarDocumento":
                        doc = new Estancia();
                        doc.setId_estancia((int) fila.getCell(0).getNumericCellValue());
                        doc.setNombre_estancia(fila.getCell(1).getStringCellValue());
                        doc.setNombre_responsable(fila.getCell(2).getStringCellValue());
                        doc.setDireccion(fila.getCell(3).getStringCellValue());
                        doc.setEstado(fila.getCell(4).getStringCellValue());
                        doc.setFehca_fin(fila.getCell(5).getDateCellValue());
                        porcesos.actualizarDbDoc(doc);
                        break;
                    case "insertDocumentoRevisar":
                        RevisarAm revDoc = new RevisarAm();
                        revDoc.setId_revision((int) fila.getCell(0).getNumericCellValue());//Fila ID
                        revDoc.setId_subir_doc((int) fila.getCell(1).getNumericCellValue());//Fila ID_SUBIR_DOC
                        porcesos.insertarDatosRev(revDoc);
                        break;
                    default:
                        break;
                }
                    
            }
                 out.print("Se inserto correctamente a la base de datos");
        } catch (Exception e) {
            out.print(e.getLocalizedMessage());
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
/*
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 Date fechaFin;
                fechaFin = df.parse(fila.getCell(6).getDateCellValue());



                doc = new Estancia((int) fila.getCell(0).getNumericCellValue(),
                            fila.getCell(1).getStringCellValue(),
                            fila.getCell(2).getStringCellValue(),
                            fila.getCell(3).getStringCellValue(),
                            fila.getCell(4).getStringCellValue(),
                            fila.getCell(5).getStringCellValue(),
                            fila.getCell(6).getDateCellValue());
 */
