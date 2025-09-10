package models;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AccionEvento {
    private final String URL = "jdbc:mysql://localhost:3307/tarea1?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "root123";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertarEvento(Evento evento) throws ParseException {
        String sql = "INSERT INTO eventos (nombre, descripcion, fecha, hora, categoria, precio, cupos) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdfDate.parse(evento.getFecha());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            java.util.Date time = sdfTime.parse(evento.getHora());
            java.sql.Time sqlTime = new java.sql.Time(time.getTime());

            pstmt.setString(1, evento.getNombre());
            pstmt.setString(2, evento.getDescripcion());
            pstmt.setDate(3, sqlDate);
            pstmt.setTime(4, sqlTime);
            pstmt.setString(5, evento.getCategoria());
            pstmt.setInt(6, evento.getPrecio());
            pstmt.setInt(7, evento.getCupos());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Evento> obtenerEventos() {
        String sql = "SELECT * FROM eventos";
        List<Evento> eventos = new ArrayList<>();
        try (Connection conn = conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Evento evento = new Evento(
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eventos;
    }

    public Evento verEvento(int id) {
        String sql = "SELECT * FROM eventos WHERE id = ?";
        Evento evento = null;
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("categoria"),
                        rs.getInt("precio"),
                        rs.getInt("cupos"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return evento;
    }

    public void eliminarEvento(int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarEvento(Evento evento) throws ParseException {
        String sql = "UPDATE eventos SET nombre = ?, descripcion = ?, fecha = ?, hora = ?, categoria = ?, precio = ?, cupos = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdfDate.parse(evento.getFecha());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            java.util.Date time = sdfTime.parse(evento.getHora());
            java.sql.Time sqlTime = new java.sql.Time(time.getTime());

            pstmt.setString(1, evento.getNombre());
            pstmt.setString(2, evento.getDescripcion());
            pstmt.setDate(3, sqlDate);
            pstmt.setTime(4, sqlTime);
            pstmt.setString(5, evento.getCategoria());
            pstmt.setInt(6, evento.getPrecio());
            pstmt.setInt(7, evento.getCupos());
            pstmt.setInt(8, evento.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
