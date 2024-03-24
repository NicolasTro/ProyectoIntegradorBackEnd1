document.getElementById('div-arrastable').addEventListener('dragstart', function(event) {
    event.dataTransfer.setData('text/plain', event.target.id);
    
    
    setTimeout(() => {
        event.target.classList.add('invisible');
    }, 0);
});

document.getElementById('div-arrastable').addEventListener('dragend', function(event) {
  
    event.target.classList.remove('invisible');
});

function allowDrop(event) {
    event.preventDefault();
}

function drop(event) {
    event.preventDefault();
    var data = event.dataTransfer.getData('text');
    var elementoArrastrado = document.getElementById(data);
    
    
    event.target.appendChild(elementoArrastrado);
    elementoArrastrado.classList.remove('invisible');
}