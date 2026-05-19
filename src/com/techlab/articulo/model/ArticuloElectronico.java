package com.techlab.articulo.model;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Articulo.
 *
 * Representa un artículo electrónico.
 *
 * ATRIBUTO ESPECÍFICO
 * ------------------------------------------------------------
 * - garantiaMeses : int
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
 * - si la garantía supera 12 meses, aplicar un recargo
 * - si no, dejar el precio igual
 *
 * Lo importante no es la regla comercial exacta,
 * sino mostrar que cada subtipo implementa el cálculo de manera distinta.
 */
public class ArticuloElectronico extends Articulo {

    private int garantiaMeses;

    public ArticuloElectronico(int codigo, String nombre, double precio, int stock, int garantiaMeses) {
        super(codigo, nombre, precio, stock);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoArticulo() {
        return "Electrónico";
    }

    @Override
    public double calcularPrecioFinal() {
        if (garantiaMeses > 12) return precio * 1.10;
        return precio;
    }

    @Override
    public String toString() {
        return "[" + getTipoArticulo() + "] Código: " + codigo
                + " | " + nombre
                + " | Precio: $" + precio
                + " | Stock: " + stock
                + " | Garantía: " + garantiaMeses + " meses";
    }
}
