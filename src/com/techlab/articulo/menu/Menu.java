package com.techlab.articulo.menu;

import com.techlab.articulo.utils.Validaciones;
import java.util.Scanner;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta debe ser la clase base de todos los menús.
 *
 * Objetivo:
 * centralizar la lógica común para no repetir código.
 *
 * Esta clase debe:
 * - guardar un Scanner compartido
 * - declarar el método mostrarMenu()
 * - declarar el método ejecutar()
 *
 * Además, podés agregar métodos protegidos reutilizables, por ejemplo:
 * - leerEntero(String mensaje)
 * - leerDouble(String mensaje)
 * - leerTexto(String mensaje)
 * - leerSiNo(String mensaje)
 *
 * IMPORTANTE:
 * Esta clase debe ser abstracta, porque no tiene sentido crear un
 * "menú genérico" instanciable. Solo debe servir como base para:
 * - MenuArticulos
 * - MenuCategorias
 */
public abstract class Menu {

    protected Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    // TODO:
    // Declarar método abstracto para mostrar el menú.
    public abstract void mostrarMenu();

    // TODO:
    // Declarar método abstracto para ejecutar el menú.
    public abstract void ejecutar();

    protected int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    protected double leerDouble(String mensaje) {
        System.out.print(mensaje);
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    protected String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    protected int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            valor = leerEntero(mensaje);
            if (!Validaciones.validarPositivo(valor)) {
                System.out.println("Debe ingresar un número positivo.");
            }
        } while (!Validaciones.validarPositivo(valor));
        return valor;
    }

    protected double leerDoublePositivo(String mensaje) {
        double valor;
        do {
            valor = leerDouble(mensaje);
            if (!Validaciones.validarPositivo(valor)) {
                System.out.println("Debe ingresar un número positivo.");
            }
        } while (!Validaciones.validarPositivo(valor));
        return valor;
    }
}
