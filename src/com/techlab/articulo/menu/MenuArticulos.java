package com.techlab.articulo.menu;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuArticulos extends Menu {

    private ArrayList<Articulo> articulos;
    private ArrayList<Categoria> categorias;
    private int proximoCodigo = 1;

    public MenuArticulos(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) {
        super(scanner);
        this.articulos = articulos;
        this.categorias = categorias;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ ARTÍCULOS ---");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar artículo");
        System.out.println("4 - Modificar artículo");
        System.out.println("5 - Eliminar artículo");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        int eleccion;
        do {
            mostrarMenu();
            System.out.print("Elija una opción: ");
            eleccion = scanner.nextInt();
            scanner.nextLine();
            switch (eleccion) {
                case 1: ingresarArticulo(); break;
                case 2: listarArticulos(); break;
                case 3: consultarArticulo(); break;
                case 4: modificarArticulo(); break;
                case 5: eliminarArticulo(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (eleccion != 0);
    }

    private void ingresarArticulo() {
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías disponibles. Cree una categoría primero.");
            return;
        }

        Categoria categoria = pedirCategoriaExistente();
        if (categoria == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }

        String nombre = pedirNombreArticulo();
        double precio = pedirPrecioArticulo();

        System.out.println("Tipo de artículo:");
        System.out.println("  1 - Electrónico");
        System.out.println("  2 - Alimenticio");
        System.out.print("Elija: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        int codigo = proximoCodigo++;
        Articulo articulo;

        if (tipo == 1) {
            int garantia = pedirGarantia();
            articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantia);
        } else if (tipo == 2) {
            int dias = pedirDiasParaVencimiento();
            articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, dias);
        } else {
            System.out.println("Tipo inválido. Artículo no creado.");
            proximoCodigo--;
            return;
        }

        articulos.add(articulo);
        System.out.println("Artículo agregado con código " + codigo + ".");
    }

    private void listarArticulos() {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }
        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");
        for (Articulo a : articulos) {
            System.out.println(a);
        }
    }

    private void consultarArticulo() {
        System.out.print("Ingrese código del artículo: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        Articulo a = buscarPorCodigo(codigo);
        if (a == null) {
            System.out.println("Artículo no encontrado.");
        } else {
            System.out.println(a);
        }
    }

    private void modificarArticulo() {
        System.out.print("Ingrese código del artículo a modificar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        Articulo a = buscarPorCodigo(codigo);
        if (a == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }
        System.out.println("Artículo actual: " + a);
        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            a.setNombre(nuevoNombre);
        }
        System.out.print("Nuevo precio (0 para mantener): ");
        double nuevoPrecio = scanner.nextDouble();
        scanner.nextLine();
        if (nuevoPrecio > 0) {
            a.setPrecio(nuevoPrecio);
        }
        System.out.println("Artículo modificado.");
    }

    private void eliminarArticulo() {
        System.out.print("Ingrese código del artículo a eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        Articulo a = buscarPorCodigo(codigo);
        if (a == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }
        articulos.remove(a);
        System.out.println("Artículo eliminado.");
    }

    private Categoria pedirCategoriaExistente() {
        System.out.println("\nCategorías disponibles:");
        for (Categoria c : categorias) {
            System.out.println("  " + c.getCodigo() + " - " + c);
        }
        System.out.print("Ingrese código de categoría: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        for (Categoria c : categorias) {
            if (c.getCodigo() == codigo) return c;
        }
        return null;
    }

    private String pedirNombreArticulo() {
        System.out.print("Nombre del artículo: ");
        return scanner.nextLine();
    }

    private double pedirPrecioArticulo() {
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        return precio;
    }

    private int pedirGarantia() {
        System.out.print("Garantía en meses: ");
        int g = scanner.nextInt();
        scanner.nextLine();
        return g;
    }

    private int pedirDiasParaVencimiento() {
        System.out.print("Días para vencimiento: ");
        int d = scanner.nextInt();
        scanner.nextLine();
        return d;
    }

    private Articulo buscarPorCodigo(int codigo) {
        for (Articulo a : articulos) {
            if (a.getCodigo() == codigo) return a;
        }
        return null;
    }
}
