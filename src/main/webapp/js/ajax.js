$(document).ready(function () {
    //metodo para enviar el documento de AM
    $("#enviarDoc").click(function (e) {
        e.preventDefault();

        var txtFechaInicio = $("#txtFechaInicio").val();
        var archivo = document.getElementById("fileSubirDoc").files;
        if (txtFechaInicio !== "") {
            if (archivo.length === 1) {
                var nombreArchivo = archivo[0].name;
                var ext = nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1).toLowerCase();
                if (ext === 'pdf') {
                    var dataForm = new FormData($("#formEnviaDoc")[0]);
                    $.ajax({
                        type: "POST",
                        url: "../ControladorProcesos",
                        data: dataForm,
                        contentType: false,
                        processData: false,
                        success: function (dataForm) {
                            $("#tituloModal").html("ÉXITO!");
                            $("#contenidoModal").html("<strong style='color: green;'>" + dataForm + "</strong>");
                            $("#errorModal").modal('show');
                            $("#exitModal").click(function () {
                                location.reload();
                            });

                        }
                    });
                } else {
                    $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
                    $("#contenidoModal").html("EL ARCHIVO TIENE UN FORMATO INVALIDO");
                    $("#errorModal").modal('show');
                }
            } else {
                $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
                $("#contenidoModal").html("DEBE DE SELECCIONAR UN ARCHIVO PDF");
                $("#errorModal").modal('show');
                $("#exitModal").click(function () {
                    $("#fileSubirDoc").focus();
                });
            }
        } else {
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBE INGRESAR LA FECHA DE INICIO");
            $("#errorModal").modal('show');
            $("#exitModal").click(function () {
                $("#txtFechaInicio").focus();
            });

        }

    });
    //Termina el meto para enviar el documento de AM

    //Enpieza el metodo para enviar el cambio de domicilio
    $("#enviarCD").click(function (e) {
        e.preventDefault();
        var txtCambioDireccion = $("#txtCambioDireccion").val();
        var archivo = document.getElementById("archivoDomicilio").files;
        if (txtCambioDireccion !== '') {
            if (archivo.length === 1) {
                var nombreArchivo = archivo[0].name;
                var ext = nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1).toLowerCase();
                if (ext === 'pdf') {
                    var dataForm = new FormData($("#formsubirDatosCD")[0]);
                    $.ajax({
                        type: "POST",
                        url: "ControladorCD",
                        data: dataForm,
                        contentType: false,
                        processData: false,
                        success: function (dataForm) {
                            $("#tituloModal").html("ÉXITO!");
                            $("#contenidoModal").html("<strong style='color: green;'>" + dataForm + "</strong>");
                            $("#errorModal").modal('show');
                            $("#exitModal").click(function () {
                                location.reload();
                            });

                        }
                    });
                } else {
                    $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
                    $("#contenidoModal").html("EL ARCHIVO TIENE UN FORMATO INVALIDO");
                    $("#errorModal").modal('show');
                }
            } else {
                $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
                $("#contenidoModal").html("DEBES DE SELECCIONAR UN ARCHIVO");
                $("#errorModal").modal('show');
            }
        } else {
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("EL CAMPO DIRECCIÓN DEBE DE ESTAR LLENO");
            $("#errorModal").modal('show');
            $("#exitModal").click(function () {
                $("#txtCambioDireccion").focus();
            });
        }
    });
    //Empieza el metodo para enviar cambio de responsable
    $("#enviarCR").click(function (e) {
        e.preventDefault();
        var txtResponsable = $("#txtResponsable").val();
        var archivo = document.getElementById("archivoResponsable").files;
        if (txtResponsable !== '') {
            if (archivo.length === 1) {
                var nombreArchivo = archivo[0].name;
                var ext = nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1).toLowerCase();
                if (ext === 'pdf') {
                    var dataForm = new FormData($("#formsubirDatosCR")[0]);
                    $.ajax({
                        type: "POST",
                        url: "ControladorCR",
                        data: dataForm,
                        contentType: false,
                        processData: false,
                        success: function (dataForm) {
                            $("#tituloModal").html("ÉXITO!");
                            $("#contenidoModal").html("<strong style='color: green;'>" + dataForm + "</strong>");
                            $("#errorModal").modal('show');
                            $("#exitModal").click(function () {
                                location.reload();
                            });

                        }
                    });
                } else {
                    $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
                    $("#contenidoModal").html("EL ARCHIVO TIENE UN FORMATO INVALIDO");
                    $("#errorModal").modal('show');
                }
            } else {
                $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
                $("#contenidoModal").html("DEBES DE SELECCIONAR UN ARCHIVO");
                $("#errorModal").modal('show');
            }
        } else {
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("EL CAMPO NOMBRE DEL RESPONSABLE DEBE DE ESTAR LLENO");
            $("#errorModal").modal('show');
            $("#exitModal").click(function () {
                $("#txtResponsable").focus();
            });
        }
    });


    //Metodo que valida si el campo id esta vacio
    $("#enviarId").click(function (e) {

        var id = $("#idEstancia").val();

        if (id === "") {
            e.preventDefault();
            $("#contenidoModal").html("DEBE INGRESAR EL ID DE LA ESTANCIA");
            $("#errorModal").modal('show');
        } else if (!$.isNumeric(id)) {
            e.preventDefault();
            $("#contenidoModal").html("EL CAMPO ID TIENE UN VALOR INVALIDO");
            $("#errorModal").modal('show');

        }
        $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
        $("#exitModal").click(function () {
            $("#idEstancia").focus();
        });

    });
    //Termina el metodo validar id


    //Metodo que valida y envia la revision del documento de AM
    $("#btnEnviarRev").click(function (e) {

        e.preventDefault();

        var proceso = $("#proceso").val();

        var selectNombreEstancia = $("#selectNombreEstancia").val();
        var selectNombreRespon = $("#selectNombreRespon").val();
        var selectDireccion = $("#selectDireccion").val();
        var selectEstado = $("#selectEstado").val();
        var selectFechaInicio = $("#selectFechaInicio").val();
        var selectFechaFin = $("#selectFechaFin").val();
        var txtUsuarioRev = $("#txtUsuarioRev").val();

        var selectEstatus = $("#selectEstatus").val();
        var comentariosAm = $("#comentariosAm").val();

        if (selectNombreEstancia === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL NOMBRE DE LA ESTANCIA");
            $("#exitModal").click(function () {
                $("#selectNombreEstancia").focus();
            });
        } else if (selectNombreRespon === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL NOMBRE DE LA RESPONSABLE");
            $("#exitModal").click(function () {
                $("#selectNombreRespon").focus();
            });
        } else if (selectDireccion === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR LA DIRECCIÓN");
            $("#exitModal").click(function () {
                $("#selectDireccion").focus();
            });
        } else if (selectEstado === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL ESTADO");
            $("#exitModal").click(function () {
                $("#selectEstado").focus();
            });
        } else if (selectFechaInicio === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR LA FECHA DE INICIO");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectFechaInicio === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR LA FECHA DE INICIO");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectEstatus === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES SELECCIONAR EL ESTATUS");
            $("#exitModal").click(function () {
                $("#selectFechaFin").focus();
            });
        } else {
            $.post("ControladorProcesos",
                    {//etiqueta name--------//etiqueta id
                        proceso: proceso,
                        selectNombreEstancia: selectNombreEstancia,
                        selectNombreRespon: selectNombreRespon,
                        selectDireccion: selectDireccion,
                        selectEstado: selectEstado,
                        selectFechaInicio: selectFechaInicio,
                        selectFechaFin: selectFechaFin,
                        selectEstatus: selectEstatus,
                        comentariosAm: comentariosAm,
                        txtUsuarioRev: txtUsuarioRev
                    },
                    function (data) {
                        $("#tituloModal").html("<p style='color: green'>ÉXITO!</P>");
                        $("#contenidoModal").html("<strong style='color: green;'>" + data + "</strong>");
                        $("#errorModal").modal('show');
                        $("#exitModal").click(function () {
                            location.reload();
                        });
                    });
        }

    });
    //Termina la validacion y envio de la revision am

    //Metodo que valida y envia la revisión del documento CD
    $("#btnEnviarRevDomicilio").click(function (e) {
        e.preventDefault();

        var proceso = $("#proceso").val();

        var selectDoc1 = $("#selectDoc1").val();
        var selectDoc2 = $("#selectDoc2").val();
        var selectDoc3 = $("#selectDoc3").val();
        var selectDoc4 = $("#selectDoc4").val();
        var selectDoc5 = $("#selectDoc5").val();
        var selectDoc6 = $("#selectDoc6").val();
        var estatus = $("#estatus").val();

        var txtAreacomentarios = $("#txtAreacomentarios").val();
        var txtUsuarioRev = $("#txtUsuarioRev").val();

        if (selectDoc1 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 1");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectDoc2 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 2");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectDoc3 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 3");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectDoc4 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 4");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectDoc5 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 5");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectDoc6 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 6");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (estatus === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES ASIGNAR EL ESTATUS");
            $("#exitModal").click(function () {
                $("#estatus").focus();
            });
        } else {
            $.post("ControladorCD",
                    {//etiqueta name--------//etiqueta id
                        proceso: proceso,
                        selectDoc1: selectDoc1,
                        selectDoc2: selectDoc2,
                        selectDoc3: selectDoc3,
                        selectDoc4: selectDoc4,
                        selectDoc5: selectDoc5,
                        selectDoc6: selectDoc6,
                        txtAreacomentarios: txtAreacomentarios,
                        estatus: estatus,
                        txtUsuarioRev: txtUsuarioRev
                    },
                    function (data) {
                        $("#tituloModal").html("<p style='color: green'>ÉXITO!</P>");
                        $("#contenidoModal").html("<strong style='color: green;'>" + data + "</strong>");
                        $("#errorModal").modal('show');
                        $("#exitModal").click(function () {
                            location.reload();
                        });
                    });
        }



    });

    //Metodo que valida y envia la revisión del documento CR
    $("#btnEnviarRevResponsable").click(function (e) {
        e.preventDefault();

        var proceso = $("#proceso").val();

        var selectDoc1 = $("#selectDoc1").val();
        var selectDoc2 = $("#selectDoc2").val();
        var selectDoc3 = $("#selectDoc3").val();
        var estatus = $("#estatus").val();

        var txtAreacomentarios = $("#txtAreacomentarios").val();
        var txtUsuarioRev = $("#txtUsuarioRev").val();

        if (selectDoc1 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 1");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectDoc2 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 2");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (selectDoc3 === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES EVALUAR EL DOCUMENTO 3");
            $("#exitModal").click(function () {
                $("#selectFechaInicio").focus();
            });
        } else if (estatus === '') {
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES ASIGNAR EL ESTATUS");
            $("#exitModal").click(function () {
                $("#estatus").focus();
            });
        } else {
            $.post("ControladorCR",
                    {//etiqueta name--------//etiqueta id
                        proceso: proceso,
                        selectDoc1: selectDoc1,
                        selectDoc2: selectDoc2,
                        selectDoc3: selectDoc3,
                        txtAreacomentarios: txtAreacomentarios,
                        estatus: estatus,
                        txtUsuarioRev: txtUsuarioRev
                    },
                    function (data) {
                        $("#tituloModal").html("<p style='color: green'>ÉXITO!</P>");
                        $("#contenidoModal").html("<strong style='color: green;'>" + data + "</strong>");
                        $("#errorModal").modal('show');
                        $("#exitModal").click(function () {
                            location.reload();
                        });
                    });
        }
    });

    //Agrgar UsuariO
    $("#agregarUsuario").click(function (e) {

        e.preventDefault();

        var txtUsuario = $("#txtUsuario").val();
        var txtContra = $("#txtContra").val();
        var selectEstado = $("#selectEstado").val();
        var selectPrivilegio = $("#selectPrivilegio").val();
        var proceso = $("#proceso").val();

        if (txtUsuario === '') {

            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBE INGRESAR EL USUARIO DE LA ESTANCIA");
            $("#errorModal").modal('show');
            $("#exitModal").click(function () {
                $("#txtUsuario").focus();
            });
        } else if (txtContra === '') {

            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBE LA CONTRASEÑA DEL USUARIO ");
            $("#errorModal").modal('show');
            $("#exitModal").click(function () {
                $("#txtContra").focus();
            });
        } else if (selectEstado === '') {

            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBE SELECCIONAR EL ESTADO DEL USUARIO ");
            $("#errorModal").modal('show');
            $("#exitModal").click(function () {
                $("#selectEstado").focus();
            });
        } else if (selectPrivilegio === '') {

            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBE SELECCIONAR EL PRIVILEGIO DEL USUARIO ");
            $("#errorModal").modal('show');
            $("#exitModal").click(function () {
                $("#selectPrivilegio").focus();
            });
        } else {
            $.post("ControladorUsuario",
                    {//etiqueta name--------//etiqueta id
                        proceso: proceso,
                        txtUsuario: txtUsuario,
                        txtContra: txtContra,
                        selectEstado: selectEstado,
                        selectPrivilegio: selectPrivilegio
                    },
                    function (data) {
                        $("#tituloModal").html("<p style='color: green'>ÉXITO!</P>");
                        $("#contenidoModal").html("<strong style='color: green;'>" + data + "</strong>");
                        $("#errorModal").modal('show');
                        $("#exitModal").click(function () {
                            location.reload();
                        });
                    });
        }
    });

    //Metodo pata validar el campo id del listado de la estancias 
    $("#buscarListado").click(function (e) {

        var btnBuscarRev = $("#btnBuscarRev").val();
        var proceso = $("#proceso").val();
        var estado = $("#estado").val();

        if (btnBuscarRev === '') {
            e.preventDefault();
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("DEBES AGREGAR EL ID DE LA ESTANCIA");
            $("#exitModal").click(function () {
                $("#btnBuscarRev").focus();
            });
        } else if (!$.isNumeric(btnBuscarRev)) {
            e.preventDefault();
            $("#errorModal").modal('show');
            $("#tituloModal").html("<p style='color: tomato'>UPS!!!</p>");
            $("#contenidoModal").html("EL CAMPO ID TIENE UN VALOR INVALIDO");
            $("#exitModal").click(function () {
                $("#btnBuscarRev").focus();
            });
        }

    });
    //Termina el metodo para validar el campo od del listado

    //Metodo para verificar si los campos id y contraseña estan vacios  
    $("#btnIniciar").click(function (e) {

        var txtusuario = $("#txtusuario").val();
        var txtcontrasenia = $("#txtcontrasenia").val();

        if (txtusuario === '' || txtcontrasenia === '') {
            e.preventDefault();
            $("#error").html("<br><strong>EL CAMPO USUARIO Y/O CRONTRASEÑA ESTAN VACIOS</strong>");
        } else {
            $("#error").html("");
        }
    });

    $("#errorModal2").modal('show');
    $("#volverListado").click(function () {
        window.history.back();
    });
    //RECARGA LA PAGINA LISTADO CD

    //Termina la verificacion   
    $("#visualizar").click(function () {
        $("#visualizarDocs").modal("show");
    });

    //Mostra el modal usuario
    $("#modalUsuario").modal("show");
    $("#salirUsuario").click(function (e) {
        location.href = "/ControladorUsuario?proceso=listarUsuario";
    });

});
