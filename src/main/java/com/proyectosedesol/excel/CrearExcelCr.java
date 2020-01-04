/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectosedesol.excel;

import com.proyectosedesol.entidades.EstanciaCd;
import com.proyectosedesol.entidades.EstanciaCr;
import com.proyectosedesol.modelo.ModeloProcesos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CrearExcelCr extends HttpServlet {

    private ModeloProcesos procesos;
    @Resource(name = "jdbc/sedesol")
    private DataSource pool;

    @Override
    public void init() throws ServletException {
        try {
            procesos = new ModeloProcesos(pool);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        response.setContentType("text/html;charset=UTF-8");
       
        response.setHeader("Content-Disposition", "attachment; filename=ListadoCR.xls");
        response.setContentType("application/vnd.ms-excel");
       
        
        PrintWriter out = response.getWriter();

        String estado = request.getParameter("estado");
        List<EstanciaCr> lista;

        try {
            lista = procesos.getListadoCR(estado);
            out.println("ID ESTANCIA\t"+"NOMBRE DE LA ESTANCIA\t" + "NOMBRE DE LA RESPONSABLE\t" + 
                    "DIRECCION\t" + "ESTADO\t" + "DOCUMENTO 1\t" + "DOCUMENTO 2\t" + "DOCUMENTO 3\t"
                    + "COMENTARIOS\t" + "ESTATUS\t"+ "USUARIO QUE REVISO\t");          
                for (EstanciaCr col : lista) {
                    out.println(col.getId_estancia()+"\t"
                            +col.getNombre_estancia()+"\t"
                            +col.getNombre_responsable()+"\t"
                            +col.getDireccion()+"\t"
                            +col.getEstado()+"\t"
                            +col.getR_docR1()+"\t"
                            +col.getR_docR2()+"\t"
                            +col.getR_docR3()+"\t"
                            +col.getComentarios()+"\t"
                             +col.getEstatus()+"\t"
                            +col.getUsuario_rev());
                            
                            
                }

        } catch (SQLException e) {
            out.print("No se encontro el archivo, Error:" + e.getLocalizedMessage());
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
