package com.jorge.colegio;

public class Main {

    public static void main(String[] args) {

        GestionColegio gestionColegio = new GestionColegio();

        // Crear 5 alumnos
        gestionColegio.altaAlumno("12345678A", "Alumno 1", "español");
        gestionColegio.altaAlumno("98765432F", "Alumno 2", "francés");
        gestionColegio.altaAlumno("55886633S", "Alumno 3", "andorrano");
        gestionColegio.altaAlumnoConFecha("91827346H", "Alumno 4", 2023, 7, 1, "español");
        gestionColegio.altaAlumnoConFecha("10000059P", "Alumno 5", 2023, 9, 10, "portugués");


        // Crear 2 profesores
        gestionColegio.altaProfesor("65432100N", "Profesor 1", "ciencias");
        gestionColegio.altaProfesor("99966655D", "Profesor 2", "inglés");


        // Crear 3 asignaturas
        gestionColegio.altaAsignatura("QUI", "Química", 80, "65432100N");
        gestionColegio.altaAsignatura("MAT", "Matemáticas", 120, "65432100N");
        gestionColegio.altaAsignatura("ING", "Inglés", 70, "99966655D");


        // Listar solo los profesores
        gestionColegio.listarProfesores();
        emptyLine();

        // Listar solo los alumnos
        gestionColegio.listarAlumnos();
        emptyLine();

        // Listar las asignaturas
        gestionColegio.listarAsignaturas();
        emptyLine();


        // Matricular algunos alumnos en las asignaturas
        gestionColegio.matricularAlumno("QUI", "10000059P");
        gestionColegio.matricularAlumno("MAT", "12345678A");
        gestionColegio.matricularAlumno("MAT", "98765432F");
        gestionColegio.matricularAlumno("MAT", "55886633S");
        gestionColegio.matricularAlumno("MAT", "10000059P");
        gestionColegio.matricularAlumno("MAT", "91827346H");
        gestionColegio.matricularAlumno("ING", "12345678A");


        // Mostrar el expediente de dos alumnos
        gestionColegio.expedienteAlumno("10000059P");
        emptyLine();
        gestionColegio.expedienteAlumno("12345678A");
        emptyLine();


        // Mostrar la ficha de los dos profesores
        gestionColegio.fichaProfesor("65432100N");
        emptyLine();
        gestionColegio.fichaProfesor("99966655D");
        emptyLine();


        // Listar los alumnos de un año concreto y de una asignatura concreta
        gestionColegio.listaAlumnosPorAnnoYAsignatura(2023, "MAT");
        emptyLine();


        // Guardar datos en ficheros
        gestionColegio.guardarDatos();


        // Cargar datos desde ficheros, deberían salir duplicados
        gestionColegio.cargarDatos();
        gestionColegio.listarAsignaturas();
        emptyLine();
        gestionColegio.listarAlumnos();
        emptyLine();
        gestionColegio.listarProfesores();
        emptyLine();

    }

    private static void emptyLine() {
        System.out.println();
    }

}