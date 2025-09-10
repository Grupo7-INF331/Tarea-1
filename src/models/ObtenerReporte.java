package models;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ObtenerReporte {
    private final String URL = "jdbc:mysql://localhost:3307/tarea1?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "root123";

    public ObtenerReporte(){}


    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private List<Evento> obtenerTodosReporte() {
        String query = "SELECT * FROM eventos WHERE fecha >= ?";

        java.util.Date now = new java.util.Date();


        SimpleDateFormat formato_fecha_sql = new SimpleDateFormat("yyyy-MM-dd"); // formato SQL
        String fecha_sql = formato_fecha_sql.format(now);


        List<Evento> eventos = new ArrayList<>();
        Evento evento = null;
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fecha_sql);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("categoria"),
                        rs.getInt("precio"),
                        rs.getInt("cupos"));
                eventos.add(evento);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return filtrarEventoHora(eventos);
    }

    private List<Evento> filtrarEventoHora(List<Evento> eventos){
        LocalTime ahora = LocalTime.now();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        eventos.removeIf(evento -> {
            String hora = evento.getHora();
            LocalTime hora_evento = LocalTime.parse(hora, fmt);
            return hora_evento.isBefore(ahora);
        });
        return eventos;
    }

    public Reportes obtenerReportes(){

        List<Evento> eventos = obtenerTodosReporte();
        int total_eventos = eventos.size();
        int cupos_disponibles = 0;
        int eventos_agotados = 0;
        for (Evento evento : eventos) {
            int cupos = evento.getCupos();
            cupos_disponibles += cupos;
            if (cupos == 0) eventos_agotados++;

        }
        return new Reportes(total_eventos,cupos_disponibles,eventos_agotados);
    }
}
