package bookstore;

import bookstore.Persson;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Iuliu
 */
public class Employee extends Persson {

    private final StringProperty initialer = new SimpleStringProperty(this, "initialer");
    private final StringProperty salary = new SimpleStringProperty(this, "salary");

    public StringProperty initialerProperty() {
        return initialer;
    }

    public final String getInitialer() {
        return initialerProperty().get();
    }

    public final void setInitialer(String title) {
        initialerProperty().set(title);
    }

    public StringProperty salaryProperty() {
        return salary;
    }

    public final String getSalary() {
        return salaryProperty().get();
    }

    public final void setSalary(String salary) {
        salaryProperty().set(salary);
    }

    public Employee() {
    }

    public Employee(String lastName, String firstName, String adress, String email, String phone, String initialer, String salary) {
        super(lastName, firstName, adress, email, phone);
        setInitialer(initialer);
        setSalary(salary);
    }

    public static ArrayList<TableColumn<Employee, ?>> getColumn(TableView table) {
        int i;
        ArrayList<TableColumn<Employee, ?>> columns = new ArrayList<TableColumn<Employee, ?>>();
        String[] columnNames = {"First Name", "Last Name", "Adress", "Email", "Phone", "Initialer", "Salary"};
        String[] variableNames = {"lastn", "first", "adress", "email", "phone", "initialer", "salary"};
        Integer[] column_width = {57, 53, 53, 53, 53, 53, 63};

        i = 0;
        TableColumn<Employee, String> lastnCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Employee, String> firstCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Employee, String> adressCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Employee, String> emailCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Employee, String> phoneCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Employee, String> initialerCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Employee, String> salaryCol = new TableColumn<>(columnNames[i++]);

        i = 0;
        lastnCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        firstCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        adressCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        emailCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        initialerCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));

       i = 0;
        lastnCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        firstCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        adressCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        emailCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        phoneCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        initialerCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        salaryCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        
        columns.add(lastnCol);
        columns.add(firstCol);
        columns.add(adressCol);
        columns.add(emailCol);
        columns.add(phoneCol);
        columns.add(initialerCol);
        columns.add(salaryCol);
        return columns;
    }

}
