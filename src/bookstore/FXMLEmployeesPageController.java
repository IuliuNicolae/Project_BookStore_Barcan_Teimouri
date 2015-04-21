/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLEmployeesPageController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private TextArea textArea;
    
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
    private Connection con;
    private Statement st;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea.setVisible(false);
        button6.setVisible(false);
       // textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
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
    private void handleViewButton(ActionEvent event) {
        textArea.setVisible(true);
        button6.setVisible(false);
       // textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);

    }

    @FXML
    private void handleNewButton(ActionEvent event) {
        textArea.setVisible(false);
        button6.setVisible(true);
       // textField1.setVisible(true);
        textField2.setVisible(true);
        textField3.setVisible(true);
        textField4.setVisible(true);
        textField5.setVisible(true);
        textField6.setVisible(true);
        textField7.setVisible(true);
        textField8.setVisible(true);
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        textArea.setVisible(false);
        button6.setVisible(false);
     //   textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) {
      //  String q1 = textField1.getText();
        String q2 = textField2.getText();
        String q3 = textField3.getText();
        String q4 = textField4.getText();
        String q5 = textField5.getText();
        String q6 = textField6.getText();
        String q7 = textField7.getText();
        String q8 = textField8.getText();
        DBConnect connect = new DBConnect();
        connect.setData(null,q2,q3,q4,q5,q6,q7,q8);
    }

}
