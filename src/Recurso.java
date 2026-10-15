/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Daniela Hernandez
 */
// CLASE ABSTRACTA: Es un molde general. Nunca creamos un "Recurso" suelto, sino un Libro, Revista o Equipo.
public abstract class Recurso {
    
    // ENCAPSULAMIENTO: Atributos 'private' para que nadie pueda modificarlos directamente desde afuera.
    private String codigo;
    private String titulo;
    private boolean disponible;
    private int diasPrestamo;

    public Recurso(String codigo, String titulo, int diasPrestamo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponible = true; // Por defecto, al registrarse, está disponible.
        this.diasPrestamo = diasPrestamo;
    }

    // GETTERS Y SETTERS: Son la forma segura de acceder o cambiar los atributos privados.
    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public int getDiasPrestamo() { return diasPrestamo; }

    // MÉTODO ABSTRACTO (POLIMORFISMO): Obliga a que cada clase "hija" decida cómo se calcula su propia multa.
    public abstract double calcularMulta(int diasRetraso);
    
    @Override
    public String toString() {
        return "Código: " + codigo + " | Título: " + titulo + " | Días Préstamo: " + diasPrestamo;
    }
}