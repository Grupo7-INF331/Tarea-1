package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public Evento updateHandler(Evento antiguo) throws ParseException {
        Evento nuevo = new Evento(antiguo.getId(), antiguo.getNombre(), antiguo.getDescripcion(), antiguo.getFecha(),
                antiguo.getHora(), antiguo.getCategoria(), antiguo.getPrecio(), antiguo.getCupos());
        int opcion;
        do {
            opcion = vista.detallesActualización(antiguo, nuevo);
            switch (opcion) {
                case 1:
                    nuevo = nuevoCambio(nuevo);
                    System.out.println("Cambio realizado exitosamente.");
                    break;
                case 2:
                    if (vista.confirmar()) {
                        modelo.actualizarEvento(nuevo);
                        System.out.println("Cambios guardados exitosamente.");
                        antiguo = new Evento(nuevo.getId(), nuevo.getNombre(), nuevo.getDescripcion(), nuevo.getFecha(),
                                nuevo.getHora(), nuevo.getCategoria(), nuevo.getPrecio(), nuevo.getCupos());
                        opcion = 3;
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
        return nuevo;
    }

    public int eventoHandler(int id) throws ParseException {
        Evento evento = modelo.verEvento(id);
        int accion;
        do {
            accion = vista.eventoSeleccionado(evento);
            switch (accion) {
                case 1:
                    if (evento.getCupos() > 0) {
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
                    evento = updateHandler(evento);
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
                    id = 0;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (accion != 5);
        return id;
    }

    public void seleccionEventoHandler(List<Evento> eventos) throws ParseException {
        while (true) {
            int idSeleccionado = vista.resultados(eventos);

            if (idSeleccionado == 0) {
                System.out.println("Operación cancelada.");
                return; // salir sin seleccionar
            }

            // Buscar evento por ID
            Evento seleccionado = null;
            for (Evento e : eventos) {
                if (e.getId() == idSeleccionado) {
                    seleccionado = e;
                    break;
                }
            }

            if (seleccionado == null) {
                System.out.println("No existe un evento con ese ID. Intente de nuevo.\n");
                continue; // volver a pedir ID
            }

            // Evento válido encontrado → manejarlo
            int result = eventoHandler(seleccionado.getId());

            if (result == 0) {
                // Evento eliminado o usuario salió del menú del evento
                return;
            }
        }
    }

    public void filtradorHandler(List<String> filtros, List<Evento> eventos) throws ParseException {
        int opcion;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        do {
            opcion = vista.filtrador();
            switch (opcion) {
                case 1:
                    if (filtros.stream().anyMatch(f -> f.startsWith("Nombre ="))) {
                        System.out.println("El filtro de este campo ya ha sido aplicado.\n");
                        return;
                    }
                    String nombre = vista.pedirNombre();
                    filtros.add("Nombre = '" + nombre + "'");
                    eventos.removeIf(evento -> !evento.getNombre().toLowerCase().contains(nombre.toLowerCase()));
                    return;
                case 2:
                    if (filtros.stream().anyMatch(f -> f.startsWith("Categoría ="))) {
                        System.out.println("El filtro de este campo ya ha sido aplicado.\n");
                        return;
                    }
                    String categoria = vista.pedirCategoria();
                    filtros.add("Categoría = '" + categoria + "'");
                    eventos.removeIf(evento -> !evento.getCategoria().equalsIgnoreCase(categoria));
                    return;
                case 3:
                    if (filtros.stream().anyMatch(f -> f.startsWith("Fecha entre"))) {
                        System.out.println("El filtro de este campo ya ha sido aplicado.\n");
                        return;
                    }
                    System.out.println("Fecha mínima");
                    String fechaMinStr = vista.pedirFecha();
                    System.out.println("Fecha máxima");
                    String fechaMaxStr = vista.pedirFecha();
                    while (sdf.parse(fechaMaxStr).before(sdf.parse(fechaMinStr))) {
                        System.out.println("La fecha máxima no puede ser anterior a la mínima. Intente de nuevo.");
                        fechaMaxStr = vista.pedirFecha();
                    }

                    java.sql.Date fechaMin = new java.sql.Date(sdf.parse(fechaMinStr).getTime());
                    java.sql.Date fechaMax = new java.sql.Date(sdf.parse(fechaMaxStr).getTime());

                    filtros.add("Fecha entre '" + fechaMinStr + "' y '" + fechaMaxStr + "'");

                    eventos.removeIf(evento -> {
                        try {
                            java.sql.Date fechaEvento = new java.sql.Date(sdf.parse(evento.getFecha()).getTime());
                            return fechaEvento.before(fechaMin) || fechaEvento.after(fechaMax);
                        } catch (ParseException e) {
                            return true; // si no se puede parsear, lo descartamos
                        }
                    });
                    return;
                case 4:
                    if (filtros.stream().anyMatch(f -> f.startsWith("Precio entre"))) {
                        System.out.println("El filtro de este campo ya ha sido aplicado.\n");
                        return;
                    }
                    System.out.println("Precio mínimo");
                    int precioMin = vista.pedirPrecio();

                    System.out.println("Precio máximo");
                    final int[] precioMax = { vista.pedirPrecio() };
                    while (precioMax[0] < precioMin) {
                        System.out.println("El precio máximo no puede ser menor que el mínimo. Intente de nuevo.");
                        precioMax[0] = vista.pedirPrecio();
                    }
                    filtros.add("Precio entre " + precioMin + " y " + precioMax[0]);
                    eventos.removeIf(evento -> evento.getPrecio() < precioMin || evento.getPrecio() > precioMax[0]);
                    return;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    public void buscadorHandler() throws ParseException {
        List<Evento> eventos = modelo.obtenerEventos();
        List<String> filtros = new ArrayList<>();
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos disponibles.\n");
            return;
        }
        int opcion = 0;
        do {
            try {
                opcion = vista.buscador(filtros);
                switch (opcion) {
                    case 1:
                        filtradorHandler(filtros, eventos);
                        break;
                    case 2:
                        seleccionEventoHandler(eventos);
                        break;
                    case 3:
                        eventos = modelo.obtenerEventos();
                        filtros.clear();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error al procesar la opción. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    public void iniciar() throws ParseException {
        int opcion;
        do {
            opcion = vista.menu();
            switch (opcion) {
                case 1:
                    buscadorHandler();
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
                    // verRegistro();
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
