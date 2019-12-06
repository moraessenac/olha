/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marcelo.smoraes2
 */
public class ConexaoBanco {
    
    //private static String url = "jdbc:mysql://localhost:3306/prototypeeop";
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "adminadmin";
    private static Connection conexao;
    private static String urlString = "jdbc:mysql://localhost:3306/clientebd?useTimezone=true&serverTimezone=UTC";

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                conexao = DriverManager.getConnection(urlString, username, password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return conexao;
    }
    
}
