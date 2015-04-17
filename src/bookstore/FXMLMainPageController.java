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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXMLMainPageController implements Initializable {

    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Label label;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private ImageView imageView;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;

    private String idUser = "user", passUser = "pass1", idManager = "manager", passManager = "pass2";
    private boolean isConnected = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(FXMLMainPageController.class.getResourceAsStream("Papirus.png"));
        imageView.setImage(image);
    }

    @FXML
    public void signIn(ActionEvent event) {
        label.setText("");

        if (text1.getText().toString().equals(idUser) && pass1.getText().toString().equals(passUser) || text3.getText().toString().equals(idManager) && pass2.getText().toString().equals(passManager)) {
            label.setTextFill(Color.GREEN);
            label.setText("Log in succeded.You can connect to database!");
            isConnected=true;
        } else {
            label.setTextFill(Color.RED);
            label.setText("Log in failed!");
        }
        text1.setText("");
        pass1.setText("");
        text3.setText("");
        pass2.setText("");
    }

    @FXML
    public void exit(ActionEvent event) {
        Stage stage = (Stage) Button3.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void connect(ActionEvent event){
    if (isConnected==true){
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
    }else{
        label.setText("You have to sign in first!");
    }
        }

}
