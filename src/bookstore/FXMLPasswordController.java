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
    PasswordField pass1;
    @FXML
    PasswordField pass2;
    @FXML
    Label labelName;
    @FXML
    Label labelError;
    DBConnect con = new DBConnect();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_user = DataStorage.getDataStorage().getId_user();
    }

    @FXML
    private void handleButtonDone(ActionEvent event) {
        if (pass1.getText().equals(pass2.getText()) ) {
            String pass = pass1.getText();
            Stage stage = (Stage) doneButton.getScene().getWindow();
            stage.close();
            con.setPassword(pass, id_user);
        } else {
            pass1.clear();
            pass2.clear();
            labelError.setText("Passwor error!Try again!");

        }
    }
}
