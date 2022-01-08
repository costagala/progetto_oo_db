package progetto_oo_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    protected Connection connessioneDB() {

        Connection conn = null;

        final String url = "jdbc:postgresql://localhost:5432/progetto_oo_db";
        final String user = "postgres";
        final String password = "admin";

        try{
            conn = DriverManager.getConnection(url,user,password);
            if(conn!=null)
                System.out.println("Connessione stabilita con il database!");

        }catch(SQLException e){
        }

        return conn;
    }




}
