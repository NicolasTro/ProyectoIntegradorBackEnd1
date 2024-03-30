function eliminarOdontologo(listaBtnEliminar) {
    listaBtnEliminar.forEach((botonEliminar) => {
        let idBtn;
		idBtn = botonEliminar.dataset.id;

		botonEliminar.addEventListener("click", function () {
			$("#staticBackdropOdontologoDelete").modal("show");
            
			let btnConfirmarEliminar = document.querySelector("#confirmarOdontologoDelete");

			btnConfirmarEliminar.addEventListener("click", function (event) {
				event.preventDefault();

				const url = "/odontologos/eliminar/" + idBtn;
				console.log(url);
				const settings = {
					method: "DELETE",
					headers: {
						"Content-Type": "application/json",
					},
				};

				fetch(url, settings)
                .then((response) => response.json())
                .then((data) => {
                    
                    let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Odontólogo Actualizado </div>";
                    // document.querySelector('#response').innerHTML = successAlert;
                    // document.querySelector('#response').style.display = "block";
                })
                .catch((error) => {
                    let errorAlert = "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";
                    //  document.querySelector('#response').innerHTML = errorAlert;
                    //  document.querySelector('#response').style.display = "block";
                });
                
				
                $("#staticBackdropOdontologoDelete").modal("hide");
                location.reload();
			});
		});
	});
}
