/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

/**
 *
 * @author Daniela Hernandez
 */

public class Docente extends Usuario {
    public Docente(String documento, String nombre, String correo) {
        // Le pasamos un '5' al padre para establecer su límite (regla del problema).
        super(documento, nombre, correo, 5);
    }
}

