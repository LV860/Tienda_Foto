/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/

package com.tiendavirtual.tienda_foto;

import com.tiendavirtual.tienda_foto.dominio.Compra;
import com.tiendavirtual.tienda_foto.dominio.Foto;
import com.tiendavirtual.tienda_foto.dominio.Usuario;
import com.tiendavirtual.tienda_foto.negocio.NegocioTienda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    ObservableList<String> listFotos = FXCollections.observableArrayList();

    ObservableList<String> listCompras = FXCollections.observableArrayList();
    ObservableList<String> listFechas = FXCollections.observableArrayList();

    ObservableList<String> listInfoFecha = FXCollections.observableArrayList();
    ObservableList<String> listInfoCompra = FXCollections.observableArrayList();
    ObservableList<String> listFotosCompradas = FXCollections.observableArrayList();
    ObservableList<String> listFotosCompradasTotal = FXCollections.observableArrayList();
    private NegocioTienda negocio = new NegocioTienda();
    private ListView<Foto> lstFotosInterna = new ListView<>();


    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private TextField txtContrasenaUsuario;
    @FXML
    private Button btnLogin1;
    @FXML
    private Label txtExisteUsurious;
    @FXML
    private ListView<String> lstFotos;
    @FXML
    private ListView<String> lstFotosCompradas;
    @FXML
    private TextField txtNombreFotoAgregada;
    @FXML
    private TextField txtCantidadFotoAgregada;
    @FXML
    private TextField txtCompra_FotoID;
    @FXML
    private TextField txtCompra_FotoCantFotos;
    @FXML
    private TextField txtFotoEliminada;
    @FXML
    private TextField txtCantMonedas1000;
    @FXML
    private TextField txtCantMonedas200;
    @FXML
    private TextField txtCantMonedas50;
    @FXML
    private TextField txtCantMonedas100;
    @FXML
    private TextField txtCantMonedas500;
    @FXML
    private Label txtTotalCompra;
    @FXML
    private ListView lstTotal;
    @FXML
    private ListView lstRegistroCompras;
    @FXML
    private ListView lstRegistroFechas;
    @FXML
    private TextField txtNumeroCompra;
    @FXML
    private Label txtInfoPago;
    @FXML
    private Label txtInfoCambio;
    @FXML
    private Label txtTotalCompra1;
    @FXML
    private TextField txtNumeroIDFecha;

    private void refrescarListaInfoCompra(String numeroCompra)
    {
        //this.lstRegistroCompras.getItems().removeAll(listCompras);
        List<String> info = new ArrayList<>();
        for (int i=0; i<this.negocio.getListaCompras().size();i++){
            if(this.negocio.getListaCompras().get(i).getNumero().toString().equalsIgnoreCase(numeroCompra)){
                for (int x = 0; x < this.negocio.getListaCompra_Fotos().size(); x++) {
                    if (this.negocio.getListaCompras().get(i).getId().equals(this.negocio.getListaCompra_Fotos().get(x).getCompraid())) {
                        String mensaje = " Compra: "+ this.negocio.getListaCompras().get(x).getNumero().toString() +" Foto: "+ this.negocio.getListaCompra_Fotos().get(x).getFotoid().toString() + " Cantidad: "+ this.negocio.getListaCompra_Fotos().get(x).getCantidad_fotos().toString();
                        info.add(mensaje);
                    }

                }
            }

        }
        this.listInfoCompra.setAll(info);
        System.out.println(this.listInfoCompra);

        this.lstRegistroCompras.setItems(this.listInfoCompra);

    }


    private void refrescarListaInfoFechas(String idFecha)
    {
        List<String> info = new ArrayList<>();
        for (int i=0; i<this.negocio.getListaPeriodo().size();i++){
            if(this.negocio.getListaPeriodo().get(i).getId().toString().equalsIgnoreCase(idFecha)) {
                for (int j = 0; j < this.negocio.getListaCompras().size(); j++) {
                    if(this.negocio.getListaPeriodo().get(i).getId().equals(this.negocio.getListaCompras().get(j).getPeriodoid())) {
                        for (int x = 0; x < this.negocio.getListaCompra_Fotos().size(); x++) {
                            if (this.negocio.getListaCompras().get(j).getId().equals(this.negocio.getListaCompra_Fotos().get(x).getCompraid())) {
                                String mensaje = "Periodo: "+ this.negocio.getListaPeriodo().get(i).getFecha() + " Compra: "+ this.negocio.getListaCompras().get(j).getNumero().toString() +" Foto: "+ this.negocio.getListaCompra_Fotos().get(x).getFotoid().toString() + " Cantidad: "+ this.negocio.getListaCompra_Fotos().get(x).getCantidad_fotos().toString();
                                info.add(mensaje);
                            }

                        }
                    }
                }
            }
        }
        this.listInfoFecha.setAll(info);
        System.out.println(this.listInfoFecha);

        this.lstRegistroFechas.setItems(this.listInfoFecha);

    }
    private void refrescarListaFecha()
    {
        List<String> fechas = new ArrayList<>();
        for (int i=0; i<this.negocio.getListaPeriodo().size();i++){
            fechas.add(this.negocio.getListaPeriodo().get(i).toString());
        }
        this.listFechas.setAll(fechas);
        System.out.println(this.listFechas);

        this.lstRegistroFechas.setItems(this.listFechas);

    }
    private void refrescarListaFotos()
    {
        List<String> fotos = new ArrayList<>();
        for (int i=0; i<this.negocio.getListaFotos().size();i++){
            fotos.add(this.negocio.getListaFotos().get(i).toString());
        }
        this.listFotos.setAll(fotos);
        System.out.println(this.listFotos);

        this.lstFotos.setItems(this.listFotos);

    }

    private void refrescarListaCompras()
    {
        List<String> compras = new ArrayList<>();
        for (int i=0; i<this.negocio.getListaCompras().size();i++){
            compras.add(this.negocio.getListaCompras().get(i).getNumero().toString());
        }
        this.listCompras.setAll(compras);
        System.out.println(this.listCompras);

        this.lstRegistroCompras.setItems(this.listCompras);

    }

    private void refrescarListaFotosComprada()
    {
        List<String> compra_fotos = new ArrayList<>();


        for (int i=0; i<this.negocio.getListaCompra_Fotos().size();i++){
            if(this.negocio.getListaCompra_Fotos().get(i).getCompraid().equals(NegocioTienda.getCompraActual().getId())){
                for (int j=0; j<this.negocio.getListaPrecioFoto().size();j++){
                    if(this.negocio.getListaCompra_Fotos().get(i).getCompraid().equals(this.negocio.getListaPrecioFoto().get(j).getCompraID()) && this.negocio.getListaCompra_Fotos().get(i).getFotoid().equals(this.negocio.getListaPrecioFoto().get(j).getFotoID())){
                        String precio= "Precio: ";
                        compra_fotos.add(precio +this.negocio.getListaPrecioFoto().get(j).getPrecioFotos().toString()+" " + this.negocio.getListaCompra_Fotos().get(i).toString());
                    }
                }
            }
        }
        this.listFotosCompradas.setAll(compra_fotos);
        System.out.println(this.listFotosCompradas);

        this.lstFotosCompradas.setItems(this.listFotosCompradas);

    }
    private void refrescarListaFotosCompradaTotalizar()
    {
        List<String> compra_fotos = new ArrayList<>();


        for (int i=0; i<this.negocio.getListaCompra_Fotos().size();i++){
            if(this.negocio.getListaCompra_Fotos().get(i).getCompraid().equals(NegocioTienda.getCompraActual().getId())){
                for (int j=0; j<this.negocio.getListaPrecioFoto().size();j++){
                    if(this.negocio.getListaCompra_Fotos().get(i).getCompraid().equals(this.negocio.getListaPrecioFoto().get(j).getCompraID()) && this.negocio.getListaCompra_Fotos().get(i).getFotoid().equals(this.negocio.getListaPrecioFoto().get(j).getFotoID())){
                        String precio= "Precio: ";
                        compra_fotos.add(precio +this.negocio.getListaPrecioFoto().get(j).getPrecioFotos().toString()+" " + this.negocio.getListaCompra_Fotos().get(i).toString());
                    }
                }
            }
        }
        this.listFotosCompradasTotal.setAll(compra_fotos);
        System.out.println(this.listFotosCompradasTotal);

        this.lstTotal.setItems(this.listFotosCompradasTotal);



    }

    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void onButtonLoginClick(ActionEvent actionEvent) {


        boolean existeUsuario = this.negocio.ComprobarUsurario(this.txtNombreUsuario.getText(),this.txtContrasenaUsuario.getText());
        if(existeUsuario)
        {
            txtExisteUsurious.setText("Usuario encontrado" );
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(this.txtNombreUsuario.getText());
            usuario.setContraseña(this.txtContrasenaUsuario.getText());

            NegocioTienda.setCliente(usuario);

            cerrarVentana(actionEvent);
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Iniciar_compra.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            stage = new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();


        }
        else {
            txtExisteUsurious.setText("no existe");
        }




    }

    public void onbtnIniciarCompraClick(ActionEvent actionEvent) {

        cerrarVentana(actionEvent);
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Compra.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        NegocioTienda.setCompraActual(this.negocio.crearCompra(NegocioTienda.getCliente()));
    }

    public void OnbtnAgregarFotoClicked(ActionEvent actionEvent) {
        this.negocio.agregarFotoCompra(this.txtNombreFotoAgregada.getText(),this.txtCantidadFotoAgregada.getText(), NegocioTienda.getCompraActual());

    }


    public void onbtnRecargarClick(ActionEvent actionEvent) {
        this.refrescarListaFotos();
    }

    public void onbtnRevisarCompraClick(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ModificarCompra.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void onbtnRecargar2Click(ActionEvent actionEvent) {
        this.refrescarListaFotosComprada();
    }

    public void onbtnModificarClick(ActionEvent actionEvent) {
        this.negocio.ModificarFotoCompra(this.txtCompra_FotoCantFotos.getText(),this.txtCompra_FotoID.getText());
    }

    public void onbtnComprobarClick(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Totalizar_Compra.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

    }

    public void onbtnEliminarClick(ActionEvent actionEvent) {
        this.negocio.EliminarFotoCompra(this.txtFotoEliminada.getText());
    }

    public void onbtnListaCompradasTotalClick(ActionEvent actionEvent) {

        txtTotalCompra.setText(String.valueOf(this.negocio.Totalizar_Compra(NegocioTienda.getCompraActual())));
        this.refrescarListaFotosCompradaTotalizar();

    }

    public void onbtnPagarCompraClick(ActionEvent actionEvent) {
        this.negocio.CrearPago(this.txtCantMonedas1000.getText(), this.txtCantMonedas500.getText(),this.txtCantMonedas200.getText(),this.txtCantMonedas100.getText(),this.txtCantMonedas50.getText(),NegocioTienda.getCompraActual());
        cerrarVentana(actionEvent);
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("RegistroCompra.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void onbtnGuardarCompraClick(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LoginUsuario.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

    }

    public void onbtnConsultarClick(ActionEvent actionEvent) {
        this.refrescarListaInfoCompra(this.txtNumeroCompra.getText());

    }

    public void onbtnGenerarReporteClick(ActionEvent actionEvent) {
        this.txtTotalCompra1.setText(Integer.toString(this.negocio.Totalizar_Compra(NegocioTienda.getCompraActual())));
        this.txtInfoPago.setText(Integer.toString(this.negocio.ObtenerSaldo(NegocioTienda.getCompraActual())));
        this.txtInfoCambio.setText(Integer.toString(this.negocio.ObtenerSaldo(NegocioTienda.getCompraActual())-this.negocio.Totalizar_Compra(NegocioTienda.getCompraActual())));
        cerrarVentana(actionEvent);
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ReporteFecha.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void onbtnSalirClick(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LoginUsuario.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void onbtnSalirFechaClick(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LoginUsuario.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void onbtnConsultarFechaClick(ActionEvent actionEvent) {
        this.refrescarListaInfoFechas(this.txtNumeroIDFecha.getText());
    }

    public void onRecargarFechas(ActionEvent actionEvent) {
        this.refrescarListaFecha();
    }

    public void onRecargarNumeroCompraClick(ActionEvent actionEvent) {
        this.refrescarListaCompras();
    }


}