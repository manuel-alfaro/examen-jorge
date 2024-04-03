package com.jorge.colegio;

import java.util.List;

public class Asignatura {

    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Profesor profesor;
    private List<Alumno> alumnos;

    public Asignatura(String codigo, String nombre, int horasAnuales, Profesor profesor, List<Alumno> alumnos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horasAnuales = horasAnuales;
        this.profesor = profesor;
        this.alumnos = alumnos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        this.horasAnuales = horasAnuales;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

}