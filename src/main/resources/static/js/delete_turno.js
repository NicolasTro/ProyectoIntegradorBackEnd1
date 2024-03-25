window.addEventListener('load', function(e){
    let id;
    console.log(id);

    let listaBtnEliminar = document.querySelectorAll("btnTabla")

    document.addEventListener('click', function(e){

        if(e.target instanceof(HTMLButtonElement)){

            if(e.target.textContent=="Eliminar"){
                id = e.target.dataset.id
                console.log(id);


    const url = '/turnos/eliminar/'+id;
    // console.log(url)
       const settings = {
           method: 'DELETE',
           headers: {
               'Content-Type': 'application/json',
            },
            // body: JSON.stringify(formData)
       }

       fetch(url, settings)
           .then(response => response.json())
           .then(data => {
            console.log("no entra aca")
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno Eliminado </div>'

                document.querySelector('#responseTurno').innerHTML = successAlert;
                document.querySelector('#responseTurno').style.display = "block";

           })
           .catch(error => {
             let errorAlert = "<div class='alert alert-danger alert-dismissible'>"+
                                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>"+
                                    "<strong> Error intente nuevamente</strong></div>"
                     document.querySelector('#responseTurno').innerHTML = errorAlert;
                     document.querySelector('#responseTurno').style.display = "block";

                    });

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnoLista.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
     });
}
}


});
}
)
