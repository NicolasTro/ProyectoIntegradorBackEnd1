window.addEventListener("load", function () {
	//FUNCION PARA VALIDAR FECHAS Y QUE NO PERMITA UN INGRESO MENOR A LA FECHA ACTUAL
	validarFecha();
	//CREA HEADER DE LA TABLA CON LA VARIABLE PASADA POR PARAMETRO Y CUERPO DE TABLA
	tablaNueva(tituloTablaPacientes);

	//FUNCION QUE MUESTRA ICONOS DE CARGA EN VISTA PACIENTES
	let iconosDeCarga = document.getElementById("cargando");
	if (iconosDeCarga !== null) {
		iconosDeCarga.style.visibility = "visible";
		//OCULTA ICONOS DE CARGA Y LISTA PACIENTES EXISTENTES
		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			obtenerListaPacientes();
		}, 3000);
	}
	//FUNCION BUSQUEDA PERSONALIZADA LIMPIA TABLA CARGA HEADER Y BODY
	let formularioBusqueda = document.getElementById("formBusqueda");
	if (formularioBusqueda !== null) {
		formularioBusqueda.addEventListener("submit", function (event) {
			event.preventDefault();
			clearTabla();
			tablaNueva(tituloTablaPacientes);

			iconosDeCarga.style.visibility = "visible";
			//OCULTA ICONOS DE CARGA Y LLAMA A BUSQUEDA PACIENTE PERSONALIZADO
			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				busquedaPacientePersonalizado();
			}, 3000);
		});
	}
	//FUNCION PARA COMBO DE TIPO DE BUSQUEDA
	let comboBusqueda = document.getElementById("comboBusqueda");
	if (comboBusqueda !== null) {
		comboBusqueda.addEventListener("change", function () {
			let formBusqueda = document.getElementById("formBusqueda");
			let inputBusqueda = document.getElementById("search");
			//SI EL TIPO DE BUSQUEDA ES MENOR QUE 4 CARGA EL INPUT TIPO TEXTO
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
				//LLAMA A VALIDAR INPUT QUE VALIDA SI ES NUMERICO, SOLO DEJE INGRESAR NUMEROS
				validacionInput();
				//SI BUSQUEDA ES MENOR QUE 4, LIMPIA CAMPOS CADA VEZ QUE SE EJECUTA EL EVENTO CHANGE
			} else if (inputBusqueda != null && comboBusqueda.value < 4) {
				inputBusqueda.value = "";
			} else if (comboBusqueda.value == 4) {
				//SI EL TIPO DE BUSQUEDA ES IGUAL A 4, CREA UN ELEMENTO DEL TIPO CALENDAR PARA HACER BUSQUEDA POR FECHA
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

			if (validacionInputId !== null) {
				validacionInputId.value = "";
			} else {
				//ESTE CODIGO SE PODRIA SIMPLIFICAR CON EL DE ARRIBA
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
			}
			clearTabla();
			tablaNueva(tituloTablaPacientes);
			//ESTA PARTE SE PUEDE HACER UNA FUNCION PORQUE SE REPITE EN VARIOS LADOS
			iconosDeCarga.style.visibility = "visible";
			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				obtenerListaPacientes();
			}, 3000);
		});
	}
});
//RECBIE UN PACIENTE POR PARAMETRO Y CARGA LOS DATOS POR FILA
function cargarRegistro(patient) {
	return `<td class="td_id align-middle tamanioTexto">${patient.id}</td><td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}</td><td class="td_apellido align-middle tamanioTexto">${patient.apellido.toUpperCase()}</td>
                                        <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                        <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                        <td class="td_domicilio align-middle tamanioTexto"><button type="button" class="btn btn-dark masInfo" data-toggle="modal" data-target="#modalDomicilio" id='masInfo'     data-calle='${patient.calle}'  data-numero='${
		patient.numero
	}' data-localidad='${patient.localidad}' data-provincia='${patient.provincia}'>
										mas Info</button></td><td td_btn align-middle tamanioTexto><div class="dropdown"><button class='btn btn-dark dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                            <div class='dropdown-menu traerAlFrente'>
											<button type='button' data-id='${patient.id}' data-nombre='${patient.nombre}' data-apellido='${patient.apellido}' data-dni='${patient.dni}' data-fechaIngreso='${patient.fechaIngreso}' data-domicilioid='${patient.domicilio_id}' data-calle='${
		patient.calle
	}' data-numero='${patient.numero}' data-localidad='${patient.localidad}' data-provincia='${
		patient.provincia
	}'  class='btn btn-primary btnTabla btnTablaModificar dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
                                            <button type='button' data-id='${patient.id}'  class='btn btn-primary dropdown-item btnTablaEliminar' data-target='#staticBackdropPacienteDelete' data-toggle='modal'>Eliminar</button>
                                            </div>
                                          </div>
                                        </td>`;
}
//CARGA LOS DATOS DE DOMICILIO EN CADA MODAL
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
//RESETEA CAMPOS DEL MODAL
function resetUploadForm() {
	document.querySelector("#nombrePaciente").value = "";
	document.querySelector("#apellidoPaciente").value = "";
	document.querySelector("#dni").value = "";
	document.querySelector("#fechaIngreso").value = "";
	document.querySelector("#domicilioCalle").value = "";
	document.querySelector("#domicilioNumero").value = "";
	document.querySelector("#domicilioLocalidad").value = "";
	document.querySelector("#domicilioProvincia").value = "";
}
//TITULO DE TABLA DE PACIENTES
let tituloTablaPacientes =
	"<th scope='col'>Id</th>" + "<th scope='col'>Nombre </th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>DNI</th>" + "<th scope='col'>FechaIngreso</th>" + "<th scope='col'>Domicilio</th>" + "<th scope='col'>Gestionar</th></tr></thead>";