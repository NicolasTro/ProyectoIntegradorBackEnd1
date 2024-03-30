// ##############################################################################################################################
//FUNCION OBTENER LISTA DE TURNOS
function obtenerListaTurnos() {
	const url = "/turnos/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");

				for (turno of data) {
					turnoRow = body.insertRow();
					let tr_id = "tr_" + turno.id;
					turnoRow.id = tr_id;
					turnoRow.innerHTML = cargarRegistro(turno);
				}

				let listaBtnEliminarRegistros = document.querySelectorAll(".btnTablaEliminar");
				eliminarTurno(listaBtnEliminarRegistros);
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch((error) => {
			noSeEncontraronRegistros();
		});
}

// ##############################################################################################################################
// ##############################################################################################################################
function busquedaPacientePersonalizado() {
	let tipoDeBusqueda = document.getElementById("comboBusqueda");

	let valorBusqueda;
	if (tipoDeBusqueda.value <= 3) {
		valorBusqueda = document.getElementById("search");
	} else {
		valorBusqueda = document.getElementById("searchCalendar");
	}

	const url = `/turnos/buscar?valor=${valorBusqueda.value}&tipoDeBusqueda=${tipoDeBusqueda.value}`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			clearTabla();
			tablaNueva();

			if (data.length == 1) {
				let tablaBody = document.getElementById("cuerpoTabla");

				tablaBody.innerHTML = "";
				let patientRow = tablaBody.insertRow();

				let tr_id = "tr_" + data[0].id;

				patientRow.id = tr_id;
				patientRow.innerHTML = cargarRegistro(data[0]);
			} else if (data.length > 1) {
				let body = document.getElementById("cuerpoTabla");

				for (patient of data) {
					patientRow = body.insertRow();
					let tr_id = "tr_" + patient.id;
					patientRow.id = tr_id;
					patientRow.innerHTML = cargarRegistro(patient);
				}



				
			} else {
				noSeEncontraronRegistrosPaciente();
			}
		})
		.catch((error) => {
			noSeEncontraronRegistroPaciente();
		});
}

// ##############################################################################################################################
function listarPacientes() {
	const url = `/pacientes/listar`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			let comboPacientes = document.getElementById("comboTurnoPaciente");

			for (patient of data) {
				let comboValor = document.createElement("option");

				comboValor.value = patient.id;
				comboValor.textContent = patient.nombre;
				comboPacientes.appendChild(comboValor);
			}
		})
		.catch((error) => {});
}

function listarOdontologos() {
	const url = `/odontologos/listarDTO`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			let comboOdontologo = document.getElementById("comboTurnoOdontologo");

			for (odontologo of data) {
				let comboValor = document.createElement("option");

				comboValor.value = odontologo.id;
				comboValor.textContent = odontologo.nombre + " " + odontologo.apellido;
				comboOdontologo.appendChild(comboValor);
			}
		})
		.catch((error) => {});
}

let tituloTablaTurno = "<th scope='col'>Id</th>" + "<th scope='col'>Odontologo</th>" + "<th scope='col'>Paciente</th>" + "<th scope='col'>Fecha y hora</th>" + "<th scope='col'>Gestionar </th>";
