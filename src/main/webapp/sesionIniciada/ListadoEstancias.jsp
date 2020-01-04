<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/Headboard.jsp" %>
        <title>Listado de estancias</title>
    </head>
    <!--VALIDAR SI SE HA INICIADO SESION-->
    <c:if test="${privilegio!=null}"> 
        <body> 
            <%@include file="componentes/Menu.jsp" %>
            <main>
                <h1 style="text-align: center;">LISTADO DE ESTANCIAS AUTORIZACIÓN DEL MODELO</h1>
                <hr>
                <c:if test="${numeroPaginas==null}">
                    <div>
                        <!--Enlace para volver al listado completo-->
                        <a class="btn btn-outline-info" role="button" aria-pressed="true" href="/ControladorProcesos?proceso=listado&estado=${estado}">Volver al listado completo</a>
                    </div>
                </c:if>
                <div> 
                    <c:if test="${numeroPaginas!=null}"> 
                        <p style="margin-bottom: 3px">Paginación</p>
                        <nav aria-label="Page navigation " id="paginacion">
                            <ul class="pagination justify-content-center" id="enlaces"> 
                                <c:if test="${pagina!=0}">
                                    <!--pagian anterior-->
                                    <li class="page-item " >
                                        <a class="page-link "  href="?proceso=paginacion&estado=${estado}&pagina=${pagina-1}">&lt;&lt;Anterior</a>
                                    </li>
                                </c:if> 
                                <%
                                    String numPaginas = request.getAttribute("numeroPaginas").toString();
                                    int totalPaginas = Integer.parseInt(numPaginas);
                                    for (int i = 0; i <= totalPaginas; i++) {%>       
                                <li class="page-item ">
                                    <a class="page-link " href="?proceso=paginacion&estado=${estado}&pagina=<%=i%>"><%=i + 1%></a>
                                </li>
                                <%}%>
                                <!--pagina siguiente-->
                                <c:if test="${pagina<numeroPaginas}">
                                    <li class="page-item">
                                        <a class="page-link" href="?proceso=paginacion&estado=${estado}&pagina=${pagina+1}">Siguiente&gt;&gt; </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </c:if>

                    <nav class="navbar buscador">
                        <div style="margin-right: 30rem;">
                            <!--Descargar el listado-->
                            <form action="/CrearExcel" method="GET">
                                <input type="hidden" name="estado" value="${estado}">
                                <button type="submit" class="btn btn-success">Descargar el listado</button>
                            </form>
                        </div>


                        <b style="float: left;">BUSCAR LA ESTANCIA:</b>
                        <form action="/ControladorProcesos" method="GET" class="form-inline">
                            <input type="hidden" name="proceso" value="idBuscarRev" id="proceso">
                            <input type="hidden" name="estado" value="${estado}" id="proceso">
                            <input name="btnBuscarRev" id="btnBuscarRev" class="form-control mr-sm-2" type="search" placeholder="Id de la estancia" aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="buscarListado">Buscar</button>
                        </form>
                    </nav>
                </div>
                <article>
                    <div style="width:98%;height:500px;overflow:auto; padding:1rem;">
                        <c:if test="${noEncontrado==null}">
                            <table class="table table-striped table-bordered tablaRev">                 
                                <thead>
                                    <tr>
                                        <th scope="col">ID ESTANCIA</th>
                                        <th scope="col">NOMBRE DE LA ESTANCIA</th>
                                        <th scope="col">NOMBRE DE LA RESPONSABLE</th>
                                        <th scope="col">DIRECCIÓN</th>
                                        <th scope="col">ESTADO DE LA REPUBLICA</th>
                                        <th scope="col">FECHA DE INICIO</th>
                                        <th scope="col">FECHA DE FIN</th>
                                        <th scope="col">ID REVISIÓN</th>
                                        <th scope="col">NOMBRE DE LA ESTANCIA</th>
                                        <th scope="col">NOMBRE DE LA RESPONSABLE</th>
                                        <th scope="col">DIRECCIÓN</th>
                                        <th scope="col">ESTADO DE LA REPUBLICA</th>
                                        <th scope="col">FECHA DE INICIO</th>
                                        <th scope="col">FECHA DE FIN</th>
                                        <th scope="col">COMENTARIOS</th>
                                        <th scope="col">ESTATUS</th>
                                        <th scope="col">USUARIO QUE REVISO</th>
                                            <c:if test="${privilegio=='Administrador'}">
                                            <th scope="col">ELIMINAR REGISTRO</th>
                                            </c:if>
                                    </tr>
                                </thead>
                                <tbody>   
                                    <c:forEach var="listadoTemp" items="${listado}">
                                        <tr>
                                            <td>${listadoTemp.idEstancia}</td>
                                            <td>${listadoTemp.listaNombreEstancia}</td>
                                            <td>${listadoTemp.listaNombreResponsable}</td>
                                            <td>${listadoTemp.listaDireccion}</td>
                                            <td>${listadoTemp.listaEstado}</td>
                                            <td>${listadoTemp.listaFechaInicio}</td>
                                            <td>${listadoTemp.listaFechaFin}</td>
                                            <td>${listadoTemp.idRevision}</td>
                                            <td>${listadoTemp.listaRNombreEstancia}</td>
                                            <td>${listadoTemp.listaRDireccion}</td>
                                            <td>${listadoTemp.listaRNombreResponsable}</td>
                                            <td>${listadoTemp.listaREstado}</td>
                                            <td>${listadoTemp.listaRFechaInicio}</td>
                                            <td>${listadoTemp.listaRFechaFin}</td>
                                            <td>${listadoTemp.comentarios}</td>
                                            <td>${listadoTemp.estatus}</td>
                                            <td>${listadoTemp.listaUsuarioRev}</td>

                                            <c:if test="${privilegio=='Administrador' && listadoTemp.listaRutaDoc!=null}">
                                                <td><a href="?proceso=eliminar&id=${listadoTemp.idEstancia}&ruta=${listadoTemp.listaRutaDoc}">Eliminar</a></td>                                
                                            </c:if>
                                            <c:if test="${privilegio=='Administrador' && listadoTemp.listaRutaDoc==null}">
                                                <td><a >Sin documento</a></td>                                
                                            </c:if>
                                        </tr>  
                                    </c:forEach>
                                </tbody>

                                <tbody>               
                                    <tr>
                                        <td>${listadoDatos.id_estancia}</td>
                                        <td>${listadoDatos.nombre_estancia}</td>
                                        <td>${listadoDatos.nombre_responsable}</td>
                                        <td>${listadoDatos.direccion}</td>
                                        <td>${listadoDatos.estado}</td>
                                        <td>${listadoDatos.fecha_inicio}</td>
                                        <td>${listadoDatos.fehca_fin}</td>
                                        <td>${listadoRev.id_revision}</td>
                                        <td>${listadoRev.r_nombre_estancia}</td>
                                        <td>${listadoRev.r_nombre_responsable}</td>
                                        <td>${listadoRev.r_direccion}</td>
                                        <td>${listadoRev.r_estado}</td>
                                        <td>${listadoRev.r_fecha_inicio}</td>
                                        <td>${listadoRev.r_fecha_fin}</td>
                                        <td>${listadoRev.usuario_rev}</td>
                                        <td>${listadoRev.comentarios}</td>
                                        <td>${listadoRev.estatus}</td>
                                        <c:if test="${privilegio=='Administrador'}">
                                            <td><a href="?proceso=eliminar&id=${listadoDatos.id_estancia}&ruta=${listadoDatos.ruta_doc}">Eliminar</a></td>                                
                                        </c:if>
                                    </tr>  

                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${noEncontrado!=null || eliminarS!=null || eliminarE!=null  }">
                            <div class="modal" id="errorModal2" tabindex="-1" role="dialog" style="text-align: center;">
                                <div class="modal-dialog" role="document"> 
                                    <div class="modal-content">       
                                        <c:if test="${eliminarE!=null || noEncontrado!=null}" >
                                            <div class="modal-header">
                                                <h4 class="modal-title" style="color:red;">ERROR!</h4>    
                                            </div>
                                        </c:if>
                                        <c:if test="${eliminarS!=null}" >
                                            <div class="modal-header">
                                                <h4 class="modal-title" style="color:#006847">ÉXITO!</h4>    
                                            </div>
                                        </c:if>
                                        <c:if test="${noEncontrado!=null}" >
                                            <div class="modal-body">
                                                <p style="font-size: 25px; color: tomato; "><strong>${noEncontrado}</strong></p>
                                            </div>
                                        </c:if>
                                        <c:if test="${eliminarE!=null}" >
                                            <div class="modal-body">
                                                <p style="font-size: 25px; color: tomato; "><strong>${eliminarE}</strong></p>
                                            </div>
                                        </c:if>
                                        <c:if test="${eliminarS!=null}" >
                                            <div class="modal-body">
                                                <p style="font-size: 25px; color: #006847; "><strong>${eliminarS}</strong></p>
                                            </div>
                                        </c:if>
                                        <div class="modal-footer">   
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal" id="volverListado">Salir</button>
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
