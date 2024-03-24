window.addEventListener("load", function () {
  cargarEncabezadoTabla();
  cargarCuerpoTabla();
  obtenerListaPacientes();

  let formularioBusqueda = document.getElementById("formBusqueda");
  console.log(formularioBusqueda);
  formularioBusqueda.addEventListener("submit", function (event) {
    event.preventDefault();
    clearTabla();
    cargarEncabezadoTabla();
    cargarCuerpoTabla();
    console.log("aca no busca");
    let iconosDeCarga = document.getElementById("cargando");
    iconosDeCarga.style.visibility = "visible";

    setTimeout(function () {
      iconosDeCarga.style.visibility = "hidden";
      busquedaPacientePersonalizado();
    }, 3000);
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
    })
    .catch((error) => {
      noSeEncontraronRegistro();
    });
}

function cargarEncabezadoTabla() {
  let table = document.getElementById("patientTable");
  let encabezadoTabla = document.createElement("thead");
  encabezadoTabla.id = "encabezado";
  table.appendChild(encabezadoTabla);
  let pacienteRowHeader = document.getElementById("encabezado");
  // "<th scope='col'>Apellido</th>" +

  pacienteRowHeader.innerHTML =
    "<th scope='col'>Id</th>" +
    "<th scope='col'>Nombre y Apellido" +
    "<th scope='col'>DNI</th>" +
    "<th scope='col'>FechaIngreso</th>" +
    "<th scope='col'>ID_domicilio</th>" +
    "<th scope='col'>Gestionar</th></tr></thead>";
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

function cargarRegistro(patient) {
  return `<td class="td_id align-middle tamanioTexto">${patient.id}</td>
                                        <td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()} 
                                        ${patient.apellido.toUpperCase()}</td>
                                        <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                        <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                        <td class="td_domicilio align-middle tamanioTexto">${patient.domicilio.id}</td>
                                        <td>
                                          <div class="dropdown">
                                            <button class='btn btn-secondary dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                            <div class='dropdown-menu'>
                                              <button type='button' data-id=${patient.id} data-domicilioId=${
    patient.domicilio.id
  } class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
                                              <button type='button' data-id=${patient.id} data-domicilioId=${
    patient.domicilio.id
  } class='btn btn-primary dropdown-item btnTabla' data-toggle='modal'>Eliminar</button>
                                            </div>
                                          </div>
                                        </td>`;
}
function busquedaPacientePersonalizado() {
  

  let tipoDeBusqueda = document.getElementById("comboBusqueda");
  let valorBusqueda = document.getElementById("search");
  console.log(tipoDeBusqueda.value);

  const url = `/pacientes/buscar?valor=${valorBusqueda.value}&tipoDeBusqueda=${tipoDeBusqueda.value}`;
  console.log(url);
  const settings = {
    method: "GET",
  };

  return fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      clearTabla();
      tablaNueva();

      if (data.length == 1) {
        console.log(data);
        console.log(data[0].id);
        let tablaBody = document.getElementById("cuerpoTabla");
        console.log(tablaBody);
        tablaBody.innerHTML = "";
        let patientRow = tablaBody.insertRow();

        let tr_id = "tr_" + data[0].id;

        patientRow.id = tr_id;
        patientRow.innerHTML = cargarRegistro(data[0]);
      } else if (data.length > 1) {
        let body = document.getElementById("cuerpoTabla");
        console.log(body);
        for (patient of data) {
          patientRow = body.insertRow();
          let tr_id = "tr_" + patient.id;
          patientRow.id = tr_id;
          patientRow.innerHTML = cargarRegistro(patient);
        }
      } else {
        noSeEncontraronRegistros();
      }
    })
    .catch((error) => {
      noSeEncontraronRegistros();
      console.log("adasd");
    });

  // todo PARA QUE SIRVE ESTA FUNCION
  // (function () {
  //     let pathname = window.location.pathname;
  //     if (pathname == "/odontologoLista.html") {
  //       document.querySelector(".nav .nav-item a:last").addClass("active");
  //     }
  //   });
}
function noSeEncontraronRegistro() {
  clearTabla();
  let sinRegistro = document.createElement("tr");
  sinRegistro.id = "idSinRegistro";

  document.getElementById("cuerpoTabla").appendChild(sinRegistro);

  let filSinRegistro = document.getElementById("idSinRegistro");

  filSinRegistro.innerHTML = "<td></td><td>No se encontraron registros</td><td></td><td></td><td></td>";
}
