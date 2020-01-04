
package com.proyectosedesol.entidades;

public class RevisarCd {
    
    private int id_revision;
    private int id_estancia;
    private String r_docD1;
    private String r_docD2;
    private String r_docD3;
    private String r_docD4;
    private String r_docD5;
    private String r_docD6;
    private String comentarios;
    private String estatus;
    private String usuario_rev;
    
    public RevisarCd(){}

     public RevisarCd(int id_estancia, String r_docD1, String r_docD2, String r_docD3, String r_docD4, String r_docD5, String r_docD6, String comentarios, String estatus, String usuario_rev) {
        this.id_estancia = id_estancia;
        this.r_docD1 = r_docD1;
        this.r_docD2 = r_docD2;
        this.r_docD3 = r_docD3;
        this.r_docD4 = r_docD4;
        this.r_docD5 = r_docD5;
        this.r_docD6 = r_docD6;
        this.estatus = estatus;
        this.comentarios = comentarios;
        this.usuario_rev = usuario_rev;
    }

    public String getUsuario_rev() {
        return usuario_rev;
    }

    public void setUsuario_rev(String usuario_rev) {
        this.usuario_rev = usuario_rev;
    }

    public int getId_revision() {
        return id_revision;
    }

    public void setId_revision(int id_revision) {
        this.id_revision = id_revision;
    }

    public int getId_estancia() {
        return id_estancia;
    }

    public void setId_estancia(int id_estancia) {
        this.id_estancia = id_estancia;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
}
