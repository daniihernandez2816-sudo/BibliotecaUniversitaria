/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Daniela Hernandez
 */
public class Estudiante extends Usuario {
    public Estudiante(String documento, String nombre, String correo) {
        // Le pasamos un '3' al padre para establecer su límite de préstamos.
        super(documento, nombre, correo, 3);
    }
}
