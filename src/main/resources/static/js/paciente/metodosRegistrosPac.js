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
			// cargarEncabezadoTabla(tituloTablaPacientes);
			// cargarCuerpoTabla();

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
			// cargarEncabezadoTabla(tituloTablaPacientes);
			// cargarCuerpoTabla();
			// let iconosDeCarga = document.getElementById("cargando");
			iconosDeCarga.style.visibility = "visible";

			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				obtenerListaPacientes();
			}, 3000);
		});
	}
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

function cargarRegistro(patient) {
	return `<td class="td_id align-middle tamanioTexto">${patient.id}</td><td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}</td><td class="td_apellido align-middle tamanioTexto">${patient.apellido.toUpperCase()}</td>
                                        <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                        <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                        <td class="td_domicilio align-middle tamanioTexto"><button type="button" class="btn btn-dark" data-toggle="modal" data-target="#modalDomicilio${patient.id}">
										mas Info</button> 
									  <div class="modal fade " id="modalDomicilio${patient.id}" tabindex="-1" aria-labelledby="titoloDatosDomicilio" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
										  <div class="modal-content">
											<div class="modal-header">
											  <h5 class="modal-title" id="tituloDatosDomicilio">Datos domicilio</h5>
											  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											  </button>
											</div>
											<div class="modal-body"><div class="form-group"><label class="control-label" for="domicilioCalle">Calle</label>
											<input type="text" class="form-control" id="domicilioCalle" readonly value="${patient.calle}"/>
										</div>
										<div class="form-group">
											<label class="control-label" for="domicilioNumero">Numero de domicilio</label>
											<input type="text" class="form-control" id="domicilioNumero" readonly value="${patient.numero}"/>
										</div>
										<div class="form-group">
											<label class="control-label" for="domicilioLocalidad">Localidad</label>
											<input type="text" class="form-control" id="domicilioLocalidad" readonly value="${patient.localidad}"/>
										</div>
										<div class="form-group">
											<label class="control-label" for="domicilioProvincia">Provincia</label>
											<input type="text" class="form-control" id="domicilioProvincia" readonly value="${patient.provincia}"/>
										</div></div>
											<div class="modal-footer">
											  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>											  
											</div>
										  </div>
										</div>
									  </div>
                                        </td>
                                        <td td_btn align-middle tamanioTexto>
                                          <div class="dropdown">
                                            <button class='btn btn-dark dropdown-toggle' 
                                            type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                            <div class='dropdown-menu traerAlFrente'>
											<button type='button' data-id='${patient.id}' data-domicilioId='${patient.id}' class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
                                            <button type='button' data-id='${patient.id}'  class='btn btn-primary dropdown-item btnTablaEliminar' data-target='#staticBackdropPacienteDelete' data-toggle='modal'>Eliminar</button>
                                            </div>
                                          </div>
                                        </td>`;
}
