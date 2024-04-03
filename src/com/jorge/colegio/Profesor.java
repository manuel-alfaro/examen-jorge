package com.jorge.colegio;

public class Profesor extends Personal {

    private String departamento;

    public Profesor(String dni, String nombre, String departamento) {
        super(dni, nombre);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}