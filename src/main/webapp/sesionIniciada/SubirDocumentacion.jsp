<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html>
    <head>

        <%@include file="componentes/Headboard.jsp" %>
        <title>Subir documentacion</title>
    </head>
    <!--VALIDAR SI SE HA INICIADO SESION-->
    <c:if test="${privilegio!=null}"> 
        <body>
            <c:if test="${privilegio=='Estandar' || privilegio=='Administrador' }">
                <%@include file="componentes/Menu.jsp" %>
                <main >
                    <article>
                        <h1>SUBIR LA AUTORIZACIÓN DEL MODELO</h1>
                        <hr>
                        <!--Buscar documeto-->
                        <form action="/ControladorProcesos" method="GET" id="formbuscar">
                            <input type="hidden" name="proceso" value="idEstancia" id="proceso">
                            <input type="hidden" name="buscar" value="idSubir"> 
                            <div class="form-row">
                                <div class="form-group  col-md-6" >
                                    <label for="idestancia">ID de la estancia:</label>
                                    <input type="text" class="form-control" id="idEstancia" placeholder="Id de la estancia" name="txtIdEstancia" value="${DatosDoc.id_estancia}">
                                </div>
                                <div class="form-group col-md-4" style="margin-left: 5rem;">
                                    <div><br></div>
                                    <input type="submit" class="btn btn-outline-danger btn-lg btn-block " id="enviarId" value="Buscar la estancia" >
                                </div>
                            </div>
                        </form>

                        <!--ENVIAR EL DOCUMENTO-->
                        <c:if test="${DatosDoc.id_estancia!=null}">
                            <form  id="formEnviaDoc" enctype="multipart/form-data" >     
                                <input type="hidden" name="proceso" value="subirDoc" >      
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtEstancia">Nombre de la estancia:</label>
                                        <input type="text" class="form-control" id="txtEstancia" name="txtEstancia" value="${DatosDoc.nombre_estancia}" disabled>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="txtResponsable" >Nombre de la responsable:</label>
                                        <input type="text" class="form-control" id="txtResponsable"  name="txtResponsable" value="${DatosDoc.nombre_responsable}" disabled>  
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtDireccion">Dirección:</label>

                                        <textarea style="width: 100%;" name="txtDireccion" id="txtDireccion" cols="55" rows="3" disabled>${DatosDoc.direccion}</textarea>
                                    </div>
                                    <div class="form-group col-md-6">

                                        <label for="txtEstado">Estado de la republica:</label>
                                        <input type="text" class="form-control" id="txtEstado" name="txtEstado" value="${DatosDoc.estado}" disabled>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="inputCity">Fecha de inicio AM:</label>
                                        <div class="input-group date calendario" id="calendario">
                                            <c:if test="${DatosDoc.fecha_inicio!=null}">
                                                <input type="text" class="form-control" value="${DatosDoc.fecha_inicio}" name="txtFechaInicio" id="txtFechaInicio" disabled><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </c:if>
                                                <c:if test="${DatosDoc.fecha_inicio==null}">
                                                <input type="text" class="form-control" placeholder="dd/mm/aaaa" name="txtFechaInicio" id="txtFechaInicio"><span class="input-group-addon"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                    </c:if>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-4" >
                                        <label for="inputState">Fecha de fin AM:</label>
                                        <input type="text" class="form-control" id="txtFechaFin"  value="31/12/2019" name="txtFechaFin" disabled>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <c:if test="${DatosDoc.ruta_doc==null}">
                                            <label for="inputState">Subir documento PDF:</label>
                                            <input type="file" class="form-control" id="fileSubirDoc" name="fileSubirDoc">
                                        </c:if>
                                        <c:if test="${DatosDoc.ruta_doc!=null}">
                                            <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                                <c:param name="documento" value="${DatosDoc.ruta_doc}"></c:param>
                                            </c:url>
                                            <label for="inputState">Documento en el servidor:</label>
                                            <a class="form-control" href="${linkTempDoc}"  target="_blank">Ver el documento</a>
                                        </c:if>
                                    </div>
                                </div>
                                <c:if test="${DatosDoc.ruta_doc==null && DatosDoc.fecha_inicio==null}">
                                    <button type="submit" class="btn btn-outline-success btn-lg btn-block " id="enviarDoc" name="proceso" >Enviar</button>
                                </c:if>
                                <c:if test="${DatosDoc.ruta_doc!=null && DatosDoc.fecha_inicio!=''}">
                                    <button type="submit" class="btn btn-outline-success btn-lg btn-block " id="enviarDoc" name="proceso" disabled>Enviar</button>
                                </c:if>
                            </form>
                        </c:if>


                        <c:if test="${noEncontrado!=null || formatoInvalido!=null}">
                            <div class="modal" id="errorModal2" tabindex="-1" role="dialog" style="text-align: center;">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" style="color:red;">ERROR!</h4>    
                                        </div>
                                        <div class="modal-body">
                                            <c:if test="${noEncontrado!=null}">
                                                <p ><strong>${noEncontrado}</strong></p>
                                                    </c:if>
                                                    <c:if test="${formatoInvalido!=null}">
                                                <p><strong>${formatoInvalido}</strong></p>
                                            </c:if>
                                        </div>
                                        <div class="modal-footer">   
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal" >Salir</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                    </article>
                </main>
                <%@include file="componentes/Footer.jsp" %>
            </c:if>
            <c:if test="${privilegio!='Estandar' && privilegio!='Administrador' }">
                <% response.sendRedirect("/sesionIniciada/Inicio.jsp");%>
            </c:if>
        </body>
    </c:if>
        <!--REDIRECCIONAR AL INICIO DE SESION CUANDO SE INTENTE ACCEDER SIN TENER LA SESION INICIADA-->
        <c:if test="${privilegio==null}">
            <%response.sendRedirect("../index.jsp");%>
        </c:if>
    </html>
