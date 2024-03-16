// window.addEventListener("load", function () {
//   let btnMenuAgregarOdontologo = document.querySelector(".registroOd");
//   let btnGuardarOdontologo = document.getElementById("btn-add-new-dentist");

  
//   btnMenuAgregarOdontologo.addEventListener("click", function (e) {
//     let registrarOdontologos = document.getElementById("registrarOdontologos");
//     let imagenFondo = document.querySelector(".imagenPrincipal")
//     imagenFondo.classList.toggle("blur");



//     registrarOdontologos.classList.toggle("ocultarRegistroOdontologos");



//   });


//   btnGuardarOdontologo.addEventListener('submit', function(event){

//     event.preventDefault();

//         //creamos un JSON que tendrá los datos del nuevo odontólogo
//          const formData = {
//              nombre: document.querySelector('#nombre').value,
//              apellido: document.querySelector('#apellido').value,
//              matricula: document.querySelector('#matricula').value,
 
//          };
//          //invocamos utilizando la función fetch la API odontólogos con el método POST que guardará
//          //el odontólogo que enviaremos en formato JSON
//          const url = '/odontologos/registrar';
//          const settings = {
//              method: 'POST',
//              headers: {
//                  'Content-Type': 'application/json',
//              },
//              body: JSON.stringify(formData)
//          }
 
//          fetch(url, settings)
//              .then(response => response.json())
//              .then(data => {
//                   //Si no hay ningun error se muestra un mensaje diciendo que el odontólogo
//                   //se agrego bien
//                   let successAlert = '<div class="alert alert-success alert-dismissible">' +
//                       '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
//                       '<strong></strong> Odontólogo agregado </div>'
 
//                   document.querySelector('#response').innerHTML = successAlert;
//                   document.querySelector('#response').style.display = "block";
//                   resetUploadForm();
 
//              })
//              .catch(error => {
//                      //Si hay algun error se muestra un mensaje diciendo que el odontólogo
//                      //no se pudo guardar y se intente nuevamente
//                      let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
//                                       '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
//                                       '<strong> Error intente nuevamente</strong> </div>'
 
//                        document.querySelector('#response').innerHTML = errorAlert;
//                        document.querySelector('#response').style.display = "block";
//                       //se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
//                       resetUploadForm();})
//      });
 
 
//      function resetUploadForm(){
//          document.querySelector('#nombre').value = "";
//          document.querySelector('#apellido').value = "";
//          document.querySelector('#matricula').value = "";
 
//      }
 
//      (function(){
//          let pathname = window.location.pathname;
//          if(pathname === "/"){
//              document.querySelector(".nav .nav-item a:first").addClass("active");
//          } else if (pathname == "/odontologoLista.html") {
//              document.querySelector(".nav .nav-item a:last").addClass("active");
//          }
//      })();





// });
