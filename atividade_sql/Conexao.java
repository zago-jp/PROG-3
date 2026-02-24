import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL =
            "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "zagoneles123"; // coloque sua senha aqui

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
