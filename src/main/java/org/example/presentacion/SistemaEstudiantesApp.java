package org.example.presentacion;

import org.example.datos.EstudianteDAO;
import org.example.dominio.Estudiante;

import java.util.List;
import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        EstudianteDAO estudianteDao = new EstudianteDAO();

        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(sc, estudianteDao);
            } catch (Exception e) {

            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("::: SISTEMA ESTUDIANTES :::");
        System.out.println(" 1- Listar Estudiantes \n 2- Buscar Estudiante \n 3- Agregar Estudiante \n " +
                "4- Modificar Estudiante \n 5- Eliminar Estudiante \n 6- Salir \n::: Escoge una Opcion :::");
    }

    private static boolean ejecutarOpciones(Scanner sc, EstudianteDAO estudianteDAO) {
        int opcion = Integer.parseInt(sc.nextLine());
        boolean salir = false;

        switch (opcion) {
            case 1:
                System.out.println(":: Listado de Estudiantes ::");
                List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
                break;
            case 2:
                System.out.println(":: Buscar estudiante :: \nIngresa el ID del estudiante a buscar:");
                int idEstudiante = Integer.parseInt(sc.nextLine());
                Estudiante estudiante = new Estudiante(idEstudiante);
                boolean estudianteEncontrado = estudianteDAO.findById(estudiante);
                if (estudianteEncontrado) {
                    System.out.println("Se encontró el estudiante: " + estudiante);
                } else {
                    System.out.println("Estudiane no encontrado: " + estudiante);
                }
                break;
            case 3:
                System.out.println(":: Agregar Estudiante ::");
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido: ");
                String apellido = sc.nextLine();
                System.out.print("Teléfono: ");
                String telefono = sc.nextLine();
                System.out.print("E-mail: ");
                String email = sc.nextLine();
                //Creamos objeto estudiante
                Estudiante estudianteAgregar = new Estudiante(nombre, apellido, telefono, email);
                boolean estudianteAgregado = estudianteDAO.addStudent(estudianteAgregar);
                if (estudianteAgregado) {
                    System.out.println("Estudiante agregado: " + estudianteAgregar);
                } else {
                    System.out.println("El estudiante no fue agregado: " + estudianteAgregar);
                }
                break;
            case 4:
                System.out.println(":: Modificar estudiante ::");
                System.out.print("Ingresa el ID del estudiante a modificar: ");
                int idEstudianteM = Integer.parseInt(sc.nextLine());
                System.out.print("Nombre: ");
                String nombreM = sc.nextLine();
                System.out.print("Apellido: ");
                String apellidoM = sc.nextLine();
                System.out.print("Teléfono: ");
                String telefonoM = sc.nextLine();
                System.out.print("E-mail: ");
                String emailM = sc.nextLine();

                //Objeto de estudiante a modificar
                Estudiante estudianteModificar = new Estudiante(idEstudianteM, nombreM, apellidoM, telefonoM, emailM);
                boolean estudianteModificado = estudianteDAO.modificarEstudiante(estudianteModificar);
                if (estudianteModificado) {
                    System.out.println("Estudiante fué modificado: " + estudianteModificar);
                } else {
                    System.out.println("El estudiante NO fue modificado: " + estudianteModificar);
                }
                break;
            case 5:
                System.out.println(":: Eliminar Estudiante ::");
                System.out.print("Ingresa el ID del estudiante a Eliminar: ");
                int idEstudianteE = Integer.parseInt(sc.nextLine());
                Estudiante estudianteEliminar = new Estudiante(idEstudianteE);
                boolean estudianteEliminado = estudianteDAO.eliminarEstudiante(estudianteEliminar);
                if (estudianteEliminado){
                    System.out.println("Estudiante Eliminado: "+ estudianteEliminar);
                }else {
                    System.out.println("Estudiante NO eliminado: "+ estudianteEliminar);
                }
                break;

            case 6:
                System.out.println(":: ¡Hasta Pronto! ::");
                salir= true;
                break;
            default:
                System.out.println("Opción no reconocida. Intenta con una de las opciones del menú.");
        }
        return salir;
    }
}