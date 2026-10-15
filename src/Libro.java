/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Daniela Hernandez
 */
// HERENCIA: Con 'extends', Libro hereda todo de Recurso (es una clase hija).
public class Libro extends Recurso {
    private String autor;
    private int numeroPaginas;

    public Libro(String codigo, String titulo, int diasPrestamo, String autor, int numeroPaginas) {
        // 'super' llama al constructor de la clase padre (Recurso) para inicializar esos datos.
        super(codigo, titulo, diasPrestamo);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    // POLIMORFISMO: '@Override' sobrescribe el método del padre. Aquí definimos que la multa es $1000.
    @Override
    public double calcularMulta(int diasRetraso) {
        return diasRetraso * 1000.0;
    }

    @Override
    public String toString() {
        return "[Libro] " + super.toString() + " | Autor: " + autor;
    }
}