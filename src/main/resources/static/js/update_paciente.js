window.addEventListener('load', function(e){

    const formulario = document.querySelector("#updatePaciente");
    let id;
    let ID_domicilio;

    document.addEventListener('click', function(e){

        if(e.target instanceof(HTMLButtonElement)){
            if(e.target.textContent=="Modificar"){

                id = e.target.dataset.id
                
                idDomicilio = e.target.dataset.domicilioid;
            }
        }
    });

    formulario.addEventListener('submit', function (e) {
        // e.preventDefault()
  
  const formData = {
    id,
    nombre: document.querySelector("#nombrePacienteUpdate").value,
    apellido: document.querySelector("#apellidoPacienteUpdate").value,
    dni: document.querySelector("#dniUpdate").value,
    fechaIngreso: document.querySelector("#fechaIngresoUpdate").value,
    domicilio : {
        id: idDomicilio,
        calle: document.querySelector("#domicilioCalleUpdate").value,
        numero: document.querySelector("#domicilioNumeroUpdate").value,
        localidad: document.querySelector("#domicilioLocalidadUpdate").value,
        provincia: document.querySelector("#domicilioProvinciaUpdate").value,
    }
    }

    const url = '/pacientes/actualizar';
       const settings = {
           method: 'PUT',
           headers: {
               'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
       }

       fetch(url, settings)
           .then(response => response.json())
           .then(data => {
            console.log(data);
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Paciente Actualizado </div>'

                document.querySelector('#responsePatientUpdate').innerHTML = successAlert;
                document.querySelector('#responsePatientUpdate').style.display = "block";


           })
           .catch(error => {
             let errorAlert = "<div class='alert alert-danger alert-dismissible'>"+
                                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>"+
                                    "<strong> Error intente nuevamente</strong></div>"
                                    document.querySelector('#responsePatientUpdate').innerHTML = errorAlert;
                                    document.querySelector('#responsePatientUpdate').style.display = "block";

                    });

   (function(){
       let pathname = window.location.pathname;
       if(pathname === "/"){
           document.querySelector(".nav .nav-item a:first").addClass("active");
       } else if (pathname == "/pacienteLista.html") {
           document.querySelector(".nav .nav-item a:last").addClass("active");
       }
    });

});


}
)
