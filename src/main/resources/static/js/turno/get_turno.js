// ##############################################################################################################################
//FUNCION OBTENER LISTA DE TURNOS
function obtenerListaTurnos() {
	const url = "/turnos/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");

				for (turno of data) {
					turnoRow = body.insertRow();
					let tr_id = "tr_" + turno.id;
					turnoRow.id = tr_id;
					turnoRow.innerHTML = cargarRegistro(turno);
				}

				let listaBtnModificarRegistros = document.querySelectorAll(".btnTablaModificar");
				let listaBtnEliminarRegistros = document.querySelectorAll(".btnTablaEliminar");

				modificarTurno(listaBtnModificarRegistros);
				eliminarTurno(listaBtnEliminarRegistros);
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch(error => {
			noSeEncontraronRegistros();
		});
}

// ##############################################################################################################################
// ##############################################################################################################################
function busquedaTurnosPersonalizado() {
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
		.then(response => response.json())
		.then(data => {
			// clearTabla();
			// tablaNueva();

			// if (data.length == 1) {
			// 	let tablaBody = document.getElementById("cuerpoTabla");

			// 	tablaBody.innerHTML = "";
			// 	let patientRow = tablaBody.insertRow();

			// 	let tr_id = "tr_" + data[0].id;

			// 	patientRow.id = tr_id;
			// 	patientRow.innerHTML = cargarRegistro(data[0]);
			// } else if (data.length > 1) {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");
				console.log(body);
				for (turno of data) {
					console.log(turno);
					turnoRow = body.insertRow();
					let tr_id = "tr_" + turno.id;
					turnoRow.id = tr_id;
					turnoRow.innerHTML = cargarRegistro(turno);
				}

				let listaBtnModificarRegistros = document.querySelectorAll(".btnTablaModificar");
				let listaBtnEliminarRegistros = document.querySelectorAll(".btnTablaEliminar");

				modificarTurno(listaBtnModificarRegistros);
				eliminarTurno(listaBtnEliminarRegistros);
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch(error => {
			noSeEncontraronRegistros();
		});
}

// ##############################################################################################################################
function listarPacientes(combolistaPacientes) {
	const url = `/pacientes/listar`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			let comboPacientes = document.querySelector(combolistaPacientes);
			// let comboPacientes = document.querySelector(".comboTurnoPaciente");

			for (patient of data) {
				let comboValor = document.createElement("option");

				comboValor.value = patient.id;
				comboValor.textContent = patient.nombre;
				comboPacientes.appendChild(comboValor);
			}
		})
		.catch(error => {});
}

function listarOdontologos(comboListaOdontologos) {
	const url = `/odontologos/listarDTO`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			let comboOdontologo = document.querySelector(comboListaOdontologos);

			for (odontologo of data) {
				let comboValor = document.createElement("option");

				comboValor.value = odontologo.id;
				comboValor.textContent = odontologo.nombre + " " + odontologo.apellido;
				comboOdontologo.appendChild(comboValor);
			}
		})
		.catch(error => {});
}
