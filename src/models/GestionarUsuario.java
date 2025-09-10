package models;

import org.apache.logging.log4j.Logger;
import java.sql.*;

public class GestionarUsuario {
    private final String URL = "jdbc:mysql://localhost:3306/tarea1?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "";
    private static final Logger logger = Log.getLogger(GestionarUsuario.class);

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public int existeUsuario(Usuario user) {
        System.out.println(user.getUser() + " " + user.getPassword());
        String sql = "SELECT * FROM usuarios WHERE user = ? AND password = ?";

        if (user.getUser().length() > 50 || user.getPassword().length() > 255)
            return -1;
        try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // si ejecuto rs.next hay mas de 1
                return 1;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return 0; // en caso de error o si no existe
    }

    public int queDiceConsola(int i) {
        if (i == 1) {
            logger.info("Iniciando Sesion");
            return 1;
        } else if (i == 0)
            logger.warn("No se encontro el usuario y/o la contrasena");

        else
            logger.error("Solo se permite un maximo de 50 caracteres para el usuario y 255 para la contrasena");
        return 0;
    }
}
