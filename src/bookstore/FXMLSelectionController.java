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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLSelectionController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEmployeesButton(ActionEvent event) {
           try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEmployeesPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception ex) {
                Logger.getLogger(FXMLSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void handleClientsButton(ActionEvent event) {
           try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLClientsPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception ex) {
                Logger.getLogger(FXMLSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
          try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainPage.fxml"));
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
         Stage stage = (Stage) button5.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleBooksPage(ActionEvent event) {
           try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBooksPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception ex) {
                Logger.getLogger(FXMLSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
