window.addEventListener("load", function (e) {
  const formulario = document.querySelector("#formTurnoUpdate");
  let id;

  document.addEventListener("click", function (e) {
    if (e.target instanceof HTMLButtonElement) {
      if (e.target.textContent == "Modificar") {
        id = e.target.dataset.id;
        // console.log("Id seleccionado: "+ id)
      }
    }
  });
  formulario.addEventListener("submit", function (e) {
    // e.preventDefault()

    const formData = {
      id,
      paciente: {
      id: parseInt( document.querySelector("#pacienteUpdate").value),
      },
      odontologo:{
       id: parseInt(document.querySelector("#odontologoUpdate").value),
       },
      fecha: document.querySelector("#fechaUpdate").value,
    };

    const url = "/turnos/actualizar";
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    console.log(formData);

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Turno Actualizado </div>";

        document.querySelector("#responseTurnoUpdate").innerHTML = successAlert;
        document.querySelector("#responseTurnoUpdate").style.display = "block";
      })
      .catch((error) => {
        let errorAlert =
          "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";

        document.querySelector("#responseTurnoUpdate").innerHTML = errorAlert;
        document.querySelector("#responseTurnoUpdate").style.display = "block";
      });

    (function () {
      let pathname = window.location.pathname;
      if (pathname === "/") {
        document.querySelector(".nav .nav-item a:first").addClass("active");
      } else if (pathname == "/turnoLista.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    });
  });
});
