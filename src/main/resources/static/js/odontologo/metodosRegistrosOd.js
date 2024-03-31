window.addEventListener("load", function () {
	//CARGA DE CABEZAL Y CUERPO DE TABLA DE REGISTROS
	//################################################################################
	$(document).ready(function () {
		// Configurar popovers
		$('[data-toggle="popover"]').popover();

		// $('[data-toggle="popover"]').contentText="asdasd";
	});
	// cargarEncabezadoTabla(tituloTabla);
	// cargarCuerpoTabla();
	tablaNueva(tituloTabla);
	let iconosDeCarga = document.getElementById("cargando");
	if (iconosDeCarga !== null) {
		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";

			obtenerListaOdontologos();
		}, 3000);
	}

	

	//################################################################################
	// DISPARA EL EVENTO SUBMIT DEL FORMULARIO DE BUSQUEDA PERSONALIZADA
	let formularioBusqueda = document.getElementById("formBusqueda");
	if (formularioBusqueda !== null) {
		formularioBusqueda.addEventListener("submit", function (event) {
			event.preventDefault();

			clearTabla();
			tablaNueva(tituloTabla);

			//ICONOS DE LOADING
			let iconosDeCarga = document.getElementById("cargando");
			iconosDeCarga.style.visibility = "visible";

			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				busquedaOdontologoPersonalizada();
				9;
			}, 3000);
		});
	}

	//DISPARA EL EVENGO CHANGE DEL COMBOBOX DE BUSQUEDA PARA RESETEAR EL FILTRO DE BUSQUEDA
	//################################################################################
	let validacionInputId = document.getElementById("search");
	let tipoDeBusqueda = document.getElementById("comboBusqueda");
	if (validacionInputId !== null) {
		tipoDeBusqueda.addEventListener("change", function () {
			validacionInputId.value = "";
		});
	}
	//DISPARA EL EVENTO CLICK DEL BOTON RESET DE BUSQUEDA, BORRA TABLA, LA CARGA NUEVAMENTE
	//LIMPIA LOS REGISTROS BUSCADOS, DEVUELVE TODOS LOS REGISTROS EXISTENTES
	//################################################################################
	let btnReset = document.getElementById("btnReset");
	if (btnReset !== null) {
		btnReset.addEventListener("click", function (event) {
			tipoDeBusqueda.value = 1;
			validacionInputId.value = "";
			clearTabla();
			tablaNueva(tituloTabla);
			// cargarEncabezadoTabla(tituloTabla);
			// cargarCuerpoTabla();

			iconosDeCarga.style.visibility = "visible";

			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				obtenerListaOdontologos();
			}, 3000);
		});
	}

	validacionInput();
});

function cargarRegistro(dentist) {
	
	return `<td class="td_id align-middle tamanioTexto">${dentist.id}</td><td class="td_nombre align-middle tamanioTexto">${dentist.nombre.toUpperCase()}</td>
  <td class="td_apellido align-middle tamanioTexto">${dentist.apellido.toUpperCase()}</td><td class="td_matricula align-middle tamanioTexto">${dentist.matricula.toUpperCase()}</td><td class='align-middle tamanioTexto'>
    <div class="dropdown"><button class='btn btn-dark dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${
			dentist.id
		} class='btn btn-primary btnTabla btnTablaModificar dropdown-item' data-toggle='modal' data-target='#staticBackdrop' data-id='${dentist.id}' data-nombre='${dentist.nombre}'data-apellido='${dentist.apellido}' data-matricula='${dentist.matricula}'>Modificar</button><button type='button' data-id=${
		dentist.id
	} class='btn btn-primary dropdown-item btnTabla btnTablaEliminar' data-toggle='modal' data-target='#staticBackdropOdontologoDelete' >Eliminar</button></div></div></td>`;
}

let tituloTabla = "<th scope='col'>Id</th>" + "<th scope='col'>Nombre</th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>Matrícula</th>" + "<th scope='col'>Gestionar </th>";



// function validarCamposIngresados(formularioGenerico, mensaje) {
// 	let formEspecifico = "#" + formularioGenerico.id;

// 	let listaInputsAValidar = document.querySelectorAll(".validacion");
// 	let camposRequeridos = document.querySelectorAll(`${formEspecifico} [required]`);

// 	let contador = 0;
// 	let retorno = false;

// 	let idInput = 0;
// 	listaInputsAValidar.forEach(input => {
// 		idInput++;
// 		input.id = idInput;
// 	});

// 	camposRequeridos.forEach(campo => {
// 		let algo = document.getElementById(campo.id);

// 		algo.setAttribute("data-content", "Campos invalidos");
// 		if (campo.value.trim() == "") {
// 			campo.style.border = "solid red 3px";

// 			$("#" + campo.id).popover("show");
// 		} else {
// 			$("#" + campo.id).popover("hide");
// 			campo.style.border = "none";
// 			contador += 1;
// 		}
// 	});

// 	if (contador == camposRequeridos.length) {
// 		retorno = true;
// 	} else {
// 		let errorAlert = '<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong> Datos incorrectos</strong> </div>";

		
// 		let bloqueMensaje = document.querySelector(mensaje);

// 		bloqueMensaje.innerHTML = errorAlert;
// 		let mostrarMensaje = document.querySelector(mensaje);
// 		mostrarMensaje.style.display = "block";
// 		setTimeout(function () {
// 			bloqueMensaje.innerHTML = "";
// 			mostrarMensaje.style.display = "none";
// 			$("#" + campo.id).popover("hide");
// 		}, 3000);
// 	}

// 	return retorno;
// }
