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

    //Agregar Estudiante
    public boolean addStudent(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email)" +
                "VALUES(?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al agregar al Estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexión: " + e.getMessage());
            }

        }
        return false;
    }

    //modificar estudinte
    public boolean modificarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "UPDATE estudiante SET nombre = ?, apellido = ?, telefono = ?, email = ? WHERE id_estudiante = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión" + e.getMessage());
            }
        }
        return false;
    }

    //Eliminar estudiante
    public boolean eliminarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión");
            }
        }

        return false;
    }
}

/*    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = new EstudianteDAO();

       //Agregar estudiante
        Estudiante nuevoEstudiante = new Estudiante("Mistk","ketchup", "88552233","mistik@kanto.com");
        boolean fueAgregado = estudianteDAO.addStudent(nuevoEstudiante);
        if(fueAgregado){
            System.out.println("Estudiante Agregado: " + nuevoEstudiante);
        }else {
            System.out.println("No se pudo agregar al nuevo estudiante: " + nuevoEstudiante);
        }

      //modificar Estudiante
        Estudiante estudianteModificar = new Estudiante(3, "Mistik", "Ketchup", "7744115522", "mistik@kanto.com.mx");
        boolean fueModificado = estudianteDAO.modificarEstudiante(estudianteModificar);
        if (fueModificado) {
            System.out.println("Estudiante modificado: " + estudianteModificar);
        } else {
            System.out.println("No se pudo modificar " + estudianteModificar);
        }

        //Eliminar Estudiante

      Estudiante estudianteEliminar =  new Estudiante(3);
        boolean fueEliminado = estudianteDAO.eliminarEstudiante(estudianteEliminar);

        if (fueEliminado){
            System.out.println("El estudinte fué eliminado: "+ estudianteEliminar);
        }else {
            System.out.println("El estudiante no se pudo eliminar: "+ estudianteEliminar);
        }
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
} */
