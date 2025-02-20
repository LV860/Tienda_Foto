/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;

public class Moneda {

    private BigDecimal id;
    private BigDecimal valor;

    public Moneda() {
    }

    public Moneda(BigDecimal id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "id=" + id +
                ", valor=" + valor +
                '}';
    }
}
