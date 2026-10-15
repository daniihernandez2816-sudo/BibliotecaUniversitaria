/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Daniela Hernandez
 */
public class Revista extends Recurso {
    private int numeroEdicion;
    private String periodicidad;

    public Revista(String codigo, String titulo, int diasPrestamo, int numeroEdicion, String periodicidad) {
        super(codigo, titulo, diasPrestamo);
        this.numeroEdicion = numeroEdicion;
        this.periodicidad = periodicidad;
    }

    // POLIMORFISMO: La revista también cobra $1000 por día de retraso.
    @Override
    public double calcularMulta(int diasRetraso) {
        return diasRetraso * 1000.0;
    }

    @Override
    public String toString() {
        return "[Revista] " + super.toString() + " | Edición: " + numeroEdicion;
    }
}
