function obtenerListaTurnos() {
    const url = "/turnos/listar";
    const settings = {
      method: "GET",
    };
  
    return fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
            console.log(data);
        if (data.length > 0) {
          
            let body = document.getElementById("cuerpoTabla");
            
            for (turno of data) {
              turnoRow = body.insertRow();
              let tr_id = "tr_" + turno.id;
              turnoRow.id = tr_id;            
              turnoRow.innerHTML = cargarRegistro(turno);           
            }
          
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

  let iconosDeCarga = document.getElementById("cargando");
  iconosDeCarga.style.visibility = "visible";

  setTimeout(function () {
    iconosDeCarga.style.visibility = "hidden";

    obtenerListaTurnos();
  }, 3000);
    (function () {
      let pathname = window.location.pathname;
      if (pathname == "/turnoLista.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    });
    
  });
  
  function cargarEncabezadoTabla() {
    let tabla = document.getElementById("turnoTable");
    let encabezadoTabla = document.createElement("thead");
    encabezadoTabla.id = "encabezado";
    tabla.appendChild(encabezadoTabla);
    let dentistRowHeader = document.getElementById("encabezado");
    dentistRowHeader.innerHTML = "<th scope='col'>Id</th>" + "<th scope='col'>Odontologo</th>" + "<th scope='col'>Paciente</th>" + "<th scope='col'>Fecha y hora</th>" + "<th scope='col'>Gestionar </th>";
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
    let tabla = document.getElementById("turnoTable");
    let cuerpoTabla = document.createElement("tbody");
    cuerpoTabla.id = "cuerpoTabla";
    tabla.appendChild(cuerpoTabla);
  }
  
  function clearTabla() {
    let tabla = document.getElementById("turnoTable");
    tabla.innerHTML = "";
    cargarEncabezadoTabla();
    cargarCuerpoTabla();
  }
  
  function cargarRegistro(turno){
    console.log(turno);
    return  `<td class="td_id align-middle tamanioTexto">${turno.id}</td><td class="td_odontologo align-middle tamanioTexto">${turno.odontologo.nombre}</td>
    <td class="td_paciente align-middle tamanioTexto">${turno.paciente.nombre}</td><td class="td_fechaTurno align-middle tamanioTexto">${turno.fechaYHora}</td><td><div class="dropdown"><button class='btn btn-secondary dropdown-toggle' type='button'data-toggle='dropdown' aria-expanded='false'></button><div class='dropdown-menu'><button type='button' data-id=${
  turno.id
  } class='btn btn-primary btnTabla dropdown-item' data-toggle='modal' data-target='#staticBackdropTurnoUpdate'>Modificar</button><button type='button' data-id=${
  turno.id
  } class='btn btn-primary dropdown-item btnTabla' data-toggle='modal' >Eliminar</button></div></div></td>`;
  
  
  
  }