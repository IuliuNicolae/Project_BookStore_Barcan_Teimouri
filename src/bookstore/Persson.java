/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Iuliu
 */
public class Persson {
    private SimpleStringProperty lastName;
    private SimpleStringProperty firstName;
    private SimpleStringProperty adress;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    public Persson(SimpleStringProperty lastName,SimpleStringProperty firstName,SimpleStringProperty adress,
    SimpleStringProperty email,SimpleStringProperty phone){
        this.lastName=lastName;
        this.firstName=firstName;
        this.adress=adress;
        this.email=email;
        this.phone=phone;
    }
    public SimpleStringProperty getLastName() {
        return lastName;
    }
    public SimpleStringProperty getFirstName() {
        return firstName;
    }
    public SimpleStringProperty getAdress() {
        return adress;
    }
    public SimpleStringProperty getEmail() {
        return email;
    }
    public SimpleStringProperty getPhone() {
        return phone;
    }
    
}
