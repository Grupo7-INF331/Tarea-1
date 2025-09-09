package controllers;

import java.text.ParseException;
import java.util.List;

import models.*;
import views.VistaEvento;

public class ControladorEvento {
    private final VistaEvento vista;
    private final AccionEvento modelo;

    public ControladorEvento(VistaEvento vista, AccionEvento modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void iniciar() throws ParseException {
        int opcion;
        do {
            opcion = vista.menu();
            switch (opcion) {
                case 1:
                    List<Evento> eventos = modelo.obtenerEventos();
                    int res = vista.resultados(eventos);
                    break;
                case 2:
                    String nombre = vista.pedirNombre();
                    String descripcion = vista.pedirDescripcion();
                    String fecha = vista.pedirFecha();
                    String hora = vista.pedirHora();
                    String categoria = vista.pedirCategoria();
                    int precio = vista.pedirPrecio();
                    int cupos = vista.pedirCupos();
                    Evento nuevoEvento = new Evento(nombre, descripcion, fecha, hora, categoria, precio, cupos);
                    modelo.insertarEvento(nuevoEvento);
                    break;
                case 3:
                    // actualizarEvento();
                    break;
                case 4:
                    // eliminarEvento();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}
