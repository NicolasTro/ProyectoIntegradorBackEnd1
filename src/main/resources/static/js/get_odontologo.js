window.addEventListener("load", function () {
  
  (function () {
    const url = "/odontologos/listar";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        //recorremos la colección de odontólogos del JSON
        
        if (data.length > 0) {
          let table = document.getElementById("dentistTable");
          let dentistRowHeader = table.insertRow();

          dentistRowHeader.innerHTML =
            "<thead><tr>" +
            "<th scope='col'>Id</th>" +
            "<th scope='col'>Nombre</th>" +
            "<th scope='col'>Apellido</th>" +
            "<th scope='col'>Matrícula</th>" +
            "<th scope='col'>Gestionar </th></tr></thead>";

          for (dentist of data) {
            let table = document.getElementById("dentistTable");
            let dentistRow = table.insertRow();
            let tr_id = "tr_" + dentist.id;
            dentistRow.id = tr_id;

            dentistRow.innerHTML = `<td class="td_id align-middle tamanioTexto">${
              dentist.id
            }</td><td class="td_nombre align-middle tamanioTexto">${dentist.nombre.toUpperCase()}</td>
                                   <td class="td_apellido align-middle tamanioTexto">${dentist.apellido.toUpperCase()}</td><td class="td_matricula align-middle tamanioTexto">${
              dentist.matricula
            }</td><td><div class="dropdown"><button class='btn btn-secondary dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${
              dentist.id
            } class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdrop'>Modificar</button><button type='button' data-id=${
              dentist.id
            } class='btn btn-primary dropdown-item btnTabla' data-toggle='modal' >Eliminar</button></div></div></td>`;
          }
          
          //  let dentistRow = table.insertRow();
          // dentistRow.innerHTML = "<td></td><button type='button' class='btn btn-primary' >Registrar odontologo</button><td></td><td></td><td></td>"


          // let tablaLlena = document.getElementById("listadoOdontologo")
          // tablaLlena.innerHTML += "<button type='button' class='btn btn-primary' >Registrar odontologo</button><td></td><td></td><td></td><td></td>"



        } else {
          let table = document.getElementById("dentistTableBody");
          let dentistRow = table.insertRow();
          dentistRow.innerHTML = "<td>No se encontraron registros</td>";
        }
      })
      .catch((error) => {
        let table = document.getElementById("dentistTableBody");
        let dentistRow = table.insertRow();
        dentistRow.innerHTML = "<td>No se encontraron registros</td>";
      });
  })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/odontologoLista.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});
