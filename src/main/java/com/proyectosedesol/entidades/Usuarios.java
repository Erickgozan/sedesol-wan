
package com.proyectosedesol.entidades;


public class Usuarios {
    
    private String usuario;
    private String contrasenia;
    private String estado;
    private String privilegio;
    
    public Usuarios(){
        
    }

    public Usuarios(String usuario, String contrasenia, String estado, String privilegio) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.privilegio = privilegio;
    }
    
    public Usuarios(String usuario, String estado, String privilegio){
        this.usuario = usuario;
        this.estado = estado;
        this.privilegio = privilegio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + ", estado=" + estado + ", privilegio=" + privilegio + '}';
    }
    
   
}
