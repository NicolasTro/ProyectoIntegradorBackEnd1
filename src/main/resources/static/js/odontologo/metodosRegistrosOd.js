//EVENT LISTENER QUE SE EJECUTA EN LA CARGA DE LA VENTANA
window.addEventListener("load", function () {
	$(document).ready(function () {
		$('[data-toggle="popover"]').popover();
	});

	//#######################################################################################################
	//FUNCION TABLA NUEVA RECIBE UN HEADER POR PARAMETRO Y CARGA EL ENCABEZADO Y EL CUERPO VACIO DE LA TABLA
	tablaNueva(tituloTabla);
	let iconosDeCarga = document.getElementById("cargando");
	if (iconosDeCarga !== null) {
		//MUESTRA ICONOS DE CARGANDO
		iconosDeCarga.style.visibility = "visible";
		//DESPUES DEL TIEMPO ESPECIFICADO, OCULTA LOS ICONOS DE CARGA Y LLAMA A LA FUNCION OBTENER LISTA ODONTOLOGOS QUE CARGA TODOS LOS ODONTOLOGOS EXISTENTES
		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			obtenerListaOdontologos();
		}, 3000);
	}

	//################################################################################
	// CAPTURA EL EVENTO SUBMIT DEL FORMULARIO DE BUSQUEDA PERSONALIZADA
	let formularioBusqueda = document.getElementById("formBusqueda");
	if (formularioBusqueda !== null) {
		formularioBusqueda.addEventListener("submit", function (event) {
			event.preventDefault();

			clearTabla();
			tablaNueva(tituloTabla);

			//ESTA PARTE SE PODRIA SIMPLIFICAR PASANDO EL METODO BUSQUEDA ODONTOLOGO PERSONALIZADA POR PARAMETRO YA QUE ES MUY SIMILAR A LA FUNCION SUBMIT
			//ICONOS DE LOADING
			let iconosDeCarga = document.getElementById("cargando");
			iconosDeCarga.style.visibility = "visible";

			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				busquedaOdontologoPersonalizada();
			}, 3000);
		});
	}

	//################################################################################
	//DISPARA EL EVENGO CHANGE DEL COMBOBOX DE BUSQUEDA PARA RESETEAR EL FILTRO DE BUSQUEDA
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
			//ESTO SE PODRIA HACER UNA FUNCION PORQUE TAMBIEN SE REPITE
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
		} class='btn btn-primary btnTabla btnTablaModificar dropdown-item' data-toggle='modal' data-target='#staticBackdrop' data-id='${dentist.id}' data-nombre='${dentist.nombre}'data-apellido='${dentist.apellido}' data-matricula='${
		dentist.matricula
	}'>Modificar</button><button type='button' data-id=${dentist.id} class='btn btn-primary dropdown-item btnTabla btnTablaEliminar' data-toggle='modal' data-target='#staticBackdropOdontologoDelete' >Eliminar</button></div></div></td>`;
}

let tituloTabla = "<th scope='col'>Id</th>" + "<th scope='col'>Nombre</th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>Matrícula</th>" + "<th scope='col'>Gestionar </th>";
