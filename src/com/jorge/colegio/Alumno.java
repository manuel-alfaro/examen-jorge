package com.jorge.colegio;

import java.time.LocalDate;

public class Alumno extends Personal {

    private LocalDate fechaMatriculacion;
    private String nacionalidad;

    public Alumno(String dni, String nombre, LocalDate fechaMatriculacion, String nacionalidad) {
        super(dni, nombre);
        this.fechaMatriculacion = fechaMatriculacion;
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

}