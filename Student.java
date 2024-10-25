package Beans;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private int id; // ID will now be manually assigned

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email) { // Constructor with ID
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String text) {
    }

    public void setLastName(String text) {
    }

    public void setEmail(String text) {
    }

    // Other getters, setters, and methods remain unchanged
}
