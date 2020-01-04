
package com.proyectosedesol.controlador;

import com.proyectosedesol.entidades.Usuarios;
import com.proyectosedesol.modelo.ModeloProcesos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class ControladorUsuario extends HttpServlet {
    
    private ModeloProcesos procesos;
    @Resource(name = "jdbc/sedesol")
    private DataSource pool;
    
    
    @Override
    public void init() throws ServletException{
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
 
        switch(proceso){
            case "insertUser":
                 insertarUsuario(request,response);
                break;
            case "listarUsuario":
                listarUsuarios(request,response);
                break;
            case "eliminar":
            eliminarUsuario(request,response);
            break;
        }
    }
    
      private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        String txtUsuario = request.getParameter("txtUsuario");
        String txtContra = request.getParameter("txtContra");
        String selectEstado = request.getParameter("selectEstado");
        String selectPrivilegio = request.getParameter("selectPrivilegio");
        
        Usuarios usuario;
        try{
            out = response.getWriter();
            
            usuario = new Usuarios(txtUsuario, txtContra, selectEstado, selectPrivilegio);
            procesos.agregarUsuario(usuario);
            out.print("EL USUARIO SE AGREGO CORRECTAMENTE");
        }catch(IOException | SQLException e){
            out.print("Error en al agregar el usuario " + e.getLocalizedMessage());
        }
    }
      
      private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
            
          List<Usuarios> usuarios;
            
          try{
             usuarios = procesos.getUsuarios();
             request.setAttribute("listaUser", usuarios);
              RequestDispatcher rd =  request.getRequestDispatcher("/sesionIniciada/AgregarUsuario.jsp");
              rd.forward(request, response);
          }catch(IOException | ServletException | SQLException e){
              response.getWriter().print("Error en el listado de usuarios " + e.getLocalizedMessage());
          }
    }
      
      private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
          PrintWriter out=null;
          String usuario = request.getParameter("usuario");
          //boolean eliminado;
          try{
                out = response.getWriter();
                procesos.eliminarUsuario(usuario);
                request.setAttribute("mensaje", "SE ELIMINO EL USUARIO CORRECTAMENTE");
                RequestDispatcher rd = request.getRequestDispatcher("/sesionIniciada/AgregarUsuario.jsp");
                rd.forward(request, response);
               
          }catch(IOException | SQLException | ServletException e){
              out.print("Error " + e.getLocalizedMessage());
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
