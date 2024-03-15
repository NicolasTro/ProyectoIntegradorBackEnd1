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
          
            let tr_id = "tr_" + dentist.id;
            dentistRow.id = tr_id;

            //            dentistRow.innerHTML =  `
            //                                        <td class="td_id">${dentist.id}</td>
            //                                        <td class="td_nombre">${dentist.nombre.toUpperCase()}</td>
            //                                        <td class="td_apellido">${dentist.apellido.toUpperCase()}</td>
            //                                        <td class="td_matricula">${dentist.matricula}</td>
            //                                    `;
            console.log(dentist.id)
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
              `</td><td><div class='dropdown'><button class='btn btn-secondary dropdown-toggle' type='button'
              data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${dentist.id} class='btn btn-primary dropdown-item' data-toggle='modal' data-target='#staticBackdrop'>Modificar</button>
              <a class='dropdown-item' href='#'>Eliminar</a></div></div></td>`
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



function cargarModal(dentistaId){



let retorno = `<div class='modal fade' id='staticBackdrop' data-backdrop='static' data-keyboard='false' tabindex='-1'
  aria-labelledby='staticBackdropLabel' aria-hidden='true'><div class='modal-dialog'><div class='modal-content'>
      <div class='modal-header'><h5 class='modal-title' id='staticBackdropLabel'>Actualizar Odontologo</h5>
       <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>\&times;</span></button>
      </div><div class='modal-body'><form id='formOdontologoUpdate'><div class='form-group'><label for='nombreUpdate'>Ingresa nuevo nombre</label>
      <input type='text' class='form-control' id='nombreUpdate' aria-describedby='emailHelp' placeholder='Nombre'/></div>
         <div class='form-group'><label for='apellidoUpdate'>Ingresa nuevo Apellido</label><input type='text'
         class='form-control' id='apellidoUpdate' placeholder='Apellido'/></div><div class='form-group'>
         <label for='matriculaUpdate'>Ingresa nueva matricula</label><input type='text' class='form-control' id='matriculaUpdate'
         placeholder='Matricula'/></div><div class='modal-footer'><button type='submit' data-idDentista=${dentistaId} class='btn btn-primary' id='actualizarBtn'>
         Actualizar</button><button type='button' class='btn btn-danger' data-dismiss='modal'>Cancelar Actualizacion</button></div>
        </form></div></div></div></div>`;
       
          // });
          
          
          
          return retorno;
        }
         
        
       
  

