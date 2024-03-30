function actualizarPaciente(listaBtnModificarRegistros) {
	listaBtnModificarRegistros.forEach(botonModificar => {


		// console.log(botonModificar);
		let id = botonModificar.dataset.id;
		let nombre = botonModificar.dataset.nombre;
		let apellido = botonModificar.dataset.apellido;
		let dni = botonModificar.dataset.dni;
		let fechaIngreso = botonModificar.dataset.fechaingreso;
		let calle = botonModificar.dataset.calle;
		let numero = botonModificar.dataset.numero;
		let localidad = botonModificar.dataset.localidad;
		let provincia = botonModificar.dataset.provincia;
		let domicilio_id = botonModificar.dataset.domicilioid;
		// console.log("domicilio id "+ botonModificar.dataset.domicilio_id);

		botonModificar.addEventListener("click", function (e) {
			// e.preventDefault();
			$("#staticBackdropPacienteUpdate").modal("show");


console.log(nombre);
console.log(apellido);
console.log(fechaIngreso);

			document.querySelector("#nombrePacienteUpdate").value = nombre;
			document.querySelector("#apellidoPacienteUpdate").value = apellido;
			document.querySelector("#dniUpdate").value = dni;
			document.querySelector("#fechaIngresoUpdate").value = fechaIngreso;
			document.querySelector("#domicilioCalleUpdate").value = calle;
			document.querySelector("#domicilioNumeroUpdate").value = numero;
			document.querySelector("#domicilioLocalidadUpdate").value = localidad;
			document.querySelector("#domicilioProvinciaUpdate").value = provincia;
			// document.querySelector("#domicilioProvinciaUpdate").value = provincia;

			let formulario = document.querySelector("#updatePaciente");
			if (formulario !== null) {
				formulario.addEventListener("submit", function (event) {
					event.preventDefault();

					const formData = {
						id,
						nombre: document.querySelector("#nombrePacienteUpdate").value,
						apellido: document.querySelector("#apellidoPacienteUpdate").value,
						dni: document.querySelector("#dniUpdate").value,
						fechaIngreso: document.querySelector("#fechaIngresoUpdate").value,
						domicilio_id: domicilio_id,
						calle: document.querySelector("#domicilioCalleUpdate").value,
						numero: document.querySelector("#domicilioNumeroUpdate").value,
						localidad: document.querySelector("#domicilioLocalidadUpdate").value,
						provincia: document.querySelector("#domicilioProvinciaUpdate").value,
					};

					console.log(formData);
					const url = "/pacientes/actualizar";
					const settings = {
						method: "PUT",
						headers: {
							"Content-Type": "application/json",
						},
						body: JSON.stringify(formData),
					};

					fetch(url, settings)
						.then(response => response.json())
						.then(data => {
							console.log(data);
							let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Paciente Actualizado </div>";

							document.querySelector("#responsePatientUpdate").innerHTML = successAlert;
							document.querySelector("#responsePatientUpdate").style.display = "block";
						})
						.catch(error => {
							let errorAlert = "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";
							document.querySelector("#responsePatientUpdate").innerHTML = errorAlert;
							document.querySelector("#responsePatientUpdate").style.display = "block";
						});
				});
			}
		});
	});
}
