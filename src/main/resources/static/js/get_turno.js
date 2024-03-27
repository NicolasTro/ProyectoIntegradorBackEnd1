function obtenerListaTurnos() {
	const url = "/turnos/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			console.log(data);
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");

				for (turno of data) {
					turnoRow = body.insertRow();
					let tr_id = "tr_" + turno.id;
					turnoRow.id = tr_id;
					turnoRow.innerHTML = cargarRegistro(turno);
				}
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch((error) => {
			noSeEncontraronRegistros();
		});
}

window.addEventListener("load", function () {
	cargarEncabezadoTabla();
	cargarCuerpoTabla();

	let iconosDeCarga = document.getElementById("cargando");
	iconosDeCarga.style.visibility = "visible";

	setTimeout(function () {
		iconosDeCarga.style.visibility = "hidden";

		obtenerListaTurnos();
	}, 3000);


let btnRegistrarTurno = document.getElementById("btnRegistrarTurno");

console.log(btnRegistrarTurno.id);



	(function () {
		let pathname = window.location.pathname;
		if (pathname == "/turnoLista.html") {
			document.querySelector(".nav .nav-item a:last").addClass("active");
		}
	});

btnRegistrarTurno.addEventListener('click', function(){

listarPacientes();
listarOdontologos();

	
});
	
});

function cargarEncabezadoTabla() {
	let tabla = document.getElementById("turnoTable");
	let encabezadoTabla = document.createElement("thead");
	encabezadoTabla.id = "encabezado";
	tabla.appendChild(encabezadoTabla);
	let dentistRowHeader = document.getElementById("encabezado");
	dentistRowHeader.innerHTML = "<th scope='col'>Id</th>" + "<th scope='col'>Odontologo</th>" + "<th scope='col'>Paciente</th>" + "<th scope='col'>Fecha y hora</th>" + "<th scope='col'>Gestionar </th>";
}

function noSeEncontraronRegistros() {
	clearTabla();
	let sinRegistro = document.createElement("tr");
	sinRegistro.id = "idSinRegistro";
	document.getElementById("cuerpoTabla").appendChild(sinRegistro);
	let filSinRegistro = document.getElementById("idSinRegistro");
	filSinRegistro.innerHTML = "<td></td><td>No se encontraron registros</td><td></td><td></td><td></td>";
}

function cargarCuerpoTabla() {
	let tabla = document.getElementById("turnoTable");
	let cuerpoTabla = document.createElement("tbody");
	cuerpoTabla.id = "cuerpoTabla";
	tabla.appendChild(cuerpoTabla);
}

function clearTabla() {
	let tabla = document.getElementById("turnoTable");
	tabla.innerHTML = "";
	cargarEncabezadoTabla();
	cargarCuerpoTabla();
}

function cargarRegistro(turno) {
	console.log(turno);
	return `<td class="td_id align-middle tamanioTexto">${turno.id}</td><td class="td_odontologo align-middle tamanioTexto">${turno.odontologo.nombre}</td>
    <td class="td_paciente align-middle tamanioTexto">${turno.paciente.nombre}</td><td class="td_fechaTurno align-middle tamanioTexto">${turno.fechaYHora}</td><td><div class="dropdown"><button class='btn btn-secondary dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${turno.id} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropTurnoUpdate'>Modificar</button><button type='button' data-id=${turno.id} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal' >Eliminar</button></div></div></td>`;
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

					let;
				}
			} else {
				noSeEncontraronRegistrosPaciente();
			}
		})
		.catch((error) => {
			noSeEncontraronRegistroPaciente();
		});
}
function listarPacientes(){
	

	const url = `/pacientes/listar`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		

			
				let comboPacientes = document.getElementById("comboTurnoPaciente");
console.log(comboPacientes);
				for (patient of data) {
let comboValor = document.createElement("option");

comboValor.value = patient.id;
comboValor.textContent = patient.nombre;
comboPacientes.appendChild(comboValor);


					
				}
			
		})
		.catch((error) => {
			
		});

}


function  listarOdontologos(){
	const url = `/odontologos/listarDTO`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		

			
				let comboOdontologo = document.getElementById("comboTurnoOdontologo");
console.log(comboOdontologo);
				for (odontologo of data) {
let comboValor = document.createElement("option");

comboValor.value = odontologo.id;
comboValor.textContent = odontologo.nombre;
comboOdontologo.appendChild(comboValor);


					
				}
			
		})
		.catch((error) => {
			
		});


}