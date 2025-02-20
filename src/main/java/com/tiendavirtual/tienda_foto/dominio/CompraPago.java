package com.tiendavirtual.tienda_foto.dominio;
/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
import java.math.BigDecimal;

public class CompraPago {
    private BigDecimal compraID;
    private BigDecimal saldoTotal;

    public CompraPago() {
    }

    public CompraPago(BigDecimal compraID, BigDecimal saldoTotal) {
        this.compraID = compraID;
        this.saldoTotal = saldoTotal;
    }

    public BigDecimal getCompraID() {
        return compraID;
    }

    public void setCompraID(BigDecimal compraID) {
        this.compraID = compraID;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    @Override
    public String toString() {
        return "CompraPago{" +
                "compraID=" + compraID +
                ", saldoTotal=" + saldoTotal +
                '}';
    }
}
