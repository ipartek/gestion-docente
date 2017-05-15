/**
 * 
 */
$(document).ready( function() {
      $('#fichero').on('change', function(e) {
    	  var fileName = e.target.files[0].name;
    	  $("#temario").val(fileName);
      });
      $('#cursoForm').submit(function(e){
    	  var enviar = true;
    	  var fileInput = $('#fichero');
    	  var maxSize = fileInput.data('max-size');
          if(fileInput.get(0).files.length>0){
              var fileSize = fileInput.get(0).files[0].size; // in bytes
              if(fileSize>maxSize){
                  alert('el tamaño del archivo es mayor que ' + maxSize + ' bytes');
                  enviar = false;
              }
          }/*else{
              alert('elija archivo');
              enviar =  false;
          }*/
          return enviar;
      });
      
      
  });