<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/Headboard.jsp" %>
        <title>Revisar Cambio de domicilio</title>
    </head>
    <!--VALIDAR SI SE HA INICIADO SESION-->
    <c:if test="${privilegio!=null}"> 
        <body>
            <c:if test="${privilegio=='Procesos' || privilegio=='Administrador' }">
                <%@include file="componentes/Menu.jsp" %>
                <main>
                    <h1 style="color: rgb(0, 104, 71)">REVISAR EL CAMBIO DE DOMICILIO</h1>
                    <div>
                        <hr>
                    </div>
                    <article>
                        <form action="/ControladorCD" method="GET">
                            <input type="hidden" name="proceso" value="CambioDomicilio">               
                            <input type="hidden" name="buscar" value="idRevisar"> 
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
                        <!--Tabla que se genera a partir de la base de datos-->
                        <c:if test="${datosEstancia.id_estancia!=null}">
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
                                        <td>${datosEstancia.id_estancia}</td>
                                        <td>${datosEstancia.nombre_estancia}</td>
                                        <td>${datosEstancia.nombre_responsable}</td>
                                        <td>${datosEstancia.direccion}</td>
                                        <td>${datosEstancia.estado}</td>
                                        <td>${datosEstancia.fecha_inicio}</td>
                                        <td>${datosEstancia.fehca_fin}</td>
                                    </tr>

                                </tbody>
                            </table>

                            <!--Formulario para revisar la documetacion -->
                            <c:if test="${datosEstancia.estatus_domicilio!=null}">
                                <form action="/ControladorCD" method="POST" id="formRevisarDomicilio">
                                    <c:if test="${revisionCD.usuario_rev==null}">
                                        <input type="hidden" name="proceso" value="insertarCD" id="proceso">
                                    </c:if>
                                    <c:if test="${revisionCD.usuario_rev!=null}">
                                        <input type="hidden" name="proceso" value="actualizarCD" id="proceso">
                                    </c:if>
                                    <section class="contenedorFvalidar">
                                        <div class="form-row" >
                                            <div class="form-group col-md-6 ">
                                                <label for="selectNomEstancia">DOCUMENTO 1:</label>
                                                <select class="form-control form-revDoc" name="selectDoc1" id="selectDoc1">                                          
                                                    <option>${revisionCD.r_docD1}</option>
                                                    <option>CONSISTENTE</option>
                                                    <option>INCONSISTENTE</option>
                                                    <option>POR REVISAR</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectNomResponsable">DOCUMENTO 2:</label>
                                                <select class="form-control form-revDoc" name="selectDoc2" id="selectDoc2" >
                                                    <option>${revisionCD.r_docD2}</option>
                                                    <option>CONSISTENTE</option>
                                                    <option>INCONSISTENTE</option>
                                                    <option>POR REVISAR</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectDireccion">DOCUMENTO 3:</label>
                                                <select class="form-control form-revDoc" name="selectDoc3" id="selectDoc3">
                                                    <option>${revisionCD.r_docD3}</option>
                                                    <option>CONSISTENTE</option>
                                                    <option>INCONSISTENTE</option>
                                                    <option>POR REVISAR</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectEstado">DOCUMENTO 4:</label>
                                                <select class="form-control form-revDoc" name="selectDoc4" id="selectDoc4">
                                                    <option>${revisionCD.r_docD4}</option>
                                                    <option>CONSISTENTE</option>
                                                    <option>INCONSISTENTE</option>
                                                    <option>POR REVISAR</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectFechaIn ">DOCUMENTO 5:</label>
                                                <select class="form-control form-revDoc"  name="selectDoc5" id="selectDoc5">
                                                    <option>${revisionCD.r_docD5}</option>
                                                    <option>CONSISTENTE</option>
                                                    <option>INCONSISTENTE</option>
                                                    <option>POR REVISAR</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectFechaFin">DOCUMENTO 6:</label>
                                                <select class="form-control form-revDoc" name="selectDoc6" id="selectDoc6">
                                                    <option>${revisionCD.r_docD6}</option>
                                                    <option>CONSISTENTE</option>
                                                    <option>INCONSISTENTE</option>
                                                    <option>POR REVISAR</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectFechaFin">ESTATUS CAMBIO DE DOMICILIO:</label>
                                                <select class="form-control form-revDoc" name="estatus" id="estatus">
                                                    <option>${revisionCD.estatus}</option>
                                                    <option>EN PROCESO</option>
                                                    <option>FINALIZADO</option>
                                                    <option>CANCELADO</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectFechaFin">COMENTARIOS DE AVANCE DEL PROCESO:</label>
                                                <br>
                                                <textarea style="width: 80%; float: left" name="txtAreacomentarios" id="txtAreacomentarios">${revisionCD.comentarios}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="selectFechaFin ">USUARIO QUE REVISÓ EL PROCESO :</label>
                                                <br>
                                                <input class="form-control form-revDoc" type="text" name="txtUsuarioRev" id="txtUsuarioRev" value="${usuario}" disabled>
                                                <input class="form-control form-revDoc" type="hidden" name="txtUsuarioRev" id="txtUsuarioRev" value="${usuario}" >
                                            </div>
                                        </div>
                                    </section>
                                    <div class="documento">     
                                        <ul class="list-group" style="width: 65%; margin-left: 28%; padding: 10px;" id="listadoDocumentos">
                                            <li class="list-group-item"><strong>LISTA DE DOCUMENTOS CAMBIO DE DOMICILIO</strong></li>
                                            <li class="list-group-item list-group-item-secondary" id="lista1"><a id="btn1" href="#">DOCUMENTO 1</a>
                                                <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                                    <c:param name="documento" value="${datosEstancia.ruta_docD1}"></c:param>
                                                </c:url> 
                                                <c:if test="${datosEstancia.ruta_docD1!=null}">
                                                    <iframe src="${linkTempDoc}" frameborder="1" width="700" height="590" id="frame1"></iframe>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD1==null}">
                                                    <p style="padding-top: 10px; color: #b37400">EL DOCUMENTO 1 AUN NO SE HA SUBIDO</p>
                                                </c:if>
                                            </li>
                                            <li class="list-group-item "><a id="btn2" href="#">DOCUMENTO 2</a>
                                                <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                                    <c:param name="documento" value="${datosEstancia.ruta_docD2}"></c:param>
                                                </c:url> 
                                                <c:if test="${datosEstancia.ruta_docD2!=null}">
                                                    <iframe src="${linkTempDoc}" frameborder="1" width="700" height="590" id="frame2"></iframe>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD2==null}">
                                                    <p style="padding-top: 10px; color: #b37400">EL DOCUMENTO 2 AUN NO SE HA SUBIDO</p>
                                                </c:if>
                                            </li>
                                            <li class="list-group-item list-group-item-secondary"><a id="btn3" href="#">DOCUMENTO 3</a>
                                                <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                                    <c:param name="documento" value="${datosEstancia.ruta_docD3}"></c:param>
                                                </c:url> 
                                                <c:if test="${datosEstancia.ruta_docD3!=null}">
                                                    <iframe src="${linkTempDoc}" frameborder="1" width="700" height="590" id="frame3"></iframe>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD3==null}">
                                                    <p style="padding-top: 10px; color: #b37400">EL DOCUMENTO 3 AUN NO SE HA SUBIDO</p>
                                                </c:if>
                                            </li>
                                            <li class="list-group-item"><a id="btn4" href="#">DOCUMENTO 4</a>
                                                <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                                    <c:param name="documento" value="${datosEstancia.ruta_docD4}"></c:param>
                                                </c:url> 
                                                <c:if test="${datosEstancia.ruta_docD4!=null}">
                                                    <iframe src="${linkTempDoc}" frameborder="1" width="700" height="590" id="frame4"></iframe>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD4==null}">
                                                    <p style="padding-top: 10px; color: #b37400">EL DOCUMENTO 4 AUN NO SE HA SUBIDO</p>
                                                </c:if>
                                            </li>
                                            <li class="list-group-item list-group-item-secondary"><a id="btn5" href="#">DOCUMENTO 5</a>
                                                <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                                    <c:param name="documento" value="${datosEstancia.ruta_docD5}"></c:param>
                                                </c:url> 
                                                <c:if test="${datosEstancia.ruta_docD5!=null}">
                                                    <iframe src="${linkTempDoc}" frameborder="1" width="700" height="590" id="frame5"></iframe>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD5==null}">
                                                    <p style="padding-top: 10px; color: #b37400">EL DOCUMENTO 5 AUN NO SE HA SUBIDO</p>
                                                </c:if>
                                            </li>
                                            <li class="list-group-item "><a id="btn6" href="#">DOCUMENTO 6</a>
                                                <c:url var="linkTempDoc" value="/../VisualizarPDF">
                                                    <c:param name="documento" value="${datosEstancia.ruta_docD6}"></c:param>
                                                </c:url> 
                                                <c:if test="${datosEstancia.ruta_docD6!=null}">
                                                    <iframe src="${linkTempDoc}" frameborder="1" width="700" height="590" id="frame6"></iframe>
                                                    </c:if>
                                                    <c:if test="${datosEstancia.ruta_docD6==null}">
                                                    <p style="padding-top: 10px; color: #b37400">EL DOCUMENTO 6 AUN NO SE HA SUBIDO</p>
                                                </c:if>
                                            </li>
                                        </ul>
                                        <!---->
                                    </div>
                                    <button type="submit" class="btn btn-outline-success btn-lg btn-block" id="btnEnviarRevDomicilio">Enviar</button>
                                </form>
                            </c:if>                  
                            <c:if test="${datosEstancia.estatus_domicilio==null}">
                                <div class="modal" id="errorModal2" tabindex="-1" role="dialog" style="text-align: center;">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title" style="color: orange;">AVISO!</h4>    
                                            </div>
                                            <div class="modal-body">
                                                <p style="font-size: 15px;  "><strong>LA ESTANCIA NO HA EMPEZADO EL PROCESO DE CAMBIO DE DOMICILIO</strong></p>
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
