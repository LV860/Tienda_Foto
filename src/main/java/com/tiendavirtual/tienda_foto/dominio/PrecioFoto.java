/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;

public class PrecioFoto {

    private BigDecimal compraID;

    private BigDecimal fotoID;
    private BigDecimal precioFotos;

    public PrecioFoto() {
    }

    public PrecioFoto(BigDecimal compraID, BigDecimal fotoID, BigDecimal precioFotos) {
        this.compraID = compraID;
        this.fotoID = fotoID;
        this.precioFotos = precioFotos;
    }

    public BigDecimal getCompraID() {
        return compraID;
    }

    public void setCompraID(BigDecimal compraID) {
        this.compraID = compraID;
    }

    public BigDecimal getPrecioFotos() {
        return precioFotos;
    }

    public void setPrecioFotos(BigDecimal precioFotos) {
        this.precioFotos = precioFotos;
    }

    public BigDecimal getFotoID() {
        return fotoID;
    }

    public void setFotoID(BigDecimal fotoID) {
        this.fotoID = fotoID;
    }

    @Override
    public String toString() {
        return "PrecioFoto{" +
                "compraID=" + compraID +
                ", fotoID=" + fotoID +
                ", precioFotos=" + precioFotos +
                '}';
    }
}
