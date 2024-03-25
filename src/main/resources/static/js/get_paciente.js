function noSeEncontraronRegistroPaciente() {
	clearTabla();

	let sinRegistro = document.createElement("tr");
	sinRegistro.id = "idSinRegistro";

	document.getElementById("patientTable").appendChild(sinRegistro);

	let filSinRegistro = document.getElementById("idSinRegistro");

	filSinRegistro.innerHTML = "<td></td><td>No se encontraron registros</td><td></td><td></td><td></td>";
}

window.addEventListener("load", function () {
	let iconosDeCarga = document.getElementById("cargando");
	cargarEncabezadoTabla();
	cargarCuerpoTabla();
	iconosDeCarga.style.visibility = "visible";

	setTimeout(function () {
		iconosDeCarga.style.visibility = "hidden";
		obtenerListaPacientes();
	}, 3000);

	let formularioBusqueda = document.getElementById("formBusqueda");

	formularioBusqueda.addEventListener("submit", function (event) {
		event.preventDefault();
		clearTabla();
		cargarEncabezadoTabla();
		cargarCuerpoTabla();

		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			busquedaPacientePersonalizado();
		}, 3000);
	});

	let comboBusqueda = document.getElementById("comboBusqueda");
	comboBusqueda.addEventListener("change", function () {
		let formBusqueda = document.getElementById("formBusqueda");
		let inputBusqueda = document.getElementById("search");

		if (inputBusqueda == null && comboBusqueda.value < 4) {
			let calendario = document.getElementById("searchCalendar");
			let inputBusqueda = document.createElement("input");
			inputBusqueda.type = "text";
			inputBusqueda.className = "form-control mr-sm-2 idBusqueda";
			inputBusqueda.id = "search";
			inputBusqueda.type = "search";
			inputBusqueda.placeholder = "Buscar";
			inputBusqueda.ariaLabel = "Search";
			inputBusqueda.required = true;

			formBusqueda.replaceChild(inputBusqueda, calendario);
			validacionInput();
		} else if (inputBusqueda != null && comboBusqueda.value < 4) {
			inputBusqueda.value = "";
		} else if (comboBusqueda.value == 4) {
			let calendario = document.createElement("input");
			calendario.type = "date";

			calendario.className = "form-control mr-sm-2 idBusqueda btnHeaderRegistro";
			calendario.id = "searchCalendar";
			calendario.required = true;

			formBusqueda.replaceChild(calendario, inputBusqueda);
		}
	});

	validacionInput();

	let btnReset = document.getElementById("btnReset");

	btnReset.addEventListener("click", function (event) {
		let tipoDeBusqueda = document.getElementById("comboBusqueda");
		let validacionInputId = document.getElementById("search");
		tipoDeBusqueda.value = 1;
		validacionInputId.value = "";

		clearTabla();
		tablaNueva();
		cargarEncabezadoTabla();
		cargarCuerpoTabla();
		// let iconosDeCarga = document.getElementById("cargando");
		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			obtenerListaPacientes();
		}, 3000);
	});

	document.addEventListener("click", function (e) {
		if (e.target instanceof HTMLButtonElement) {
			var popover = new bootstrap.Popover(e.target);

			popover.show();

			setTimeout(function () {
				popover.hide();
			}, 1000);
		}
	});

	(function () {
		let pathname = window.location.pathname;
		if (pathname == "/pacienteLista.html") {
			document.querySelector(".nav .nav-item a:last").addClass("active");
		}
	});
});

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
			} else {
				noSeEncontraronRegistrosPaciente();
			}
		})
		.catch((error) => {
			console.log(error);
			noSeEncontraronRegistrosPaciente();
		});
}

function cargarEncabezadoTabla() {
	let table = document.getElementById("patientTable");
	let encabezadoTabla = document.createElement("thead");
	encabezadoTabla.id = "encabezado";
	encabezadoTabla.className = "thead-dark ";
	encabezadoTabla.className += "sticky-top";
	table.appendChild(encabezadoTabla);
	let pacienteRowHeader = document.getElementById("encabezado");
	// "<th scope='col'>Apellido</th>" +

	pacienteRowHeader.innerHTML = "<th scope='col'>Id</th>" + "<th scope='col'>Nombre </th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>DNI</th>" + "<th scope='col'>FechaIngreso</th>" + "<th scope='col'>Domicilio</th>" + "<th scope='col'>Gestionar</th></tr></thead>";
}

function cargarCuerpoTabla() {
	let tabla = document.getElementById("patientTable");
	let cuerpoTabla = document.createElement("tbody");
	cuerpoTabla.id = "cuerpoTabla";
	tabla.appendChild(cuerpoTabla);
}

function tablaNueva() {
	cargarEncabezadoTabla();
	cargarCuerpoTabla();
}

function clearTabla() {
	let tabla = document.getElementById("patientTable");
	tabla.innerHTML = "";
	// cargarEncabezadoTabla();
	// cargarCuerpoTabla();
}

// function cargarRegistro(patient) {

// 	return `<td class="td_id align-middle tamanioTexto">${patient.id}</td><td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}${patient.apellido.toUpperCase()}</td>
//                                         <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
//                                         <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
//                                         <td class="td_domicilio align-middle tamanioTexto"><button type='button' class='btn btn-dark' data-domicilioId=${patient.domicilio.id} id='masInfo'>mas info</button></td>
//                                         <td>
//                                           <div class="dropdown">
//                                             <button class='btn btn-dark dropdown-toggle'
//                                             type='button' data-toggle='dropdown' aria-expanded='false'></button>
//                                             <div class='dropdown-menu'>
//                                               <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
//                                               <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal'>Eliminar</button>
//                                             </div>
//                                           </div>
//                                         </td>`;
// }
{
	/* <button type='button' class='btn btn-dark' data-domicilioId=${patient.domicilio.id} id='masInfo'>mas info</button> */
}
// 	<button type="button" class="btn btn-secondary" data-container="body" data-toggle="popover" data-placement="top" data-content="${cardContent}" data-id='${patient.domicilio.id}' id='popo'>
// 	mas info
//   </button>
function cargarRegistro(patient) {
	console.log(patient.domicilio);
	return `<td class="td_id align-middle tamanioTexto">${patient.id}</td><td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}</td><td class="td_apellido align-middle tamanioTexto">${patient.apellido.toUpperCase()}</td>
                                        <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                        <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                        <td class="td_domicilio align-middle tamanioTexto">
                                        
                                 
									  
                                        
									  
									  <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#modalDomicilio${patient.id}">
										mas Info
									  </button>
									  
									  
									  <div class="modal fade " id="modalDomicilio${patient.id}" tabindex="-1" aria-labelledby="titoloDatosDomicilio" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
										  <div class="modal-content">
											<div class="modal-header">
											  <h5 class="modal-title" id="tituloDatosDomicilio">Datos domicilio</h5>
											  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											  </button>
											</div>
											<div class="modal-body">
											  

											<div class="form-group">
											<label class="control-label" for="domicilioCalle">Calle</label>
											<input type="text" class="form-control" id="domicilioCalle" readonly value="${patient.domicilio.calle}"/>
										</div>
										<div class="form-group">
											<label class="control-label" for="domicilioNumero">Numero de domicilio</label>
											<input type="text" class="form-control" id="domicilioNumero" readonly value="${patient.domicilio.numero}"/>
										</div>
										<div class="form-group">
											<label class="control-label" for="domicilioLocalidad">Localidad</label>
											<input type="text" class="form-control" id="domicilioLocalidad" readonly value="${patient.domicilio.localidad}"/>
										</div>
										<div class="form-group">
											<label class="control-label" for="domicilioProvincia">Provincia</label>
											<input type="text" class="form-control" id="domicilioProvincia" readonly value="${patient.domicilio.provincia}"/>
										</div>

									


											</div>
											<div class="modal-footer">
											  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
											  
											</div>
										  </div>
										</div>
									  </div>






                                         
                                        
                                        </td>
                                        <td>
                                          <div class="dropdown">
                                            <button class='btn btn-dark dropdown-toggle' 
                                            type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                            <div class='dropdown-menu'>
                                              <button type='button' data-id='${patient.id}' data-domicilioId='${
		patient.domicilio.id
	}' class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
                                              <button type='button' data-id='${patient.id}' data-domicilioId='${patient.domicilio.id}' class='btn btn-primary dropdown-item btnTabla' data-toggle='modal'>Eliminar</button>
                                            </div>
                                          </div>
                                        </td>`;

	
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

	// todo PARA QUE SIRVE ESTA FUNCION
	(function () {
		let pathname = window.location.pathname;
		if (pathname == "/pacienteLista.html") {
			document.querySelector(".nav .nav-item a:last").addClass("active");
		}
	})();
}

function validacionInput() {
	let validacionInputId = document.getElementById("search");
	let tipoDeBusqueda = document.getElementById("comboBusqueda");
	tipoDeBusqueda.addEventListener("change", function () {
		validacionInputId.value = "";
	});

	validacionInputId.addEventListener("keypress", function (event) {
		if (tipoDeBusqueda.value == 1) {
			const codigoTecla = event.keyCode;

			if (codigoTecla === 8 || (codigoTecla >= 48 && codigoTecla <= 57)) {
				return true;
			} else {
				event.preventDefault();
				return false;
			}
		}
	});
}
