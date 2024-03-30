window.addEventListener("load", function () {
	tablaNueva(tituloTablaPacientes);

	let iconosDeCarga = document.getElementById("cargando");
	if (iconosDeCarga !== null) {
		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			obtenerListaPacientes();
		}, 3000);
	}

	let formularioBusqueda = document.getElementById("formBusqueda");
	if (formularioBusqueda !== null) {
		formularioBusqueda.addEventListener("submit", function (event) {
			event.preventDefault();
			clearTabla();
			tablaNueva(tituloTablaPacientes);

			iconosDeCarga.style.visibility = "visible";

			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				busquedaPacientePersonalizado();
			}, 3000);
		});
	}

	let comboBusqueda = document.getElementById("comboBusqueda");
	if (comboBusqueda !== null) {
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
	}

	validacionInput();

	let btnReset = document.getElementById("btnReset");

	if (btnReset !== null) {
		btnReset.addEventListener("click", function (event) {
			let tipoDeBusqueda = document.getElementById("comboBusqueda");
			let validacionInputId = document.getElementById("search");
			tipoDeBusqueda.value = 1;
			validacionInputId.value = "";

			clearTabla();
			tablaNueva(tituloTablaPacientes);

			iconosDeCarga.style.visibility = "visible";

			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				obtenerListaPacientes();
			}, 3000);
		});
	}
	// document.addEventListener("click", function (e) {
	// 	if (e.target instanceof HTMLButtonElement) {
	// 		var popover = new bootstrap.Popover(e.target);

	// 		popover.show();

	// 		setTimeout(function () {
	// 			popover.hide();
	// 		}, 1000);
	// 	}
	// });
});

function cargarRegistro(patient) {
	
	return `<td class="td_id align-middle tamanioTexto">${patient.id}</td><td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}</td><td class="td_apellido align-middle tamanioTexto">${patient.apellido.toUpperCase()}</td>
                                        <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                        <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                        <td class="td_domicilio align-middle tamanioTexto"><button type="button" class="btn btn-dark masInfo" data-toggle="modal" data-target="#modalDomicilio" id='masInfo'     data-calle='${patient.calle}'  data-numero='${patient.numero}' data-localidad='${
		patient.localidad
	}' data-provincia='${patient.provincia}'>
										mas Info</button></td><td td_btn align-middle tamanioTexto><div class="dropdown"><button class='btn btn-dark dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                            <div class='dropdown-menu traerAlFrente'>
											<button type='button' data-id='${patient.id}' data-nombre='${patient.nombre}' data-apellido='${patient.apellido}' data-dni='${patient.dni}' data-fechaIngreso='${patient.fechaIngreso}' data-domicilioid='${patient.domicilio_id}' data-calle='${patient.calle}' data-numero='${
		patient.numero
	}' data-localidad='${patient.localidad}' data-provincia='${patient.provincia}'  class='btn btn-primary btnTabla btnTablaModificar dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
                                            <button type='button' data-id='${patient.id}'  class='btn btn-primary dropdown-item btnTablaEliminar' data-target='#staticBackdropPacienteDelete' data-toggle='modal'>Eliminar</button>
                                            </div>
                                          </div>
                                        </td>`;
}

function cargarMasInfo(listaBtnDomicilios) {
	listaBtnDomicilios.forEach(boton => {
		
		boton.addEventListener("click", function () {
			$("#modalDomicilio").modal("show");

			document.querySelector("#domicilioCalleModal").value = boton.dataset.calle;

			document.querySelector("#domicilioNumeroModal").value = boton.dataset.numero;
			document.querySelector("#domicilioLocalidadModal").value = boton.dataset.localidad;
			document.querySelector("#domicilioProvinciaModal").value = boton.dataset.provincia;
		});
	});
}
