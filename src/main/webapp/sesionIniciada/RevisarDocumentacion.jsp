<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/Headboard.jsp" %>
        <title>Revisar Documentacion</title>
    </head>
<!--VALIDAR SI SE HA INICIADO SESION-->
    <c:if test="${privilegio!=null}"> 
    <body>
        <c:if test="${privilegio=='Procesos' || privilegio=='Administrador' }">
            <%@include file="componentes/Menu.jsp" %>
            <main>
                <h1>REVISAR LA AUTORIZACIÓN DEL MODELO</h1>
                <div>
                    <hr>
                </div>
                <article>
                    <form action="/ControladorProcesos" method="GET">
                        <input type="hidden" name="proceso" value="idEstancia">               
                        <input type="hidden" name="buscar" value="idRevisar"> 
                        <div class="form-row">
                            <div class="form-group  col-md-6">
                                <label for="idestancia">ID de la estancia:</label>
                                <input type="text" class="form-control" id="idEstancia" placeholder="Id de la estancia" name="txtIdEstancia" value="${DatosDoc.id_estancia}">
                            </div>
                            <div class="form-group col-md-4" style="margin-left: 5rem;">
                                <div><br></div>
                                <input type="submit" class="btn btn-outline-danger btn-lg btn-block " id="enviarId" value="Buscar la estancia" >
                            </div>
                        </div>
                    </form>

                    <!--Tabla que se genera a partir de la base de datos-->
                    <c:if test="${DatosDoc.id_estancia!=null}">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">NOMBRE DE LA ESTANCIA</th>
                                    <th scope="col">NOMBRE DE LA RESPONSABLE</th>
                                    <th scope="col">DIRECCIÓN</th>
                                    <th scope="col">ESTADO DE LA REPUBLICA</th>
                                    <th scope="col">FECHA DE INICIO</th>
                                    <th scope="col">FECHA DE FIN</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${DatosDoc.id_estancia}</td>
                                    <td>${DatosDoc.nombre_estancia}</td>
                                    <td>${DatosDoc.nombre_responsable}</td>
                                    <td>${DatosDoc.direccion}</td>
                                    <td>${DatosDoc.estado}</td>
                                    <td>${DatosDoc.fecha_inicio}</td>
                                    <td>${DatosDoc.fehca_fin}</td>
                                </tr>

                            </tbody>
                        </table>

                        <!--Formulario para revisar la documetacion -->
                        <c:if test="${DatosDoc.ruta_doc!=null}">
                            <form action="/ControladorProcesos" method="POST" id="formRevisarDoc">
                                <input type="hidden" name="proceso" value="revisarDoc" id="proceso">
                                <section class="contenedorFvalidar">
                                    <div class="form-row" >
                                        <div class="form-group col-md-6 ">
                                            <label for="selectNomEstancia">NOMBRE DE LA ESTANCIA:</label>
                                            <select class="form-control form-revDoc" name="selectNombreEstancia" id="selectNombreEstancia">                                          
                                                <option>${DatosRevision.r_nombre_estancia}</option>
                                                <option>CONSISTENTE</option>
                                                <option>INCONSISTENTE</option>
                                                <option>POR REVISAR</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="selectNomResponsable">NOMBRE DE LA RESPONSABLE:</label>
                                            <select class="form-control form-revDoc" name="selectNombreRespon" id="selectNombreRespon" >
                                                <option>${DatosRevision.r_nombre_responsable}</option>
                                                <option>CONSISTENTE</option>
                                                <option>INCONSISTENTE</option>
                                                <option>POR REVISAR</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="selectDireccion">DIRECCIÓN:</label>
                                            <select class="form-control form-revDoc" name="selectDireccion" id="selectDireccion">
                                                <option>${DatosRevision.r_direccion}</option>
                                                <option>CONSISTENTE</option>
                                                <option>INCONSISTENTE</option>
                                                <option>POR REVISAR</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="selectEstado">ESTADO DE LA REPUBLICA:</label>
                                            <select class="form-control form-revDoc" name="selectEstado" id="selectEstado">
                                                <option>${DatosRevision.r_estado}</option>
                                                <option>CONSISTENTE</option>
                                                <option>INCONSISTENTE</option>
                                                <option>POR REVISAR</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="selectFechaIn ">FECHA DE INICIO:</label>
                                            <select class="form-control form-revDoc"  name="selectFechaInicio" id="selectFechaInicio">
                                                <option>${DatosRevision.r_fecha_inicio}</option>
                                                <option>CONSISTENTE</option>
                                                <option>INCONSISTENTE</option>
                                                <option>POR REVISAR</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="selectFechaFin">FECHA DE FIN:</label>
                                            <select class="form-control form-revDoc" name="selectFechaFin" id="selectFechaFin">
                                                <option>${DatosRevision.r_fecha_fin}</option>
                                                <option>CONSISTENTE</option>
                                                <option>INCONSISTENTE</option>
                                                <option>POR REVISAR</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="selectEstatus">ESTATUS:</label>
                                            <select class="form-control form-revDoc" name="selectEstatus" id="selectEstatus">
                                                <option>${DatosRevision.estatus}</option>
                                                <option>EN PROCESO</option>
                                                <option>FINALIZADO</option>
                                                <option>CANCELADO</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="comentariosAm">COMETARIOS:</label>
                                            <textarea style="width: 80%; float: left" name="comentariosAm" id="comentariosAm">${DatosRevision.comentarios}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="selectFechaFin ">USUARIO QUE REVISÓ EL DOCUMENTO :</label>
                                            <br>
                                            <input class="form-control form-revDoc" type="text" name="txtUsuarioRev" id="txtUsuarioRev" value="${usuario}" disabled>
                                            <input class="form-control form-revDoc" type="hidden" name="txtUsuarioRev" id="txtUsuarioRev" value="${usuario}" >
                                        </div>
                                    </div>
                                </section>
                                <div class="documento">
                                    <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                        <c:param name="documento" value="${DatosDoc.ruta_doc}"></c:param>
                                    </c:url>
                                    <iframe src="${linkTempDoc}" frameborder="1" width="750" height="790"></iframe>
                                </div>
                                <button type="submit" class="btn btn-outline-success btn-lg btn-block" id="btnEnviarRev">Enviar</button>
                            </form>
                        </c:if>

                        <c:if test="${linkTempDoc==null}">
                            <div class="modal" id="errorModal2" tabindex="-1" role="dialog" style="text-align: center;">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" style="color: orange;">AVISO!</h4>    
                                        </div>
                                        <div class="modal-body">
                                            <p style="font-size: 15px;  "><strong>LA ESTANCIA NO HA CARGADO LA AUTORIZACIÓN DEL MODELO</strong></p>
                                        </div>
                                        <div class="modal-footer">   
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal" >Salir</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:if>

                </article>
            </main>

            <%@include file="componentes/Footer.jsp" %>
        </c:if>
        <c:if test="${privilegio!='Procesos' && privilegio!='Administrador' }">
            <% response.sendRedirect("/sesionIniciada/Inicio.jsp");%>
        </c:if>
    </body>
 </c:if>
    <!--REDIRECCIONAR AL INICIO DE SESION CUANDO SE INTENTE ACCEDER SIN TENER LA SESION INICIADA-->
    <c:if test="${privilegio==null}">
        <%response.sendRedirect("../index.jsp");%>
    </c:if>
</html>