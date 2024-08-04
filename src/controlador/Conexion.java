
package controlador;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectar(){

        String sjdbc = "jdbc:sqlite";
        Path path = Paths.get("src/bd/db.db");

        String url = sjdbc + ":" + path.toAbsolutePath();
        Connection connect = null;

        try {
            connect = DriverManager.getConnection(url);
            if (connect != null){
                System.out.println("Conexión extablecida ;)");
            }

        }catch(SQLException ex){
            System.out.println("No se ha podido conectar\n" + ex.getMessage());
        }

        return connect;
    }

}
