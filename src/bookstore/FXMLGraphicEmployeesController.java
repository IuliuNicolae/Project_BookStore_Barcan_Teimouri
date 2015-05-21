/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class FXMLGraphicEmployeesController implements Initializable {

    DBConnection dbConnection = new DBConnection();
    @FXML
    private PieChart pieChart;
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    private String temporaryEmployee;
    private int employeeResult;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < dbConnection.getAllEmployees().size(); i++) {
            temporaryEmployee = dbConnection.getAllEmployees().get(i);
            employeeResult = dbConnection.getEmployeeResult(temporaryEmployee);
            System.out.print(employeeResult);
            System.out.println(temporaryEmployee);

            data.add(new PieChart.Data(temporaryEmployee, employeeResult));
        }
        pieChart.setData(data);

    }
}
