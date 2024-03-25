window.addEventListener("load", function () {
	let iconosDeCarga = document.getElementById("cargando");
	cargarEncabezadoTabla();
	cargarCuerpoTabla();
	iconosDeCarga.style.visibility = "visible";

	setTimeout(function () {
		iconosDeCarga.style.visibility = "hidden";

		obtenerListaPacientes();
	}, 300);

	let formularioBusqueda = document.getElementById("formBusqueda");

	formularioBusqueda.addEventListener("submit", function (event) {
		event.preventDefault();
		clearTabla();
		cargarEncabezadoTabla();
		cargarCuerpoTabla();

		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			busquedaPacientePersonalizado();
		}, 300);
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
			calendario.type = "date";

			calendario.className = "form-control mr-sm-2 idBusqueda btnHeaderRegistro";
			calendario.id = "searchCalendar";
			calendario.required = true;

			formBusqueda.replaceChild(calendario, inputBusqueda);
		}
	});

	validacionInput();
  

	let btnReset = document.getElementById("btnReset");



	btnReset.addEventListener("click", function (event) {
		let tipoDeBusqueda = document.getElementById("comboBusqueda");
		let validacionInputId = document.getElementById("search");
		tipoDeBusqueda.value = 1;
		validacionInputId.value = "";

		clearTabla();
		tablaNueva();
		cargarEncabezadoTabla();
		cargarCuerpoTabla();
		// let iconosDeCarga = document.getElementById("cargando");
		iconosDeCarga.style.visibility = "visible";

		setTimeout(function () {
			iconosDeCarga.style.visibility = "hidden";
			obtenerListaPacientes();




     


      console.log(elementos.tagName);



		}, 3000);
	});

	
 
  // cargarDomicilio();
  
  
  
  
  
  
    
    // elementos.forEach(function(elemento) {
    //   console.log(elemento);
    //     let popover = new bootstrap.Popover(elemento);

    // });

  
  
  
  

    



    this.document.addEventListener('click', function(event){

console.log(event.target);

    });
  
  
  
  
  
  
  
  
  
  
  
  
  
  (function () {
		let pathname = window.location.pathname;
		if (pathname == "/pacienteLista.html") {
			document.querySelector(".nav .nav-item a:last").addClass("active");
		}
	});




});

function obtenerListaPacientes() {
	const url = "/pacientes/listar";
	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			if (data.length > 0) {
				let body = document.getElementById("cuerpoTabla");

				for (patient of data) {
					let patientRow = body.insertRow();
					let tr_id = "tr_" + patient.id;
					patientRow.id = tr_id;
					patientRow.innerHTML = cargarRegistro(patient);




				}
			} else {
				noSeEncontraronRegistro();
			}
      cargarDomicilio();
		})
		.catch((error) => {
			noSeEncontraronRegistro();
		});
}

function cargarEncabezadoTabla() {
	let table = document.getElementById("patientTable");
	let encabezadoTabla = document.createElement("thead");
	encabezadoTabla.id = "encabezado";
	encabezadoTabla.className = "thead-dark ";
	encabezadoTabla.className += "sticky-top";
	table.appendChild(encabezadoTabla);
	let pacienteRowHeader = document.getElementById("encabezado");
	// "<th scope='col'>Apellido</th>" +

	pacienteRowHeader.innerHTML = "<th scope='col'>Id</th>" + "<th scope='col'>Nombre y Apellido" + "<th scope='col'>DNI</th>" + "<th scope='col'>FechaIngreso</th>" + "<th scope='col'>Domicilio</th>" + "<th scope='col'>Gestionar</th></tr></thead>";
}

function cargarCuerpoTabla() {
	let tabla = document.getElementById("patientTable");
	let cuerpoTabla = document.createElement("tbody");
	cuerpoTabla.id = "cuerpoTabla";
	tabla.appendChild(cuerpoTabla);
}

function tablaNueva() {
	cargarEncabezadoTabla();
	cargarCuerpoTabla();
}

function clearTabla() {
	let tabla = document.getElementById("patientTable");
	tabla.innerHTML = "";
	// cargarEncabezadoTabla();
	// cargarCuerpoTabla();
}

// function cargarRegistro(patient) {
  
// 	return `<td class="td_id align-middle tamanioTexto">${patient.id}</td><td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}${patient.apellido.toUpperCase()}</td>
//                                         <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
//                                         <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
//                                         <td class="td_domicilio align-middle tamanioTexto"><button type='button' class='btn btn-dark' data-domicilioId=${patient.domicilio.id} id='masInfo'>mas info</button></td>
//                                         <td>
//                                           <div class="dropdown">
//                                             <button class='btn btn-dark dropdown-toggle' 
//                                             type='button' data-toggle='dropdown' aria-expanded='false'></button>
//                                             <div class='dropdown-menu'>
//                                               <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
//                                               <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal'>Eliminar</button>
//                                             </div>
//                                           </div>
//                                         </td>`;
// }
{/* <button type='button' class='btn btn-dark' data-domicilioId=${patient.domicilio.id} id='masInfo'>mas info</button> */}
function cargarRegistro(patient) {
  
	return `<td class="td_id align-middle tamanioTexto">${patient.id}</td><td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}${patient.apellido.toUpperCase()}</td>
                                        <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                        <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                        <td class="td_domicilio align-middle tamanioTexto">
                                        
                                        <button type="button" class="btn btn-secondary popOver" data-container="body" data-toggle="popover" data-placement="top" data-content="Top popover" id='popo'>
                                        mas info
                                      </button>

                                        
                                        
                                        
                                        
                                        </td>
                                        <td>
                                          <div class="dropdown">
                                            <button class='btn btn-dark dropdown-toggle' 
                                            type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                            <div class='dropdown-menu'>
                                              <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
                                              <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal'>Eliminar</button>
                                            </div>
                                          </div>
                                        </td>`;
}
function busquedaPacientePersonalizado() {
	let tipoDeBusqueda = document.getElementById("comboBusqueda");
	let valorBusqueda;
	if (tipoDeBusqueda.value <= 3) {
		valorBusqueda = document.getElementById("search");
	} else {
		valorBusqueda = document.getElementById("searchCalendar");
	}

	const url = `/pacientes/buscar?valor=${valorBusqueda.value}&tipoDeBusqueda=${tipoDeBusqueda.value}`;

	const settings = {
		method: "GET",
	};

	return fetch(url, settings)
		.then((response) => response.json())
		.then((data) => {
			clearTabla();
			tablaNueva();

			if (data.length == 1) {
				let tablaBody = document.getElementById("cuerpoTabla");

				tablaBody.innerHTML = "";
				let patientRow = tablaBody.insertRow();

				let tr_id = "tr_" + data[0].id;

				patientRow.id = tr_id;
				patientRow.innerHTML = cargarRegistro(data[0]);
			} else if (data.length > 1) {
				let body = document.getElementById("cuerpoTabla");

				for (patient of data) {
					patientRow = body.insertRow();
					let tr_id = "tr_" + patient.id;
					patientRow.id = tr_id;
					patientRow.innerHTML = cargarRegistro(patient);

          let 
				}






			} else {
				noSeEncontraronRegistrosPaciente();
				// noSeEncontraronRegistrosPaciente();
			}
		})
		.catch((error) => {
			console.log("aca");
			noSeEncontraronRegistroPaciente();
		});

	// todo PARA QUE SIRVE ESTA FUNCION
	(function () {
		let pathname = window.location.pathname;
		if (pathname == "/pacienteLista.html") {
			document.querySelector(".nav .nav-item a:last").addClass("active");
		}
	})();
}
function noSeEncontraronRegistroPaciente() {
	console.log("q pasa");
	clearTabla();

	let sinRegistro = document.createElement("tr");
	sinRegistro.id = "idSinRegistro";

	document.getElementById("patientTable").appendChild(sinRegistro);

	let filSinRegistro = document.getElementById("idSinRegistro");

	filSinRegistro.innerHTML = "<td></td><td>No se encontraron registros</td><td></td><td></td><td></td>";
}

function validacionInput() {
	let validacionInputId = document.getElementById("search");
	let tipoDeBusqueda = document.getElementById("comboBusqueda");
	tipoDeBusqueda.addEventListener("change", function () {
		validacionInputId.value = "";
	});

	validacionInputId.addEventListener("keypress", function (event) {
		// console.log(tipoDeBusqueda);

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

function cargarDomicilio(){

  
  let btnMasInfo = document.querySelectorAll("#masInfo");
  
btnMasInfo.forEach(element => {
  
  
  element.addEventListener('click', function(event){
   
   




    
  })
});
}