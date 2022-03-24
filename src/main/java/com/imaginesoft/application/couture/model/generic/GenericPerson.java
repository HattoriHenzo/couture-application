package com.imaginesoft.application.couture.model.generic;

import com.imaginesoft.application.couture.model.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public abstract class GenericPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "The First Name can't be empty")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotEmpty(message = "The Last Name can't be empty")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotEmpty(message = "The Telephone can't be empty")
    @Column(name = "TELEPHONE")
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    protected GenericPerson() {
        // Default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
