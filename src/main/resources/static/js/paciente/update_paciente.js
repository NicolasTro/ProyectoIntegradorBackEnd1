function actualizarPaciente(listaBtnModificarRegistros) {
	listaBtnModificarRegistros.forEach(botonModificar => {
		//POR CADA BOTON MODIFICAR, SE OBTIENEN LOS DATOS QUE VIENEN DENTRO DE LAS VARIABLES DATA
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
		//AGREGAMOS UN EVENT LISTENER POR CADA BOTON
		botonModificar.addEventListener("click", function (e) {
			$("#staticBackdropPacienteUpdate").modal("show");
			let inputNumericoAValidar = document.querySelector("#domicilioNumeroUpdate");

			validarInputNumerico(inputNumericoAValidar);
			//AGREGA LOS DATOS A LOS CAMPOS DEL MODAL DE MODIFICAR
			document.querySelector("#nombrePacienteUpdate").value = nombre;
			document.querySelector("#apellidoPacienteUpdate").value = apellido;
			document.querySelector("#dniUpdate").value = dni;
			document.querySelector("#fechaIngresoUpdate").value = fechaIngreso;
			document.querySelector("#domicilioCalleUpdate").value = calle;
			document.querySelector("#domicilioNumeroUpdate").value = numero;
			document.querySelector("#domicilioLocalidadUpdate").value = localidad;
			document.querySelector("#domicilioProvinciaUpdate").value = provincia;

			let formulario = document.querySelector("#updatePaciente");

			if (formulario !== null) {
				formulario.addEventListener("submit", function (event) {
					event.preventDefault();
					//VALIDA CAMPOS CON LA FUNCION VALIDAR CAMPOS INGRESADOS QUE RECIBE FORMULARIO Y ELEMENTO PARA MOSTRAR MENSAJES
					let bandera = validarCamposIngresados(formulario, "#responsePatientUpdate");

					if (bandera) {
						const formData = {
							id,
							nombre: document.querySelector("#nombrePacienteUpdate").value.trim(),
							apellido: document.querySelector("#apellidoPacienteUpdate").value.trim(),
							dni: document.querySelector("#dniUpdate").value.trim(),
							fechaIngreso: document.querySelector("#fechaIngresoUpdate").value,
							domicilio_id: domicilio_id,
							calle: document.querySelector("#domicilioCalleUpdate").value.trim(),
							numero: document.querySelector("#domicilioNumeroUpdate").value.trim(),
							localidad: document.querySelector("#domicilioLocalidadUpdate").value.trim(),
							provincia: document.querySelector("#domicilioProvinciaUpdate").value.trim(),
						};

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
								let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Paciente Actualizado </div>";
								document.querySelector("#responsePatientUpdate").innerHTML = successAlert;
								document.querySelector("#responsePatientUpdate").style.display = "block";
								//SI LA PROMESA ES CORRECTA CIERRA MODAL, RESETEA CAMPOS, Y RECARGA LA PAGINA
								setTimeout(function () {
									$("#staticBackdropPacienteUpdate").modal("hide");
									resetUploadForm();
								}, 1000);
								location.reload();
							})
							.catch(error => {
								let errorAlert = "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";
								document.querySelector("#responsePatientUpdate").innerHTML = errorAlert;
								document.querySelector("#responsePatientUpdate").style.display = "block";
							});
					}
				});
			}
		});
	});
}
