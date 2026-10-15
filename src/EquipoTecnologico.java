/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Daniela Hernandez
 */
public class EquipoTecnologico extends Recurso {
    private String marca;
    private String serial;

    public EquipoTecnologico(String codigo, String titulo, int diasPrestamo, String marca, String serial) {
        super(codigo, titulo, diasPrestamo);
        this.marca = marca;
        this.serial = serial;
    }

    // POLIMORFISMO: A diferencia del libro, el equipo cobra $3000 de multa. Esto demuestra el polimorfismo.
    @Override
    public double calcularMulta(int diasRetraso) {
        return diasRetraso * 3000.0;
    }

    @Override
    public String toString() {
        return "[Equipo] " + super.toString() + " | Marca: " + marca + " | Serial: " + serial;
    }
}
