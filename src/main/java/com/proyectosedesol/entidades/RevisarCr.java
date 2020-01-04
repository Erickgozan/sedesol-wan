package com.proyectosedesol.entidades;

public class RevisarCr {

    private int id_revision_cr;
    private int id_estancia;
    private String r_doc_R1;
    private String r_doc_R2;
    private String r_doc_R3;
    private String comentarios;
    private String estatus;
    private String usuario_rev;
    
    public RevisarCr(){}

    public RevisarCr( int id_estancia, String r_doc_R1, String r_doc_R2, String r_doc_R3, String comentarios, String estatus, String usuario_rev) {
       
        this.id_estancia = id_estancia;
        this.r_doc_R1 = r_doc_R1;
        this.r_doc_R2 = r_doc_R2;
        this.r_doc_R3 = r_doc_R3;
        this.comentarios = comentarios;
        this.estatus = estatus;
        this.usuario_rev = usuario_rev;
    }

    public String getUsuario_rev() {
        return usuario_rev;
    }

    public void setUsuario_rev(String usuario_rev) {
        this.usuario_rev = usuario_rev;
    }

    public int getId_estancia() {
        return id_estancia;
    }

    public void setId_estancia(int id_estancia) {
        this.id_estancia = id_estancia;
    }

    public String getR_doc_R1() {
        return r_doc_R1;
    }

    public void setR_doc_R1(String r_doc_R1) {
        this.r_doc_R1 = r_doc_R1;
    }

    public String getR_doc_R2() {
        return r_doc_R2;
    }

    public void setR_doc_R2(String r_doc_R2) {
        this.r_doc_R2 = r_doc_R2;
    }

    public String getR_doc_R3() {
        return r_doc_R3;
    }

    public void setR_doc_R3(String r_doc_R3) {
        this.r_doc_R3 = r_doc_R3;
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

    public int getId_revision_cr() {
        return id_revision_cr;
    }

    public void setId_revision_cr(int id_revision_cr) {
        this.id_revision_cr = id_revision_cr;
    }
    
    

}
