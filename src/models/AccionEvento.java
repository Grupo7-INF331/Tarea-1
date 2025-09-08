package models;

import java.sql.*;

public class AccionEvento {
    private final String URL = "jdbc:mysql://localhost:3306/tarea1";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertarEvento(Evento evento) {
        String sql = "INSERT INTO eventos (id, nombre, descripcion, fecha, hora, categoria, precio, cupos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, evento.getId());
            pstmt.setString(2, evento.getNombre());
            pstmt.setString(3, evento.getDescripcion());
            pstmt.setDate(4, Date.valueOf(evento.getFecha()));
            pstmt.setTime(5, Time.valueOf(evento.getHora()));
            pstmt.setString(6, evento.getCategoria());
            pstmt.setInt(7, evento.getPrecio());
            pstmt.setInt(8, evento.getCupos());
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
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("categoria"),
                        rs.getInt("precio"),
                        rs.getInt("cupos")
                );
                eventos.add(evento);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eventos;
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

    public void actualizarEvento(Evento evento) {
        String sql = "UPDATE eventos SET nombre = ?, descripcion = ?, fecha = ?, hora = ?, categoria = ?, precio = ?, cupos = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, evento.getNombre());
            pstmt.setString(2, evento.getDescripcion());
            pstmt.setDate(3, Date.valueOf(evento.getFecha()));
            pstmt.setTime(4, Time.valueOf(evento.getHora()));
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
