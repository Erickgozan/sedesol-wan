package com.proyectosedesol.controlador;

import com.proyectosedesol.modelo.ModeloSesion;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.proyectosedesol.entidades.Usuarios;

public class ControladorSesion extends HttpServlet {

    private ModeloSesion modelo;
    @Resource(name = "jdbc/sedesol")
    private DataSource pool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modelo = new ModeloSesion(pool);
        } catch (Exception e) {
            System.out.println("Eror en el servidor " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       //PrintWriter out = response.getWriter();
        Usuarios usuariosDb;
        RequestDispatcher dispatcher;

        String usuario = request.getParameter("txtUsuario");
        String contrasenia = request.getParameter("txtContrasenia");
        try {

            usuariosDb = modelo.getLogUsuario(usuario, contrasenia);

            request.setAttribute("usuario", usuario);
            request.setAttribute("privilegio", usuariosDb.getPrivilegio());
            request.setAttribute("estado", usuariosDb.getEstado());
            dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            
        } catch (NullPointerException | SQLException e) {
                      
            request.setAttribute("error", "El USUARIO O CONTRASEÑA SON INCORRECTOS");
           
            dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
