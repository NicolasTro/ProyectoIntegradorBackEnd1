window.addEventListener("load", function () {

  const formulario = document.querySelector("#agregarPaciente");  
  formulario.addEventListener("submit", function (event) {
    
  event.preventDefault();

  const formData = {
    nombre: document.querySelector("#nombrePaciente").value,
    apellido: document.querySelector("#apellidoPaciente").value,
    dni: document.querySelector("#dni").value,
    fechaIngreso: document.querySelector("#fechaIngreso").value,
    domicilio : {
        calle: document.querySelector("#domicilioCalle").value,
        numero: document.querySelector("#domicilioNumero").value,
        localidad: document.querySelector("#domicilioLocalidad").value,
        provincia: document.querySelector("#domicilioProvincia").value,
    },
  };

  const url = "/pacientes/registrar";
  
  const settings = {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(formData),
  };

  fetch(url, settings)
    .then((response)  => response.json())
    .then((data) => {
    
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Paciente agregado </strong> </div>";

        document.querySelector("#responsePatient").innerHTML = successAlert;
        document.querySelector("#responsePatient").style.display = "block";
        resetUploadForm();
    })
    .catch((error) => {
    
        let errorAlert =
          '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Error intente nuevamente</strong> </div>";

        document.querySelector("#responsePatient").innerHTML = errorAlert;
        document.querySelector("#responsePatient").style.display = "block";
        resetUploadForm();
    });
  });

    function resetUploadForm() {
        document.querySelector("#nombrePaciente").value = "";
        document.querySelector("#apellidoPaciente").value = "";
        document.querySelector("#dni").value = "";
        document.querySelector("#fechaIngreso").value = "";
        document.querySelector("#domicilioCalle").value = "";
        document.querySelector("#domicilioNumero").value = "";
        document.querySelector("#domicilioLocalidad").value = "";
        document.querySelector("#domicilioProvincia").value = "";
    }
});