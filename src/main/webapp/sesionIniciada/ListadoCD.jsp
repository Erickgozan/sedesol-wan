<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/Headboard.jsp" %>
        <title>Listado de estancias cambio de domicilio</title>
    </head>
</head>
<!--VALIDAR SI SE HA INICIADO SESION-->
<c:if test="${privilegio!=null}"> 
    <body>  
        <%@include file="componentes/Menu.jsp" %>
        <main>
            <h1 style="color: rgb(0, 104, 71)">LISTADO DE ESTANCIAS CAMBIO DE DOMICILIO</h1>
            <hr>
            <div>
                <c:if test="${estado=='OFICINAS CENTRALES'}"> 
                    <!--Enlace para volver al listado completo-->
                    <a class="btn btn-outline-info" role="button" aria-pressed="true" href="/ControladorCD?proceso=listarCD&estado=${estado}">Volver al listado completo</a>
                </c:if>
            </div>
            <div> 
                <nav class="navbar buscador">
                    <div style="margin-right: 120px">
                        <!--DESCARGAR EL LISTADO-->
                        <form action="/CrearExcelCd" method="GET">
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


                    <form action="/ControladorCD" method="GET" class="form-inline">
                        <c:if test="${estado=='OFICINAS CENTRALES'}">
                            <b>BUSCAR POR ID,NOMBRE ESTANCIA,NOMBRE RESPONSABLE O ESTADO</b><br>
                        </c:if>
                        <c:if test="${estado!='OFICINAS CENTRALES'}">
                            <b>BUSCAR POR ID</b><br>
                        </c:if>
                        <input type="hidden" name="proceso" value="idBuscarEstanciaCd" id="proceso">
                        <input type="hidden" name="estado" value="${estado}" id="proceso">
                        <input name="BuscarEstanciaCd"  class="form-control mr-sm-2" type="search" placeholder="buscar estancia" aria-label="Search">
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
                                <th>DOCUMENTO 4</th>
                                <th>DOCUMENTO 5</th>
                                <th>DOCUMENTO 6</th>
                                <th>COMENTARIOS</th>
                                <th>ESTATUS</th>
                                <th>USUARIO QUE REVISO</th>
                                    <c:if test="${privilegio=='Administrador'}">
                                    <th scope="col">ELIMINAR DOC 1</th>
                                    <th scope="col">ELIMINAR DOC 2</th>
                                    <th scope="col">ELIMINAR DOC 3</th>
                                    <th scope="col">ELIMINAR DOC 4</th>
                                    <th scope="col">ELIMINAR DOC 5</th>
                                    <th scope="col">ELIMINAR DOC 6</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>   
                            <c:forEach var="estanciaCd" items="${estanciaCd}">
                                <tr>
                                    <td>${estanciaCd.id_estancia}</td>
                                    <td>${estanciaCd.nombre_estancia}</td>
                                    <td>${estanciaCd.nombre_responsable}</td>
                                    <td>${estanciaCd.direccion}</td>
                                    <td>${estanciaCd.estado}</td>
                                    <td>${estanciaCd.r_docD1}</td>
                                    <td>${estanciaCd.r_docD2}</td>
                                    <td>${estanciaCd.r_docD3}</td>
                                    <td>${estanciaCd.r_docD4}</td>
                                    <td>${estanciaCd.r_docD5}</td>
                                    <td>${estanciaCd.r_docD6}</td>
                                    <td>${estanciaCd.comentarios}</td>
                                    <td>${estanciaCd.estatus}</td>
                                    <td>${estanciaCd.usuario_rev}</td>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD1 != null}">
                                        <td><a href="?proceso=eliminar&id=${estanciaCd.id_estancia}&ruta=${estanciaCd.ruta_docD1}&documento=doc1">Eliminar</a></td>                                
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD1 == null}">
                                        <td><a>Sin documento</a></td>                                
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD2 != null}">
                                        <td><a href="?proceso=eliminar&id=${estanciaCd.id_estancia}&ruta=${estanciaCd.ruta_docD2}&documento=doc2">Eliminar</a></td>  
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD2 == null}">
                                        <td><a>Sin documento</a></td>                                
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD3 != null}">
                                        <td><a href="?proceso=eliminar&id=${estanciaCd.id_estancia}&ruta=${estanciaCd.ruta_docD3}&documento=doc3">Eliminar</a></td>  
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD3 == null}">
                                        <td><a>Sin documento</a></td>                                
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD4 != null}">
                                        <td><a href="?proceso=eliminar&id=${estanciaCd.id_estancia}&ruta=${estanciaCd.ruta_docD4}&documento=doc4">Eliminar</a></td>  
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD4 == null}">
                                        <td><a>Sin documento</a></td>                                
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD5 != null}">
                                        <td><a href="?proceso=eliminar&id=${estanciaCd.id_estancia}&ruta=${estanciaCd.ruta_docD5}&documento=doc5">Eliminar</a></td>  
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD5 == null}">
                                        <td><a>Sin documento</a></td>                                
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD6 != null}">
                                        <td><a href="?proceso=eliminar&id=${estanciaCd.id_estancia}&ruta=${estanciaCd.ruta_docD6}&documento=doc6">Eliminar</a></td>  
                                    </c:if>
                                    <c:if test="${privilegio=='Administrador' && estanciaCd.ruta_docD6 == null}">
                                        <td><a>Sin documento</a></td>                                
                                    </c:if>
                                </tr>  
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--MODAL QUE VERIFICA SI LA EXTANCIA NO EXISTE Y SI SE PUDO ELIMINA O NO SE PUDO ELIMINAR-->
                    <c:if test="${eliminarCd!=null }">
                        <div class="modal" id="errorModal2" tabindex="-1" role="dialog" style="text-align: center;">
                            <div class="modal-dialog" role="document"> 
                                <div class="modal-content">       
                                    <div class="modal-header">
                                        <h4 class="modal-title" style="color:#006847">ÉXITO!</h4>    
                                    </div>
                                    <div class="modal-body">
                                        <p style="font-size: 25px; color: tomato; "><strong>${eliminarCd}</strong></p>
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
