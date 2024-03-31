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
				//TRAEMOS UNA LISTA DE BOTONES DE MODIFICAR Y ELIMINAR REGISTROS TURNO
				let listaBtnModificarRegistros = document.querySelectorAll(".btnTablaModificar");
				let listaBtnEliminarRegistros = document.querySelectorAll(".btnTablaEliminar");
				//PASAMOS LOS BOTONES POR PARAMETRO A SUS RESPECTIVAS FUNCIONES
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
			//ACA ESTA FUNCION FUE SIMPLIFICADA PORQUE ERA SIMILIAR EN LAS OTRAS VISTAS DE PACIENTE Y ODONTOLOG, FALTA SIMPLIFICAR MAS
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
				//LISTA DE BOTONES MODIFICAR Y ELIMINAR
				let listaBtnModificarRegistros = document.querySelectorAll(".btnTablaModificar");
				let listaBtnEliminarRegistros = document.querySelectorAll(".btnTablaEliminar");
				//LOS PASAMOS POR PARAMETRO A LAS FUNCIONES
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
//LISTAMOS LOS PACIENTES Y LOS CARGAMOS EN LOS COMBOBOX DE LOS MODALES DE TURNO
function listarPacientes(combolistaPacientes) {
	const url = `/pacientes/listar`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			let comboPacientes = document.querySelector(combolistaPacientes);

			for (patient of data) {
				let comboValor = document.createElement("option");

				comboValor.value = patient.id;
				comboValor.textContent = patient.nombre;
				comboPacientes.appendChild(comboValor);
			}
		})
		.catch(error => {});
}

// ##############################################################################################################################
//LISTAMOS LOS ODONTOLOGOS Y LOS CARGAMOS EN LOS COMBOBOX DE LOS MODALES DE TURNO
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
