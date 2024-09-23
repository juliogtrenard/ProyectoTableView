package es.juliogtrenard.proyectotableview;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import static javafx.scene.control.TableView.TableViewSelectionModel;

/**
 * Aplicación JavaFX que muestra una tabla de personas con funcionalidades para añadir, eliminar y restaurar filas.
 * Esta clase extiende de Application y proporciona la interfaz de usuario principal.
 */
public class ProyectoTableView extends Application {
    /** Campo de texto para el nombre de la persona */
    private TextField fNameField;
    /** Campo de texto para el apellido de la persona */
    private TextField lNameField;
    /** Selector de fecha para la fecha de nacimiento de la persona */
    private DatePicker dobField;
    /** Tabla para mostrar la lista de personas */
    private TableView<Person> table;

    /**
     * Punto de entrada principal de la aplicación.
     * @param args Argumentos de línea de comando (no utilizados)
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Método llamado al iniciar la aplicación JavaFX.
     * Configura y muestra la interfaz de usuario principal.
     * @param stage El escenario principal de la aplicación
     */
    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage) {
        fNameField = new TextField();
        lNameField = new TextField();
        dobField = new DatePicker();
        table = new TableView<>(PersonTableUtil.getPersonList());
        // Turn on multi-row selection for the TableView
        TableViewSelectionModel<Person> tsm = table.getSelectionModel();
        tsm.setSelectionMode(SelectionMode.MULTIPLE);
        // Add columns to the TableView
        table.getColumns().addAll(PersonTableUtil.getIdColumn(), PersonTableUtil.getFirstNameColumn(), PersonTableUtil.getLastNameColumn(), PersonTableUtil.getBirthDateColumn());
        GridPane newDataPane  = this.getNewPersonDataPane();
        Button restoreBtn = new Button("Restore Rows");
        restoreBtn.setOnAction(e -> restoreRows());

        KeyCombination kr = new KeyCodeCombination(KeyCode.R, KeyCombination.ALT_DOWN);
        Mnemonic mnemonic1 = new Mnemonic(restoreBtn, kr);

        Button deleteBtn = new Button("Delete Selected Rows");
        deleteBtn.setOnAction(e -> deleteSelectedRows());

        KeyCombination kd = new KeyCodeCombination(KeyCode.D, KeyCombination.ALT_DOWN);
        Mnemonic mnemonic2 = new Mnemonic(deleteBtn, kd);

        VBox root = new VBox(newDataPane, new HBox(restoreBtn, deleteBtn), table);
        root.setSpacing(5);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.addMnemonic(mnemonic1);
        scene.addMnemonic(mnemonic2);
        stage.setTitle("Adding/Deleting Rows in a TableViews");
        stage.show();
    }

    /**
     * Crea y devuelve un panel para ingresar datos de una nueva persona.
     * @return GridPane con campos de entrada y botón para añadir una nueva persona
     */
    public GridPane getNewPersonDataPane() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        pane.addRow(0, new Label("First Name:"), fNameField);
        pane.addRow(1, new Label("Last Name:"), lNameField);
        pane.addRow(2, new Label("Birth Date:"), dobField);
        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addPerson());
        // Add the "Add" button
        pane.add(addBtn, 2, 0);
        return pane;
    }

    /**
     * Elimina las filas seleccionadas de la tabla.
     * Si no hay filas seleccionadas, muestra un mensaje en la consola.
     */
    public void deleteSelectedRows() {
        TableViewSelectionModel<Person> tsm = table.getSelectionModel();
        if (tsm.isEmpty()) {
            System.out.println("Please select a row to delete.");
            return;
        }
        // Get all selected row indices in an array
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        // Sort the array
        Arrays.sort(selectedIndices);
        // Delete rows (last to first)
        for(int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i].intValue());
            table.getItems().remove(selectedIndices[i].intValue());
        }
    }

    /**
     * Restaura todas las filas de la tabla a su estado inicial.
     * Limpia la tabla actual y la rellena con la lista de personas predefinida.
     */
    public void restoreRows() {
        table.getItems().clear();
        table.getItems().addAll(PersonTableUtil.getPersonList());
    }

    /**
     * Crea y devuelve un nuevo objeto Person con los datos ingresados en los campos.
     * @return Nuevo objeto Person con los datos de los campos de entrada
     */
    public Person getPerson() {
        return new Person(fNameField.getText(), lNameField.getText(), dobField.getValue());
    }

    /**
     * Añade una nueva persona a la tabla.
     * Crea un nuevo objeto Person con los datos ingresados y lo añade a la tabla.
     * Luego limpia los campos de entrada.
     */
    public void addPerson() {
        Person p = getPerson();
        table.getItems().add(p);
        clearFields();
    }

    /**
     * Limpia todos los campos de entrada.
     * Establece los campos de texto y el selector de fecha a null.
     */
    public void clearFields() {
        fNameField.setText(null);
        lNameField.setText(null);
        dobField.setValue(null);
    }
}