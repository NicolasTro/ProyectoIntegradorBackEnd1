window.addEventListener("load", function () {
  let busqueda = document.querySelector(".busqueda");

  busqueda.addEventListener("submit", function (event) {
    event.preventDefault();

    let idBuscar = document.getElementById("search");

    let valorBusqueda = idBuscar.value;

    console.log("valor busqueda" + valorBusqueda);

    (function () {
      const url = `/odontologos/${valorBusqueda}`;
      const settings = {
        method: "GET",
      };

      fetch(url, settings)
        .then((response) => response.json())
        .then((data) => {
          clearTabla();

          let tablaBody = document.getElementById("cuerpoTabla");
          console.log(tablaBody);
          tablaBody.innerHTML = "";
          let dentistRow = tablaBody.insertRow();

          let tr_id = "tr_" + dentist.id;

          dentistRow.id = tr_id;
          dentistRow.innerHTML = cargarRegistro(data);
          
          
          // `<td class="td_id align-middle tamanioTexto">${data.id}</td><td class="td_nombre align-middle tamanioTexto">${data.nombre.toUpperCase()}</td>
          //                        <td class="td_apellido align-middle tamanioTexto">${data.apellido.toUpperCase()}</td><td class="td_matricula align-middle tamanioTexto">${
          //   data.matricula
          // }</td><td><div class="dropdown"><button class='btn btn-secondary dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${
          //   data.id
          // } class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdrop'>Modificar</button><button type='button' data-id=${
          //   data.id
          // } class='btn btn-primary dropdown-item btnTabla' data-toggle='modal' >Eliminar</button></div></div></td></tbody>`;
        })
        .catch((error) => {
          noSeEncontraronRegistros();
        });
    })();
    (function () {
      let pathname = window.location.pathname;
      if (pathname == "/odontologoLista.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    });
  });

  let inputSearch = document.getElementById("search");

  inputSearch.addEventListener("keypress", function (event) {
    const codigoTecla = event.keyCode;

    if (codigoTecla === 8 || (codigoTecla >= 48 && codigoTecla <= 57)) {
      return true;
    } else {
      event.preventDefault();
      return false;
    }
  });
});
