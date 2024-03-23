document.getElementById('div-arrastable').addEventListener('dragstart', function(event) {
    event.dataTransfer.setData('text/plain', event.target.id);
    
    // Temporizador para hacer el elemento invisible después de capturarlo para el arrastre.
    setTimeout(() => {
        event.target.classList.add('invisible');
    }, 0);
});

document.getElementById('div-arrastable').addEventListener('dragend', function(event) {
    // Hace el elemento visible nuevamente cuando el arrastre termina.
    event.target.classList.remove('invisible');
});

function allowDrop(event) {
    event.preventDefault();
}

function drop(event) {
    event.preventDefault();
    var data = event.dataTransfer.getData('text');
    var elementoArrastrado = document.getElementById(data);
    
    // Mueve el elemento al contenedor de destino y lo hace visible.
    event.target.appendChild(elementoArrastrado);
    elementoArrastrado.classList.remove('invisible');
}