package es.juliogtrenard.proyectotableview;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa a una persona con atributos básicos y métodos relacionados.
 * Esta clase incluye validaciones y categorización por edad.
 */
public class Person {
    /** Secuencia atómica para generar IDs únicos de persona. */
    private static AtomicInteger personSequence = new AtomicInteger(0);
    
    /** ID único de la persona. */
    private int personId;
    
    /** Nombre de la persona. */
    private String firstName;
    
    /** Apellido de la persona. */
    private String lastName;
    
    /** Fecha de nacimiento de la persona. */
    private LocalDate birthDate;

    /**
     * Constructor por defecto. Inicializa una persona con todos los campos en null.
     */
    public Person() {
        this(null, null, null);
    }

    /**
     * Constructor que inicializa una persona con nombre, apellido y fecha de nacimiento.
     * @param firstName Nombre de la persona.
     * @param lastName Apellido de la persona.
     * @param birthDate Fecha de nacimiento de la persona.
     */
    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    /**
     * Obtiene el ID de la persona.
     * @return El ID de la persona.
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Establece el ID de la persona.
     * @param personId El nuevo ID de la persona.
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Obtiene el nombre de la persona.
     * @return El nombre de la persona.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre de la persona.
     * @param firstName El nuevo nombre de la persona.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido de la persona.
     * @return El apellido de la persona.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido de la persona.
     * @param lastName El nuevo apellido de la persona.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene la fecha de nacimiento de la persona.
     * @return La fecha de nacimiento de la persona.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Establece la fecha de nacimiento de la persona.
     * @param birthDate La nueva fecha de nacimiento de la persona.
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Valida la fecha de nacimiento.
     * @param bdate La fecha de nacimiento a validar.
     * @return true si la fecha es válida, false en caso contrario.
     */
    public boolean isValidBirthDate(LocalDate bdate) {
        return isValidBirthDate(bdate, new ArrayList<>());
    }

    /**
     * Valida la fecha de nacimiento y agrega errores a una lista.
     * @param bdate La fecha de nacimiento a validar.
     * @param errorList Lista donde se agregarán los mensajes de error.
     * @return true si la fecha es válida, false en caso contrario.
     */
    public boolean isValidBirthDate(LocalDate bdate, List<String> errorList) {
        if (bdate == null) {
            return true;
        }
        if (bdate.isAfter(LocalDate.now())) {
            errorList.add("Birth date must not be in future.");
            return false;
        }
        return true;
    }

    /**
     * Valida si la persona actual es válida.
     * @param errorList Lista donde se agregarán los mensajes de error.
     * @return true si la persona es válida, false en caso contrario.
     */
    public boolean isValidPerson(List<String> errorList) {
        return isValidPerson(this, errorList);
    }

    /**
     * Valida si una persona es válida.
     * @param p La persona a validar.
     * @param errorList Lista donde se agregarán los mensajes de error.
     * @return true si la persona es válida, false en caso contrario.
     */
    public boolean isValidPerson(Person p, List<String> errorList) {
        boolean isValid = true;
        String fn = p.getFirstName();
        if (fn == null || fn.trim().length() == 0) {
            errorList.add("First name must contain minimum one character.");
            isValid = false;
        }
        String ln = p.getLastName();
        if (ln == null || ln.trim().length() == 0) {
            errorList.add("Last name must contain minimum one character.");
            isValid = false;
        }
        if (!isValidBirthDate(this.getBirthDate(), errorList)) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Intenta guardar la persona si es válida.
     * @param errorList Lista donde se agregarán los mensajes de error si la persona no es válida.
     * @return true si la persona se guardó exitosamente, false en caso contrario.
     */
    public boolean save(List<String> errorList) {
        boolean isSaved = false;
        if (isValidPerson(errorList)) {
            System.out.println("Saved " + this.toString());
            isSaved = true;
        }
        return isSaved;
    }

    /**
     * Devuelve una representación en cadena de la persona.
     * @return Una cadena que representa a la persona.
     */
    @Override
    public String toString() {
        return "[personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
    }
}
