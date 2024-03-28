window.addEventListener("load", function () {
	//CARGA DE CABEZAL Y CUERPO DE TABLA DE REGISTROS
	//################################################################################

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
	(function () {
		let pathname = window.location.pathname;
		if (pathname == "/odontologoLista.html") {
			document.querySelector(".nav .nav-item a:last").addClass("active");
		}
	});

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
	if (validacionInputId !== null) {
		let tipoDeBusqueda = document.getElementById("comboBusqueda");
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
  <td class="td_apellido align-middle tamanioTexto">${dentist.apellido.toUpperCase()}</td><td class="td_matricula align-middle tamanioTexto">${dentist.matricula}</td><td class='align-middle tamanioTexto'>
    <div class="dropdown"><button class='btn btn-dark dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${
			dentist.id
		} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdrop'>Modificar</button><button type='button' data-id=${
		dentist.id
	} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal' >Eliminar</button></div></div></td>`;
}

let tituloTabla = "<th scope='col'>Id</th>" + "<th scope='col'>Nombre</th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>Matrícula</th>" + "<th scope='col'>Gestionar </th>";

function validarCamposIngresados() {
	let nombreValido = false;
	let apellidoValido = false;
	let matriculaValida = false;

	let nombreOdontologo = document.getElementById("nombreOdontologo");
	let apellidoOdontologo = document.getElementById("apellidoOdontologo");
	let matriculaOdontologo = document.getElementById("matriculaOdontologo");


	let camposRequeridos = document.querySelectorAll('[required]');

	camposRequeridos.forEach(campo =>{

if(campo.value.trim()==""){

	campo.style.border = "solid red 3px";
	campo.textContent="";
	
}else{

	campo.style.border ="none";
}


	})



	if (nombreOdontologo.value.trim() === "") {
		// nombreOdontologo.classList.add("alert-danger");
		// nombreOdontologo.classList.add("border-danger");
		
		

		nombreValido = false;
	} else {
		nombreOdontologo.classList.remove("alert-danger");
		nombreOdontologo.classList.remove("border-danger");
		nombreOdontologo.ariaPlaceholder = "";
		nombreValido = true;
	}

	if (apellidoOdontologo.value.trim() === "") {
		apellidoOdontologo.classList.add("alert-danger");
		apellidoOdontologo.classList.add("border-danger");
		apellidoValido = false;
	} else {
		apellidoOdontologo.classList.remove("alert-danger");
		apellidoOdontologo.classList.remove("border-danger");
		apellidoValido = true;
	}
	if (matriculaOdontologo.value.trim() === "") {
		matriculaOdontologo.classList.add("alert-danger");
		matriculaOdontologo.classList.add("border-danger");
		matriculaValido = false;
	} else {
		matriculaOdontologo.classList.remove("alert-danger");
		matriculaOdontologo.classList.remove("border-danger");
		matriculaValido = true;
	}
	if (nombreValido && apellidoValido && matriculaValida) {
		return true;
	} else {
		return false;
	}
}
