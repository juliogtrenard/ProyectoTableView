package es.juliogtrenard.proyectotableview;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utilidad para crear y manejar elementos de tabla relacionados con la clase Person.
 * Esta clase proporciona métodos estáticos para generar listas de personas y columnas de tabla.
 */
public class PersonTableUtil {
    
    /**
     * Genera y devuelve una lista observable de personas.
     * 
     * @return ObservableList<Person> Una lista observable que contiene cinco objetos Person predefinidos.
     */
    public static ObservableList<Person> getPersonList() {
        Person p1 = new Person("Ashwin", "Sharan", LocalDate.of(2012, 10, 11));
        Person p2 = new Person("Advik", "Sharan", LocalDate.of(2012, 10, 11));
        Person p3 = new Person("Layne", "Estes", LocalDate.of(2011, 12, 16));
        Person p4 = new Person("Mason", "Boyd", LocalDate.of(2003, 4, 20));
        Person p5 = new Person("Babalu", "Sharan", LocalDate.of(1980, 1, 10));
        return FXCollections.<Person>observableArrayList(p1, p2, p3, p4, p5);
    }

    /**
     * Crea y configura una columna de tabla para el ID de la persona.
     * 
     * @return TableColumn<Person, Integer> Una columna de tabla configurada para mostrar el ID de la persona.
     */
    public static TableColumn<Person, Integer> getIdColumn() {
        TableColumn<Person, Integer> personIdCol = new TableColumn<>("Id");
        personIdCol.setCellValueFactory(new PropertyValueFactory<>("personId"));
        return personIdCol;
    }

    /**
     * Crea y configura una columna de tabla para el nombre de la persona.
     * 
     * @return TableColumn<Person, String> Una columna de tabla configurada para mostrar el nombre de la persona.
     */
    public static TableColumn<Person, String> getFirstNameColumn() {
        TableColumn<Person, String> fNameCol = new TableColumn<>("First Name");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        return fNameCol;
    }

    /**
     * Crea y configura una columna de tabla para el apellido de la persona.
     * 
     * @return TableColumn<Person, String> Una columna de tabla configurada para mostrar el apellido de la persona.
     */
    public static TableColumn<Person, String> getLastNameColumn() {
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return lastNameCol;
    }

    /**
     * Crea y configura una columna de tabla para la fecha de nacimiento de la persona.
     * 
     * @return TableColumn<Person, LocalDate> Una columna de tabla configurada para mostrar la fecha de nacimiento de la persona.
     */
    public static TableColumn<Person, LocalDate> getBirthDateColumn() {
        TableColumn<Person, LocalDate> bDateCol = new TableColumn<>("Birth Date");
        bDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        return bDateCol;
    }
}
