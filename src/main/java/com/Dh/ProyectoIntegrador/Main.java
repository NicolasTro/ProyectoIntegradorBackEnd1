package com.Dh.ProyectoIntegrador;

import com.Dh.ProyectoIntegrador.dao.BD;
import com.Dh.ProyectoIntegrador.model.Domicilio;
import com.Dh.ProyectoIntegrador.service.DomicilioService;

public class Main {
    public static void main(String[] args) {
        BD.crearTablas();

        Domicilio domicilio = new Domicilio("Calle A", 123, "Luján", "Mendoza");
        Domicilio domicilio2 = new Domicilio("Calle B", 456, "Carlos Paz", "Cordoba");

        //creo una instancia de la clase para usar sus métodos
        DomicilioService domicilioService = new DomicilioService();

        domicilioService.guardar(domicilio);
        domicilioService.guardar(domicilio2);

        System.out.println("Este el sout: " + domicilio + " este es el otro domicilio: " + domicilio2);

        domicilioService.listarTodos();
    }
}
