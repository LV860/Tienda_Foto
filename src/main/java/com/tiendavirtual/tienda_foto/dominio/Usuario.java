
/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.dominio;

import java.math.BigDecimal;
public class Usuario {
    private BigDecimal id;
    private String nombreUsuario;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(BigDecimal id, String nombreUsuario, String contraseña) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
