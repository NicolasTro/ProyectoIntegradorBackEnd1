window.addEventListener("load", function () {
  //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
  //los datos que el usuario cargará del nuevo odontólogo
  const formulario = document.querySelector("#agregarDentista");

  
  //Ante un submit del formulario se ejecutará la siguiente funcion
  formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    
    //creamos un JSON que tendrá los datos del nuevo odontólogo
    const formData = {
      nombre: document.querySelector("#nombreOdontologo").value,
      apellido: document.querySelector("#apellidoOdontologo").value,
      matricula: document.querySelector("#matriculaOdontologo").value,
    };

    
    //invocamos utilizando la función fetch la API odontólogos con el método POST que guardará
    //el odontólogo que enviaremos en formato JSON
    const url = "/odontologos/registrar";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        //Si no hay ningun error se muestra un mensaje diciendo que el odontólogo
        //se agrego bien
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Odontólogo agregado </div>";

        document.querySelector("#responseDentist").innerHTML = successAlert;
        document.querySelector("#responseDentist").style.display = "block";
        resetUploadForm();
      })
      .catch((error) => {
        //Si hay algun error se muestra un mensaje diciendo que el odontólogo
        //no se pudo guardar y se intente nuevamente
        let errorAlert =
          '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Error intente nuevamente</strong> </div>";

        document.querySelector("#responseDentist").innerHTML = errorAlert;
        document.querySelector("#responseDentist").style.display = "block";
        //se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
        resetUploadForm();
      });
  });

  function resetUploadForm() {
    document.querySelector("#nombreOdontologo").value = "";
    document.querySelector("#apellidoOdontologo").value = "";
    document.querySelector("#matriculaOdontologo").value = "";
  }




  

//  (function(){
//      let pathname = window.location.pathname;
//      if(pathname === "/"){
//          document.querySelector(".nav .nav-item a:first").addClass("active");
//      } else if (pathname == "/index.html") {
//          document.querySelector(".nav .nav-item a:last").addClass("active");
//      }
//  })();
});
