package provider;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_connection {

    public Connection get_Connection() {
        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");
            try {
                con = DriverManager.getConnection(Environment.environement, "ouail", "OUAIL");
            } catch (Exception e1) {
                System.out.println("can't connect to db");
            }
        } catch (ClassNotFoundException e1) {
            System.out.println("erreur class not found");
        }

        return con;
    }

}
