window.addEventListener("load", function () {
	let formularioODIndex = document.getElementById("agregarDentista");
	let formularioODLista = document.getElementById("agregarDentistaLista");

	if (formularioODIndex !== null) {
		formularioODIndex.addEventListener("submit", function (event) {
			event.preventDefault();
			formularioInsertar(event, formularioODIndex);
		});
	}
	if (formularioODLista !== null) {
		formularioODLista.addEventListener("submit", function (event) {
			event.preventDefault();
			formularioInsertar(event, formularioODLista);
		});
	}
});

function resetUploadForm() {
	document.querySelector("#nombreOdontologo").value = "";
	document.querySelector("#apellidoOdontologo").value = "";
	document.querySelector("#matriculaOdontologo").value = "";
}
function formularioInsertar(event, form) {
	

	let bandera = validarCamposIngresados(form, '.responseDentist');

	if (bandera) {
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
				console.log(data);
				//Si no hay ningun error se muestra un mensaje diciendo que el odontólogo
				//se agrego bien
				let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong>Odontólogo agregado </strong> </div>";

				document.querySelector(".responseDentist").innerHTML += successAlert;
				document.querySelector(".responseDentist").style.display = "block";

				setTimeout(function () {
					$("#staticBackdropOdontologo").modal("hide");
					resetUploadForm();
				}, 1000);
				location.reload();
			})
			.catch((error) => {
				//Si hay algun error se muestra un mensaje diciendo que el odontólogo
				//no se pudo guardar y se intente nuevamente

				let errorAlert = '<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong> Error intente nuevamente</strong> </div>";

				document.querySelector(".responseDentist").innerHTML = errorAlert;
				document.querySelector(".responseDentist").style.display = "block";
				//se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
				resetUploadForm();
			});
	}
}
