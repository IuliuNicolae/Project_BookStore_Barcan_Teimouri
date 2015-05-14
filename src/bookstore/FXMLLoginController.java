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

public class FXMLLoginController implements Initializable {

   
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Label label;
    @FXML
    private TextField text1;
    @FXML
    private ImageView imageView;
    @FXML
    private PasswordField pass1;
    @FXML
    Parent root;
    DBConnect con = new DBConnect();
    // private String idUser = "user", passUser = "1", idManager = "manager", passManager = "pass2";
    private boolean isConnectedUser = false;
    private boolean isConnectedManager = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(FXMLLoginController.class.getResourceAsStream("Papirus.png"));
        imageView.setImage(image);
        Button2.setVisible(false);

    }

    @FXML
    public void signIn(ActionEvent event) {
        DataStorage.getDataStorage().setLoginUser(text1.getText());
        label.setText("");
        if (pass1.getText().equals(con.getPass(text1.getText())) && con.getIsManagerValues(text1.getText()) == true) {
            label.setTextFill(Color.GREEN);
            label.setText("Log in succeded.You can connect to database!");
            isConnectedManager = true;
            Button2.setVisible(true);
            DataStorage.getDataStorage().setLogAsManager(true);

        } else if (pass1.getText().equals(con.getPass(text1.getText())) && con.getIsManagerValues(text1.getText()) == false) {
            label.setTextFill(Color.GREEN);
            label.setText("Log in succeded.You can connect to database!");
            isConnectedUser = true;
            Button2.setVisible(true);

            DataStorage.getDataStorage().setLogAsManager(false);
        } else {
            label.setTextFill(Color.RED);
            label.setText("Log in failed!");
            Button2.setVisible(false);
            DataStorage.getDataStorage().setLogAsManager(false);
        }
        text1.setText("");
        pass1.setText("");

    }

    @FXML
    public void exit(ActionEvent event) {
        Stage stage = (Stage) Button3.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void connect(ActionEvent event) {
        if (isConnectedUser == true || isConnectedManager == true) {
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                // imageView.
                stage.show();

            } catch (Exception ex) {
                Logger.getLogger(FXMLMainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            label.setText("You have to sign in first!");
        }
    }

}
