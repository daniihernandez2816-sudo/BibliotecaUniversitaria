/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate; // Se añade para manejar fechas
/**
 *
 * @author Daniela Hernandez
 */
public class Main {
    static ArrayList<Recurso> inventario = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Prestamo> prestamos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        inventario.add(new Libro("L01", "Cien Años de Soledad", 15, "Gabo", 400));
        inventario.add(new Revista("R01", "Revista Semana", 7, 2045, "Semanal"));
        inventario.add(new EquipoTecnologico("E01", "Portátil HP", 3, "HP", "SN123"));
        usuarios.add(new Estudiante("1001", "Daniela Hernandez", "dhernandez@unisalle.edu.co"));

        int opcion = 0;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Registrar Recurso");
            System.out.println("3. Registrar Préstamo");
            System.out.println("4. Registrar Devolución (y ver multas)");
            System.out.println("5. Listar Recursos Disponibles");
            System.out.println("6. Listar Préstamos Activos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1: registrarUsuario(); break;
                case 2: registrarRecurso(); break;
                case 3: registrarPrestamo(); break;
                case 4: registrarDevolucion(); break;
                case 5: listarRecursosDisponibles(); break;
                case 6: listarPrestamosUsuario(); break;
                case 7: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }

    static void registrarUsuario() {
        System.out.print("Documento: "); String doc = sc.nextLine();
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Correo: "); String correo = sc.nextLine();
        System.out.print("Tipo (1: Estudiante, 2: Docente): "); String tipo = sc.nextLine();
        
        if (tipo.equals("1")) usuarios.add(new Estudiante(doc, nom, correo));
        else if (tipo.equals("2")) usuarios.add(new Docente(doc, nom, correo));
        System.out.println("Registrado exitosamente.");
    }

    static void registrarRecurso() {
        System.out.print("Código: "); String cod = sc.nextLine();
        System.out.print("Título: "); String tit = sc.nextLine();
        System.out.print("Días de préstamo: "); int dias = Integer.parseInt(sc.nextLine());
        
        System.out.print("Tipo (1. Libro | 2. Revista | 3. Equipo): ");
        String tipo = sc.nextLine();
        
        switch (tipo) {
            case "1":
                System.out.print("Autor: "); String aut = sc.nextLine();
                System.out.print("Páginas: "); int pag = Integer.parseInt(sc.nextLine());
                inventario.add(new Libro(cod, tit, dias, aut, pag));
                break;
            case "2":
                System.out.print("Edición: "); int ed = Integer.parseInt(sc.nextLine());
                System.out.print("Periodicidad: "); String per = sc.nextLine();
                inventario.add(new Revista(cod, tit, dias, ed, per));
                break;
            case "3":
                System.out.print("Marca: "); String marca = sc.nextLine();
                System.out.print("Serial: "); String serial = sc.nextLine();
                inventario.add(new EquipoTecnologico(cod, tit, dias, marca, serial));
                break;
        }
        System.out.println("Recurso registrado.");
    }

    static void registrarPrestamo() {
        System.out.print("Documento del usuario: "); String doc = sc.nextLine();
        Usuario user = buscarUsuario(doc);
        if (user == null || !user.puedePrestar()) {
            System.out.println("Error: Usuario no existe o superó límite de préstamos."); return;
        }
        
        System.out.print("Código del recurso: "); String cod = sc.nextLine();
        Recurso rec = buscarRecurso(cod);
        if (rec == null || !rec.isDisponible()) {
            System.out.println("Error: Recurso no existe o ya está prestado."); return;
        }
        
        Prestamo p = new Prestamo(user, rec);
        prestamos.add(p);
        System.out.println("Préstamo exitoso. Devolver el: " + p.getFechaDevolucionPrevista());
    }

    // ESTE MÉTODO CAMBIÓ PARA PEDIR FECHA
    static void registrarDevolucion() {
        System.out.print("Código del recurso a devolver: "); String cod = sc.nextLine();
        Prestamo prestamoActivo = null;
        
        for (Prestamo p : prestamos) {
            if (p.isActivo() && p.getRecurso().getCodigo().equals(cod)) {
                prestamoActivo = p; break;
            }
        }
        
        if (prestamoActivo == null) {
            System.out.println("No hay un préstamo activo para ese recurso.");
            return;
        }
        
        System.out.println("Fecha límite de entrega era: " + prestamoActivo.getFechaDevolucionPrevista());
        System.out.print("Ingrese fecha de entrega (Formato AAAA-MM-DD) o presione Enter para usar la de hoy: ");
        String fechaInput = sc.nextLine();
        
        LocalDate fechaEntrega;
        if (fechaInput.trim().isEmpty()) {
            fechaEntrega = LocalDate.now(); // Si el usuario solo da Enter, asume que es hoy
        } else {
            try {
                fechaEntrega = LocalDate.parse(fechaInput); // Convierte el texto a fecha
            } catch (Exception e) {
                System.out.println("Formato inválido. Asumiendo fecha de hoy.");
                fechaEntrega = LocalDate.now();
            }
        }
        
        double multa = prestamoActivo.procesarDevolucion(fechaEntrega);
        System.out.println("Devuelto exitosamente.");
        if (multa > 0) {
            System.out.println("ATENCIÓN: Multa generada por retraso de $" + multa);
        } else {
            System.out.println("Entregado a tiempo. Multa: $0.0");
        }
    }

    static void listarRecursosDisponibles() {
        for (Recurso r : inventario) {
            if (r.isDisponible()) System.out.println(r.toString());
        }
    }

    static void listarPrestamosUsuario() {
        System.out.print("Documento: "); String doc = sc.nextLine();
        Usuario user = buscarUsuario(doc);
        
        // Primero validamos si el usuario existe
        if (user == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }
        
        System.out.println("Préstamos activos para " + user.getNombre() + ":");
        boolean tienePrestamos = false; // Variable para rastrear si encontramos alguno
        
        for (Prestamo p : prestamos) {
            if (p.isActivo() && p.getUsuario().getDocumento().equals(doc)) {
                System.out.println("- " + p.getRecurso().getTitulo() + " | Fecha límite: " + p.getFechaDevolucionPrevista());
                tienePrestamos = true; // Si entra aquí, significa que sí tiene préstamos
            }
        }
        
        // Si después de revisar todo, la variable sigue siendo falsa, mostramos el mensaje
        if (!tienePrestamos) {
            System.out.println("El usuario no tiene ningún préstamo activo en este momento.");
        }
    }

    static Usuario buscarUsuario(String doc) {
        for (Usuario u : usuarios) if (u.getDocumento().equals(doc)) return u;
        return null;
    }
    static Recurso buscarRecurso(String cod) {
        for (Recurso r : inventario) if (r.getCodigo().equals(cod)) return r;
        return null;
    }
}