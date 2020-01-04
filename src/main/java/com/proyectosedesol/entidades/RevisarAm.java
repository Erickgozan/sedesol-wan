package com.proyectosedesol.entidades;

public class RevisarAm {

    private int id_revision;
    private int id_subir_doc;
    private String r_nombre_estancia;
    private String r_nombre_responsable;
    private String r_direccion;
    private String r_estado;
    private String r_fecha_inicio;
    private String r_fecha_fin;
    private String comentarios;
    private String estatus;
    private String usuario_rev;

   public RevisarAm(){
       
   }

    public RevisarAm(int id_revision, int id_subir_doc, String r_nombre_estancia, String r_nombre_responsable, String r_direccion, String r_estado, String r_fecha_inicio, String r_fecha_fin,String comentarios,String estatus, String usuario_rev) {
        this.id_revision = id_revision;
        this.id_subir_doc = id_subir_doc;
        this.r_nombre_estancia = r_nombre_estancia;
        this.r_nombre_responsable = r_nombre_responsable;
        this.r_direccion = r_direccion;
        this.r_estado = r_estado;
        this.r_fecha_inicio = r_fecha_inicio;
        this.r_fecha_fin = r_fecha_fin;
        this.comentarios = comentarios;
        this.estatus = estatus;
        this.usuario_rev = usuario_rev;
    }

    public int getId_revision() {
        return id_revision;
    }

    public void setId_revision(int id_revision) {
        this.id_revision = id_revision;
    }

    public int getId_subir_doc() {
        return id_subir_doc;
    }

    public void setId_subir_doc(int id_subir_doc) {
        this.id_subir_doc = id_subir_doc;
    }

    public String getR_nombre_estancia() {
        return r_nombre_estancia;
    }

    public void setR_nombre_estancia(String r_nombre_estancia) {
        this.r_nombre_estancia = r_nombre_estancia;
    }

    public String getR_nombre_responsable() {
        return r_nombre_responsable;
    }

    public void setR_nombre_responsable(String r_nombre_responsable) {
        this.r_nombre_responsable = r_nombre_responsable;
    }

    public String getR_direccion() {
        return r_direccion;
    }

    public void setR_direccion(String r_direccion) {
        this.r_direccion = r_direccion;
    }

    public String getR_estado() {
        return r_estado;
    }

    public void setR_estado(String r_estado) {
        this.r_estado = r_estado;
    }

    public String getR_fecha_inicio() {
        return r_fecha_inicio;
    }

    public void setR_fecha_inicio(String r_fecha_inicio) {
        this.r_fecha_inicio = r_fecha_inicio;
    }

    public String getR_fecha_fin() {
        return r_fecha_fin;
    }

    public void setR_fecha_fin(String r_fecha_fin) {
        this.r_fecha_fin = r_fecha_fin;
    }

    public String getUsuario_rev() {
        return usuario_rev;
    }

    public void setUsuario_rev(String usuario_rev) {
        this.usuario_rev = usuario_rev;
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


   
}
