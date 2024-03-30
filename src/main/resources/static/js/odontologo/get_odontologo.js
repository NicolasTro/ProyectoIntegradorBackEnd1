function obtenerListaOdontologos() {
	const url = "/odontologos/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");

				for (dentist of data) {
					dentistRow = body.insertRow();
					let tr_id = "tr_" + dentist.id;
					dentistRow.id = tr_id;
					dentistRow.innerHTML = cargarRegistro(dentist);
				}

				let listaBtnModificar = document.querySelectorAll(".btnTablaModificar");

				
				
				let listaBtnEliminar = document.querySelectorAll(".btnTablaEliminar");

				actualizarOdontologo(listaBtnModificar);
				eliminarOdontologo(listaBtnEliminar);
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch(error => {
			noSeEncontraronRegistros();
		});
}

function busquedaOdontologoPersonalizada() {
	let tipoDeBusqueda = document.getElementById("comboBusqueda");
	let valorBusqueda = document.getElementById("search");

	const url = `/odontologos/buscar?valor=${valorBusqueda.value}&tipoDeBusqueda=${tipoDeBusqueda.value}`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			clearTabla();
			tablaNueva(tituloTabla);

			if (data.length == 1) {
				let tablaBody = document.getElementById("cuerpoTabla");

				tablaBody.innerHTML = "";
				let dentistRow = tablaBody.insertRow();

				let tr_id = "tr_" + data[0].id;

				dentistRow.id = tr_id;
				dentistRow.innerHTML = cargarRegistro(data[0]);
			} else if (data.length > 1) {
				let body = document.getElementById("cuerpoTabla");

				for (dentist of data) {
					dentistRow = body.insertRow();
					let tr_id = "tr_" + dentist.id;
					dentistRow.id = tr_id;
					dentistRow.innerHTML = cargarRegistro(dentist);
				}
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch(error => {
			noSeEncontraronRegistros();
		});
}
