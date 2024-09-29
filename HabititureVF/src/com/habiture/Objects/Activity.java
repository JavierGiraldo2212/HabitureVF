package com.habiture.Objects;

import java.util.Comparator;
import java.time.LocalDate;
import java.time.LocalTime;

public class Activity {

    private String nombre;
    private LocalDate fecha;
    private int nivelImportancia;


    private LocalTime hora;
    private String descripcion;


    public enum NivelImportancia {
        UNO(1), DOS(2), TRES(3), CUATRO(4), CINCO(5);

        private final int valor;

        NivelImportancia(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }

        public static NivelImportancia fromValor(int valor) {
            for (NivelImportancia nivel : values()) {
                if (nivel.getValor() == valor) {
                    return nivel;
                }
            }
            return UNO;
        }
    }


    public Activity(String nombre, LocalDate fecha, int nivelImportancia) {
        this(nombre, fecha, NivelImportancia.fromValor(nivelImportancia).getValor(), LocalTime.of(23, 59), null);
    }

    public Activity(String nombre, LocalDate fecha, int nivelImportancia, LocalTime hora) {
        this(nombre, fecha, NivelImportancia.fromValor(nivelImportancia).getValor(), hora, null);
    }

    public Activity(String nombre, LocalDate fecha, int nivelImportancia, String descripcion) {
        this(nombre, fecha, NivelImportancia.fromValor(nivelImportancia).getValor(), LocalTime.of(23, 59), descripcion);
    }

    public Activity(String nombre, LocalDate fecha, int nivelImportancia, LocalTime hora, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.nivelImportancia = NivelImportancia.fromValor(nivelImportancia).getValor();
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public static Comparator<Activity> byFecha() {
        return (a1, a2) -> a1.getFecha().compareTo(a2.getFecha());
    }

    public static Comparator<Activity> byImportancia() {
        return (a1, a2) -> Integer.compare(a1.getNivelImportancia(), a2.getNivelImportancia());
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNivelImportancia() {
        return nivelImportancia;
    }

    public void setNivelImportancia(int nivelImportancia) {
        this.nivelImportancia = NivelImportancia.fromValor(nivelImportancia).getValor();
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre);
        sb.append(", Fecha: ").append(fecha);
        sb.append(", Nivel de importancia: ").append(nivelImportancia);
        if (hora != null) {
            sb.append(", Hora: ").append(hora);
        }
        if (descripcion != null && !descripcion.isEmpty()) {
            sb.append(", Descripción: ").append(descripcion);
        }
        return sb.toString();
    }
}