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
						//CERRAMOS MODAL
						$("#staticBackdropPacienteDelete").modal("hide");
						if (!response.ok) {
							//SI RESPUESTA NO ES CORRECTA RECHAZA LA PROMESA Y ENVIA AL CATCH PARA MOSTRAR MENSAJE DE ERROR
							return response.text().then(text => Promise.reject(text));
						}
					})
					.then(data => {
						//SI ESTA CORRECTO, MOSTRAMOS MENSAJE DE ELIMINACION Y RECARGAMOS PAGINA
						setTimeout(function () {
							alert("Paciente eliminado correctamente");
							location.reload();
						}, 1000);
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
