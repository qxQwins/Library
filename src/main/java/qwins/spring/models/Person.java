package qwins.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 3, max = 100, message = "ФИО должно иметь от 3 до 100 символов")
    private String fio;

    @Min(value = 0, message = "Год не может быть меньше 0")
    private int yearOfBirth;

    @NotEmpty(message = "Электронная почта не может быть пустой")
    @Email(message = "Электронная почта введена неправильно")
    private String email;

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {
    }

    public Person(int id, String fio, int yearOfBirth, String email) {
        this.id = id;
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
