package views;

import models.Evento;
import java.util.Scanner;

public class VistaEvento {

    private final Scanner sc = new Scanner(System.in);

    public int menu() {
        System.out.println("=== Gestión de Eventos ===");
        System.out.println("1. Mostrar Eventos");
        System.out.println("2. Crear Evento");
        System.out.println("3. Actualizar Evento");
        System.out.println("4. Eliminar Evento");
        System.out.println("5. Salir");
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

}
