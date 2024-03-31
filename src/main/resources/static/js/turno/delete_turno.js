function eliminarTurno(listaBtnEliminar) {
	listaBtnEliminar.forEach((botonEliminar) => {
		let idBtn;
		idBtn = botonEliminar.dataset.id;

		botonEliminar.addEventListener("click", function () {
			$("#staticBackdropTurnoDelete").modal("show");

			let btnConfirmarEliminar = document.querySelector("#confirmarTurnoDelete");

			btnConfirmarEliminar.addEventListener("click", function (event) {
				event.preventDefault();

				const url = "/turnos/eliminar/" + idBtn;

				const settings = {
					method: "DELETE",
					headers: {
						"Content-Type": "application/json",
					},
				};

				fetch(url, settings)
					.then((response) => response.json())
					.then((data) => {
						
						let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Turno Eliminado </div>";

						document.querySelector("#responseTurno").innerHTML = successAlert;
						document.querySelector("#responseTurno").style.display = "block";
					})
					.catch((error) => {
						let errorAlert = "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";
						document.querySelector("#responseTurno").innerHTML = errorAlert;
						document.querySelector("#responseTurno").style.display = "block";
					});
				location.reload();
				$("#staticBackdropTurnoDelete").modal("hide");
			});
		});
	});
}
