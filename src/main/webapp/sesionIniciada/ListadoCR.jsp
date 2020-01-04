<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/Headboard.jsp" %>
        <title>Listado de estancias cambio de domicilio</title>
    </head>
<!--VALIDAR SI SE HA INICIADO SESION-->
<c:if test="${privilegio!=null}"> 
        <body>  
            <%@include file="componentes/Menu.jsp" %>
            <main>
                <h1 style="color: rgb(219, 47, 47)">LISTADO DE ESTANCIAS CAMBIO DE RESPONSABLE</h1>
                <hr>
                <div>
                    <c:if test="${estado=='OFICINAS CENTRALES'}"> 
                        <!--Enlace para volver al listado completo-->
                        <a class="btn btn-outline-info" role="button" aria-pressed="true" href="/ControladorCR?proceso=listarCR&estado=${estado}">Volver al listado completo</a>
                    </c:if>
                </div>
                <div> 
                    <nav class="navbar buscador">
                        <div style="margin-right: 120px">
                            <!--DESCARGAR EL LISTADO-->
                            <form action="/CrearExcelCr" method="GET">
                                <input type="hidden" name="estado" value="${estado}">
                                <button type="submit" class="btn btn-success">Descargar el listado</button>
                            </form>                
                        </div>

                        <c:if test="${estado!='OFICINAS CENTRALES'}"> 
                            <div style="padding-right: 100px">
                                <!--Enlace para volver al listado completo-->
                                <a class="btn btn-outline-info" role="button" aria-pressed="true" href="/ControladorCD?proceso=listarCD&estado=${estado}">Volver al listado completo</a>
                            </div>   
                        </c:if>


                        <form action="/ControladorCR" method="GET" class="form-inline">
                            <c:if test="${estado=='OFICINAS CENTRALES'}">
                                <b>BUSCAR POR ID,NOMBRE ESTANCIA,NOMBRE RESPONSABLE O ESTADO</b><br>
                            </c:if>
                            <c:if test="${estado!='OFICINAS CENTRALES'}">
                                <b>BUSCAR POR ID</b><br>
                            </c:if>
                            <input type="hidden" name="proceso" value="idBuscarEstanciaCr" id="proceso">
                            <input type="hidden" name="estado" value="${estado}" id="estado">
                            <input name="BuscarEstanciaCr"  class="form-control mr-sm-2" type="search" placeholder="buscar estancia" aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >Buscar</button>
                        </form>
                    </nav>
                </div>
                <article>
                    <div style="width:98%;height:500px;overflow:auto; padding:1rem;">
                        <table class="table table-striped table-bordered tablaRev">                 
                            <thead>
                                <tr><th>ID DE LA ESTANCIA</th>
                                    <th>NOMBRE DE LA ESTANCIA</th>
                                    <th>NOMBRE DE LA RESPONSABLE</th>
                                    <th>DIRECCIÓN</th>
                                    <th>ESTADO</th> 
                                    <th>DOCUMENTO 1</th>
                                    <th>DOCUMENTO 2</th>
                                    <th>DOCUMENTO 3</th>
                                    <th>COMENTARIOS</th>
                                    <th>ESTATUS</th>
                                    <th>USUARIO QUE REVISO</th>
                                        <c:if test="${privilegio=='Administrador'}">
                                        <th scope="col">ELIMINAR DOC 1</th>
                                        <th scope="col">ELIMINAR DOC 2</th>
                                        <th scope="col">ELIMINAR DOC 3</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <tbody>   
                                <!--INNER JON ESTANCIA CON REVISARCr-->
                                <c:forEach var="estanciaCr" items="${estanciaCr}">
                                    <tr>
                                        <td>${estanciaCr.id_estancia}</td>
                                        <td>${estanciaCr.nombre_estancia}</td>
                                        <td>${estanciaCr.nombre_responsable}</td>
                                        <td>${estanciaCr.direccion}</td>
                                        <td>${estanciaCr.estado}</td>
                                        <td>${estanciaCr.r_docR1}</td>
                                        <td>${estanciaCr.r_docR2}</td>
                                        <td>${estanciaCr.r_docR3}</td>
                                        <td>${estanciaCr.comentarios}</td>
                                        <td>${estanciaCr.estatus}</td>
                                        <td>${estanciaCr.usuario_rev}</td>
                                        <c:if test="${privilegio=='Administrador' && estanciaCr.ruta_docR1 != null}">
                                            <td><a href="?proceso=eliminar&id=${estanciaCr.id_estancia}&ruta=${estanciaCr.ruta_docR1}&documento=doc1">Eliminar</a></td>                                
                                        </c:if>
                                        <c:if test="${privilegio=='Administrador' && estanciaCr.ruta_docR1 == null}">
                                            <td><a>Sin documento</a></td>                                
                                        </c:if>
                                        <c:if test="${privilegio=='Administrador' && estanciaCr.ruta_docR2 != null}">
                                            <td><a href="?proceso=eliminar&id=${estanciaCr.id_estancia}&ruta=${estanciaCr.ruta_docR2}&documento=doc2">Eliminar</a></td>  
                                        </c:if>
                                        <c:if test="${privilegio=='Administrador' && estanciaCr.ruta_docR2 == null}">
                                            <td><a>Sin documento</a></td>                                
                                        </c:if>
                                        <c:if test="${privilegio=='Administrador' && estanciaCr.ruta_docR3 != null}">
                                            <td><a href="?proceso=eliminar&id=${estanciaCr.id_estancia}&ruta=${estanciaCr.ruta_docR3}&documento=doc3">Eliminar</a></td>  
                                        </c:if>
                                        <c:if test="${privilegio=='Administrador' && estanciaCr.ruta_docR3 == null}">
                                            <td><a>Sin documento</a></td>                                
                                        </c:if>

                                    </tr>  
                                </c:forEach>
                            </tbody>
                        </table>
                        <!--MODAL QUE VERIFICA SI LA EXTANCIA NO EXISTE Y SI SE PUDO ELIMINA O NO SE PUDO ELIMINAR-->
                        <c:if test="${eliminarCr!=null }">
                            <div class="modal" id="errorModal2" tabindex="-1" role="dialog" style="text-align: center;">
                                <div class="modal-dialog" role="document"> 
                                    <div class="modal-content">       
                                        <div class="modal-header">
                                            <h4 class="modal-title" style="color:#006847">ÉXITO!</h4>    
                                        </div>
                                        <div class="modal-body">
                                            <p style="font-size: 25px; color: tomato; "><strong>${eliminarCr}</strong></p>
                                        </div>
                                        <div class="modal-footer">   
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal" id="volverListadoCD">Salir</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>              
                    </div>
                </article>

            </main>
            <%@include file="componentes/Footer.jsp" %>  
        </body>
 </c:if>
<!--REDIRECCIONAR AL INICIO DE SESION CUANDO SE INTENTE ACCEDER SIN TENER LA SESION INICIADA-->
<c:if test="${privilegio==null}">
    <%response.sendRedirect("../index.jsp");%>
</c:if>
</html>
