function obtenerListaOdontologos() {
	const url = "/odontologos/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");
				//SI SE RECIBEN REGISTROS EN DATA, LOS RECORRE, CREA UNA FILA NUEVA Y LLAMA A LA FUNCION CARGAR REGISTRO QUE RECIBE AL DENTISTA POR PARAMETRO
				for (dentist of data) {
					dentistRow = body.insertRow();
					let tr_id = "tr_" + dentist.id;
					dentistRow.id = tr_id;
					dentistRow.innerHTML = cargarRegistro(dentist);
				}

				let listaBtnModificar = document.querySelectorAll(".btnTablaModificar");
				let listaBtnEliminar = document.querySelectorAll(".btnTablaEliminar");
				//INVOCAMOS A LAS FUNCIONES ACTUALIZAR Y ELIMINAR QUE RECIBEN UNA LISTA DE BOTONES CADA UNA PARA DARLE FUNCIONALIDADES ESPECIFICAS
				actualizarOdontologo(listaBtnModificar);
				eliminarOdontologo(listaBtnEliminar);
			} else {
				//FUNCION PARA ELIMINAR RESTOS DE LA TABLA Y MOSTRAR NO SE ENCONTRARON REGISTROS
				noSeEncontraronRegistros();
			}
		})
		.catch(error => {
			console.log(error);
			noSeEncontraronRegistros();
		});
}

function busquedaOdontologoPersonalizada() {
	let tipoDeBusqueda = document.getElementById("comboBusqueda");
	let valorBusqueda = document.getElementById("search");

	const url = `/odontologos/buscar?valor=${valorBusqueda.value}&tipoDeBusqueda=${tipoDeBusqueda.value}`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then(response => response.json())
		.then(data => {
			//LIMPIAMOS TABLA CON CLEAR TABLA, Y CARGAMOS NUEVAMENTE EL ENCABEZADO Y EL CUERPO
			clearTabla();
			tablaNueva(tituloTabla);
			//ESTA PARTE SE PUEDE SIMPILFICAR EN UNA SEGUNDA INSTANCIA
			//#################################################################
			if (data.length == 1) {
				let tablaBody = document.getElementById("cuerpoTabla");

				tablaBody.innerHTML = "";
				let dentistRow = tablaBody.insertRow();

				let tr_id = "tr_" + data[0].id;

				dentistRow.id = tr_id;
				dentistRow.innerHTML = cargarRegistro(data[0]);
				//###############################################################
			} else if (data.length > 1) {
				let body = document.getElementById("cuerpoTabla");

				for (dentist of data) {
					dentistRow = body.insertRow();
					let tr_id = "tr_" + dentist.id;
					dentistRow.id = tr_id;
					dentistRow.innerHTML = cargarRegistro(dentist);
				}
			} else {
				noSeEncontraronRegistros();
			}
		})
		.catch(error => {
			noSeEncontraronRegistros();
		});
}
