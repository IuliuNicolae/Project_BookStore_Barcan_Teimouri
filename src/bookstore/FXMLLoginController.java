/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
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
    private Button exitButton;
    @FXML
    private Label labelMessages;
    @FXML
    private TextField idText;
    @FXML
    private ImageView imageView;
    @FXML
    private PasswordField passwordUser;

    DBConnection dbConnection = new DBConnection();
    Properties properties = new Properties();
    private boolean isConnectedUser = false;
    private boolean isConnectedManager = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(FXMLLoginController.class.getResourceAsStream("TheBookStore.jpg"));
        imageView.setImage(image);

        try {
            File file = new File("userId.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            Enumeration enumerationKeys = properties.keys();
            while (enumerationKeys.hasMoreElements()) {
                String key = (String) enumerationKeys.nextElement();
                String value = properties.getProperty(key);
                idText.setText(value);
                System.out.println(key + ":" + value);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex + "file not found 2");

        } catch (IOException ex) {
            System.out.println(ex + "IOException 2");
        }

    }

    @FXML
    public void signIn(ActionEvent event) {
        DataStorage.getDataStorage().setLoginUser(idText.getText());//it helps to sell a book
        labelMessages.setText("");
        if (passwordUser.getText().equals(dbConnection.getPassword(idText.getText())) && dbConnection.getIsManagerValues(idText.getText()) == true) {
            labelMessages.setTextFill(Color.GREEN);
            labelMessages.setText("Log in succeded.You can connect to database!");
            isConnectedManager = true;

            DataStorage.getDataStorage().setLogAsManager(true);

        } else if (passwordUser.getText().equals(dbConnection.getPassword(idText.getText())) && dbConnection.getIsManagerValues(idText.getText()) == false) {
            labelMessages.setTextFill(Color.GREEN);
            labelMessages.setText("Log in succeded.You can connect to database!");
            isConnectedUser = true;

            DataStorage.getDataStorage().setLogAsManager(false);
        } else {
            labelMessages.setTextFill(Color.RED);
            labelMessages.setText("Log in failed!");
            DataStorage.getDataStorage().setLogAsManager(false);
        }

        try {

            String lastId = idText.getText();
            Properties properties = new Properties();
            properties.setProperty("userId", lastId);

            File file = new File("userId.properties");
            try (FileOutputStream fileOutput = new FileOutputStream(file)) {
                properties.store(fileOutput, "User Id");
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex + "file not found");

        } catch (IOException ex) {
            System.out.println(ex + "IOException");
        }
        if (isConnectedUser == true || isConnectedManager == true) {
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Main Menu");

                stage.show();

            } catch (Exception ex) {
                Logger.getLogger(FXMLMainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            labelMessages.setText("Sign in failed!!");
        }
        idText.setText("");
        passwordUser.setText("");
    }

    @FXML
    public void exit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
