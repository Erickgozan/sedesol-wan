package com.proyectosedesol.visualizarPDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisualizarPDF extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = request.getParameter("documento");
        try {
            
            File archivos = new File(ruta);
            FileInputStream ficheroInput = new FileInputStream(archivos.getAbsolutePath());//Documento de entrada
            int tamanoInput = ficheroInput.available();//tamaño total del documento
            byte[] datosPDF = new byte[tamanoInput];//Arreglo de bytes que componen el documento
            ficheroInput.read(datosPDF, 0, tamanoInput);//Lee todos los datos del documento byte[], comenzando en 0 y termninado tamanoInput(total)
 
            response.setHeader("Content-disposition", "inline; filename=documentos.pdf");
            response.setContentType("application/pdf");//Estandar MIME
            response.setContentLength(tamanoInput);
            response.getOutputStream().write(datosPDF);
            ficheroInput.close();
        
        } catch (IOException e) {
            response.getWriter().println(e.getLocalizedMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
