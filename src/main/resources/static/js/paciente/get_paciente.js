function obtenerListaPacientes() {
	const url = "/pacientes/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");

				for (patient of data) {
					let patientRow = body.insertRow();
					let tr_id = "tr_" + patient.id;
					patientRow.id = tr_id;
					patientRow.innerHTML = cargarRegistro(patient);
				}

				//TRAEMOS 3 LISTAS, 1 DE BOTONES A MODIFICAR, OTRA DE ELIMINAR Y OTRA DE BOTONS DE DOMICILIO SEGUN LOS REGISTROS EXISTENTES
				let listaBtnModificarRegistros = document.querySelectorAll(".btnTablaModificar");
				let listaBtnEliminarRegistros = document.querySelectorAll(".btnTablaEliminar");
				let listaBtnDomicilios = document.querySelectorAll("#masInfo");
				//FUNCION PARA CARGAR INFORMACION DE DOMICILIOS DE CADA PACIENTE
				cargarMasInfo(listaBtnDomicilios);
				actualizarPaciente(listaBtnModificarRegistros);
				eliminarPaciente(listaBtnEliminarRegistros);
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch(error => {
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
		.then(response => response.json())

		.then(data => {
			if (data.length > 0) {
				clearTabla();
				tablaNueva(tituloTablaPacientes);
				let body = document.getElementById("cuerpoTabla");
				//ESTE METODO DE BUSQUEDA PERSONALIZADA SE PODRIA SIMPLIFICAR TAMBIEN CON EL METODO GET DE LISTAR PACIENTES
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
		.catch(error => {
			noSeEncontraronRegistros();
		});
}
