package com.techlab.articulo;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.articulo.menu.MenuArticulos;
import com.techlab.articulo.menu.MenuPedidos;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Pedido;

public class App {

    public static void main(String[] args) {

        // ============================================================
        // INSTRUCCIONES PARA ESTA CLASE
        // ============================================================
        //
        // Esta clase debe actuar como punto de entrada del programa.
        //
        // ¿Qué debe hacer el alumno acá?
        // 1) Crear un Scanner compartido.
        // 2) Crear los repositorios necesarios:
        //    - Repositorio<Articulo>
        //    - Repositorio<Categoria>
        // 3) Crear los dos menús:
        //    - MenuArticulos
        //    - MenuCategorias
        // 4) Crear un menú principal que permita elegir:
        //    - 1: ir al menú de artículos
        //    - 2: ir al menú de categorías
        //    - 0: salir
        // 5) Cerrar correctamente el Scanner al finalizar.
        //
        // Sugerencia:
        // El main NO debe tener toda la lógica del sistema.
        // El main solo debe coordinar el flujo principal.
        //
        // ============================================================

        Scanner scanner = new Scanner(System.in);

        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        MenuArticulos menuArticulos = new MenuArticulos(scanner, articulos);
        MenuPedidos menuPedidos = new MenuPedidos(scanner, articulos, pedidos);

        int eleccion;
        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTIÓN - TECHLAB");
            System.out.println("========================================");
            System.out.println("1 - Gestionar artículos");
            System.out.println("2 - Gestionar pedidos");
            System.out.println("0 - Salir");
            System.out.print("Elija una opción: ");
            eleccion = scanner.nextInt();
            scanner.nextLine();
            switch (eleccion) {
                case 1: menuArticulos.ejecutar(); break;
                case 2: menuPedidos.ejecutar(); break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (eleccion != 0);

        scanner.close();
    }
}
