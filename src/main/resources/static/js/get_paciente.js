window.addEventListener("load", function () {
  console.log("cargando lista");

  (function () {
    const url = "/pacientes/listar";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data.length > 0) {
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

          for (patient of data) {
            console.log(patient)
            let table = document.getElementById("tablePaciente");
            let patientRow = table.insertRow();
            let tr_id = "tr_" + patient.id;
            patientRow.id = tr_id;

            patientRow.innerHTML = `<td class="td_id align-middle tamanioTexto">${patient.id}</td>
                                    <td class="td_nombre align-middle tamanioTexto">${patient.nombre.toUpperCase()}</td>
                                    <td class="td_apellido align-middle tamanioTexto">${patient.apellido.toUpperCase()}</td>
                                    <td class="td_dni align-middle tamanioTexto">${patient.dni}</td>
                                    <td class="td_dni align-middle tamanioTexto">${patient.fechaIngreso}</td>
                                    <td class="td_domicilio align-middle tamanioTexto">${patient.domicilio.id}</td>
                                    <td>
                                      <div class="dropdown">
                                        <button class='btn btn-secondary dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'></button>
                                        <div class='dropdown-menu'>
                                          <button type='button' data-id=${patient.id} class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdrop'>Modificar</button>
                                          <button type='button' data-id=${patient.id} class='btn btn-primary dropdown-item btnTabla' data-toggle='modal'>Eliminar</button>
                                        </div>
                                      </div>
                                    </td>`;
          }
        } else {
          let table = document.getElementById("pacienteTableBody");
          let patientRow = table.insertRow();
          patientRow.innerHTML = "<td>No se encontraron registros</td>";
        }
      })
      .catch((error) => {
        console.error("Error al obtener la lista de pacientes:", error);
        let table = document.getElementById("pacienteTableBody");
        let patientRow = table.insertRow();
        patientRow.innerHTML = "<td>No se encontraron registros</td>";
        });
      })();

      (function () {
        let pathname = window.location.pathname;
        if (pathname == "/pacienteLista.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
        }
      });
    });
//      .catch((error) => {
//      });
//  });

