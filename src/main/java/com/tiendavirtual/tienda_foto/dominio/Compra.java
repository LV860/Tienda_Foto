/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;

public class Compra {
    private BigDecimal id;
    private BigDecimal numero;
    private BigDecimal periodoid;
    private BigDecimal usuarioid;

    public Compra() {
    }

    public Compra(BigDecimal id, BigDecimal numero, BigDecimal periodoid, BigDecimal usuarioid) {
        this.id = id;
        this.numero = numero;
        this.periodoid = periodoid;
        this.usuarioid = usuarioid;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getNumero() {
        return numero;
    }

    public void setNumero(BigDecimal numero) {
        this.numero = numero;
    }

    public BigDecimal getPeriodoid() {
        return periodoid;
    }

    public void setPeriodoid(BigDecimal periodoid) {
        this.periodoid = periodoid;
    }

    public BigDecimal getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(BigDecimal usuarioid) {
        this.usuarioid = usuarioid;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", numero=" + numero +
                ", periodoid=" + periodoid +
                ", usuarioid=" + usuarioid +
                '}';
    }
}

