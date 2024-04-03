package com.jorge.colegio;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestionColegio {

    public static final List<Asignatura> asignaturas = new ArrayList<>();
    public static final List<Personal> personal = new ArrayList<>();


    public void listarProfesores() {
        System.out.println("PROFESORES:");
        for (Personal p : personal) {
            if (p instanceof Profesor) {
                System.out.println(p.getNombre() + ", " + p.getDni() + ", " + ((Profesor) p).getDepartamento());
            }
        }
    }

    public void listarAlumnos() {
        System.out.println("ALUMNOS:");
        for (Personal p : personal) {
            if (p instanceof Alumno) {
                System.out.println(p.getNombre() + ", " + p.getDni() + ", " + ((Alumno) p).getFechaMatriculacion() + ", " + ((Alumno) p).getNacionalidad());
            }
        }
    }

    public void altaProfesor(String dni, String nombre, String departamento) {
        Profesor p = new Profesor(dni, nombre, departamento);
        personal.add(p);
    }

    public void altaAlumno(String dni, String nombre, String nacionalidad) {
        Alumno a = new Alumno(dni, nombre, LocalDate.now(), nacionalidad);
        personal.add(a);
    }

    public void altaAlumnoConFecha(String dni, String nombre, int anno, int mes, int dia, String nacionalidad) {
        Alumno a = new Alumno(dni, nombre, LocalDate.of(anno, mes, dia), nacionalidad);
        personal.add(a);
    }

    public void altaAsignatura(String codigo, String nombre, int horas, String dniProfesor) {
        if (!existeAsignatura(codigo) && existeProfesor(dniProfesor)) {
            Asignatura asignatura = new Asignatura(codigo, nombre, horas, getProfesor(dniProfesor), new ArrayList<>());
            asignaturas.add(asignatura);
            ordenarAsignaturas();
        } else {
            System.out.println("No se puede añadir la asignatura " + nombre + " con código " + codigo);
        }
    }

    private void ordenarAsignaturas() {
        asignaturas.sort(Comparator.comparing(Asignatura::getHorasAnuales));
    }

    public void listarAsignaturas() {
        for (Asignatura a : asignaturas) {
            imprimirAsignatura(a);
        }
    }

    public void imprimirAsignatura(Asignatura a) {
        System.out.println(a.getCodigo() + " - " + a.getNombre() + " - " + a.getHorasAnuales() + " horas anuales");
    }

    public void matricularAlumno(String codigoAsignatura, String dniAlumno) {
        if (existeAsignatura(codigoAsignatura) && existeAlumno(dniAlumno)) {
            getAsignatura(codigoAsignatura).getAlumnos().add(getAlumno(dniAlumno));
        } else {
            System.out.println("No se puede matricular al alumno " + dniAlumno + " en la asignatura " + codigoAsignatura);
        }
    }

    private boolean existeAsignatura(String codigo) {
        return getAsignatura(codigo) != null;
    }

    private Asignatura getAsignatura(String codigo) {
        return asignaturas.stream()
                .filter(a -> a.getCodigo().equals(codigo))
                .findAny().orElse(null);
    }

    private boolean existeProfesor(String dni) {
        return getProfesor(dni) != null;
    }

    private Profesor getProfesor(String dni) {
        return (Profesor) personal.stream()
                .filter(p -> p.getDni().equals(dni) && p instanceof Profesor)
                .findAny().orElse(null);
    }

    private boolean existeAlumno(String dni) {
        return getAlumno(dni) != null;
    }

    private Alumno getAlumno(String dni) {
        return (Alumno) personal.stream()
                .filter(p -> p.getDni().equals(dni) && p instanceof Alumno)
                .findAny().orElse(null);
    }

    private List<Asignatura> asignaturasDeUnAlumno(String dni) {
        List<Asignatura> asignaturaList = new ArrayList<>();
        for (Asignatura a : asignaturas) {
            if (a.getAlumnos().stream().anyMatch(alumno -> alumno.getDni().equals(dni))) {
                asignaturaList.add(a);
            }
        }
        return asignaturaList;
    }

    private List<Asignatura> asignaturasDeUnProfesor(String dni) {
        List<Asignatura> asignaturaList = new ArrayList<>();
        for (Asignatura a : asignaturas) {
            if (a.getProfesor().getDni().equals(dni)) {
                asignaturaList.add(a);
            }
        }
        return asignaturaList;
    }

    public void expedienteAlumno(String dni) {
        Alumno a = getAlumno(dni);
        if (a != null) {
            List<Asignatura> asignaturasDelAlumno = asignaturasDeUnAlumno(dni);
            System.out.println("Expediente de " + a.getNombre());
            System.out.println("DNI: " + dni);
            System.out.println("Fecha de matriculación: " + a.getFechaMatriculacion());
            System.out.println("Nacionalidad: " + a.getNacionalidad());
            System.out.println("Asignaturas:");
            for (Asignatura as : asignaturasDelAlumno) {
                System.out.printf("  ");
                imprimirAsignatura(as);
            }
        }
    }

    public void fichaProfesor(String dni) {
        Profesor p = getProfesor(dni);
        if (p != null) {
            List<Asignatura> asignaturasDelProfesor = asignaturasDeUnProfesor(dni);
            System.out.println("Ficha de " + p.getNombre());
            System.out.println("DNI: " + dni);
            System.out.println("Departamento: " + p.getDepartamento());
            System.out.println("Asignaturas:");
            for (Asignatura as : asignaturasDelProfesor) {
                System.out.printf("  ");
                imprimirAsignatura(as);
            }
        }
    }

    public void listaAlumnosPorAnnoYAsignatura(int anno, String asignatura) {
        Asignatura a = getAsignatura(asignatura);
        System.out.println("Alumnos de " + a.getNombre() + " del año " + anno + ":");
        for (Alumno al : a.getAlumnos()) {
            if (al.getFechaMatriculacion().getYear() == anno) {
                System.out.printf("  ");
                System.out.println(al.getNombre() + " - DNI: " + al.getDni() + " - Fecha de matriculación: " + al.getFechaMatriculacion());
            }
        }
    }

    public void guardarDatos() {
        try {
            FileWriter asignaturasFile = new FileWriter("asignaturas.csv");
            FileWriter alumnosFile = new FileWriter("alumnos.csv");
            FileWriter profesoresFile = new FileWriter("profesores.csv");

            for (Asignatura as : asignaturas) {
                asignaturasFile.write(as.getCodigo() + ";"
                        + as.getNombre() + ";"
                        + as.getHorasAnuales() + ";"
                        + as.getProfesor().getDni() + "\n");
            }

            for (Personal p : personal) {
                if (p instanceof Alumno) {
                    alumnosFile.write(p.getDni() + ";"
                    + p.getNombre() + ";"
                    + ((Alumno) p).getFechaMatriculacion() + ";"
                    + ((Alumno) p).getNacionalidad() + "\n");
                } else if (p instanceof Profesor) {
                    profesoresFile.write(p.getDni() + ";"
                            + p.getNombre() + ";"
                            + ((Profesor) p).getDepartamento() + "\n");
                }
            }

            asignaturasFile.close();
            alumnosFile.close();
            profesoresFile.close();

        } catch (IOException ioe) {
            System.out.println("Error guardando datos :(");
        }

    }

    public void cargarDatos() {
        String line;
        try {
            BufferedReader brAs = new BufferedReader(new FileReader("asignaturas.csv"));
            while ((line = brAs.readLine()) != null) {
                String[] valores = line.split(";");
                asignaturas.add(new Asignatura(valores[0], valores[1], Integer.parseInt(valores[2]), getProfesor(valores[3]), new ArrayList<>()));
            }

            BufferedReader brAl = new BufferedReader(new FileReader("alumnos.csv"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = brAl.readLine()) != null) {
                String[] valores = line.split(";");
                personal.add(new Alumno(valores[0], valores[1], LocalDate.parse(valores[2], formatter), valores[3]));
            }

            BufferedReader brPr = new BufferedReader(new FileReader("profesores.csv"));
            while ((line = brPr.readLine()) != null) {
                String[] valores = line.split(";");
                personal.add(new Profesor(valores[0], valores[1], valores[2]));
            }

        } catch (IOException ioe) {
            System.out.println("Error cargando datos :(");
        }

    }

}