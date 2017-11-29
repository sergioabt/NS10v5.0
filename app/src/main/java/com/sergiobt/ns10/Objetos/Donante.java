package com.sergiobt.ns10.Objetos;

/**
 * Created by User on 26/11/2017.
 */

public class Donante {
    String cedula,nombre,edad,tipos,genero,antecedentes,peso,presion,ultd ;

    public Donante() {
    }

    public Donante(String cedula, String nombre, String edad, String tipos, String genero, String antecedentes, String peso, String presion, String ultd) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.tipos = tipos;
        this.genero = genero;
        this.antecedentes = antecedentes;
        this.peso = peso;
        this.presion = presion;
        this.ultd = ultd;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTipos() {
        return tipos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getUltd() {
        return ultd;
    }

    public void setUltd(String ultd) {
        this.ultd = ultd;
    }
}
