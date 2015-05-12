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
     
        
    private final DBConnect con = new DBConnect();

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

        table.getColumns().addAll(Employee.getColumn(table));
        table.setItems(con.getDataEmployees());
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        labelError.setText(null);
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger(FXMLMainPage.class.getName()).log(Level.SEVERE, null, ex);
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
        } else {
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
            con.deleteData(initialer);
            con.deleteIdUser(initialer);
            table.getColumns().addAll(Employee.getColumn(table));
            table.setItems(con.getDataEmployees());
            
        } else {
            labelError.setText("Access denied");
        }
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) {
        labelError.setText(null);
        if (DataStorage.getDataStorage().getLogAsManager() == true) {
            String lastName = textField2.getText();
            textField2.clear();
            String firstName = textField3.getText();
            textField3.clear();
            String adress = textField4.getText();
            textField4.clear();
            String email = textField5.getText();
            textField5.clear();
            String phone = textField6.getText();
            textField6.clear();
            String initialer = textField7.getText();
            textField7.clear();
            String salary = textField8.getText();
            textField8.clear();
            DBConnect connect = new DBConnect();
            connect.setData(lastName, firstName, adress, email, phone, initialer, salary);
            connect.setNewId(initialer);
            table.getColumns().addAll(Employee.getColumn(table));
            table.setItems(con.getDataEmployees());
            DataStorage.getDataStorage().setId_User(initialer);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLPassword.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Password");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ex) {
                Logger.getLogger(FXMLBooksPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            labelError.setText("Access denied");
        }
    }
}
