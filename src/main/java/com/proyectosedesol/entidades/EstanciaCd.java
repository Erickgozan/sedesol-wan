
package com.proyectosedesol.entidades;

import java.util.Date;


public class EstanciaCd {
    
    private int id_estancia;
    private String nombre_estancia;
    private String nombre_responsable;
    private String direccion;
    private String estado;
    private String fecha_inicio;
    private Date fehca_fin;
    private String ruta_doc;
    private String ruta_docD1;
    private String ruta_docD2;
    private String ruta_docD3;
    private String ruta_docD4;
    private String ruta_docD5;
    private String ruta_docD6;
    private int id_revision;
    private String r_docD1;
    private String r_docD2;
    private String r_docD3;
    private String r_docD4;
    private String r_docD5;
    private String r_docD6;
    private String comentarios;
    private String estatus;
    private String usuario_rev;
    
     public EstanciaCd(){}

    public EstanciaCd(int id_estancia, String nombre_estancia, String nombre_responsable, String direccion, String estado, String fecha_inicio, Date fehca_fin, String ruta_doc, String ruta_docD1, String ruta_docD2, String ruta_docD3, String ruta_docD4, String ruta_docD5, String ruta_docD6, int id_revision, String r_docD1, String r_docD2, String r_docD3, String r_docD4, String r_docD5, String r_docD6, String comentarios, String usuario_rev) {
        this.id_estancia = id_estancia;
        this.nombre_estancia = nombre_estancia;
        this.nombre_responsable = nombre_responsable;
        this.direccion = direccion;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fehca_fin = fehca_fin;
        this.ruta_doc = ruta_doc;
        this.ruta_docD1 = ruta_docD1;
        this.ruta_docD2 = ruta_docD2;
        this.ruta_docD3 = ruta_docD3;
        this.ruta_docD4 = ruta_docD4;
        this.ruta_docD5 = ruta_docD5;
        this.ruta_docD6 = ruta_docD6;
        this.id_revision = id_revision;
        this.r_docD1 = r_docD1;
        this.r_docD2 = r_docD2;
        this.r_docD3 = r_docD3;
        this.r_docD4 = r_docD4;
        this.r_docD5 = r_docD5;
        this.r_docD6 = r_docD6;
        this.comentarios = comentarios;
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

    public String getRuta_docD1() {
        return ruta_docD1;
    }

    public void setRuta_docD1(String ruta_docD1) {
        this.ruta_docD1 = ruta_docD1;
    }

    public String getRuta_docD2() {
        return ruta_docD2;
    }

    public void setRuta_docD2(String ruta_docD2) {
        this.ruta_docD2 = ruta_docD2;
    }

    public String getRuta_docD3() {
        return ruta_docD3;
    }

    public void setRuta_docD3(String ruta_docD3) {
        this.ruta_docD3 = ruta_docD3;
    }

    public String getRuta_docD4() {
        return ruta_docD4;
    }

    public void setRuta_docD4(String ruta_docD4) {
        this.ruta_docD4 = ruta_docD4;
    }

    public String getRuta_docD5() {
        return ruta_docD5;
    }

    public void setRuta_docD5(String ruta_docD5) {
        this.ruta_docD5 = ruta_docD5;
    }

    public String getRuta_docD6() {
        return ruta_docD6;
    }

    public void setRuta_docD6(String ruta_docD6) {
        this.ruta_docD6 = ruta_docD6;
    }

    public int getId_revision() {
        return id_revision;
    }

    public void setId_revision(int id_revision) {
        this.id_revision = id_revision;
    }

    public String getR_docD1() {
        return r_docD1;
    }

    public void setR_docD1(String r_docD1) {
        this.r_docD1 = r_docD1;
    }

    public String getR_docD2() {
        return r_docD2;
    }

    public void setR_docD2(String r_docD2) {
        this.r_docD2 = r_docD2;
    }

    public String getR_docD3() {
        return r_docD3;
    }

    public void setR_docD3(String r_docD3) {
        this.r_docD3 = r_docD3;
    }

    public String getR_docD4() {
        return r_docD4;
    }

    public void setR_docD4(String r_docD4) {
        this.r_docD4 = r_docD4;
    }

    public String getR_docD5() {
        return r_docD5;
    }

    public void setR_docD5(String r_docD5) {
        this.r_docD5 = r_docD5;
    }

    public String getR_docD6() {
        return r_docD6;
    }

    public void setR_docD6(String r_docD6) {
        this.r_docD6 = r_docD6;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getUsuario_rev() {
        return usuario_rev;
    }

    public void setUsuario_rev(String usuario_rev) {
        this.usuario_rev = usuario_rev;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
    
   
    
    
    
}
