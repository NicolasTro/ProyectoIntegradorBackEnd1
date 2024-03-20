window.addEventListener('load', function(e){

    let id;

    let listaBtnEliminar = document.querySelectorAll("btnTabla")
    document.addEventListener('click', function(e){

        if(e.target instanceof(HTMLButtonElement)){

            if(e.target.textContent=="Eliminar"){
                id = e.target.dataset.id;

                const url = '/pacientes/eliminar/' + id;
                const settings = {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                };

                fetch(url, settings)
                    .then(response => response.json())
                    .then(data => {
                        cargarLista();
                        let successAlert = '<div class="alert alert-success alert-dismissible">' +
                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                            '<strong></strong> Paciente Actualizado </div>';

                        document.querySelector('#responsePatient').innerHTML = successAlert;
                        document.querySelector('#responsePatient').style.display = "block";
                    })
                    .catch(error => {
                        let errorAlert = "<div class='alert alert-danger alert-dismissible'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>" +
                            "<strong> Error intente nuevamente</strong></div>";

                        document.querySelector('#responsePatient').innerHTML = errorAlert;
                        document.querySelector('#responsePatient').style.display = "block";
                    });
            }
        }
    });

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odotologoLista.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    });
});
