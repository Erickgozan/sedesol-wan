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
            <c:if test="${privilegio=='Estandar' || privilegio=='Administrador' }">
                <%@include file="componentes/Menu.jsp" %>
                <main >
                    <article>
                        <h1 style="color: rgb(219, 47, 47)">PROCESO CAMBIO DE RESPONSABLE</h1>
                        <hr>
                        <!--BUSCAR LA ESTACIA-->
                        <form action="/ControladorCR" method="GET" id="formbuscar">
                            <!--INPUT QUE FUNCIONA PARA ESTABLECER EL METODO EN EL CONTROLADO-->
                            <input type="hidden" name="proceso" value="CambioResponsable" id="proceso">
                            <!--INPUT QUE FUNCIONA PARA IDENTIFICAR LA BUSQUEDA ENTRE REVISAR Y PROCESOS. EN EL CONTROLADO-->
                            <input type="hidden" name="buscar" value="idBuscar"> 
                            <div class="form-row">
                                <div class="form-group  col-md-6" >
                                    <label for="idestancia">ID de la estancia:</label>
                                    <input type="text" class="form-control" id="idEstancia" placeholder="Id de la estancia" name="txtIdEstancia" value="${datosEstancia.id_estancia}">
                                </div>
                                <div class="form-group col-md-4" style="margin-left: 5rem;">
                                    <div><br></div>
                                    <input type="submit" class="btn btn-outline-danger btn-lg btn-block " id="enviarId" value="Buscar la estancia" >
                                </div>
                            </div>
                        </form>

                        <!--ENVIAR EL NUEVO CAMBIO DE RESPONSABLE Y LOS DOCUMENTOS-->
                        <c:if test="${datosEstancia.id_estancia!=null}">
                            <form id="formsubirDatosCR" enctype="multipart/form-data" >     
                                <input type="hidden" class="form-control" name="estatusCR" value="ACTIVO"  id="estatusCR">     
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtEstancia">Nombre de la estancia:</label>
                                        <input type="text" class="form-control" id="txtEstancia" name="txtEstancia" value="${datosEstancia.nombre_estancia}" disabled>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="txtResponsable" ><strong>Editar el nombre de la responsable:</strong></label>
                                        <input type="text" class="form-control" id="txtResponsable"  name="txtResponsable" value="${datosEstancia.nombre_responsable}" >  
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtDireccion">Dirección:</label>

                                        <textarea style="width: 100%;" name="txtDireccion" id="txtDireccion" cols="55" rows="3" disabled >${datosEstancia.direccion}</textarea>
                                    </div>
                                    <div class="form-group col-md-6">

                                        <label for="txtEstado">Estado de la republica:</label>
                                        <input type="text" class="form-control" id="txtEstado" name="txtEstado" value="${datosEstancia.estado}" disabled>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="inputCity">Fecha de inicio:</label>
                                        <div class="input-group date calendario" id="calendario">
                                            <input type="text" class="form-control" value="${datosEstancia.fecha_inicio}" name="txtFechaInicio" id="txtFechaInicio" disabled><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6" >
                                        <label for="inputState">Fecha de fin:</label>
                                        <input type="text" class="form-control" id="txtFechaFin"  value="31/12/2019" name="txtFechaFin" disabled>
                                    </div>


                                    <!--ZONA DE DOCUMENTOS-->
                                    <div class="form-group col-md-12" >
                                        <h3>ZONA DE DOCUMENTOS</h3>
                                    </div>
                                    <c:if test="${datosEstancia.ruta_docR1==null || datosEstancia.ruta_docR2==null
                                                  || datosEstancia.ruta_docR3==null }">
                                          <div  class="form-group col-md-6">
                                              <label for="inputState">Subir los 3 documentos para el tramite de cambio de responsable:</label>
                                              <input type="file"  class="form-control" id="archivoResponsable" name="archivoResponsable">
                                          </div> 
                                          <c:if test="${datosEstancia.ruta_docR1==null && datosEstancia.ruta_docR2==null
                                                        && datosEstancia.ruta_docR3==null }">
                                                <div class="form-group col-md-6">
                                                    <label for="inputState">Documentos en el servidor:</label>
                                                    <label for="inputState" class="form-control" style="background-color: rgb(220, 53, 69); color: #ffffff ">No hay ningun documento en el servidor: </label>
                                                </div>
                                          </c:if>
                                    </c:if>

                                    <c:if test="${datosEstancia.ruta_docR1!=null && datosEstancia.ruta_docR2!=null
                                                  && datosEstancia.ruta_docR3!=null }">
                                          <div class="form-group col-md-6">
                                              <label for="inputState">Documentos en el servidor:</label>
                                              <label for="inputState" class="form-control" style="background-color: #006847; color: #ffffff ">Todos los archivos ya se encuentran en el servidor: </label>
                                          </div>
                                    </c:if>  
                                    <c:if test="${datosEstancia.ruta_docR1!=null || datosEstancia.ruta_docR2!=null
                                                  || datosEstancia.ruta_docR3!=null }">
                                          <div  class="form-group col-md-6">
                                              <label for="inputState">Documentos en el servidor:</label>
                                              <a id="visualizar"  class="form-control" href="#">Ver los archivos subidos</a>
                                          </div>
                                    </c:if>
                                </div>
                                <c:if test="${datosEstancia.ruta_docR1!=null && datosEstancia.ruta_docR2!=null
                                              && datosEstancia.ruta_docR3!=null }">
                                      <button type="submit" class="btn btn-outline-success btn-lg btn-block "  name="proceso" disabled>Enviar</button>                                     
                                </c:if>
                                <c:if test="${datosEstancia.ruta_docR1==null || datosEstancia.ruta_docR2==null
                                              || datosEstancia.ruta_docR3==null}">
                                      <button type="submit" class="btn btn-outline-success btn-lg btn-block "  id="enviarCR">Enviar</button>                            
                                </c:if>
                            </form>

                            <!--MODAL PARA VISUALIZAR LOS DOCUMENTOS-->
                            <div class="modal" id="visualizarDocs" tabindex="-1" role="dialog" style="text-align: center;">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">DOCUMENTOS CAMBIO DE RESPONSABLE</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-4">
                                                    <div class="list-group" id="list-tab" role="tablist">
                                                        <c:if test="${datosEstancia.ruta_docR1!=null}">
                                                            <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list" href="#list-doc1" role="tab" aria-controls="home">Documento 1</a>
                                                        </c:if>
                                                        <c:if test="${datosEstancia.ruta_docR2!=null}">
                                                            <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#list-doc2" role="tab" aria-controls="profile">Documentos 2</a>
                                                        </c:if>
                                                        <c:if test="${datosEstancia.ruta_docR3!=null}">
                                                            <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#list-doc3" role="tab" aria-controls="messages">Documento 3</a>
                                                        </c:if>  
                                                    </div>
                                                </div>
                                                <div class="col-8">
                                                    <div class="tab-content" id="nav-tabContent">
                                                        <div  class="tab-pane fade show active" id="list-doc1" role="tabpanel" aria-labelledby="list-home-list"> 
                                                            Constancia de residencia emitida por la autoridad local, especificando el domicilio completo.<br>
                                                            <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docR1}" target="_blanck">Ver el documento.</a>
                                                        </div>
                                                        <div class="tab-pane fade" id="list-doc2" role="tabpanel" aria-labelledby="list-profile-list">
                                                            Credencial para votar vigente, que contenga el domicilio actualizado.<br><br>
                                                            <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docR2}" target="_blanck">Ver el documento.</a>
                                                        </div>
                                                        <div class="tab-pane fade" id="list-doc3" role="tabpanel" aria-labelledby="list-messages-list">
                                                            Comprobante del nuevo domicilio de la familia actualizado (luz, agua, predial, teléfono, recibo de renta).<br><br>
                                                            <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docR3}" target="_blanck">Ver el documento.</a>
                                                        </div>  
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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
