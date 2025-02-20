/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;

public class Pago {

    private BigDecimal id;
    private BigDecimal compraid;
    private BigDecimal monedaid;
    private BigDecimal cantidad;

    public Pago() {
    }

    public Pago(BigDecimal id, BigDecimal compraid, BigDecimal monedaid, BigDecimal cantidad) {
        this.id = id;
        this.compraid = compraid;
        this.monedaid = monedaid;
        this.cantidad = cantidad;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getCompraid() {
        return compraid;
    }

    public void setCompraid(BigDecimal compraid) {
        this.compraid = compraid;
    }

    public BigDecimal getMonedaid() {
        return monedaid;
    }

    public void setMonedaid(BigDecimal monedaid) {
        this.monedaid = monedaid;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", compraid=" + compraid +
                ", monedaid=" + monedaid +
                ", cantidad=" + cantidad +
                '}';
    }
}
