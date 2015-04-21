package bookstore;


import bookstore.Persson;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Iuliu
 */
public class Employee  extends Persson{
    private SimpleStringProperty jobTitle;
    private SimpleStringProperty salary;
    Employee(SimpleStringProperty lastName,SimpleStringProperty firstName,SimpleStringProperty adress,
    SimpleStringProperty email,SimpleStringProperty phone,SimpleStringProperty jobTitle, SimpleStringProperty salary){
        super(lastName,firstName,adress,email,phone);
        this.jobTitle=jobTitle;
        this.salary=salary;
    }
    public SimpleStringProperty getJobTitle() {
        return jobTitle;
    }
    public SimpleStringProperty getSalary() {
        return salary;
    }
    
}
