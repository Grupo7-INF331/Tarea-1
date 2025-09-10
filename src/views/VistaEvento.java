package views;

import java.util.List;
import java.util.Scanner;
import models.Evento;
import models.Usuario;

public class VistaEvento {

    private final Scanner sc = new Scanner(System.in);

    public int menu() {
        System.out.println("=== Gestión de Eventos ===");
        System.out.println("1. Mostrar Eventos"); // -> buscador()
        System.out.println("2. Crear Evento"); // -> pedirTodosLosDatos()
        System.out.println("3. Ver Registro");
        System.out.println("4. Salir");
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        return opcion;
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
        sc.nextLine(); // Limpiar buffer
        return precio;
    }

    public int pedirCupos() {
        System.out.print("Ingrese los cupos del evento: ");
        int cupos = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        return cupos;
    }

    public int buscador(List<String> filtros) {
        System.out.println("=== Filtros aplicados ===");
        if (filtros != null) {
            for (String filtro : filtros) {
                System.out.println("- " + filtro);
            }
        }
        System.out.println("=========================");
        System.out.println("1. Añadir filtros"); // -> Pedir filtro
        System.out.println("2. Buscar"); // -> resultados()
        System.out.println("3. Volver"); // -> menu()
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        int a = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        return a;
    }

    public int resultados(List<Evento> eventos) {
        System.out.println("=== Resultados ===");
        for (Evento evento : eventos) {
            System.out.println(evento.getId() + ". " + evento.getNombre() + " | " + evento.getFecha() + " | "
                    + evento.getHora() + " | " + evento.getCategoria() + " | Precio: " + evento.getPrecio()
                    + " | Cupos: " + evento.getCupos());
        }
        System.out.println("=========================\n");
        System.out.print("Seleccionar ID de evento (0 para cancelar): "); // -> buscador() o eventoSeleccionado(evento)
        int a = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        return a;
    }

    public int eventoSeleccionado(Evento evento) {
        System.out.println("=== " + evento.getNombre() + " ===");
        System.out.println(evento.getId() + ". " + evento.getNombre() + " | " + evento.getFecha() + " | "
                + evento.getHora() + " | " + evento.getCategoria() + " | Precio: " + evento.getPrecio()
                + " | Cupos: " + evento.getCupos());
        System.out.println("=========================");
        System.out.println("1. Vender entrada"); // -> (Continuar?) Cupos--
        System.out.println("2. Devolución de entrada"); // -> (Continuar?) Cupos++
        System.out.println("3. Editar evento");
        System.out.println("4. Eliminar evento"); // -> eliminarEvento(id)
        System.out.println("5. Volver");
        System.out.println("6. Menú Principal"); // -> menu()
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        int a = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        return a;
    }

    public int detallesActualizacion(Evento antiguo, Evento nuevo) {
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
        System.out.println("=========================");
        System.out.println("1. Agregar otro cambio");
        System.out.println("2. Confirmar cambios"); // -> eventoSeleccionado(evento)
        System.out.println("3. Cancelar cambios");
        System.out.println("=========================\n");
        System.out.print("Seleccione una opción: ");
        int a = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        return a;
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
        int a = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        return a;
    }

    public Usuario pedir_usuario() {
        System.out.println("=== Para realizar acciones necesita autentificarse ===");
        System.out.println("1. Usuario");
        String user = sc.nextLine();
        System.out.println("2. Contraseña");
        String password = sc.nextLine();
        return new Usuario(user,password);
    }

    public boolean confirmar() {
        System.out.print("¿Seguro/a que desea realizar esta acción? (s/n): ");
        char respuesta = sc.next().toLowerCase().charAt(0);
        return respuesta == 's';
    }

}
