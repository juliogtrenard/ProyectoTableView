package es.juliogtrenard.proyectotableview;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador para la interfaz de usuario de la aplicación JavaFX.
 * Esta clase maneja las interacciones del usuario y actualiza la vista.
 */
public class HelloController {
    
    /**
     * Etiqueta de texto que muestra un mensaje de bienvenida.
     * Esta etiqueta se inicializa automáticamente por JavaFX mediante la anotación @FXML.
     */
    @FXML
    private Label welcomeText;

    /**
     * Maneja el evento de clic en el botón "Hello".
     * Este método se llama automáticamente cuando el usuario hace clic en el botón correspondiente en la interfaz.
     * Actualiza el texto de la etiqueta de bienvenida con un mensaje predefinido.
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}