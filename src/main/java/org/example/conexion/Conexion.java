package org.example.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion() {
        Connection conexion = null;
        String baseDatos = "estudiantes_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String user = "root";
        String password = "admin";

        // Cagamos la clase del driver de MySQL en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ocurrio un error: " + e.getMessage());
        }

        return conexion;
    }

    public static void main(String[] args) {
        Object conexion = Conexion.getConexion();
        if (conexion != null){
            System.out.println("Conexión exitosa: " + conexion);
        }else {
            System.out.println("Error en la conexión.");
        }
    }
}
