
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--Mostrar errores y Envios -->
<div class="modal" id="errorModal" tabindex="-1" role="dialog" style="text-align: center;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="tituloModal" ></h4>
            </div>
            <div class="modal-body">
                <p><strong id="contenidoModal"></strong></p>
            </div>
            <div class="modal-footer">   
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="exitModal">Salir</button>
            </div>
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="../datePicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="../js/main.js"></script>    
<script src="../js/ajax.js"></script>