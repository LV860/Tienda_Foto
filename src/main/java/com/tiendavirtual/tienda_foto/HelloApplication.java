/*
Autores: Leonardo Velázquez Colin y Pablo Javier Escobar Gómez
*/

package com.tiendavirtual.tienda_foto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TiendaVirtualFotos!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}