/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectosedesol.excel;

import com.proyectosedesol.entidades.EstanciaAm;
import com.proyectosedesol.modelo.ModeloProcesos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CrearExcelAm extends HttpServlet {

    private ModeloProcesos procesos;
    @Resource(name = "jdbc/sedesol")
    private DataSource pool;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            procesos = new ModeloProcesos(pool);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setHeader("Content-Disposition", "attachment; filename=Listado.xls");
        response.setContentType("application/vnd.ms-excel");
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();

        String estado = request.getParameter("estado");
        List<EstanciaAm> lista;
       

        try {
            lista = procesos.getListadoJoin(0, 9172, estado);
            out.println("ID ESTANCIA\t"+"NOMBRE DE LA ESTANCIA\t" + "NOMBRE DE LA RESPONSABLE\t" + 
                    "DIRECCION\t" + "ESTADO\t" + "FECHA DE INICIO\t" + "FECHA DE FIN\t" + "ID REVISION\t"+
            "R NOMBRE DE LA ESTANCIA\t"+"R NOMBRE DE LA RESPONSEBLE\t" + "R DIRECCION\t" + "R ESTADO\t" +
                   "R FECHA DE INICIO\t" +"R FECHA DE FIN\t" +"COMETARIOS\t"+"ESTATUS\t" + "USUARIO QUE REVISO\t" );          
                for (EstanciaAm fila : lista) {
                    out.println(fila.getIdEstancia()+"\t"
                            +fila.getListaNombreEstancia()+"\t"
                            +fila.getListaNombreResponsable()+"\t"
                            +fila.getListaDireccion()+"\t"
                            +fila.getListaEstado()+"\t"
                            +fila.getListaFechaInicio()+"\t"
                            +fila.getListaFechaFin()+"\t"
                            +fila.getIdRevision()+"\t"
                            +fila.getListaRNombreEstancia()+"\t"
                            +fila.getListaNombreResponsable()+"\t"
                            +fila.getListaRDireccion()+"\t"+
                            fila.getListaREstado()+"\t"
                            +fila.getListaRFechaInicio()+"\t"
                            +fila.getListaRFechaFin()+"\t"
                            +fila.getComentarios()+"\t"
                            +fila.getEstatus()+"\t"
                            + fila.getListaUsuarioRev());
                }

        } catch (SQLException e) {
            out.print("No se encontro el archivo, Error:" + e.getLocalizedMessage());
        }

    }
    /*
    +fila.getListaNombreEstancia()+"\t"
                            +fila.getListaNombreResponsable()+"\t"
                            +fila.getListaDireccion()+"\t"
                            +fila.getListaEstado()+"\t"
                            +fila.getListaFechaInicio()+"\t"
                            +fila.getListaFechaFin()+"\t"
                            +fila.getIdRevision()+"\t"
                            +fila.getListaRNombreEstancia()+"\t"
                            +fila.getListaNombreResponsable()+"\t"
                            +fila.getListaRDireccion()+"\t"+
                            fila.getListaREstado()+"\t"
                            +fila.getListaRFechaInicio()+"\t"
                            +fila.getListaRFechaFin()+"\t"
                            + fila.getListaUsuarioRev());      
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
