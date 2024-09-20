module es.juliogtrenard.proyectotableview {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.juliogtrenard.proyectotableview to javafx.fxml;
    exports es.juliogtrenard.proyectotableview;
}