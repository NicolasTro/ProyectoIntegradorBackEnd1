
window.addEventListener("load", function () {
	
});

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

function noSeEncontraronRegistros() {
	clearTabla();
	let sinRegistro = document.createElement("tr");
	sinRegistro.id = "idSinRegistro";
	document.getElementById("tablaDeRegistros").appendChild(sinRegistro);
	let filSinRegistro = document.getElementById("idSinRegistro");
	filSinRegistro.innerHTML = "<td></td><td>No se encontraron registros</td><td></td><td></td><td></td>";
}

function cargarCuerpoTabla() {
	let tabla = document.getElementById("tablaDeRegistros");
	if (tabla !== null) {
		let cuerpoTabla = document.createElement("tbody");
		cuerpoTabla.id = "cuerpoTabla";
		tabla.appendChild(cuerpoTabla);
	}
}

function tablaNueva(tituloTabla) {
	cargarEncabezadoTabla(tituloTabla);
	cargarCuerpoTabla();
}

function clearTabla() {
	let tabla = document.getElementById("tablaDeRegistros");
	if (tabla !== null) {
		tabla.innerHTML = "";
	}
}

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
		let algo = document.getElementById(campo.id);

		algo.setAttribute("data-content", "Campos invalidos");
		if (campo.value.trim() == "") {
			campo.style.border = "solid red 3px";

			$("#" + campo.id).popover("show");
		} else {
			$("#" + campo.id).popover("hide");
			campo.style.border = "none";
			contador += 1;
		}
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
			// $("#" + campo.id).popover("hide");
		}, 3000);
	}

	return retorno;
}