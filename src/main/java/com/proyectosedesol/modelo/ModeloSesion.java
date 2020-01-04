package com.proyectosedesol.modelo;

import com.proyectosedesol.entidades.Usuarios;
import java.sql.*;
import javax.sql.DataSource;

public class ModeloSesion {

    private DataSource origenDatos;

    public ModeloSesion(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    public Usuarios getLogUsuario(String usuario, String contrasenia) throws SQLException {

        Usuarios usuariosDb = null;

        Connection conexion = null;
        PreparedStatement pStatement;
        ResultSet rs = null;

        try {
            conexion = origenDatos.getConnection();
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasenia = ?";
            pStatement = conexion.prepareStatement(query);
            pStatement.setString(1, usuario);
            pStatement.setString(2, contrasenia);
            rs = pStatement.executeQuery();
            
            if (rs.next()) {

                String privilegioDb = rs.getString("privilegio");
                String estadoDb = rs.getString("estado");

                usuariosDb = new Usuarios(usuario, contrasenia, estadoDb, privilegioDb);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la conexion: " + ex.getMessage());
        } finally {
            conexion.close();
            rs.close();
        }

        return usuariosDb;
    }
    
     

}
