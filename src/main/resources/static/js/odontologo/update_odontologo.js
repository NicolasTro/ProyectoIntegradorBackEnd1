function actualizarOdontologo(listaBtnModificar) {
	listaBtnModificar.forEach(botonModificar => {
		let id = botonModificar.dataset.id;
		let nombre = botonModificar.dataset.nombre;
		let apellido = botonModificar.dataset.apellido;
		let matricula = botonModificar.dataset.matricula;

		botonModificar.addEventListener("click", function (e) {
			
			e.preventDefault();
			$("#staticBackdrop").modal("show");
			
			document.querySelector("#nombreUpdate").value = nombre;
			document.querySelector("#apellidoUpdate").value = apellido;
			document.querySelector("#matriculaUpdate").value = matricula;

			let formulario = document.querySelector("#formOdontologoUpdate");
			if (formulario !== null) {
				formulario.addEventListener("submit", function (event) {
					event.preventDefault();
					




				let bandera = validarCamposIngresados(formulario, ".responseUpdate");
				console.log(bandera);

				if (bandera) {
					const formData = {
						id,
						nombre: document.querySelector("#nombreUpdate").value,
						apellido: document.querySelector("#apellidoUpdate").value,
						matricula: document.querySelector("#matriculaUpdate").value,
					};

					const url = "/odontologos/actualizar";
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
							let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Odontólogo Actualizado </div>";

							document.querySelector(".responseUpdate").innerHTML = successAlert;
							document.querySelector(".responseUpdate").style.display = "block";




							setTimeout(function () {
								$("#staticBackdrop").modal("hide");
								resetUploadForm();
							}, 1000);
							location.reload();
						})
						.catch(error => {
							let errorAlert = "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";

							document.querySelector(".responseUpdate").innerHTML = errorAlert;
							document.querySelector(".responseUpdate").style.display = "block";
						});

					
					
				}
			});
			}
		});
	});
}
