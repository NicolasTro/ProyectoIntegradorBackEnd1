function obtenerListaPacientes() {
    const url = "/pacientes/listar";
    const settings = {
      method: "GET",
    };

    return fetch(url, settings)
          .then((response) => response.json())
          .then((data) => {
            
            // let a = document.getElementById("tablePaciente")
            if (data.length > 0) {
            let body = document.getElementById("cuerpoTabla");
            
              for (patient of data) {
                
                let patientRow = body.insertRow();
                let tr_id = "tr_" + patient.id;
                patientRow.id = tr_id;
                patientRow.innerHTML = cargarRegistro(patient)
              }
            } else {
            noSeEncontraronRegistro();
            }
          })
          .catch((error) => {
          noSeEncontraronRegistro();
            });
}
window.addEventListener("load", function () {
  
  cargarEncabezadoTabla();
  cargarCuerpoTabla();
  obtenerListaPacientes();
  (function () {
    let pathname = window.location.pathname;
    if (pathname == "/pacienteLista.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
  let listado = document.getElementById("listar");
  listado.addEventListener("click", function (event) {
  event.preventDefault();
  clearTabla();
  obtenerListaPacientes();
  })
});

function cargarEncabezadoTabla() {
    let table = document.getElementById("tablePaciente");
    let pacienteRowHeader = table.insertRow();

    pacienteRowHeader.innerHTML =
    "<thead><tr>" +
    "<th scope='col'>Id</th>" +
    "<th scope='col'>Nombre</th>" +
    "<th scope='col'>Apellido</th>" +
    "<th scope='col'>DNI</th>" +
    "<th scope='col'>FechaIngreso</th>" +
    "<th scope='col'>ID_domicilio</th>" +
    "<th scope='col'>Gestionar</th></tr></thead>";

}
function noSeEncontraronRegistro() {
    clearTabla();
    let sinRegistro = document.createElement("tr");
    sinRegistro.id = "idSinRegistro";

    document.getElementById("cuerpoTabla").appendChild(sinRegistro);

    let filSinRegistro = document.getElementById("idSinRegistro");

    filSinRegistro.innerHTML = "<td>No se encontraron registros</td>";

}
function cargarCuerpoTabla(){
    let tabla = document.getElementById("tablePaciente");
    let cuerpoTabla = document.createElement("tbody");
    cuerpoTabla.id = "cuerpoTabla";
    tabla.appendChild(cuerpoTabla);

}
function clearTabla() {
    let tabla = document.getElementById("tablePaciente");
    tabla.innerHTML = "";
    cargarEncabezadoTabla();
    cargarCuerpoTabla();

}
function cargarRegistro(patient){
return `<td class="td_id align-middle tamanioTexto">${patient.id}</td>
                                        <td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}</td>
                                        <td class="td_apellido align-middle tamanioTexto">${patient.apellido.toUpperCase()}</td>
                                        <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                        <td class="td_fecha align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                        <td class="td_domicilio align-middle tamanioTexto">${patient.domicilio.id}</td>
                                        <td>
                                          <div class="dropdown">
                                            <button class='btn btn-secondary dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                            <div class='dropdown-menu'>
                                              <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropPacienteUpdate'>Modificar</button>
                                              <button type='button' data-id=${patient.id} data-domicilioId=${patient.domicilio.id} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal'>Eliminar</button>
                                            </div>
                                          </div>
                                        </td>`;
}
