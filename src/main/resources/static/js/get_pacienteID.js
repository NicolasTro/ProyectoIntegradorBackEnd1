window.addEventListener("load", function () {
  let busqueda = document.querySelector(".busqueda");

  busqueda.addEventListener("submit", function (event) {
    event.preventDefault();

    let idBuscar = document.getElementById("search");

    let valorBusqueda = idBuscar.value;

    console.log("valor busqueda" + valorBusqueda);

    (function () {
      const url = `/pacientes/${valorBusqueda}`;
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
          let patientRow = tablaBody.insertRow();

          let tr_id = "tr_" + patient.id;

          patientRow.id = tr_id;
          patientRow.innerHTML = cargarRegistro(data);

        })
        .catch((error) => {
          noSeEncontraronRegistros();
        });
    })();
    (function () {
      let pathname = window.location.pathname;
      if (pathname == "/pacienteLista.html") {
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
