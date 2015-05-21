/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Iuliu
 */
public abstract class Person {
    
    
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private final StringProperty email = new SimpleStringProperty(this, "email");
    private final StringProperty adress = new SimpleStringProperty(this, "adress");
    private final IntegerProperty phone = new SimpleIntegerProperty(this, "phone");

    public StringProperty firstProperty() {
        return firstName;
    }

    public final String getFirstName() {
        return firstProperty().get();
    }

    public final void setFirstName(String firstName) {
        firstProperty().set(firstName);
    }

    public StringProperty lastnProperty() {
        return lastName;
    }

    public final String getName() {
        return lastnProperty().get();
    }

    public final void setLastName(String lastName) {
        lastnProperty().set(lastName);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public final String getEmail() {
        return emailProperty().get();
    }

    public final void setEmail(String email) {
        emailProperty().set(email);
    }

    public StringProperty adressProperty() {
        return adress;
    }

    public final String getAdress() {
        return adressProperty().get();
    }

    public final void setAdress(String adress) {
        adressProperty().set(adress);
    }

    public IntegerProperty phoneProperty() {
        return phone;
    }

    public final int getPhone() {
        return phoneProperty().get();
    }

    public final void setPhone(int phone) {
        phoneProperty().set(phone);
    }

    public Person() {
    }

    public Person(String lastname, String firstname, String adress, String email, int phone) {
        
        setLastName(lastname);
        setFirstName(firstname);
        setAdress(adress);
        setEmail(email);
        setPhone(phone);
    }

}
