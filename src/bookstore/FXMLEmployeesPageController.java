/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLEmployeesPageController implements Initializable {

    @FXML
    private TableView<Employee> table = new TableView<Employee>();
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private TextField textField6;
    @FXML
    private TextField textField7;
    @FXML
    private TextField textField8;
    @FXML
    private Button button6;
    @FXML
    private Label labelError;
    @FXML
    private Button button2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    labelError.setText(null);
        button6.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);

        DBConnect con = new DBConnect();
        table.getColumns().addAll(Employee.getColumn(table));
        table.setItems(con.getDataEmployees());
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        labelError.setText(null);
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSelection.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger(FXMLSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleQuitButton(ActionEvent event) {
        
        Stage stage = (Stage) button2.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleNewButton(ActionEvent event) {
        labelError.setText(null);
        if (DataStorage.getDataStorage().getLogAsManager() == true) {
            button6.setVisible(true);
            textField2.setVisible(true);
            textField3.setVisible(true);
            textField4.setVisible(true);
            textField5.setVisible(true);
            textField6.setVisible(true);
            textField7.setVisible(true);
            textField8.setVisible(true);
        }else{
            labelError.setText("Access denied!");
        }
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        labelError.setText(null);
        if (DataStorage.getDataStorage().getLogAsManager() == true) {
            button6.setVisible(false);
            textField2.setVisible(false);
            textField3.setVisible(false);
            textField4.setVisible(false);
            textField5.setVisible(false);
            textField6.setVisible(false);
            textField7.setVisible(false);
            textField8.setVisible(false);
            String initialer = table.getSelectionModel().getSelectedItem().getInitialer();
            System.out.println(initialer);
            DBConnect con = new DBConnect();
            con.deleteData(initialer);
            table.getColumns().addAll(Employee.getColumn(table));
            table.setItems(con.getDataEmployees());
        }else{
            labelError.setText("Access denied");
        }
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) {
        labelError.setText(null);
        if (DataStorage.getDataStorage().getLogAsManager() == true) {
            String q2 = textField2.getText();
            textField2.clear();
            String q3 = textField3.getText();
            textField3.clear();
            String q4 = textField4.getText();
            textField4.clear();
            String q5 = textField5.getText();
            textField5.clear();
            String q6 = textField6.getText();
            textField6.clear();
            String q7 = textField7.getText();
            textField7.clear();
            String q8 = textField8.getText();
            textField8.clear();
            DBConnect connect = new DBConnect();
            connect.setData(q2, q3, q4, q5, q6, q7, q8);
            table.getColumns().addAll(Employee.getColumn(table));

        }else{
            labelError.setText("Access denied");
        }
    }
}
