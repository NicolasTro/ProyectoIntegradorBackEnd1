function eliminarOdontologo(listaBtnEliminar) {
	listaBtnEliminar.forEach(botonEliminar => {
		//POR CADA BOTON PEDIMOS EL ID DEL ODONTOLOGO A ELIMINAR
		let idBtn;
		idBtn = botonEliminar.dataset.id;
		//AGREGAMOS UN EVENTLISTENER A CADA BOTON ELIMINAR
		botonEliminar.addEventListener("click", function () {
			//ABRIMOS MODAL CON JQUERY
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
							//RECHAZA LA PROMESA Y LA ENVIA AL CATCH PARA PODER MOSTRAR MENSAJE ALERT
							return response.text().then(text => Promise.reject(text));
						} else {
							$("#staticBackdropOdontologoDelete").modal("hide");
						}
					})
					.then(data => {
						setTimeout(function () {
							alert("Odontologo eliminado correctamente");
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
