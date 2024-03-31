function eliminarPaciente(listaBtnEliminar) {
	listaBtnEliminar.forEach(botonEliminar => {
		let idBtn;
		idBtn = botonEliminar.dataset.id;

		botonEliminar.addEventListener("click", function () {
			$("#staticBackdropPacienteDelete").modal("show");

			let btnConfirmarEliminar = document.querySelector("#confirmarPacienteDelete");

			btnConfirmarEliminar.addEventListener("click", function (event) {
				event.preventDefault();

				
				
				const url = "/pacientes/eliminar/" + idBtn;

				
				const settings = {
					method: "DELETE",
					headers: {
						"Content-Type": "application/json",
					},
				};

				fetch(url, settings)
					.then(response => {
						$("#staticBackdropPacienteDelete").modal("hide");
						if (!response.ok) {
							return response.text().then(text => Promise.reject(text));
						} else {
							response.json();
						}
					})
					.then(data => {
						setTimeout(function () {
							location.reload();
						}, 2000);
					})
					.catch(error => {
						alert(error);
						setTimeout(function () {
							location.reload();
						}, 1000);
					});

				
			});
		});
	});
}
