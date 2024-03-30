window.addEventListener("load", function () {
	//Al cargar la pagina buscamos y obtenemos el formulario donde estarán
	//los datos que el usuario cargará del nuevo odontólogo
	const formulario = document.querySelector("#agregarTurno");

	//Ante un submit del formulario se ejecutará la siguiente funcion
	formulario.addEventListener("submit", function (event) {
		event.preventDefault();

		//creamos un JSON que tendrá los datos del nuevo odontólogo
		const formData = {
			paciente_Id: document.querySelector("#comboTurnoPaciente").value,

			odontologo_Id: document.querySelector("#comboTurnoOdontologo").value,
			fechaYHora: document.querySelector("#turnoFecha").value,
		};

		//invocamos utilizando la función fetch la API odontólogos con el método POST que guardará
		//el odontólogo que enviaremos en formato JSON
		const url = "/turnos/registrar";
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
				let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong>Turno agregado </strong> </div>";

				document.querySelector("#responseTurno").innerHTML = successAlert;
				document.querySelector("#responseTurno").style.display = "block";
				setTimeout(function () {
					$("#staticBackdropTurno").modal("hide");
					resetUploadForm();
				}, 1000);
				location.reload();
			})
			.catch((error) => {
				//Si hay algun error se muestra un mensaje diciendo que el odontólogo
				//no se pudo guardar y se intente nuevamente
				let errorAlert = '<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong> Error intente nuevamente</strong> </div>";

				document.querySelector("#responseTurno").innerHTML = errorAlert;
				document.querySelector("#responseTurno").style.display = "block";
				//se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
				resetUploadForm();
			});
	});

	function resetUploadForm() {
		document.querySelector("#comboTurnoPaciente").value = "1";
		document.querySelector("#comboTurnoOdontologo").value = "1";
		document.querySelector("#turnoFecha").value = "";
	}
});
