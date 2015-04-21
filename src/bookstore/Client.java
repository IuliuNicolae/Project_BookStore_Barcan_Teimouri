/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Iuliu
 */
public class Client extends Persson{
    private SimpleIntegerProperty total;

    public Client(SimpleStringProperty lastName, SimpleStringProperty firstName, SimpleStringProperty adress, 
            SimpleStringProperty email, SimpleStringProperty phone,SimpleIntegerProperty total) {
        super(lastName, firstName, adress, email, phone);
        this.total=total;
    }
    public SimpleIntegerProperty getTotal() {
        return total;
    }
}
