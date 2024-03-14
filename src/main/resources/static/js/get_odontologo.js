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

        if(data.length>0){





let table = document.getElementById("dentistTable");
let dentistRow = table.insertRow();

            dentistRow.innerHTML = "<thead><tr>"+
                    "<th scope='col'>Id</th>" + "<th scope='col'>Nombre</th>" + "<th scope='col'>Apellido</th>" + "<th scope='col'>Matrícula</th>"+ "<th scope='col'>Gestionar </th></tr></thead>"

          for (dentist of data) {
            //por cada odontólogo armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontólogo
            let table = document.getElementById("dentistTable");
            let dentistRow = table.insertRow();
console.log("entro aca")


            let tr_id = "tr_" + dentist.id;
            dentistRow.id = tr_id;


            //            dentistRow.innerHTML =  `
            //                                        <td class="td_id">${dentist.id}</td>
            //                                        <td class="td_nombre">${dentist.nombre.toUpperCase()}</td>
            //                                        <td class="td_apellido">${dentist.apellido.toUpperCase()}</td>
            //                                        <td class="td_matricula">${dentist.matricula}</td>
            //                                    `;
            dentistRow.innerHTML =
            '<td class="td_id align-middle">' +
              dentist.id +
              "</td>" +
              '<td class="td_nombre align-middle">' +
              dentist.nombre.toUpperCase() +
              "</td>" +
              '<td class="td_apellido align-middle">' +
              dentist.apellido.toUpperCase() +
              "</td>" +
              '<td class="td_matricula align-middle">' +
              dentist.matricula +
              "</td>"+ "<td><div class='dropdown'><button class='btn btn-secondary dropdown-toggle' type='button'"+
              "data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><a class='dropdown-item' href='#'>Modificar</a>"+
              "<a class='dropdown-item' href='#'>Eliminar</a></div></div></td>";


          }
        }else{

        let table = document.getElementById("dentistTable");
                  let dentistRow = table.insertRow();

                  dentistRow.innerHTML = "<td>" + "No se encontraron registros" + "</td>";
        }

      })
      .catch((error) => {

 let table = document.getElementById("dentistTable");
          let dentistRow = table.insertRow();
          // let tr_id = "tr_" + dentist.id;
          // dentistRow.id = tr_id;

          //            dentistRow.innerHTML =  `
          //                                        <td class="td_id">${dentist.id}</td>
          //                                        <td class="td_nombre">${dentist.nombre.toUpperCase()}</td>
          //                                        <td class="td_apellido">${dentist.apellido.toUpperCase()}</td>
          //                                        <td class="td_matricula">${dentist.matricula}</td>
          //                                    `;
          dentistRow.innerHTML = "<td>" + "No se encontraron registros" + "</td>";

          // dentist.id +
          // "</td>" +
          // '<td class="td_nombre">' +
          // dentist.nombre.toUpperCase() +
          // "</td>" +
          // '<td class="td_apellido">' +
          // dentist.apellido.toUpperCase() +
          // "</td>" +
          // '<td class="td_matricula">' +
          // dentist.matricula +
          // "</td>";

    });
    })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/odontologoLista.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});
