// function modificarDomicilio(listaBtnModificar) {


// 			const url = "/turnos/actualizar";
// 			const settings = {
// 				method: "PUT",
// 				headers: {
// 					"Content-Type": "application/json",
// 				},
// 				body: JSON.stringify(formData),
// 			};
// 			console.log(formData);

// 			fetch(url, settings)
// 				.then(response => response.json())
// 				.then(data => {
// 					console.log(data);
// 					let successAlert = '<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + "<strong></strong> Turno Actualizado </div>";

// 					document.querySelector("#responseTurnoUpdate").innerHTML = successAlert;
// 					document.querySelector("#responseTurnoUpdate").style.display = "block";
// 				})
// 				.catch(error => {
// 					let errorAlert = "<div class='alert alert-danger alert-dismissible'>" + "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong> Error intente nuevamente</strong></div>";

// 					document.querySelector("#responseTurnoUpdate").innerHTML = errorAlert;
// 					document.querySelector("#responseTurnoUpdate").style.display = "block";
// 				});
// 		});
// 	});
// }
