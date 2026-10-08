package cl.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) {

        // Las credenciales ya no están escritas directamente
        String usuario = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String url = System.getenv("DB_URL");
        String nombre = "Juan";

        // Se utiliza un parámetro en lugar de concatenar
        String sql =
                "SELECT * FROM usuarios WHERE nombre = ?";
        try (Connection conexion =
                     DriverManager.getConnection(
                             url,
                             usuario,
                             password);
             PreparedStatement statement =
                     conexion.prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet resultado =
                         statement.executeQuery()) {
                while (resultado.next()) {
                    System.out.println(
                            resultado.getString("nombre"));
                }
            }
        } catch (Exception e) {

            // No se expone información interna de la excepción.
            System.err.println(
                    "No fue posible realizar la consulta.");
        }
    }
}
