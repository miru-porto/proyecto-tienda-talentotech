package com.techlab.articulo.menu;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Menu y encargarse del CRUD de categorías.
 *
 * Debe trabajar con:
 * - Repositorio<Categoria>
 * - Repositorio<Articulo>
 *
 * ¿Por qué necesita también artículos?
 * Porque antes de eliminar una categoría debe verificarse si está
 * siendo utilizada por algún artículo.
 *
 * FUNCIONALIDADES ESPERADAS
 * ------------------------------------------------------------
 * 1) Ingresar categoría
 * 2) Listar categorías
 * 3) Consultar una categoría por código
 * 4) Modificar una categoría
 * 5) Eliminar una categoría
 * 0) Volver
 *
 * VALIDACIONES
 * ------------------------------------------------------------
 * - nombre no vacío
 * - descripción no vacía
 * - no permitir categorías repetidas por nombre
 *
 * REGLA DE NEGOCIO IMPORTANTE
 * ------------------------------------------------------------
 * No se puede eliminar una categoría si existe al menos un artículo
 * asociado a ella.
 *
 * SUGERENCIA DE MÉTODOS
 * ------------------------------------------------------------
 * - ingresarCategoria()
 * - listarCategorias()
 * - consultarCategoria()
 * - modificarCategoria()
 * - eliminarCategoria()
 * - categoriaTieneArticulosAsociados(...)
 */

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.utils.Secuencias;
import com.techlab.articulo.utils.Validaciones;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuCategorias extends Menu {

    private ArrayList<Categoria> categorias;
    private ArrayList<Articulo> articulos;

    public MenuCategorias(Scanner scanner, ArrayList<Categoria> categorias, ArrayList<Articulo> articulos) {
        super(scanner);
        this.categorias = categorias;
        this.articulos = articulos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ CATEGORÍAS ---");
        System.out.println("1 - Ingresar categoría");
        System.out.println("2 - Listar categorías");
        System.out.println("3 - Consultar categoría");
        System.out.println("4 - Modificar categoría");
        System.out.println("5 - Eliminar categoría");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        int eleccion;
        do {
            mostrarMenu();
            eleccion = leerEntero("Elija una opción: ");
            switch (eleccion) {
                case 1: ingresarCategoria(); break;
                case 2: listarCategorias(); break;
                case 3: consultarCategoria(); break;
                case 4: modificarCategoria(); break;
                case 5: eliminarCategoria(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (eleccion != 0);
    }

    private void ingresarCategoria() {
        String nombre = leerTexto("Nombre de la categoría: ");
        if (!Validaciones.validarTextoNoVacio(nombre)) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        if (existeNombre(nombre)) {
            System.out.println("Ya existe una categoría con ese nombre.");
            return;
        }

        String descripcion = leerTexto("Descripción: ");
        if (!Validaciones.validarTextoNoVacio(descripcion)) {
            System.out.println("La descripción no puede estar vacía.");
            return;
        }

        int codigo = Secuencias.generarCodigoCategoria();
        categorias.add(new Categoria(codigo, nombre, descripcion));
        System.out.println("Categoría agregada con código " + codigo + ".");
    }

    private void listarCategorias() {
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías cargadas.");
            return;
        }
        System.out.println("\n--- LISTADO DE CATEGORÍAS ---");
        for (Categoria c : categorias) {
            System.out.println(c);
        }
    }

    private void consultarCategoria() {
        int codigo = leerEntero("Ingrese código de la categoría: ");
        Categoria c = buscarPorCodigo(codigo);
        if (c == null) {
            System.out.println("Categoría no encontrada.");
        } else {
            System.out.println(c);
        }
    }

    private void modificarCategoria() {
        int codigo = leerEntero("Ingrese código de la categoría a modificar: ");
        Categoria c = buscarPorCodigo(codigo);
        if (c == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }
        System.out.println("Categoría actual: " + c);

        String nuevoNombre = leerTexto("Nuevo nombre (Enter para mantener): ");
        if (Validaciones.validarTextoNoVacio(nuevoNombre)) {
            if (existeNombre(nuevoNombre)) {
                System.out.println("Ya existe una categoría con ese nombre. No se modificó.");
                return;
            }
            c.setNombre(nuevoNombre);
        }

        String nuevaDescripcion = leerTexto("Nueva descripción (Enter para mantener): ");
        if (Validaciones.validarTextoNoVacio(nuevaDescripcion)) {
            c.setDescripcion(nuevaDescripcion);
        }

        System.out.println("Categoría modificada.");
    }

    private void eliminarCategoria() {
        int codigo = leerEntero("Ingrese código de la categoría a eliminar: ");
        Categoria c = buscarPorCodigo(codigo);
        if (c == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }
        if (categoriaTieneArticulosAsociados(codigo)) {
            System.out.println("No se puede eliminar: la categoría tiene artículos asociados.");
            return;
        }
        categorias.remove(c);
        System.out.println("Categoría eliminada.");
    }

    private boolean categoriaTieneArticulosAsociados(int codigoCategoria) {
        for (Articulo a : articulos) {
            if (a.getCategoria().getCodigo() == codigoCategoria) return true;
        }
        return false;
    }

    private Categoria buscarPorCodigo(int codigo) {
        for (Categoria c : categorias) {
            if (c.getCodigo() == codigo) return c;
        }
        return null;
    }

    private boolean existeNombre(String nombre) {
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre)) return true;
        }
        return false;
    }
}
