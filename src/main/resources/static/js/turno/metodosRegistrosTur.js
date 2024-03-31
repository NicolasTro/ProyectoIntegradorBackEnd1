// FUNCION WINDOWS LOAD
window.addEventListener("load", function () {
	// validarFecha();
	tablaNueva(tituloTablaTurnos);

	let iconosDeCarga = document.getElementById("cargando");
	if (iconosDeCarga !== null) {
		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			obtenerListaTurnos();
		}, 3000);
	}

	let btnRegistrarTurno = document.getElementById("btnRegistrarTurnos");
	if (btnRegistrarTurno !== null) {
		btnRegistrarTurno.addEventListener("click", function () {
			listarPacientes('.comboTurnoPaciente');
			listarOdontologos('.comboTurnoOdontologo');
			validarFecha();
		});
	}

	let formularioBusqueda = document.getElementById("formBusqueda");
	if (formularioBusqueda !== null) {
		formularioBusqueda.addEventListener("submit", function (event) {
			event.preventDefault();
			clearTabla();
			tablaNueva(tituloTablaTurnos);

			iconosDeCarga.style.visibility = "visible";

			setTimeout(function () {
				iconosDeCarga.style.visibility = "hidden";
				busquedaTurnosPersonalizado();
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
				calendario.type = "datetime-local";

				calendario.className = "form-control mr-sm-2 idBusqueda btnHeaderRegistro";
				calendario.id = "searchCalendar";
				calendario.required = true;

				formBusqueda.replaceChild(calendario, inputBusqueda);
			}
		});
	}


let btnReset = document.querySelector('#btnResetBusquedaTurno');




if (btnReset !== null) {
	btnReset.addEventListener("click", function (event) {
		let tipoDeBusqueda = document.getElementById("comboBusqueda");
		let validacionInputId = document.getElementById("search");
		tipoDeBusqueda.value = 1;

		if(validacionInputId!==null){


			validacionInputId.value = "";


		}else{


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
		tablaNueva(tituloTablaTurnos);

		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			obtenerListaTurnos();
		}, 3000);
	});
}



	validacionInput();
});
function cargarRegistro(turno) {
	return `<td class="td_id align-middle tamanioTexto">${turno.id}</td><td class="td_odontologo align-middle tamanioTexto" data-odontologoid='${turno.odontologo_Id}'>${turno.odontologoNombre} ${turno.odontologoApellido}</td>
    <td class="td_paciente align-middle tamanioTexto" data-pacienteid='${turno.paciente_Id}'>${turno.pacienteNombre} ${turno.pacienteApellido}</td><td class="td_fechaTurno align-middle tamanioTexto">${turno.fechaYHora}</td><td><div class="dropdown"><button class='btn btn-secondary dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${turno.id} class='btn btn-primary btnTabla btnTablaModificar dropdown-item' data-toggle='modal' data-target='#staticBackdropTurnoUpdate'
	data-id='${turno.id}' data-pacienteid='${turno.paciente_Id}' data-odontologoid='${turno.odontologo_Id}' data-fechayhora='${turno.fechaYHora}'
	
	
	>Modificar</button><button type='button' data-id=${turno.id} class='btn btn-primary dropdown-item btnTabla btnTablaEliminar' data-toggle='modal'>Eliminar</button></div></div></td>`;
}
let tituloTablaTurnos = "<th scope='col'>Id</th>" + "<th scope='col'>Odontologo</th>" + "<th scope='col'>Paciente</th>" + "<th scope='col'>Fecha y hora</th>" + "<th scope='col'>Gestionar </th>";






