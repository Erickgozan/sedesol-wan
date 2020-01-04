$(document).ready(function () {
    $("#cerrar").click(function (e) {
        e.preventDefault();
        if ($("#cerrar").hasClass("activado")) {
            $("#menu").slideUp();
            $("#cerrar").removeClass("activado");
        } else {
            $("#cerrar").addClass("activado");
            $("#menu").slideDown();
        }
    });

    var url = jQuery(location).attr('href');
    if (url === "http://localhost:8080/proyectoSedesol/sesionIniciada/RevisarDocumentacion.jsp") {
        $("#li1").addClass("active");
    } else if (url === "http://localhost:8080/proyectoSedesol/sesionIniciada/SubirDocumentacion.jsp") {
        $("#li2").addClass("active");
    } else if (url === "http://localhost:8080/proyectoSedesol/sesionIniciada/CambioDomicilio.jsp") {
        $("#li3").addClass("active");
    } else if (url === "http://localhost:8080/proyectoSedesol/sesionIniciada/CambioResponsable.jsp") {
        $("#li4").addClass("active");
    } else if (url === "http://localhost:8080/proyectoSedesol/sesionIniciada/ActualizarDB.jsp") {
        $("#li5").addClass("active");
    }


    $('#calendario').datepicker({
        format: "dd/mm/yyyy",
        todayBtn: "linked",
        clearBtn: true,
        language: "es",
        autoclose: true,
        toggleActive: true

    });

    $("#enlaces li").click(function () {
        $("#enlaces li").removeClass('active');
        $(this).addClass('active');
    });
//Documentacion cambio de domicilio

    $("#frame1").addClass("documentoInactivo");
    $("#frame2").addClass("documentoInactivo");
    $("#frame3").addClass("documentoInactivo");
    $("#frame4").addClass("documentoInactivo");
    $("#frame5").addClass("documentoInactivo");
    $("#frame6").addClass("documentoInactivo");
    $("#btn1").click(function (e) {
        e.preventDefault();
        if ($("#frame1").hasClass("documentoInactivo")) {
            $("#frame1").removeClass("documentoInactivo");
            $("#frame1").addClass("documentoActivo");
        } else if ($("#frame1").hasClass("documentoActivo")) {
            $("#frame1").removeClass("documentoActivo");
            $("#frame1").addClass("documentoInactivo");
        }

    });
    $("#btn2").click(function (e) {
        e.preventDefault();
        if ($("#frame2").hasClass("documentoInactivo")) {
            $("#frame2").removeClass("documentoInactivo");
            $("#frame2").addClass("documentoActivo");
        } else if ($("#frame2").hasClass("documentoActivo")) {
            $("#frame2").removeClass("documentoActivo");
            $("#frame2").addClass("documentoInactivo");
        }

    });
    $("#btn3").click(function (e) {
        e.preventDefault();
        if ($("#frame3").hasClass("documentoInactivo")) {
            $("#frame3").removeClass("documentoInactivo");
            $("#frame3").addClass("documentoActivo");
        } else if ($("#frame3").hasClass("documentoActivo")) {
            $("#frame3").removeClass("documentoActivo");
            $("#frame3").addClass("documentoInactivo");
        }

    });
    $("#btn4").click(function (e) {
        e.preventDefault();
        if ($("#frame4").hasClass("documentoInactivo")) {
            $("#frame4").removeClass("documentoInactivo");
            $("#frame4").addClass("documentoActivo");
        } else if ($("#frame4").hasClass("documentoActivo")) {
            $("#frame4").removeClass("documentoActivo");
            $("#frame4").addClass("documentoInactivo");
        }

    });
    $("#btn5").click(function (e) {
        e.preventDefault();
        if ($("#frame5").hasClass("documentoInactivo")) {
            $("#frame5").removeClass("documentoInactivo");
            $("#frame5").addClass("documentoActivo");
        } else if ($("#frame5").hasClass("documentoActivo")) {
            $("#frame5").removeClass("documentoActivo");
            $("#frame5").addClass("documentoInactivo");
        }
    });
    $("#btn6").click(function (e) {
        e.preventDefault();
        if ($("#frame6").hasClass("documentoInactivo")) {
            $("#frame6").removeClass("documentoInactivo");
            $("#frame6").addClass("documentoActivo");
        } else if ($("#frame6").hasClass("documentoActivo")) {
            $("#frame6").removeClass("documentoActivo");
            $("#frame6").addClass("documentoInactivo");
        }

    });

    //mostrar y ocultar tabla 
    $("#tablaUser").addClass("listaInactivo");
    $("#listUsuarios").click(function (e) {
        e.preventDefault();
        if ($("#tablaUser").hasClass("listaInactivo")) {
            $("#tablaUser").removeClass("listaInactivo");
            $("#tablaUser").addClass("listaActivo");
        } else if ($("#tablaUser").hasClass("listaActivo")) {
            $("#tablaUser").removeClass("listaActivo");
            $("#tablaUser").addClass("listaInactivo");
        }
    });

});


/* 
 
 $("#elmentosMunu li").click(function(){
 $("li").removeClass("active");
 $(this).addClass("active");
 });
 */