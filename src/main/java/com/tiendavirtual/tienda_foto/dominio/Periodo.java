/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;
import java.util.Date;

public class Periodo {
    private BigDecimal id;
    private String fecha;

    public Periodo() {
    }

    public Periodo(BigDecimal id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
