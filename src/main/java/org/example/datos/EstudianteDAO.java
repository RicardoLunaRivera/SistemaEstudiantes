package org.example.datos;

import org.example.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.example.conexion.Conexion.getConexion;

// DAO -> Dataacces Object
public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();

        //Comunicarse con la base de datos
        // Prepara las sentencias SQl
        PreparedStatement ps;
        //Almacena el resultado obtenido de la base de datos
        ResultSet rs;

        //Conexión a base de datos
        Connection con = getConexion();

        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Ocurro un error: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar conexión: " + e.getMessage());
            }
        }
        return estudiantes;
    }

    public boolean findById(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar el alumno: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexión: " + e.getMessage());
            }

        }
        return false;
    }

    public boolean addStudent(Estudiante estudiante){

    }

    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        //listar estudiantes
        System.out.println("Listado de estudiantes");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);


        //buscar por Id
        Estudiante estudiante1 = new Estudiante(1);
        boolean fueEncontrado = estudianteDAO.findById(estudiante1);
        if (fueEncontrado) {
            System.out.println("Estudiante encontrado: " + estudiante1);
        } else {
            System.out.println("No se encontró estudiante: " + estudiante1.getIdEstudiante());
        }
    }
}
