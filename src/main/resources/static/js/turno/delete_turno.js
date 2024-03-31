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
					.then((response) =>{
						$("#staticBackdropTurnoDelete").modal("hide");
						if (!response.ok) {
							return response.text().then(text => Promise.reject(text));
						}					
						
					})
					.then((data) => {
						setTimeout(function () {
							alert("Turno eliminado correctamente");
							location.reload();
						}, 1000);
						
					})
					.catch((error) => {
						alert(error);
						setTimeout(function () {
							location.reload();
						}, 1000);
					});				
			});
		});
	});
}
