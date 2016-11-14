package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conection {
    
    Connection conectar = null;
    
    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/agenda","root","");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }   

    public Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}