function modificarTurno(listaBtnModificar) {
	listaBtnModificar.forEach(botonModificar => {
		listarOdontologos("#comboTurnoOdontologoUpdate");
		listarPacientes("#comboTurnoPacienteUpdate");

		let idTurno = botonModificar.dataset.id;
		let paciente_Id = botonModificar.dataset.pacienteid;
		let odontologo_Id = botonModificar.dataset.odontologoid;
		let fechaYHora = botonModificar.dataset.fechayhora;

		console.log(idTurno);
		console.log(paciente_Id);
		console.log(odontologo_Id);
		console.log(fechaYHora);

		botonModificar.addEventListener("click", function () {
			$("#staticBackdropTurnoUpdate").modal("show");

			document.querySelector("#comboTurnoPacienteUpdate").value = paciente_Id;
			document.querySelector("#comboTurnoOdontologoUpdate").value = odontologo_Id;
			document.querySelector("#fechaUpdate").value = fechaYHora;

			let formulario = document.querySelector("#formTurnoUpdate");
			if (formulario !== null) {
				formulario.addEventListener("submit", function (event) {
					event.preventDefault();

					const formData = {
						id: idTurno,
						paciente_Id: document.querySelector("#comboTurnoPacienteUpdate").value,

						odontologo_Id: document.querySelector("#comboTurnoOdontologoUpdate").value,
						fechaYHora: document.querySelector("#fechaUpdate").value,
					};

					const url = "/turnos/actualizar";
					const settings = {
						method: "PUT",
						headers: {
							"Content-Type": "application/json",
						},
						body: JSON.stringify(formData),
					};
					console.log(formData);

					fetch(url, settings)
						.then(response => response.json())
						.then(data => {
							console.log(data);
							let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Turno Actualizado </div>";

							document.querySelector("#responseTurnoUpdate").innerHTML = successAlert;
							document.querySelector("#responseTurnoUpdate").style.display = "block";

							setTimeout(function () {
								$("#staticBackdropTurnoUpdate").modal("hide");
								// resetUploadForm();
							}, 1000);
							location.reload();
						})
						.catch(error => {
							let errorAlert = "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";

							document.querySelector("#responseTurnoUpdate").innerHTML = errorAlert;
							document.querySelector("#responseTurnoUpdate").style.display = "block";
						});
				});
			}
		});
	});
}
