module com.tiendavirtual.tienda_foto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.tiendavirtual.tienda_foto to javafx.fxml;
    exports com.tiendavirtual.tienda_foto;
}