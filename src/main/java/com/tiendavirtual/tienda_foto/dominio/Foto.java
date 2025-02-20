/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;

public class Foto {

    private BigDecimal id;
    private String nombre;
    private BigDecimal precio;

    public Foto() {
    }
    public Foto(BigDecimal id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
