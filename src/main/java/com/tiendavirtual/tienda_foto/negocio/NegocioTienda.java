/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/
package com.tiendavirtual.tienda_foto.negocio;

import com.tiendavirtual.tienda_foto.dominio.*;
import com.tiendavirtual.tienda_foto.integracion.RepositorioTienda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NegocioTienda {

    private static Usuario cliente;
    private static Compra compraActual;

    private List<Usuario> ListaUsuarios = new ArrayList<>();
    private List<Pago> listaPago = new ArrayList<>();
    private List<Periodo> ListaPeriodo = new ArrayList<>();
    private List<Foto> ListaFotos = new ArrayList<>();
    private List<Compra> ListaCompras= new ArrayList<>();
    private List<Compra_Foto> ListaCompra_Fotos= new ArrayList<>();
    private List<PrecioFoto> ListaPrecioFoto= new ArrayList<>();
    private List<CompraPago> ListaCompraPago= new ArrayList<>();

    public List<CompraPago> getListaCompraPago() {
        this.ListaCompraPago = RepositorioTienda.ConsultaSaldoPorCompra();
        return ListaCompraPago;
    }

    public List<Pago> getListaPago() {
        this.listaPago = RepositorioTienda.ListaPago();
        return listaPago;
    }

    public List<PrecioFoto> getListaPrecioFoto() {
        this.ListaPrecioFoto = RepositorioTienda.ListaPrecioFoto();
        return ListaPrecioFoto;
    }

    public List<Compra_Foto> getListaCompra_Fotos() {
        this.ListaCompra_Fotos = RepositorioTienda.ListaCompra_Fotos();
        return ListaCompra_Fotos;
    }

    public static Compra getCompraActual() {
        return compraActual;
    }

    public static void setCompraActual(Compra compra) {
        compraActual = compra;
    }

    public List<Compra> getListaCompras() {
        this.ListaCompras = RepositorioTienda.ListaCompras();
        return ListaCompras;
    }
    public List<Foto> getListaFotos() {
        this.ListaFotos = RepositorioTienda.ListaFotos();
        return ListaFotos;
    }

    public List<Periodo> getListaPeriodo() {
        this.ListaPeriodo = RepositorioTienda.ListaPeriodo();
        return ListaPeriodo;
    }

    public static Usuario getCliente() {
        System.out.println("Get: "+cliente.toString());
        return cliente;
    }

    public static void setCliente(Usuario usuario) {
        Usuario clienteActual = RepositorioTienda.ConsultarUsuario(usuario.getNombreUsuario(),usuario.getContraseña());
        cliente = clienteActual;
    }


    public boolean ComprobarUsurario (String nombre_usuario, String contrasena_usuario){
        List<Usuario> usuarios = RepositorioTienda.ListarUsuarios();
        boolean existe_usuario =false;
        for(int i = 0; i < usuarios.size(); i ++){
            if (usuarios.get(i).getNombreUsuario().equalsIgnoreCase(nombre_usuario) && usuarios.get(i).getContraseña().equalsIgnoreCase(contrasena_usuario)){
                existe_usuario = true;
                return existe_usuario;
            }
        }
        return existe_usuario;
    }

    public Compra crearCompra (Usuario cliente){

        Compra compra = new Compra();
        //System.out.println(this..toString());
        compra.setUsuarioid(cliente.getId());

        //Periodo
        BigDecimal indicePeriodo;

        LocalDateTime  fechaActual = LocalDateTime.now();
        String fechaString = fechaActual.toString();


        boolean periodo_existe = false;

        List<Periodo> periodos = this.getListaPeriodo();
        for(int i =0; i<periodos.size();i++){
            if(periodos.get(i).getFecha().equals(fechaString)){
                System.out.println("El periodo ya existe");
                periodo_existe = true;
                compra.setPeriodoid(periodos.get(i).getId());
                break;
            }
        }
        if(periodo_existe == false){
            System.out.println("El periodo no existe, pero se crea");
            Periodo periodo = new Periodo();
            periodo.setFecha(fechaString);
            indicePeriodo = BigDecimal.valueOf(RepositorioTienda.InsertarPeriodo(periodo));
            compra.setPeriodoid(indicePeriodo);
        }
        boolean compra_insertada;
        compra_insertada = false;
        int IsnumeroUnico = 0;
        Random random =new Random();
        while (compra_insertada == false){
            int randomNumber = random.nextInt(100) + 1;
            BigDecimal numeroUnico = new BigDecimal(randomNumber);
            IsnumeroUnico =0;
            for(int i =0;i<this.getListaCompras().size();i++){
                if(this.getListaCompras().get(i).getNumero().equals(numeroUnico)){
                    IsnumeroUnico++;
                }
            }
            if(IsnumeroUnico == 0){
                compra_insertada = true;
                compra.setNumero(numeroUnico);
                RepositorioTienda.InsertarCompra(compra);
                break;
            }
        }
        RepositorioTienda.ConsultarCompra(compra.getNumero()).toString();
        return RepositorioTienda.ConsultarCompra(compra.getNumero());
    }

    public void agregarFotoCompra (String nombreFoto, String cantidadFoto, Compra compra){
        Foto foto = RepositorioTienda.BuscarFoto(nombreFoto);
        if (foto != null){
            boolean compra_foto_existe = false;
            Compra_Foto compraFoto = new Compra_Foto();
            compraFoto.setCompraid(compra.getId());
            compraFoto.setFotoid(foto.getId());
            BigDecimal cantidad_fotos = new BigDecimal(cantidadFoto);
            compraFoto.setCantidad_fotos(cantidad_fotos);
            for(int i=0;i<this.getListaCompra_Fotos().size();i++){
                if(this.getListaCompra_Fotos().get(i).getCompraid().equals(compraFoto.getCompraid()) && this.getListaCompra_Fotos().get(i).getFotoid().equals(compraFoto.getFotoid())){
                    compra_foto_existe = true;
                }
            }
            if(compra_foto_existe ==false){
                RepositorioTienda.InsertarCompra_Foto(compraFoto);
                System.out.println("Se agrego la foto a la compra");
            }else{
                System.out.println("Ya existe un compraID con esa fotoID, si desea modificar presione Revisar Compra");
            }

        }else{
            System.out.println("No existe la foto");
        }
    }

    public void ModificarFotoCompra (String cant_fotos, String id){
        BigDecimal cantidad_fotos = new BigDecimal(cant_fotos);
        BigDecimal compraFotoID = new BigDecimal(id);
        RepositorioTienda.ModificarCompra_Foto(cantidad_fotos,compraFotoID);
        System.out.println("Se modifico con exito la cantidad de fotos");

    }

    public void EliminarFotoCompra (String Fotoid){
        BigDecimal fotoID = new BigDecimal(Fotoid);
        RepositorioTienda.EliminarCompra_Foto(fotoID);
        System.out.println("Se elimino con exito la foto");

    }

    public int Totalizar_Compra (Compra compraActual){
        /*
        int total =0;
        for (int i=0; i<this.getListaCompra_Fotos().size();i++){
            if(this.getListaCompra_Fotos().get(i).getCompraid().equals(compraActual.getId())){
                for (int j=0; j<this.getListaPrecioFoto().size();j++){
                    if(this.getListaCompra_Fotos().get(i).getCompraid().equals(this.getListaPrecioFoto().get(j).getCompraID())){
                        //compra_fotos.add(this.negocio.getListaPrecioFoto().get(i).toString()+this.negocio.getListaCompra_Fotos().get(i).toString());

                        total += this.getListaPrecioFoto().get(j).getPrecioFotos().intValue();
                        break;

                    }
                }
                break;
            }
        }
        return total;

         */
        int total = 0;
        for (Compra_Foto compraFoto : this.getListaCompra_Fotos()) {
            if (compraFoto.getCompraid().equals(compraActual.getId())) {
                for (PrecioFoto precioFoto : this.getListaPrecioFoto()) {
                    if (compraFoto.getFotoid().equals(precioFoto.getFotoID())) {
                        total += precioFoto.getPrecioFotos().intValue();
                        break;
                    }
                }
            }
        }
        return total;
    }

    public void CrearPago (String cant1000,String cant500,String cant200,String cant100,String cant50, Compra compra){

        BigDecimal id1000 = new BigDecimal("3");
        BigDecimal id500 = new BigDecimal("4");
        BigDecimal id200 = new BigDecimal("5");
        BigDecimal id100 = new BigDecimal("6");
        BigDecimal id50 = new BigDecimal("7");

        /*
        BigDecimal id1000 = RepositorioTienda.ConsultarIDMoneda(new BigDecimal("1000"));
        BigDecimal id500 = RepositorioTienda.ConsultarIDMoneda(new BigDecimal("500"));
        BigDecimal id200 = RepositorioTienda.ConsultarIDMoneda(new BigDecimal("200"));
        BigDecimal id100 = RepositorioTienda.ConsultarIDMoneda(new BigDecimal("100"));
        BigDecimal id50 = RepositorioTienda.ConsultarIDMoneda(new BigDecimal("50"));
        */

        if(Integer.parseInt(cant1000) > 0){
            Pago pago1000 = new Pago();
            pago1000.setCantidad(new BigDecimal(cant1000));
            pago1000.setCompraid(compra.getId());
            pago1000.setMonedaid(id1000);
            RepositorioTienda.InsertarPago(pago1000);
        }

        if(Integer.parseInt(cant500) > 0) {
            Pago pago500 = new Pago();
            pago500.setCantidad(new BigDecimal(cant500));
            pago500.setCompraid(compra.getId());
            pago500.setMonedaid(id500);
            RepositorioTienda.InsertarPago(pago500);
        }
        if(Integer.parseInt(cant200) > 0) {
            Pago pago200 = new Pago();
            pago200.setCantidad(new BigDecimal(cant200));
            pago200.setCompraid(compra.getId());
            pago200.setMonedaid(id200);
            RepositorioTienda.InsertarPago(pago200);
        }
        if(Integer.parseInt(cant100) > 0) {
            Pago pago100 = new Pago();
            pago100.setCantidad(new BigDecimal(cant100));
            pago100.setCompraid(compra.getId());
            pago100.setMonedaid(id100);
            RepositorioTienda.InsertarPago(pago100);
        }
        if(Integer.parseInt(cant50) > 0) {
            Pago pago50 = new Pago();
            pago50.setCantidad(new BigDecimal(cant50));
            pago50.setCompraid(compra.getId());
            pago50.setMonedaid(id50);
            RepositorioTienda.InsertarPago(pago50);
        }

    }

    public int ObtenerSaldo (Compra compraActual){
        int saldo =0;
        for(int i=0; i<this.getListaCompraPago().size();i++){
            if(compraActual.getId().equals(this.getListaCompraPago().get(i).getCompraID())){
                saldo = this.getListaCompraPago().get(i).getSaldoTotal().intValue();
                return saldo ;
            }
        }
        return saldo;
    }
}
