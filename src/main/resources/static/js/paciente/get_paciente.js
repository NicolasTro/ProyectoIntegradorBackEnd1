function obtenerListaPacientes() {
	const url = "/pacientes/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");

				for (patient of data) {
					let patientRow = body.insertRow();
					let tr_id = "tr_" + patient.id;
					patientRow.id = tr_id;
					patientRow.innerHTML = cargarRegistro(patient);
				}

				let listaBtnEliminarRegistros = document.querySelectorAll(".btnTablaEliminar");

				eliminarPaciente(listaBtnEliminarRegistros);
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch((error) => {
			noSeEncontraronRegistros();
		});
}

function busquedaPacientePersonalizado() {
	let tipoDeBusqueda = document.getElementById("comboBusqueda");
	let valorBusqueda;
	if (tipoDeBusqueda.value <= 3) {
		valorBusqueda = document.getElementById("search");
	} else {
		valorBusqueda = document.getElementById("searchCalendar");
	}

	const url = `/pacientes/buscar?valor=${valorBusqueda.value}&tipoDeBusqueda=${tipoDeBusqueda.value}`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())

		.then((data) => {
			
			if (data.length > 0) {
				clearTabla();
				tablaNueva(tituloTablaPacientes);
				let body = document.getElementById("cuerpoTabla");

				for (patient of data) {
					patientRow = body.insertRow();
					let tr_id = "tr_" + patient.id;
					patientRow.id = tr_id;
					patientRow.innerHTML = cargarRegistro(patient);
				}
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch((error) => {
			noSeEncontraronRegistros();
		});
}

let tituloTablaPacientes =
	"<th scope='col'>Id</th>" + "<th scope='col'>Nombre </th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>DNI</th>" + "<th scope='col'>FechaIngreso</th>" + "<th scope='col'>Domicilio</th>" + "<th scope='col'>Gestionar</th></tr></thead>";
