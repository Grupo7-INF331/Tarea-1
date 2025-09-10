package controllers;

import java.text.ParseException;
import java.util.List;

import models.*;
import views.VistaEvento;

public class ControladorEvento {
    private final VistaEvento vista;
    private final AccionEvento modelo;
    private  final  ObtenerReporte modeloRep;
    private final  GestionarUsuario gestionarUsuario;
    private Usuario user;

    public ControladorEvento(VistaEvento vista, AccionEvento modelo, ObtenerReporte modeloRep,GestionarUsuario gestionarUsuario) {
        this.vista = vista;
        this.modelo = modelo;
        this.modeloRep = modeloRep;
        this.gestionarUsuario = gestionarUsuario;
        this.user = null;
    }

    public Evento nuevoCambio(Evento evento) {
        int res;
        do {
            res = vista.cambios();
            switch (res) {
                case 1:
                    String nombre = vista.pedirNombre();
                    evento.setNombre(nombre);
                    break;
                case 2:
                    String descripcion = vista.pedirDescripcion();
                    evento.setDescripcion(descripcion);
                    break;
                case 3:
                    String fecha = vista.pedirFecha();
                    evento.setFecha(fecha);
                    break;
                case 4:
                    String hora = vista.pedirHora();
                    evento.setHora(hora);
                    break;
                case 5:
                    String categoria = vista.pedirCategoria();
                    evento.setCategoria(categoria);
                    break;
                case 6:
                    int precio = vista.pedirPrecio();
                    evento.setPrecio(precio);
                    break;
                case 7:
                    int cupos = vista.pedirCupos();
                    evento.setCupos(cupos);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (res < 1 || res > 8);

        return evento;
    }

    public void updateHandler(Evento antiguo) throws ParseException {
        Evento nuevo = antiguo;
        int opcion;
        do {
            opcion = vista.detallesActualizacion(antiguo, nuevo);
            switch (opcion) {
                case 1:
                    nuevo = nuevoCambio(nuevo);
                    System.out.println("Cambio realizado exitosamente.");
                    break;
                case 2:
                    if (vista.confirmar()) {
                        modelo.actualizarEvento(nuevo);
                        System.out.println("Cambios guardados exitosamente.");
                        opcion = 4;
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    break;
                case 3:
                    System.out.println("Cambios descartados.");
                    break;
                case 4:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);
    }

    public int eventoHandler(int id) throws ParseException {
        Evento evento = modelo.verEvento(id);
        int accion;
        do {
            accion = vista.eventoSeleccionado(evento);
            switch (accion) {
                case 1:
                    if (evento.getCupos() > 1) {
                        if (vista.confirmar()) {
                            evento.setCupos(evento.getCupos() - 1);
                            modelo.actualizarEvento(evento);
                            System.out.println("Entrada vendida exitosamente.");
                        }
                    } else {
                        System.out.println("No hay cupos disponibles para este evento.");
                    }
                    break;
                case 2:
                    if (vista.confirmar()) {
                        evento.setCupos(evento.getCupos() + 1);
                        modelo.actualizarEvento(evento);
                        System.out.println("Devolución registrada exitosamente.");
                    }
                    break;
                case 3:
                    updateHandler(evento);
                    break;
                case 4:
                    if (vista.confirmar()) {
                        modelo.eliminarEvento(id);
                        System.out.println("Evento eliminado exitosamente.");
                        accion = 6;
                        id = 0;
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    break;
                case 5:
                    break;
                case 6:
                    id = 0;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (accion != 6 && accion != 5);
        return id;
    }

    public void seleccionEventoHandler(List<Evento> eventos) throws ParseException {
        int res;
        do {
            res = vista.resultados(eventos);
            if (res > 0) {
                if(user == null){
                    Usuario usuario = vista.pedir_usuario();
                    int queryRes = gestionarUsuario.existeUsuario(usuario);
                    if(queryRes == 1){this.user = usuario;}
                    continue;
                }
                res = eventoHandler(res);


            } else if (res < 0) {
                System.out.println("Opción no válida, intente de nuevo.");
            }

        } while (res != 0);
    }

    public void iniciar() throws ParseException {
        int opcion;
        do {
            opcion = vista.menu();
            switch (opcion) {
                case 1:
                    List<Evento> eventos = modelo.obtenerEventos();
                    // Falta colocar el buscador/filtros, estamos asumiendo
                    // que no existe un buscador de momento
                    seleccionEventoHandler(eventos);
                    break;

                case 2:
                    if(user == null){
                        Usuario usuario = vista.pedir_usuario();
                        int queryRes = gestionarUsuario.existeUsuario(usuario);
                        if(queryRes == 1){this.user = usuario;}
                        continue;
                    }
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
                    Reportes reporte = modeloRep.obtenerReportes();

                    System.out.println("Reporte de los eventos:" + "\n" +
                            "Total eventos Registrados: "+ reporte.getTotal_eventos_registrados() + "\n" +
                            "Total cupos en todos los eventos: " + reporte.getCupos_disponibles() + "\n" +
                            "Eventos agotados: "+ reporte.getEventos_agotados() + "\n"
                    );
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 4);
    }
}
