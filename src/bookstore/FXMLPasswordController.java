/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLPasswordController implements Initializable {

    private String id_user;

    @FXML
    Button doneButton;
    @FXML
    PasswordField password1;
    @FXML
    PasswordField password2;
    @FXML
    Label labelName;
    @FXML
    Label labelError;
    DBConnection dbConnection = new DBConnection();
    private String name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        password1.clear();
        password2.clear();
        id_user = dbConnection.getIdLastEmployee();
        name = dbConnection.getFirstNameLastEmployee();
        labelName.setText(name);
    }

    @FXML
    private void handleButtonDone(ActionEvent event) {

        if (password1.getText().equals(password2.getText())) {
            String pass = password1.getText();
            dbConnection.setPassword(pass, id_user);
            Stage stage = (Stage) doneButton.getScene().getWindow();
            stage.close();

        } else {
            labelError.setText("Passwor error!Try again!");
            password1.clear();
            password2.clear();
        }
    }
}
