package views;

import models.Evento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import models.Usuario;

public class VistaEvento {

    private final Scanner sc = new Scanner(System.in);

    public int menu() {
        int opcion = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("=== Gestión de Eventos ===");
                System.out.println("1. Mostrar Eventos"); // -> buscador()
                System.out.println("2. Crear Evento"); // -> pedirTodosLosDatos()
                System.out.println("3. Ver Registro");
                System.out.println("4. Salir");
                System.out.println("=========================\n");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                if (opcion < 1 || opcion > 4) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                sc.nextLine(); // Limpiar buffer
            }
            System.out.println();
        }
        return opcion;
    }

    public String pedirNombre() {
        System.out.print("Ingrese el nombre del evento: ");
        String nombre = sc.nextLine();
        System.out.println();
        return nombre;
    }

    public String pedirDescripcion() {
        System.out.print("Ingrese la descripción del evento: ");
        String descripcion = sc.nextLine();
        System.out.println();
        return descripcion;
    }

    public String pedirFecha() {
        String fecha = null;
        boolean valida = false;

        while (!valida) {
            System.out.print("Ingrese la fecha del evento (DD-MM-YYYY): ");
            fecha = sc.nextLine();

            // Validación de formato con regex
            if (fecha.matches("\\d{2}-\\d{2}-\\d{4}")) {
                // Validación de existencia real de la fecha
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);
                try {
                    sdf.parse(fecha);
                    valida = true; // pasó la validación
                } catch (ParseException e) {
                    System.out.println("La fecha no existe. Intente de nuevo.");
                }
            } else {
                System.out.println("Formato inválido. Use DD-MM-YYYY.");
            }
            System.out.println();
        }
        return fecha;
    }

    public String pedirHora() {
        String hora = null;
        boolean valida = false;

        while (!valida) {
            System.out.print("Ingrese la hora del evento (HH:MM): ");
            hora = sc.nextLine();

            // Validación de formato con regex
            if (hora.matches("\\d{2}:\\d{2}")) {
                // Validación de existencia real de la hora
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sdf.setLenient(false);
                try {
                    sdf.parse(hora);
                    valida = true; // pasó la validación
                } catch (ParseException e) {
                    System.out.println("La hora no existe. Intente de nuevo.");
                }
            } else {
                System.out.println("Formato inválido. Use HH:MM.");
            }
            System.out.println();
        }
        return hora;
    }

    public String pedirCategoria() {
        String categoria = null;
        boolean valida = false;

        while (!valida) {
            System.out.print("Ingrese la categoría del evento (charla, show, taller): ");
            categoria = sc.nextLine().toLowerCase();

            // Validación de categoría no vacía
            if (categoria.equals("charla") || categoria.equals("show") || categoria.equals("taller")) {
                valida = true; // pasó la validación
            } else {
                System.out.println("Categoría no válida. Intente de nuevo.");
            }
            System.out.println();
        }
        return categoria.substring(0, 1).toUpperCase() + categoria.substring(1);
    }

    public int pedirPrecio() {
        int precio = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Ingrese el precio del evento: ");
                precio = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo. Intente de nuevo.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Precio inválido. Intente de nuevo.");
                sc.nextLine(); // Limpiar buffer
            }
            System.out.println();
        }
        return precio;
    }

    public int pedirCupos() {
        int cupos = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Ingrese los cupos del evento: ");
                cupos = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                if (cupos < 0) {
                    System.out.println("Los cupos no pueden ser negativos. Intente de nuevo.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Cupos inválidos. Intente de nuevo.");
                sc.nextLine(); // Limpiar buffer
            }
            System.out.println();
        }
        return cupos;
    }

    public int buscador(List<String> filtros) {
        System.out.println("=========================\n");
        int opcion = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("=== Filtros aplicados ===");
                if (filtros != null) {
                    for (String filtro : filtros) {
                        System.out.println("- " + filtro);
                    }
                }
                System.out.println("=== Buscador de Eventos ===");
                System.out.println("1. Añadir filtros"); // -> Pedir filtro
                System.out.println("2. Aplicar filtros"); // -> resultados()
                System.out.println("3. Limpiar filtros"); // -> limpiarFiltros()
                System.out.println("4. Volver"); // -> menu()
                System.out.println("=========================\n");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                if (opcion < 1 || opcion > 4) {
                    System.out.println("Opción no válida. Intente de nuevo.\n");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.\n");
                sc.nextLine(); // limpiar buffer
            }
            System.out.println();
        }
        return opcion;
    }

    public int filtrador() {
        int opcion = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("=== Filtros disponibles ===");
                System.out.println("1. Nombre");
                System.out.println("2. Categoría");
                System.out.println("3. Intervalo de fechas");
                System.out.println("4. Rango de precio");
                System.out.println("5. Volver");
                System.out.println("=========================\n");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opción no válida. Intente de nuevo.\n");
                } else {
                    valid = true;
                }
            } catch (Exception e) {
                System.out.println("Error al mostrar filtros. Intente de nuevo.");
                sc.nextLine(); // limpiar buffer
            }
            System.out.println();
        }
        return opcion;
    }

    public int resultados(List<Evento> eventos) {

        System.out.println("=== Resultados ===");
        for (Evento evento : eventos) {
            System.out.println(evento.getId() + ". " + evento.getNombre() + " | " + evento.getFecha() + " | "
                    + evento.getHora() + " | " + evento.getCategoria() + " | Precio: " + evento.getPrecio()
                    + " | Cupos: " + evento.getCupos());
        }
        System.out.println("=========================\n");
        int id = -1;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print("Seleccionar ID de evento (0 para cancelar): ");
                id = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.\n");
                sc.nextLine(); // descartar la entrada errónea
            }
        }
        System.out.println();
        return id;
    }

    public int eventoSeleccionado(Evento evento) {
        int a = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("=== " + evento.getNombre() + " ===");
                System.out.println(evento.getId() + ". " + evento.getNombre() + " | " + evento.getFecha() + " | "
                        + evento.getHora() + " | " + evento.getCategoria() + " | Precio: " + evento.getPrecio()
                        + " | Cupos: " + evento.getCupos());
                System.out.println("=========================");
                System.out.println("1. Vender entrada"); // -> (Continuar?) Cupos--
                System.out.println("2. Devolución de entrada"); // -> (Continuar?) Cupos++
                System.out.println("3. Editar evento");
                System.out.println("4. Eliminar evento"); // -> eliminarEvento(id)
                System.out.println("5. Menú Principal"); // -> menu()
                System.out.println("=========================\n");
                System.out.print("Seleccione una opción: ");
                a = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                if (a < 1 || a > 6) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                sc.nextLine(); // Limpiar buffer
                continue;
            }
            System.out.println();
        }

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
        int a = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("=========================");
                System.out.println("1. Realizar un nuevo cambio"); // -> nuevoCambio(nuevo)
                System.out.println("2. Confirmar cambios"); // -> eventoSeleccionado(evento)
                System.out.println("3. Cancelar cambios");
                System.out.println("=========================\n");
                System.out.print("Seleccione una opción: ");
                a = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                if (a < 1 || a > 3) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                sc.nextLine(); // Limpiar buffer
            }
            System.out.println();
        }
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

        int a = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Seleccione una opción: ");
                a = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                if (a < 1 || a > 8) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                sc.nextLine(); // Limpiar buffer
            }
            System.out.println();
        }
        return a;
    }

    public Usuario pedir_usuario() {
        System.out.println("=== Para realizar acciones necesita autentificarse ===");
        System.out.println("1. Usuario");
        String user = sc.nextLine();
        System.out.println("2. Contraseña");
        String password = sc.nextLine();
        return new Usuario(user, password);
    }

    public boolean confirmar() {
        boolean valid = false;
        char respuesta = ' ';
        while (!valid) {
            System.out.print("¿Seguro/a que desea realizar esta acción? (s/n): ");
            respuesta = sc.next().toLowerCase().charAt(0);
            if (respuesta == 's' || respuesta == 'n') {
                valid = true;
                return respuesta == 's';
            } else {
                System.out.println("Entrada no válida. Intente de nuevo.");
                sc.nextLine(); // Limpiar buffer
            }
            System.out.println();
        }
        return respuesta == 's';
    }

}
