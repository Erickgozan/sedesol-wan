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
            <main>
                <article>
                    <h1 style="color: rgb(0, 104, 71)">PROCESO DE CAMBIO DEL DOMICILIO</h1>
                    <hr>
                    <!--BUSCAR LA ESTANCIA-->
                    <form action="/ControladorCD" method="GET" id="formbuscar">
                        <input type="hidden" name="proceso" value="CambioDomicilio" id="proceso">
                        <input type="hidden" name="buscar" value="idBuscar" id="proceso">
                        <div class="form-row">
                            <div class="form-group  col-md-6">
                                <label for="idestancia">ID de la estancia:</label>
                                <input type="text" class="form-control" id="idEstancia" placeholder="Id de la estancia" name="txtIdEstancia" value="${datosEstancia.id_estancia}">
                            </div>
                            <div class="form-group col-md-4" style="margin-left: 5rem;">
                                <div><br></div>
                                <input type="submit" class="btn btn-outline-danger btn-lg btn-block " id="enviarId" value="Buscar la estancia" >
                            </div>
                        </div>
                    </form>

                    <!--ENVIAR LA NUMEVA DIRECCIÓN Y LOS DOCUMENTOS-->
                    <c:if test="${datosEstancia.id_estancia!=null}">
                        <form action="../ControladorCD" method="POST"  id="formsubirDatosCD" enctype="multipart/form-data" >     
                            <input type="hidden" class="form-control" name="estatusCD" value="ACTIVO"  id="estatusCD">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="txtEstancia">Nombre de la estancia:</label>
                                    <input type="text" class="form-control"  value="${datosEstancia.nombre_estancia}" disabled>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="txtResponsable" >Nombre de la responsable:</label>
                                    <input type="text" class="form-control"    value="${datosEstancia.nombre_responsable}" disabled>  
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="txtDireccion" ><strong>ACTUALIZAR LA DIRECCIÓN:</strong></label>
                                    <textarea style="width: 100%;"  rows="3" name="txtCambioDireccion"  id="txtCambioDireccion" >${datosEstancia.direccion}</textarea>
                                </div>
                                <div class="form-group col-md-6">

                                    <label for="txtEstado">Estado de la republica:</label>
                                    <input type="text" class="form-control"  value="${datosEstancia.estado}" disabled>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="inputCity">Fecha de inicio AM:</label>
                                    <div class="input-group date calendario" id="calendario">
                                        <input type="text" class="form-control" value="${datosEstancia.fecha_inicio}"  disabled><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                                <div class="form-group col-md-6" >
                                    <label for="inputState">Fecha de fin AM:</label>
                                    <input type="text" class="form-control"  value="31/12/2019"  disabled>
                                </div>

                                <!--ZONA DE DOCUMENTOS-->
                                <div class="form-group col-md-12" >
                                    <h3>ZONA DE DOCUMENTOS</h3>
                                </div>
                                <c:if test="${datosEstancia.ruta_docD1==null || datosEstancia.ruta_docD2==null
                                              || datosEstancia.ruta_docD3==null || datosEstancia.ruta_docD4==null ||
                                              datosEstancia.ruta_docD5==null || datosEstancia.ruta_docD6==null}">

                                      <div  class="form-group col-md-6">
                                          <label for="inputState">Subir los 6 documentos que se requieren para el tramite de cambio de domicilio:</label>
                                          <input type="file"  class="form-control" id="archivoDomicilio" name="archivoDomicilio">
                                      </div> 
                                      <c:if test="${datosEstancia.ruta_docD1==null && datosEstancia.ruta_docD2==null
                                                    && datosEstancia.ruta_docD3==null && datosEstancia.ruta_docD4==null &&
                                                    datosEstancia.ruta_docD5==null && datosEstancia.ruta_docD6==null}">
                                            <div class="form-group col-md-6">
                                                <label for="inputState">Documentos en el servidor:</label>
                                                <label for="inputState" class="form-control" style="background-color: rgb(220, 53, 69); color: #ffffff ">No hay ningun documento en el servidor: </label>
                                            </div>
                                      </c:if>
                                </c:if>

                                <c:if test="${datosEstancia.ruta_docD1!=null && datosEstancia.ruta_docD2!=null
                                              && datosEstancia.ruta_docD3!=null && datosEstancia.ruta_docD4!=null &&
                                              datosEstancia.ruta_docD5!=null && datosEstancia.ruta_docD6!=null}">
                                      <div class="form-group col-md-6">
                                          <label for="inputState">Documentos en el servidor:</label>
                                          <label for="inputState" class="form-control" style="background-color: #006847; color: #ffffff ">Todos los archivos ya se encuentran en el servidor: </label>
                                      </div>
                                </c:if>  
                                <c:if test="${datosEstancia.ruta_docD1!=null || datosEstancia.ruta_docD2!=null
                                              || datosEstancia.ruta_docD3!=null || datosEstancia.ruta_docD4!=null ||
                                              datosEstancia.ruta_docD5!=null || datosEstancia.ruta_docD6!=null}">
                                      <div  class="form-group col-md-6">
                                          <label for="inputState">Documentos en el servidor:</label>
                                          <a id="visualizar"  class="form-control" href="#">Ver los archivos subidos</a>
                                      </div>
                                </c:if>
                            </div>
                            <c:if test="${datosEstancia.ruta_docD1!=null && datosEstancia.ruta_docD2!=null
                                          && datosEstancia.ruta_docD3!=null && datosEstancia.ruta_docD4!=null &&
                                          datosEstancia.ruta_docD5!=null && datosEstancia.ruta_docD6!=null}">
                                  <button type="submit" class="btn btn-outline-success btn-lg btn-block "  name="proceso" disabled>Enviar</button>                                     
                            </c:if>
                            <c:if test="${datosEstancia.ruta_docD1==null || datosEstancia.ruta_docD2==null
                                          || datosEstancia.ruta_docD3==null || datosEstancia.ruta_docD4==null ||
                                          datosEstancia.ruta_docD5==null || datosEstancia.ruta_docD6==null}">
                                  <button type="submit" class="btn btn-outline-success btn-lg btn-block "  name="proceso" id="enviarCD">Enviar</button>                            
                            </c:if>
                        </form>
                        <!--Modal para visualizar los documentos-->
                        <div class="modal" id="visualizarDocs" tabindex="-1" role="dialog" style="text-align: center;">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">DOCUMENTOS CAMBIO DE DOMICILIO</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-4">
                                                <div class="list-group" id="list-tab" role="tablist">
                                                    <c:if test="${datosEstancia.ruta_docD1!=null}">
                                                        <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list" href="#list-doc1" role="tab" aria-controls="home">Documento 1</a>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD2!=null}">
                                                        <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#list-doc2" role="tab" aria-controls="profile">Documentos 2</a>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD3!=null}">
                                                        <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#list-doc3" role="tab" aria-controls="messages">Documento 3</a>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD4!=null}">
                                                        <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-doc4" role="tab" aria-controls="settings">Documento 4</a>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD5!=null}">
                                                        <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-doc5" role="tab" aria-controls="settings">Documento 5</a>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD6!=null}">
                                                        <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-doc6" role="tab" aria-controls="settings">Documento 6</a>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="col-8">
                                                <div class="tab-content" id="nav-tabContent">
                                                    <div  class="tab-pane fade show active" id="list-doc1" role="tabpanel" aria-labelledby="list-home-list"> 
                                                        Constancia de residencia emitida por la autoridad local, especificando el domicilio completo.<br>
                                                        <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docD1}" target="_blanck">Ver el documento.</a>
                                                    </div>
                                                    <div class="tab-pane fade" id="list-doc2" role="tabpanel" aria-labelledby="list-profile-list">
                                                        Credencial para votar vigente, que contenga el domicilio actualizado.<br><br>
                                                        <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docD2}" target="_blanck">Ver el documento.</a>
                                                    </div>
                                                    <div class="tab-pane fade" id="list-doc3" role="tabpanel" aria-labelledby="list-messages-list">
                                                        Comprobante del nuevo domicilio de la familia actualizado (luz, agua, predial, teléfono, recibo de renta).<br><br>
                                                        <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docD3}" target="_blanck">Ver el documento.</a>
                                                    </div>
                                                    <div class="tab-pane fade" id="list-doc4" role="tabpanel" aria-labelledby="list-settings-list">
                                                        Acta de Vecindad en localidades rurales por usos y costumbres.<br><br>
                                                        <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docD4}" target="_blanck">Ver el documento</a>
                                                    </div>
                                                    <div class="tab-pane fade" id="list-doc5" role="tabpanel" aria-labelledby="list-settings-list">
                                                        formato "F1-Integrantes Registrados de la Familia" que sirve para que el personal operativo verifique la información de la familia 
                                                        y registre correctamente tu solicitud en la Ficha de Atención.<br><br>
                                                        <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docD5}" target="_blanck">Ver el documento</a>
                                                    </div>
                                                    <div class="tab-pane fade" id="list-doc6" role="tabpanel" aria-labelledby="list-settings-list">
                                                        Acta de Vecindad en localidades rurales por usos y costumbres.<br><br>
                                                        <a  href="VisualizarPDF?documento=${datosEstancia.ruta_docD6}" target="_blanck" >Ver el documento</a>
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
