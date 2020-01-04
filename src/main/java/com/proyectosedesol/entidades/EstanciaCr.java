
package com.proyectosedesol.entidades;

import java.util.Date;


public class EstanciaCr {
    
     private int id_estancia;
    private String nombre_estancia;
    private String nombre_responsable;
    private String direccion;
    private String estado;
    private String fecha_inicio;
    private Date fehca_fin;
    private String ruta_doc;
    private String ruta_docR1;
    private String ruta_docR2;
    private String ruta_docR3;
    private int id_revision;
    private String r_docR1;
    private String r_docR2;
    private String r_docR3;
    private String comentarios;
    private String estatus;
    private String usuario_rev;
    
    public EstanciaCr(){}

    public EstanciaCr(int id_estancia, String nombre_estancia, String nombre_responsable, String direccion, String estado, String fecha_inicio, Date fehca_fin, String ruta_doc, String ruta_docR1, String ruta_docR2, String ruta_docR3, int id_revision, String r_docR1, String r_docR2, String r_docR3, String comentarios, String estatus, String usuario_rev) {
        this.id_estancia = id_estancia;
        this.nombre_estancia = nombre_estancia;
        this.nombre_responsable = nombre_responsable;
        this.direccion = direccion;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fehca_fin = fehca_fin;
        this.ruta_doc = ruta_doc;
        this.ruta_docR1 = ruta_docR1;
        this.ruta_docR2 = ruta_docR2;
        this.ruta_docR3 = ruta_docR3;
        this.id_revision = id_revision;
        this.r_docR1 = r_docR1;
        this.r_docR2 = r_docR2;
        this.r_docR3 = r_docR3;
        this.comentarios = comentarios;
        this.estatus = estatus;
        this.usuario_rev = usuario_rev;
    }

    public int getId_estancia() {
        return id_estancia;
    }

    public void setId_estancia(int id_estancia) {
        this.id_estancia = id_estancia;
    }

    public String getNombre_estancia() {
        return nombre_estancia;
    }

    public void setNombre_estancia(String nombre_estancia) {
        this.nombre_estancia = nombre_estancia;
    }

    public String getNombre_responsable() {
        return nombre_responsable;
    }

    public void setNombre_responsable(String nombre_responsable) {
        this.nombre_responsable = nombre_responsable;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFehca_fin() {
        return fehca_fin;
    }

    public void setFehca_fin(Date fehca_fin) {
        this.fehca_fin = fehca_fin;
    }

    public String getRuta_doc() {
        return ruta_doc;
    }

    public void setRuta_doc(String ruta_doc) {
        this.ruta_doc = ruta_doc;
    }

    public String getRuta_docR1() {
        return ruta_docR1;
    }

    public void setRuta_docR1(String ruta_docR1) {
        this.ruta_docR1 = ruta_docR1;
    }

    public String getRuta_docR2() {
        return ruta_docR2;
    }

    public void setRuta_docR2(String ruta_docR2) {
        this.ruta_docR2 = ruta_docR2;
    }

    public String getRuta_docR3() {
        return ruta_docR3;
    }

    public void setRuta_docR3(String ruta_docR3) {
        this.ruta_docR3 = ruta_docR3;
    }

    public int getId_revision() {
        return id_revision;
    }

    public void setId_revision(int id_revision) {
        this.id_revision = id_revision;
    }

    public String getR_docR1() {
        return r_docR1;
    }

    public void setR_docR1(String r_docR1) {
        this.r_docR1 = r_docR1;
    }

    public String getR_docR2() {
        return r_docR2;
    }

    public void setR_docR2(String r_docR2) {
        this.r_docR2 = r_docR2;
    }

    public String getR_docR3() {
        return r_docR3;
    }

    public void setR_docR3(String r_docR3) {
        this.r_docR3 = r_docR3;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getUsuario_rev() {
        return usuario_rev;
    }

    public void setUsuario_rev(String usuario_rev) {
        this.usuario_rev = usuario_rev;
    }
    
    
    
}
