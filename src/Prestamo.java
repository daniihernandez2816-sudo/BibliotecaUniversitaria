/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Daniela Hernandez
 */
// ASOCIACIÓN: Esta clase une a un Usuario con un Recurso.
public class Prestamo {
    private Usuario usuario;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private boolean activo;

    public Prestamo(Usuario usuario, Recurso recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionPrevista = fechaPrestamo.plusDays(recurso.getDiasPrestamo());
        this.activo = true;
        
        usuario.registrarPrestamo();
        recurso.setDisponible(false);
    }
    
    public Recurso getRecurso() { return recurso; }
    public Usuario getUsuario() { return usuario; }
    public boolean isActivo() { return activo; }
    public LocalDate getFechaDevolucionPrevista() { return fechaDevolucionPrevista; }

    // AHORA RECIBE LA FECHA DE DEVOLUCIÓN DIRECTAMENTE
    public double procesarDevolucion(LocalDate fechaReal) {
        this.activo = false;
        usuario.registrarDevolucion(); 
        recurso.setDisponible(true);   
        
        // Compara la fecha en que se entregó vs la fecha límite
        if (fechaReal.isAfter(fechaDevolucionPrevista)) {
            // Calcula los días exactos de diferencia entre ambas fechas
            int diasRetraso = (int) ChronoUnit.DAYS.between(fechaDevolucionPrevista, fechaReal);
            return recurso.calcularMulta(diasRetraso); 
        }
        return 0.0; // Si lo entregó antes o el mismo día, la multa es 0
    }
}