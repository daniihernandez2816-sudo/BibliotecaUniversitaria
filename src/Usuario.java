/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Daniela Hernandez
 */
// CLASE ABSTRACTA: Igual que Recurso, agrupa datos generales de las personas.
public abstract class Usuario {
    private String documento;
    private String nombre;
    private String correo;
    private int maxPrestamos;
    private int prestamosActivos;

    public Usuario(String documento, String nombre, String correo, int maxPrestamos) {
        this.documento = documento;
        this.nombre = nombre;
        this.correo = correo;
        this.maxPrestamos = maxPrestamos;
        this.prestamosActivos = 0; // Inicia sin préstamos
    }

    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public int getPrestamosActivos() { return prestamosActivos; }
    public int getMaxPrestamos() { return maxPrestamos; }
    
    // VALIDACIÓN: Retorna 'true' solo si aún no supera su límite.
    public boolean puedePrestar() {
        return prestamosActivos < maxPrestamos;
    }
    
    public void registrarPrestamo() { prestamosActivos++; }
    public void registrarDevolucion() { prestamosActivos--; }
}