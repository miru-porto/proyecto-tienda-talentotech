package com.techlab.articulo.model;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Articulo.
 *
 * Representa un artículo alimenticio.
 *
 * ATRIBUTO ESPECÍFICO
 * ------------------------------------------------------------
 * - diasParaVencimiento : int
 *
 * ESTA CLASE DEBE
 * ------------------------------------------------------------
 * - tener constructor
 * - tener getters y setters
 * - sobrescribir getTipoArticulo()
 * - sobrescribir calcularPrecioFinal()
 *
 * IDEA DIDÁCTICA PARA calcularPrecioFinal()
 * ------------------------------------------------------------
 * Podés definir una regla propia, por ejemplo:
 * - si faltan pocos días para vencer, aplicar descuento
 * - si no, mantener el precio
 */
public class ArticuloAlimenticio extends Articulo {

    private int diasParaVencimiento;

    public ArticuloAlimenticio(int codigo, String nombre, double precio, Categoria categoria, int diasParaVencimiento) {
        super(codigo, nombre, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    public double calcularPrecioFinal() {
        if (diasParaVencimiento <= 3) return precio * 0.70;
        return precio;
    }

    @Override
    public String toString() {
        return "[" + getTipoArticulo() + "] Código: " + codigo
                + " | " + nombre
                + " | Precio final: $" + calcularPrecioFinal()
                + " | Vence en: " + diasParaVencimiento + " días"
                + " | Categoría: " + categoria;
    }
}
