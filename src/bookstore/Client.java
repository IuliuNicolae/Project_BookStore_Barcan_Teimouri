/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Iuliu
 */
public class Client extends Person {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final IntegerProperty total = new SimpleIntegerProperty(this, "total");
    private final IntegerProperty percent = new SimpleIntegerProperty(this, "percent");

    public IntegerProperty idProperty() {
        return id;
    }

    public final int getId() {
        return idProperty().get();
    }

    public void setId(int id) {
        idProperty().set(id);
    }

    public IntegerProperty totalProperty() {
        return total;
    }

    public final int getTotal() {
        return totalProperty().get();
    }

    public  void setTotal(int total) {
        totalProperty().set(total);
    }

    public IntegerProperty percentProperty() {
        return percent;
    }

    public final int getPercent() {
        return percentProperty().get();
    }

    public  void setPercent(int percent) {
        percentProperty().set(percent);
    }

    public Client() {
    }

    public Client(int id, String lastName, String firstName, String adress, String email, String phone, int total, int percent) {
        super(lastName, firstName, adress, email, phone);
        setId(id);
        setTotal(total);
        setPercent(percent);
    }

    public static ArrayList<TableColumn<Client, ?>> getColumn(TableView table) {
        int i;
        ArrayList<TableColumn<Client, ?>> columns = new ArrayList<TableColumn<Client, ?>>();
        String[] columnNames = {"ID Client", "First Name", "Last Name", "Adress", "Email", "Phone", "Total Bought", "Percent"};
        String[] variableNames = {"id", "lastn", "first", "adress", "email", "phone", "total", "percent"};
        Integer[] column_width = {70, 70, 70, 60, 60, 70, 90, 70};

        i = 0;
        TableColumn<Client, Integer> idCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Client, String> lastnCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Client, String> firstCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Client, String> adressCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Client, String> emailCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Client, String> phoneCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Client, Integer> totalCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Client, Integer> percentCol = new TableColumn<>(columnNames[i++]);

        i = 0;
        idCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        lastnCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        firstCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        adressCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        emailCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        totalCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        percentCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));

        i = 0;
        idCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));
        lastnCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));
        firstCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));
        adressCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));
        emailCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));
        phoneCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));
        totalCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));
        percentCol.prefWidthProperty().bind(table.prefWidthProperty().divide(560 / column_width[i++]));

        columns.add(idCol);
        columns.add(lastnCol);
        columns.add(firstCol);
        columns.add(adressCol);
        columns.add(emailCol);
        columns.add(phoneCol);
        columns.add(totalCol);
        columns.add(percentCol);
        return columns;
    }

}
