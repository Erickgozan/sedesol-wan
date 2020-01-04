package com.proyectosedesol.entidades;

import java.util.Date;

public class EstanciaAm {

    private int idEstancia;
    private String listaNombreEstancia;
    private String listaNombreResponsable;
    private String listaDireccion;
    private String listaEstado;
    private String listaFechaInicio;
    private Date listaFechaFin;
    private String listaRutaDoc;
    private int idRevision;
    private int idSubirDoc;
    private String listaRNombreEstancia;
    private String listaRNombreResponsable;
    private String listaRDireccion;
    private String listaREstado;
    private String listaRFechaInicio;
    private String listaRFechaFin;
    private String comentarios;
    private String estatus;
    private String listaUsuarioRev;

    public EstanciaAm(){
        
    }

    public EstanciaAm(int idEstancia, String listaNombreEstancia, String listaNombreResponsable, String listaDireccion, String listaEstado, String listaFechaInicio, Date listaFechaFin, String listaRutaDoc, int idRevision, int idSubirDoc, String listaRNombreEstancia, String listaRNombreResponsable, String listaRDireccion, String listaREstado, String listaRFechaInicio, String listaRFechaFin,String cometarios,String estatus, String listaUsuarioRev) {
        this.idEstancia = idEstancia;
        this.listaNombreEstancia = listaNombreEstancia;
        this.listaNombreResponsable = listaNombreResponsable;
        this.listaDireccion = listaDireccion;
        this.listaEstado = listaEstado;
        this.listaFechaInicio = listaFechaInicio;
        this.listaFechaFin = listaFechaFin;
        this.listaRutaDoc = listaRutaDoc;
        this.idRevision = idRevision;
        this.idSubirDoc = idSubirDoc;
        this.listaRNombreEstancia = listaRNombreEstancia;
        this.listaRNombreResponsable = listaRNombreResponsable;
        this.listaRDireccion = listaRDireccion;
        this.listaREstado = listaREstado;
        this.listaRFechaInicio = listaRFechaInicio;
        this.listaRFechaFin = listaRFechaFin;
        this.comentarios = cometarios;
        this.estatus = estatus;
        this.listaUsuarioRev = listaUsuarioRev;
    }

    public int getIdEstancia() {
        return idEstancia;
    }

    public void setIdEstancia(int idEstancia) {
        this.idEstancia = idEstancia;
    }

    public String getListaNombreEstancia() {
        return listaNombreEstancia;
    }

    public void setListaNombreEstancia(String listaNombreEstancia) {
        this.listaNombreEstancia = listaNombreEstancia;
    }

    public String getListaNombreResponsable() {
        return listaNombreResponsable;
    }

    public void setListaNombreResponsable(String listaNombreResponsable) {
        this.listaNombreResponsable = listaNombreResponsable;
    }

    public String getListaDireccion() {
        return listaDireccion;
    }

    public void setListaDireccion(String listaDireccion) {
        this.listaDireccion = listaDireccion;
    }

    public String getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(String listaEstado) {
        this.listaEstado = listaEstado;
    }

    public String getListaFechaInicio() {
        return listaFechaInicio;
    }

    public void setListaFechaInicio(String listaFechaInicio) {
        this.listaFechaInicio = listaFechaInicio;
    }

    public Date getListaFechaFin() {
        return listaFechaFin;
    }

    public void setListaFechaFin(Date listaFechaFin) {
        this.listaFechaFin = listaFechaFin;
    }

    public String getListaRutaDoc() {
        return listaRutaDoc;
    }

    public void setListaRutaDoc(String listaRutaDoc) {
        this.listaRutaDoc = listaRutaDoc;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public int getIdSubirDoc() {
        return idSubirDoc;
    }

    public void setIdSubirDoc(int idSubirDoc) {
        this.idSubirDoc = idSubirDoc;
    }

    public String getListaRNombreEstancia() {
        return listaRNombreEstancia;
    }

    public void setListaRNombreEstancia(String listaRNombreEstancia) {
        this.listaRNombreEstancia = listaRNombreEstancia;
    }

    public String getListaRNombreResponsable() {
        return listaRNombreResponsable;
    }

    public void setListaRNombreResponsable(String listaRNombreResponsable) {
        this.listaRNombreResponsable = listaRNombreResponsable;
    }

    public String getListaRDireccion() {
        return listaRDireccion;
    }

    public void setListaRDireccion(String listaRDireccion) {
        this.listaRDireccion = listaRDireccion;
    }

    public String getListaREstado() {
        return listaREstado;
    }

    public void setListaREstado(String listaREstado) {
        this.listaREstado = listaREstado;
    }

    public String getListaRFechaInicio() {
        return listaRFechaInicio;
    }

    public void setListaRFechaInicio(String listaRFechaInicio) {
        this.listaRFechaInicio = listaRFechaInicio;
    }

    public String getListaRFechaFin() {
        return listaRFechaFin;
    }

    public void setListaRFechaFin(String listaRFechaFin) {
        this.listaRFechaFin = listaRFechaFin;
    }

    public String getListaUsuarioRev() {
        return listaUsuarioRev;
    }

    public void setListaUsuarioRev(String listaUsuarioRev) {
        this.listaUsuarioRev = listaUsuarioRev;
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
