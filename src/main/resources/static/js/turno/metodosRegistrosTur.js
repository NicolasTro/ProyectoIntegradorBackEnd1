// FUNCION WINDOWS LOAD
window.addEventListener("load", function () {

	tablaNueva(tituloTablaTurno);

	let iconosDeCarga = document.getElementById("cargando");
	iconosDeCarga.style.visibility = "visible";

	setTimeout(function () {
		iconosDeCarga.style.visibility = "hidden";
		obtenerListaTurnos();
	}, 3000);

	let btnRegistrarTurno = document.getElementById("btnRegistrarTurno");
	console.log(btnRegistrarTurno);

	btnRegistrarTurno.addEventListener("click", function () {
		listarPacientes();
		listarOdontologos();
	});

	let formularioBusqueda = document.getElementById("formBusqueda");

	formularioBusqueda.addEventListener("submit", function (event) {
		event.preventDefault();
		clearTabla();
		tablaNueva(tituloTablaTurnos);
		

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
			calendario.type = "datetime-local";

			calendario.className = "form-control mr-sm-2 idBusqueda btnHeaderRegistro";
			calendario.id = "searchCalendar";
			calendario.required = true;

			formBusqueda.replaceChild(calendario, inputBusqueda);
		}
	});

	validacionInput();

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
function cargarRegistro(turno) {
	return `<td class="td_id align-middle tamanioTexto">${turno.id}</td><td class="td_odontologo align-middle tamanioTexto" data-odontologoid='${turno.odontologo_Id}'>${turno.odontologoNombre} ${turno.odontologoApellido}</td>
    <td class="td_paciente align-middle tamanioTexto" data-pacienteid='${turno.paciente_Id}'>${turno.pacienteNombre} ${turno.pacienteApellido}</td><td class="td_fechaTurno align-middle tamanioTexto">${turno.fechaYHora}</td><td><div class="dropdown"><button class='btn btn-secondary dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${turno.id} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropTurnoUpdate'>Modificar</button><button type='button' data-id=${turno.id} class='btn btn-primary dropdown-item btnTabla btnTablaEliminar' data-toggle='modal'>Eliminar</button></div></div></td>`;
}
