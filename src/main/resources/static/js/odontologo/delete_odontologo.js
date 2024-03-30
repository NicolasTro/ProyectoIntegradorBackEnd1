function eliminarOdontologo(listaBtnEliminar) {
	listaBtnEliminar.forEach(botonEliminar => {
		let idBtn;
		idBtn = botonEliminar.dataset.id;

		botonEliminar.addEventListener("click", function () {
			$("#staticBackdropOdontologoDelete").modal("show");

			let btnConfirmarEliminar = document.querySelector("#confirmarOdontologoDelete");

			btnConfirmarEliminar.addEventListener("click", function (event) {
				event.preventDefault();

				const url = "/odontologos/eliminar/" + idBtn;

				const settings = {
					method: "DELETE",
					headers: {
						"Content-Type": "application/json",
					},
				};

				fetch(url, settings)
					.then(response => {
						$("#staticBackdropOdontologoDelete").modal("hide");
						if (!response.ok) {
							return response.text().then(text => Promise.reject(text));
						} else {
							response.json();
						}
					})
					.then(data => {
						
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
