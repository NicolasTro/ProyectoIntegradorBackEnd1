window.addEventListener('load', function () {
    const formulario = document.querySelector('#formOdontologoUpdate');
    let idOdontologo = document.querySelector('#dentistTable');
    let btnsModificar = document.querySelectorAll('.dropdown-item[data-target="#modificarBtn"]')

console.log(idOdontologo)
btnsModificar.forEach(boton => () {
    boton.addEventListener('click', function() {

    })
})

    formulario.addEventListener('submit', function (event) {

        const formData = {
            idOdontologo,
            nombre: document.querySelector('#nombreUpdate').value,
            apellido: document.querySelector('#apellidoUpdate').value,
            matricula: document.querySelector('#matriculaUpdate').value,

        };
        const url = '/odontologos/actualizar';
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
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Odontólogo Actualizado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
//                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
//                     resetUploadForm();})
    });


//    function resetUploadForm(){
//        document.querySelector('#idOdontologo').value = "";
//        document.querySelector('#nombre').value = "";
//        document.querySelector('#apellido').value = "";
//        document.querySelector('#matricula').value = "";
//
//    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odotologoLista.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    });
});
});