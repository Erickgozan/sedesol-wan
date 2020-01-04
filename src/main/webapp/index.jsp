<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true" %>
<html>
    <head>
        <title>Inicio de sesión</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="img/png" href="img/escudo.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> 
    </head>
    <body>
        <div class="container col-lg-3">
            <h1>INICIAR SESIÓN</h1>
            <hr>
            <form  action="ControladorSesion" method="POST" id="formSesion">
                <div class="form-group">
                    <img src="img/login-usuario.png" alt="user" height="80" width="80"/>
                    <p><strong>Bienvenidos Usauarios: </strong></p>
                </div>
                <div class="form-group">
                    <label for="usuario"> Usuario:</label>
                    <input  class="form-control" type="text" name="txtUsuario" placeholder="Usuario" id="txtusuario">
                </div>
                <div class="form-group">
                    <label for="pass">Contraseña:</label>
                    <input class="form-control" type="password" name="txtContrasenia" placeholder="Contraseña"  id="txtcontrasenia" >                  
                </div> 
                <input class="btn btn-danger btn-block" type="submit" value="Iniciar Sesion" name="btnIniciar" id="btnIniciar">
            </form>
                <p style="color: red" id="error"></p>
            <c:if test="${error!=null}">
                <br>
                <p style="color: red"><strong>${error}</strong></p>
           </c:if>
        </div>        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <!--<script src="/proyectoSedesol/js/ajax.js"></script>-->
        <script src="js/ajax.js" type="text/javascript"></script>
       
    </body>
</html>
<%
    HttpSession sesion = request.getSession();
    String privilegio = "";
    String usuario = "";
    String estado = "";
    if (request.getAttribute("privilegio") != null) {
        privilegio = request.getAttribute("privilegio").toString();
        usuario = request.getAttribute("usuario").toString();
        estado = request.getAttribute("estado").toString();
        sesion.setAttribute("usuario", usuario);
        sesion.setAttribute("privilegio", privilegio);
        sesion.setAttribute("estado", estado);
        response.sendRedirect("sesionIniciada/Inicio.jsp");
    }
    if (request.getParameter("instruccion") != null) {
        session.invalidate();
    }

%>