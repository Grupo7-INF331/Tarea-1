package models;

import java.sql.Date;
import java.sql.Time;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private Time hora;
    private String categoria;
    private int precio;
    private int cupos;

    public Evento(int id, String nombre, Date fecha, Time hora, String categoria, int precio, int cupos) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.categoria = categoria;
        this.precio = precio;
        this.cupos = cupos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCupos() {
        return cupos;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
