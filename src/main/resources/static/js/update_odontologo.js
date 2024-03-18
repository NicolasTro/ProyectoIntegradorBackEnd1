
window.addEventListener('load', function(e){
    
    const formulario = document.querySelector("#formOdontologoUpdate");
    let id;
    

    
    
    document.addEventListener('click', function(e){
        
        
        if(e.target instanceof(HTMLButtonElement)){
            if(e.target.textContent=="Modificar"){

                
                id = e.target.dataset.id
                // console.log("Id seleccionado: "+ id)
            }
            
        }
        
        
    });
    
    
    
    
    
    
    formulario.addEventListener('submit', function (e) {
//        e.preventDefault()
  console.log(formulario);
  console.log("actualizacion")
  const formData = {
      id,
      nombre: document.querySelector("#nombreUpdate").value,
    apellido: document.querySelector("#apellidoUpdate").value,
    matricula: document.querySelector("#matriculaUpdate").value
    }
    
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

                
           })
           .catch(error => {
             let errorAlert = "<div class='alert alert-danger alert-dismissible'>"+
                                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>"+ 
                                    "<strong> Error intente nuevamente</strong></div>"

//console.log(error)
//                     document.querySelector('#response').innerHTML = errorAlert;
//                     document.querySelector('#response').style.display = "block";

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


}
)

