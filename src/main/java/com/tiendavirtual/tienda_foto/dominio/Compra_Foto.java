/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;

public class Compra_Foto {
    private BigDecimal id;
    private BigDecimal cantidad_fotos;
    private BigDecimal compraid;
    private BigDecimal fotoid;

    public Compra_Foto() {
    }

    public Compra_Foto(BigDecimal id, BigDecimal cantidad_fotos, BigDecimal compraid, BigDecimal fotoid) {
        this.id = id;
        this.cantidad_fotos = cantidad_fotos;
        this.compraid = compraid;
        this.fotoid = fotoid;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getCantidad_fotos() {
        return cantidad_fotos;
    }

    public void setCantidad_fotos(BigDecimal cantidad_fotos) {
        this.cantidad_fotos = cantidad_fotos;
    }

    public BigDecimal getCompraid() {
        return compraid;
    }

    public void setCompraid(BigDecimal compraid) {
        this.compraid = compraid;
    }

    public BigDecimal getFotoid() {
        return fotoid;
    }

    public void setFotoid(BigDecimal fotoid) {
        this.fotoid = fotoid;
    }

    @Override
    public String toString() {
        return "Compra_Foto{" +
                "id=" + id +
                ", cantidad_fotos=" + cantidad_fotos +
                ", compraid=" + compraid +
                ", fotoid=" + fotoid +
                '}';
    }
}
