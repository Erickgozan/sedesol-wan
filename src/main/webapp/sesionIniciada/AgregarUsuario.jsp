<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/Headboard.jsp" %>
        <title>Actualizar la BD</title>
    </head>
 <!--VALIDAR SI SE HA INICIADO SESION-->
    <c:if test="${privilegio!=null}"> 
    <body>
        <c:if test="${privilegio=='Administrador' }">
            <%@include file="componentes/Menu.jsp" %>
            <main>
                <article>
                    <h1 style="color: #122b40">AGREGAR USUSUARIO</h1>

                    <ul class="list-group" >
                        <li class="list-group-item " ><a id="listUsuarios" href="#"  style="color: black">Ver lista de ususarios</a>
                            <table class="table table-striped table-bordered"  id="tablaUser" style="padding: 10px;">
                                <thead>
                                    <tr>
                                        <th>USUARIO</th>
                                        <th>CONTRASEÑA</th>
                                        <th>ESTADO</th>
                                        <th>PRIVILEGIO</th>
                                        <th>ELIMINAR</th>
                                    </tr>
                                </thead>
                                <c:forEach var="listaUser" items="${listaUser}">
                                    <tbody>
                                        <tr>
                                            <td>${listaUser.usuario}</td>
                                            <td>${listaUser.contrasenia}</td>
                                            <td>${listaUser.estado}</td>
                                            <td>${listaUser.privilegio}</td>
                                            <td><a  href="?proceso=eliminar&usuario=${listaUser.usuario}">Eliminar</a></td>
                                        </tr>
                                    </tbody>
                                </c:forEach>
                            </table>

                        </li>

                    </ul>
                    <hr>
                    <form action="../ControladorUsuario" method="POST"  id="formAgregarUser">
                        <input type="hidden" name="proceso" id="proceso" value="insertUser">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="txtUsuario">Nombre del usuario:</label>
                                <input type="text" name="txtUsuario" id="txtUsuario" class="form-control"  >
                            </div>
                            <div class="form-group col-md-6">
                                <label for="txtContra" >Contraseña:</label>
                                <input type="text" name="txtContra" id="txtContra" class="form-control" >  
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="selectEstado">Estado:</label>
                                <select class="form-control" name="selectEstado" id="selectEstado">
                                    <option ></option>
                                    <option>AGUASCALIENTES</option>
                                    <option>BAJA CALIFORNIA</option>
                                    <option>BAJA CALIFORNIA SUR</option>
                                    <option>CAMPECHE</option>
                                    <option>CHIAPAS</option>
                                    <option>CHIHUAHUA</option>
                                    <option>COAHUILA DE ZARAGOZA</option>
                                    <option>COLIMA</option>
                                    <option>DISTRITO FEDERAL</option>
                                    <option>DURANGO</option>
                                    <option>GUERRERO</option>
                                    <option>HIDALGO</option>
                                    <option>JALISCO</option>
                                    <option>MEXICO</option>
                                    <option>MICHOACAN DE OCAMPO</option>
                                    <option>MORELOS</option>
                                    <option>NAYARIT</option>
                                    <option>NUEVO LEON</option>
                                    <option>OAXACA</option>
                                    <option>PUEBLA</option>
                                    <option>QUERETARO</option>
                                    <option>QUINTANA ROO</option>
                                    <option>SAN LUIS POTOSI</option>
                                    <option>SINALOA</option>
                                    <option>SONORA</option>
                                    <option>TABASCO</option>
                                    <option>TAMAULIPAS</option>
                                    <option>TLAXCALA</option>
                                    <option>VERACRUZ DE IGNACIO DE LA LLAVE</option>
                                    <option>YUCATAN</option>
                                    <option>ZACATECAS</option>
                                    <option>OFICINAS CENTRALES</option>
                                </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="selectPrivilegio" >Privilegio</label>
                                <select class="form-control" id="selectPrivilegio" name="selectPrivilegio">
                                    <option></option>
                                    <option>Estandar</option>
                                    <option>Procesos</option>
                                    <option>Administrador</option>
                                </select> 
                            </div>
                        </div>
                        <button type="submit" class="btn btn-outline-success btn-lg btn-block "  name="agregarUsuario" id="agregarUsuario">Agregar</button>                            

                    </form>
                    <!--MODAL USUARIO-->
                    <c:if test="${mensaje!=null}">
                        <div class="modal" id="modalUsuario" tabindex="-1" role="dialog" style="text-align: center;">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Listo!</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p><strong >${mensaje}</strong></p>
                                    </div>
                                    <div class="modal-footer">   
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="salirUsuario">Salir</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </article>
            </main>
            <%@include file="componentes/Footer.jsp" %>    
        </c:if>
         <c:if test="${privilegio!='Administrador' }">
          <% response.sendRedirect("/sesionIniciada/Inicio.jsp");%>
         </c:if>
    </body>
     </c:if>
    <!--REDIRECCIONAR AL INICIO DE SESION CUANDO SE INTENTE ACCEDER SIN TENER LA SESION INICIADA-->
    <c:if test="${privilegio==null}">
        <%response.sendRedirect("../index.jsp");%>
    </c:if>
</html>
