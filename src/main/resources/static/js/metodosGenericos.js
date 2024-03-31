window.addEventListener("load", function () {
	//CARGA LA VENTANA Y LLAMAMOS A VALIDAR FECHA PARA QUE NO PERMITA CARGAR UNA FECHA MENOR A LA ACTUAL EN PACIENTE Y TURNO
	validarFecha();
});
//FUNCION PARA CARGAR EL ENCABEZADO DE LA TABLA DE REGISROS, RECIBE UN HEADER POR PARAMETRO
function cargarEncabezadoTabla(tituloTabla) {
	let tabla = document.getElementById("tablaDeRegistros");
	if (tabla != null) {
		let encabezadoTabla = document.createElement("thead");
		encabezadoTabla.id = "encabezado";
		encabezadoTabla.className = "thead-dark ";
		encabezadoTabla.className += "sticky-top";
		tabla.appendChild(encabezadoTabla);
		let dentistRowHeader = document.getElementById("encabezado");
		dentistRowHeader.innerHTML = tituloTabla;
	}
}
//LIMPIA LA TABLA Y CARGA UNA FILA CON EL MENSAJE NO SE ENCONTRARON REGISTROS
function noSeEncontraronRegistros() {
	clearTabla();
	let sinRegistro = document.createElement("tr");
	sinRegistro.id = "idSinRegistro";
	document.getElementById("tablaDeRegistros").appendChild(sinRegistro);
	let filSinRegistro = document.getElementById("idSinRegistro");
	filSinRegistro.innerHTML = "<td></td><td>No se encontraron registros</td><td></td><td></td><td></td>";
}
//CARGA UN CUERPO A LA TABLA DE REGISTROS
function cargarCuerpoTabla() {
	let tabla = document.getElementById("tablaDeRegistros");
	if (tabla !== null) {
		let cuerpoTabla = document.createElement("tbody");
		cuerpoTabla.id = "cuerpoTabla";
		tabla.appendChild(cuerpoTabla);
	}
}

//LLAMA A CARGAR ENCABEZADO TABLA PASANDOLE EL HEADER Y CARGA CUERPO DE TABLA
function tablaNueva(tituloTabla) {
	cargarEncabezadoTabla(tituloTabla);
	cargarCuerpoTabla();
}

//ELIMINA LA TABLA 
function clearTabla() {
	let tabla = document.getElementById("tablaDeRegistros");
	if (tabla !== null) {
		tabla.innerHTML = "";
	}
}


//VALIDA CAMPOS DE BUSQUEDA Y NO PERMITE INGRESAR LETRAS SI EL TIPO DE BUSQUEDA ES 1
function validacionInput() {
	let validacionInputId = document.getElementById("search");
	if (validacionInputId !== null) {
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
}

//VALIDA CAMPOS INGRESADOS RECIBE UN FORMULARIO Y UN TIPO DE ELEMENTO PARA MOSTRAR LOS MENSAJES
function validarCamposIngresados(formularioGenerico, mensaje) {
	let formEspecifico = "#" + formularioGenerico.id;

	let listaInputsAValidar = document.querySelectorAll(".validacion");
	let camposRequeridos = document.querySelectorAll(`${formEspecifico} [required]`);

	let contador = 0;
	let retorno = false;

	let idInput = 0;
	listaInputsAValidar.forEach(input => {
		idInput++;
		input.id = idInput;
	});

	camposRequeridos.forEach(campo => {
		campo.addEventListener("change", function () {
			if (campo.value.trim() == "") {
				let algo = document.getElementById(campo.id);

				algo.setAttribute("data-content", "Campos invalidos");
				campo.style.border = "solid red 3px";

				$("#" + campo.id).popover("show");
			} else {
				$("#" + campo.id).popover("hide");
				campo.style.border = "none";
			}
		});

		let algo = document.getElementById(campo.id);
		//SI LOS CAMPOS ESTAN INVALIDOS MUESTRO POPUP MOSTRANDO ERROR

		algo.setAttribute("data-content", "Campos invalidos");
		if (campo.value.trim() == "") {
			campo.style.border = "solid red 3px";

			$("#" + campo.id).popover("show");
		} else {
			$("#" + campo.id).popover("hide");
			campo.style.border = "none";
			contador += 1;
		}

		setTimeout(function () {
			$("#" + campo.id).popover("hide");
			campo.style.border = "none";
		}, 2000);
	});

	if (contador == camposRequeridos.length) {
		retorno = true;
	} else {
		let errorAlert = '<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong> Datos incorrectos</strong> </div>";

		let bloqueMensaje = document.querySelector(mensaje);

		bloqueMensaje.innerHTML = errorAlert;
		let mostrarMensaje = document.querySelector(mensaje);
		mostrarMensaje.style.display = "block";
		setTimeout(function () {
			bloqueMensaje.innerHTML = "";
			mostrarMensaje.style.display = "none";
		}, 3000);
	}

	return retorno;
}


//VALIDA EL INPUT TIPO TEXTO PARA QUE NO PERMITA INGRESAR LETRAS
function validarInputNumerico(campoNumerico) {
	campoNumerico.addEventListener("keypress", function (event) {
		const codigoTecla = event.keyCode;

		if (codigoTecla === 8 || (codigoTecla >= 48 && codigoTecla <= 57)) {
			return true;
		} else {
			event.preventDefault();
			return false;
		}
	});
}

//VALIDA INPUT FECHA NO PERMITE INGRESAR FECHA MENOR QUE LA ACTUAL

function validarFecha() {
	const ahora = new Date();

	ahora.setMinutes(ahora.getMinutes() + 1);

	const formatNumber = num => (num < 10 ? "0" + num : num);

	const year = ahora.getFullYear();
	const month = formatNumber(ahora.getMonth() + 1);
	const day = formatNumber(ahora.getDate());
	const hours = formatNumber(ahora.getHours());
	const minutes = formatNumber(ahora.getMinutes());

	const formattedDateTime = `${year}-${month}-${day}T${hours}:${minutes}`;

	let inputFechaHora = document.querySelectorAll(".inputFechaHora");

	inputFechaHora.forEach(fecha => {
		fecha.min = formattedDateTime;
	});

	let inputFecha = document.querySelectorAll(".inputFecha");

	if (inputFecha !== null) {
		inputFecha.forEach(fecha => {
			fecha.min = `${year}-${month}-${day}`;

			fecha.value = `${year}-${month}-${day}`;
		});
	}
}
