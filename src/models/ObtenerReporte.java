package models;

import controllers.ControladorEvento;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ObtenerReporte {
    private final String URL = "jdbc:mysql://localhost:3306/tarea1?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "";
    private static final Logger logger = Log.getLogger(ControladorEvento.class);

    public ObtenerReporte() {
    }

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
            logger.error(e.getMessage());
        }

        return filtrarEventoHora(eventos);
    }

    private List<Evento> filtrarEventoHora(List<Evento> eventos) {
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();

        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        eventos.removeIf(evento -> {
            LocalDate fechaEvento = LocalDate.parse(evento.getFecha(), fmtFecha);
            LocalTime horaEvento = LocalTime.parse(evento.getHora(), fmtHora);
            return fechaEvento.equals(hoy) && horaEvento.isBefore(ahora);
        });
        return eventos;
    }

    public Reportes obtenerReportes() {

        List<Evento> eventos = obtenerTodosReporte();
        int total_eventos = eventos.size();
        int cupos_disponibles = 0;
        int eventos_agotados = 0;
        for (Evento evento : eventos) {
            int cupos = evento.getCupos();
            cupos_disponibles += cupos;
            if (cupos == 0)
                eventos_agotados++;

        }
        return new Reportes(total_eventos, cupos_disponibles, eventos_agotados);
    }
}
