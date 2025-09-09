package views;

import models.Evento;

import java.util.List;
import java.util.Scanner;

public class VistaEvento {

    private final Scanner sc = new Scanner(System.in);

    public int menu() {
        System.out.println("=== Gestión de Eventos ===");
        System.out.println("1. Mostrar Eventos");
        System.out.println("2. Crear Evento"); // Controlador
        System.out.println("3. Salir");
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        return sc.nextInt();
    }

    public String pedirNombre() {
        System.out.print("Ingrese el nombre del evento: ");
        String nombre = sc.nextLine();
        return nombre;
    }

    public String pedirDescripcion() {
        System.out.print("Ingrese la descripción del evento: ");
        String descripcion = sc.nextLine();
        return descripcion;
    }

    public String pedirFecha() {
        System.out.print("Ingrese la fecha del evento (DD-MM-YYYY): ");
        String fecha = sc.nextLine();
        return fecha;
    }

    public String pedirHora() {
        System.out.print("Ingrese la hora del evento (HH:MM): ");
        String hora = sc.nextLine();
        return hora;
    }

    public String pedirCategoria() {
        System.out.print("Ingrese la categoría del evento: ");
        String categoria = sc.nextLine();
        return categoria;
    }

    public int pedirPrecio() {
        System.out.print("Ingrese el precio del evento: ");
        int precio = sc.nextInt();
        return precio;
    }

    public int pedirCupos() {
        System.out.print("Ingrese los cupos del evento: ");
        int cupos = sc.nextInt();
        return cupos;
    }

    public int buscador(List<String> filtros) {
        System.out.println("=== Filtros aplicados ===");
        for (String filtro : filtros) {
            System.out.println("- " + filtro);
        }
        System.out.println("=========================\n");
        System.out.println("1. Aplicar filtros");
        System.out.println("2. Buscar");
        System.out.println("3. Salir");
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        return sc.nextInt();
    }

    public int resultados(List<Evento> eventos) {
        System.out.println("=== Resultados ===");
        for (Evento evento : eventos) {
            System.out.println(evento.getId() + ". " + evento.getNombre() + " | " + evento.getFecha() + " | "
                    + evento.getHora() + " | " + evento.getCategoria() + " | Precio: " + evento.getPrecio()
                    + " | Cupos: " + evento.getCupos());
        }
        System.out.println("=========================\n");
        System.out.print("Seleccionar ID de evento (0 para cancelar): ");
        return sc.nextInt();
    }

    public int detallesActualización(Evento antiguo, Evento nuevo) {
        System.out.println("=== Detalles de la actualización ===");
        for (int i = 1; i <= 7; i++) {
            switch (i) {
                case 1:
                    if (!antiguo.getNombre().equals(nuevo.getNombre())) {
                        System.out.println("Nombre: " + antiguo.getNombre() + " -> " + nuevo.getNombre());
                    }
                    break;
                case 2:
                    if (!antiguo.getDescripcion().equals(nuevo.getDescripcion())) {
                        System.out
                                .println("Descripción: " + antiguo.getDescripcion() + " -> " + nuevo.getDescripcion());
                    }
                    break;
                case 3:
                    if (!antiguo.getFecha().equals(nuevo.getFecha())) {
                        System.out.println("Fecha: " + antiguo.getFecha() + " -> " + nuevo.getFecha());
                    }
                    break;
                case 4:
                    if (!antiguo.getHora().equals(nuevo.getHora())) {
                        System.out.println("Hora: " + antiguo.getHora() + " -> " + nuevo.getHora());
                    }
                    break;
                case 5:
                    if (!antiguo.getCategoria().equals(nuevo.getCategoria())) {
                        System.out.println("Categoría: " + antiguo.getCategoria() + " -> " + nuevo.getCategoria());
                    }
                    break;
                case 6:
                    if (antiguo.getPrecio() != nuevo.getPrecio()) {
                        System.out.println("Precio: " + antiguo.getPrecio() + " -> " + nuevo.getPrecio());
                    }
                    break;
                case 7:
                    if (antiguo.getCupos() != nuevo.getCupos()) {
                        System.out.println("Cupos: " + antiguo.getCupos() + " -> " + nuevo.getCupos());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("=========================\n");
        System.out.println("1. Deshacer un cambio");
        System.out.println("2. Cancelar cambios");
        System.out.println("3. Confirmar cambios");
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        return sc.nextInt();
    }

    public int cambios() {
        System.out.println("=== ¿Qué desea cambiar? ===");
        System.out.println("1. Nombre");
        System.out.println("2. Descripción");
        System.out.println("3. Fecha");
        System.out.println("4. Hora");
        System.out.println("5. Categoría");
        System.out.println("6. Precio");
        System.out.println("7. Cupos");
        System.out.println("8. Volver");
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        return sc.nextInt();
    }

}
