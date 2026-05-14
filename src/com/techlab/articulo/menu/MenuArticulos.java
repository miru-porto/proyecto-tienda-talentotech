package com.techlab.articulo.menu;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.utils.Secuencias;
import com.techlab.articulo.utils.Validaciones;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuArticulos extends Menu {

    private ArrayList<Articulo> articulos;
    private ArrayList<Categoria> categorias;

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

        Articulo articulo;

        if (tipo == 1) {
            int garantia = pedirGarantia();
            articulo = new ArticuloElectronico(Secuencias.generarCodigoArticulo(), nombre, precio, categoria, garantia);
        } else if (tipo == 2) {
            int dias = pedirDiasParaVencimiento();
            articulo = new ArticuloAlimenticio(Secuencias.generarCodigoArticulo(), nombre, precio, categoria, dias);
        } else {
            System.out.println("Tipo inválido. Artículo no creado.");
            return;
        }

        articulos.add(articulo);
        System.out.println("Artículo agregado con código " + articulo.getCodigo() + ".");
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
        if (Validaciones.validarTextoNoVacio(nuevoNombre)) {
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
        String nombre;
        do {
            System.out.print("Nombre del artículo: ");
            nombre = scanner.nextLine();
            if (!Validaciones.validarTextoNoVacio(nombre)) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (!Validaciones.validarTextoNoVacio(nombre));
        return nombre;
    }

    private double pedirPrecioArticulo() {
        double precio;
        do {
            System.out.print("Precio: ");
            precio = scanner.nextDouble();
            scanner.nextLine();
            if (!Validaciones.validarNoNegativo(precio)) {
                System.out.println("El precio no puede ser negativo.");
            }
        } while (!Validaciones.validarNoNegativo(precio));
        return precio;
    }

    private int pedirGarantia() {
        int g;
        do {
            System.out.print("Garantía en meses: ");
            g = scanner.nextInt();
            scanner.nextLine();
            if (!Validaciones.validarNoNegativo(g)) {
                System.out.println("La garantía no puede ser negativa.");
            }
        } while (!Validaciones.validarNoNegativo(g));
        return g;
    }

    private int pedirDiasParaVencimiento() {
        int d;
        do {
            System.out.print("Días para vencimiento: ");
            d = scanner.nextInt();
            scanner.nextLine();
            if (!Validaciones.validarNoNegativo(d)) {
                System.out.println("Los días no pueden ser negativos.");
            }
        } while (!Validaciones.validarNoNegativo(d));
        return d;
    }

    private Articulo buscarPorCodigo(int codigo) {
        for (Articulo a : articulos) {
            if (a.getCodigo() == codigo) return a;
        }
        return null;
    }
}
