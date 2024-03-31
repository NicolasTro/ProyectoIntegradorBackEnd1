function eliminarTurno(listaBtnEliminar) {
	listaBtnEliminar.forEach(botonEliminar => {
		//POR CADA TURNO OBTENEMOS EL ID DEL TURNO A ELIMINAR
		let idBtn;
		idBtn = botonEliminar.dataset.id;

		botonEliminar.addEventListener("click", function () {
			$("#staticBackdropTurnoDelete").modal("show");
			//MOSTRAMOS MODAL Y LE ASIGNAMOS UN EVENT AL BOTON CONFIRMAR ELIMINACION
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
					.then(response => {
						//CERRAMOS MODAL
						$("#staticBackdropTurnoDelete").modal("hide");
						if (!response.ok) {
							//SI RESPONSE NO ES OK, RECHAZAMOS LA PROMESA Y LA ATRAPAMOS EN EL CATCH PARA MOSTRAR MENSAJE DE ERROR
							return response.text().then(text => Promise.reject(text));
						}
					})
					.then(data => {
						//MOSTRAMOS MENSAJE DE ELIMINACION CORRECTA
						setTimeout(function () {
							alert("Turno eliminado correctamente");
							//RECARGAMOS PAGINA
							location.reload();
						}, 1000);
					})
					.catch(error => {
						//MOSTRAMOS MENSAJE DE ERROR Y RECARGAMOS PAGINA
						alert(error);
						setTimeout(function () {
							location.reload();
						}, 1000);
					});
			});
		});
	});
}
