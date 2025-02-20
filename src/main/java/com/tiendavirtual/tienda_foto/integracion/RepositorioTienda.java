/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.integracion;

import com.tiendavirtual.tienda_foto.dominio.*;
import com.tiendavirtual.tienda_foto.negocio.Constantes;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTienda {
    public static List<Usuario> ListarUsuarios() {
        String SQL = "select ID,NOMBREUSUARIO, CONTRASEÑA from Usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getBigDecimal("ID"));
                usu.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setContraseña(rs.getString("CONTRASEÑA"));
                usuarios.add(usu);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return usuarios;

    }

    public static Usuario ConsultarUsuario(String nombreUsuario, String contrasenaUsuario) {
        String SQL = "select ID,NOMBREUSUARIO, CONTRASEÑA from Usuario";

        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getBigDecimal("ID"));
                usu.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setContraseña(rs.getString("CONTRASEÑA"));
                if (nombreUsuario.equalsIgnoreCase(usu.getNombreUsuario())&& contrasenaUsuario.equalsIgnoreCase(usu.getContraseña())){
                    return usu;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;

    }

    public static Compra ConsultarCompra(BigDecimal numeroUnico) {
        String SQL = "select ID,NUMERO,PERIODOID,USUARIOID from COMPRA";

        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getBigDecimal("ID"));
                compra.setNumero(rs.getBigDecimal("NUMERO"));
                compra.setPeriodoid(rs.getBigDecimal("PERIODOID"));
                compra.setUsuarioid(rs.getBigDecimal("USUARIOID"));
                if (numeroUnico.equals(compra.getNumero())){
                    return compra;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;

    }

    public static List<Foto> ListaFotos() {
        String SQL = "select ID,PRECIO, NOMBRE from FOTO";
        List<Foto> fotos = new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Foto foto = new Foto();
                foto.setId(rs.getBigDecimal("ID"));
                foto.setPrecio(rs.getBigDecimal("PRECIO"));
                foto.setNombre(rs.getString("NOMBRE"));
                fotos.add(foto);

            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return fotos;

    }
    public static List<Periodo> ListaPeriodo() {
        String SQL = "select ID,FECHA from Periodo";
        List<Periodo> periodos = new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Periodo periodo = new Periodo();
                periodo.setId(rs.getBigDecimal("ID"));
                periodo.setFecha(rs.getString("FECHA"));
                periodos.add(periodo);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return periodos;

    }
    public static Foto BuscarFoto(String nombreFoto) {
        String SQL = "select ID,PRECIO, NOMBRE from FOTO";

        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Foto foto = new Foto();
                foto.setId(rs.getBigDecimal("ID"));
                foto.setPrecio(rs.getBigDecimal("PRECIO"));
                foto.setNombre(rs.getString("NOMBRE"));
                if (foto.getNombre().equalsIgnoreCase(nombreFoto)){
                    return foto;
                }

            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;

    }
    public static Long InsertarPeriodo(Periodo periodo) {
        int afectadas = 0;
        Long primaryKey = null;
        String SQL = "insert into PERIODO (FECHA) values(?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(
                        SQL,
                        new String[] { "id" }
                );) {

            ps.setString(1, periodo.getFecha());
            afectadas = ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                primaryKey = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return primaryKey;
    }

    public static List<Compra> ListaCompras() {
        String SQL = "select ID,NUMERO,PERIODOID,USUARIOID from COMPRA";
        List<Compra> compras = new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getBigDecimal("ID"));
                compra.setNumero(rs.getBigDecimal("NUMERO"));
                compra.setPeriodoid(rs.getBigDecimal("PERIODOID"));
                compra.setUsuarioid(rs.getBigDecimal("USUARIOID"));
                compras.add(compra);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return compras;

    }
    public static void InsertarCompra(Compra compra) {
        int afectadas = 0;
        Long primaryKey = null;
        String SQL = "insert into COMPRA (NUMERO, PERIODOID, USUARIOID) values(?,?,?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(
                        SQL,
                        new String[] { "id" }
                );) {

            ps.setBigDecimal(1, compra.getNumero());
            ps.setBigDecimal(2, compra.getPeriodoid());
            ps.setBigDecimal(3, compra.getUsuarioid());
            afectadas = ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                primaryKey = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
    }
    public static List<Compra_Foto> ListaCompra_Fotos() {
        String SQL = "select ID,COMPRAID,FOTOID, CANTIDAD_FOTOS from COMPRA_FOTO";
        List<Compra_Foto> compra_fotos = new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Compra_Foto compra_foto = new Compra_Foto();
                compra_foto.setId(rs.getBigDecimal("ID"));
                compra_foto.setCompraid(rs.getBigDecimal("COMPRAID"));
                compra_foto.setFotoid(rs.getBigDecimal("FOTOID"));
                compra_foto.setCantidad_fotos(rs.getBigDecimal("CANTIDAD_FOTOS"));
                compra_fotos.add(compra_foto);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return compra_fotos;

    }

    public static void InsertarCompra_Foto(Compra_Foto compra_foto) {
        int afectadas = 0;
        Long primaryKey = null;
        String SQL = "insert into COMPRA_FOTO (COMPRAID, FOTOID, CANTIDAD_FOTOS) values(?,?,?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(
                        SQL,
                        new String[] { "id" }
                );) {

            ps.setBigDecimal(1, compra_foto.getCompraid());
            ps.setBigDecimal(2, compra_foto.getFotoid());
            ps.setBigDecimal(3, compra_foto.getCantidad_fotos());
            afectadas = ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                primaryKey = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public static void ModificarCompra_Foto(BigDecimal cant_fotos, BigDecimal id) {
        int afectadas = 0;
        Long primaryKey = null;
        String SQL = "UPDATE COMPRA_FOTO SET CANTIDAD_FOTOS = ? WHERE  ID = ?";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(
                        SQL
                );) {

            ps.setBigDecimal(1, cant_fotos);
            ps.setBigDecimal(2, id);

            afectadas = ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                primaryKey = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public static void EliminarCompra_Foto(BigDecimal FotoId) {
        int afectadas = 0;
        Long primaryKey = null;
        String SQL = "DELETE FROM COMPRA_FOTO WHERE  FOTOID = ?";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(
                        SQL
                );) {

            ps.setBigDecimal(1, FotoId);

            afectadas = ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                primaryKey = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public static List<PrecioFoto> ListaPrecioFoto() {
        String SQL = "SELECT c.ID AS \"ID_Compra\", f.ID AS \"FotoID\", SUM(cf.cantidad_fotos * f.precio) AS \"PRECIO_FOTOS\" FROM Compra c JOIN Compra_Foto cf ON cf.compraid = c.ID JOIN Foto f ON f.ID = cf.fotoid GROUP BY c.ID,f.ID";
        List<PrecioFoto> precios = new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                PrecioFoto precioFoto = new PrecioFoto();
                precioFoto.setCompraID(rs.getBigDecimal("ID_Compra"));
                precioFoto.setFotoID(rs.getBigDecimal("FotoID"));
                precioFoto.setPrecioFotos(rs.getBigDecimal("PRECIO_FOTOS"));
                precios.add(precioFoto);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return precios;

    }
    public static BigDecimal ConsultarIDMoneda(BigDecimal valor) {
        String SQL = "select ID from Moneda where VALOR = ?";
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            ps.setBigDecimal(1, valor);
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                return rs.getBigDecimal("ID");
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    public static void InsertarPago(Pago pago) {
        int afectadas = 0;
        Long primaryKey = null;
        String SQL = "insert into Pago (COMPRAID, MONEDAID, CANTIDAD) values(?,?,?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(
                        SQL,
                        new String[] { "id" }
                );) {

            ps.setBigDecimal(1,  pago.getCompraid());
            ps.setBigDecimal(2, pago.getMonedaid());
            ps.setBigDecimal(3, pago.getCantidad());
            afectadas = ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                primaryKey = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public static List<Pago> ListaPago() {
        String SQL = "select ID,COMPRAID, MONEDAID, CANTIDAD from PAGO";
        List<Pago> pagos = new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setId(rs.getBigDecimal("ID"));
                pago.setMonedaid(rs.getBigDecimal("MONEDAID"));
                pago.setCompraid(rs.getBigDecimal("COMPRAID"));
                pagos.add(pago);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return pagos;

    }

    public static List<CompraPago> ConsultaSaldoPorCompra() {
        String SQL = "SELECT c.ID AS \"ID_Compra\", SUM(p.cantidad * m.valor) AS \"Saldo_Total\" From Compra c JOIN Pago p ON p.compraid = c.ID JOIN Moneda m ON m.ID = p.monedaid GROUP BY c.ID";
        List<CompraPago> saldos= new ArrayList<>();
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            System.out.println("Conexion exitosa");
            while (rs.next()) {
                CompraPago compraPago = new CompraPago();
                compraPago.setCompraID(rs.getBigDecimal("ID_Compra"));
                compraPago.setSaldoTotal(rs.getBigDecimal("Saldo_Total"));
                saldos.add(compraPago);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return saldos;

    }


}
