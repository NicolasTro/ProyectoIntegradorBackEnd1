function obtenerListaOdontologos() {
  const url = "/odontologos/listar";
  const settings = {
    method: "GET",
  };

  return fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      // let a = document.getElementById("dentistTable");      
      if (data.length > 0) {
        // if (a != null) {
          let body = document.getElementById("cuerpoTabla");
          console.log(body);
          for (dentist of data) {
            dentistRow = body.insertRow();
            let tr_id = "tr_" + dentist.id;
            dentistRow.id = tr_id;            
            dentistRow.innerHTML = cargarRegistro(dentist);           
          }
        // }
      } else {
        noSeEncontraronRegistros();
      }
    })
    .catch((error) => {
      noSeEncontraronRegistros();
     
    });
}

window.addEventListener("load", function () {
  cargarEncabezadoTabla();
  cargarCuerpoTabla();
  obtenerListaOdontologos();
  (function () {
    let pathname = window.location.pathname;
    if (pathname == "/odontologoLista.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
  let listado = document.getElementById("listar");
  listado.addEventListener("click", function (event) {
    event.preventDefault();
    clearTabla();
    obtenerListaOdontologos();
  });
});

function cargarEncabezadoTabla() {
  let tabla = document.getElementById("dentistTable");
  let encabezadoTabla = document.createElement("thead");
  encabezadoTabla.id = "encabezado";
  tabla.appendChild(encabezadoTabla);
  let dentistRowHeader = document.getElementById("encabezado");
  dentistRowHeader.innerHTML = "<th scope='col'>Id</th>" + "<th scope='col'>Nombre</th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>Matrícula</th>" + "<th scope='col'>Gestionar </th>";
}

function noSeEncontraronRegistros() {
  clearTabla();
  let sinRegistro = document.createElement("tr");
  sinRegistro.id = "idSinRegistro";
  document.getElementById("cuerpoTabla").appendChild(sinRegistro);
  let filSinRegistro = document.getElementById("idSinRegistro");
  filSinRegistro.innerHTML = "<td></td><td>No se encontraron registros</td><td></td><td></td><td></td>";
}

function cargarCuerpoTabla() {
  let tabla = document.getElementById("dentistTable");
  let cuerpoTabla = document.createElement("tbody");
  cuerpoTabla.id = "cuerpoTabla";
  tabla.appendChild(cuerpoTabla);
}

function clearTabla() {
  let tabla = document.getElementById("dentistTable");
  tabla.innerHTML = "";
  cargarEncabezadoTabla();
  cargarCuerpoTabla();
}

function cargarRegistro(dentist){
  return  `<td class="td_id align-middle tamanioTexto">${dentist.id}</td><td class="td_nombre align-middle tamanioTexto">${dentist.nombre.toUpperCase()}</td>
  <td class="td_apellido align-middle tamanioTexto">${dentist.apellido.toUpperCase()}</td><td class="td_matricula align-middle tamanioTexto">${
dentist.matricula
}</td><td><div class="dropdown"><button class='btn btn-secondary dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${
dentist.id
} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdrop'>Modificar</button><button type='button' data-id=${
dentist.id
} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal' >Eliminar</button></div></div></td>`;



}