package models;

public class Reportes {
    private int total_eventos_registrados;
    private int cupos_disponibles;
    private int eventos_agotados;

    public Reportes(int total_eventos_registrados, int cupos_disponibles, int eventos_agotados){
        this.total_eventos_registrados = total_eventos_registrados;
        this.cupos_disponibles = cupos_disponibles;
        this.eventos_agotados = eventos_agotados;
    }

    public int getCupos_disponibles() {
        return cupos_disponibles;
    }

    public int getEventos_agotados() {
        return eventos_agotados;
    }

    public int getTotal_eventos_registrados() {
        return total_eventos_registrados;
    }

    public void setCupos_disponibles(int cupos_disponibles) {
        this.cupos_disponibles = cupos_disponibles;
    }

    public void setEventos_agotados(int eventos_agotados) {
        this.eventos_agotados = eventos_agotados;
    }

    public void setTotal_eventos_registrados(int total_eventos_registrados) {
        this.total_eventos_registrados = total_eventos_registrados;
    }
}
