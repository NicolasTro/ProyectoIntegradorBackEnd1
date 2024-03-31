window.addEventListener("load", function () {
	const formulario = document.querySelector("#agregarPaciente");
	
	let inputNumericoAValidar = document.querySelector("#domicilioNumero");
	
	validarInputNumerico(inputNumericoAValidar);
	formulario.addEventListener("submit", function (event) {
		event.preventDefault();



// validarFecha();



		let bandera = validarCamposIngresados(formulario, "#responsePatient");

		if (bandera) {
			const formData = {
				nombre: document.querySelector("#nombrePaciente").value,
				apellido: document.querySelector("#apellidoPaciente").value,
				dni: document.querySelector("#dni").value,
				fechaIngreso: document.querySelector("#fechaIngreso").value,
				calle: document.querySelector("#domicilioCalle").value,
				numero: document.querySelector("#domicilioNumero").value,
				localidad: document.querySelector("#domicilioLocalidad").value,
				provincia: document.querySelector("#domicilioProvincia").value,
			};

			const url = "/pacientes/registrar";

			const settings = {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(formData),
			};

			fetch(url, settings)
				.then(response => response.json())
				.then(data => {
					let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong> Paciente agregado </strong> </div>";

					document.querySelector("#responsePatient").innerHTML = successAlert;
					document.querySelector("#responsePatient").style.display = "block";


          setTimeout(function () {
            $("#staticBackdropPaciente").modal("hide");
            resetUploadForm();
          }, 1000);
          location.reload();


					
				})
				.catch(error => {
					let errorAlert = '<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong> Error intente nuevamente</strong> </div>";

					document.querySelector("#responsePatient").innerHTML = errorAlert;
					document.querySelector("#responsePatient").style.display = "block";
					resetUploadForm();
				});
		}
	});

});
