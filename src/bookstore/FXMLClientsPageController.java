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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLClientsPageController implements Initializable {

    @FXML
    private Button buttonClose;
    @FXML
    private TableView<Client> tableClient = new TableView<Client>();
    @FXML
    private TextField textId;
    @FXML
    private TextField textLastName;
    @FXML
    private TextField textFirstName;
    @FXML
    private TextField textAdress;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textPhone;
    @FXML
    private Label labelError;
    @FXML
    private TextArea tableClientBooks;
    DBConnect con = new DBConnect();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableClient.getColumns().addAll(Client.getColumn(tableClient));
        tableClient.setItems(con.getDataClients());
    }

    @FXML
    private void backButtonHandle(ActionEvent event) {
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
    private void handleCloseButton(ActionEvent event) {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        try {
            int id = Integer.valueOf(textId.getText());
            textId.clear();
            String lastName = textLastName.getText();
            textLastName.clear();
            String firstName = textFirstName.getText();
            textFirstName.clear();
            String adress = textAdress.getText();
            textAdress.clear();
            String email = textEmail.getText();
            textEmail.clear();
            String phone = textPhone.getText();
            textPhone.clear();
            con.setNewClient(id, lastName, firstName, adress, email, phone);
        } catch (Exception ex) {
            System.out.println("Problem");
            labelError.setText("Id is not integer");
        }

         tableClient.setItems(con.getDataClients());
    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        String lastName = textLastName.getText();
        textLastName.clear();
        String firstName = textFirstName.getText();
        textFirstName.clear();
        String adress = textAdress.getText();
        textAdress.clear();
        String email = textEmail.getText();
        textEmail.clear();
        String phone = textPhone.getText();
        textPhone.clear();
        int idClient = tableClient.getSelectionModel().getSelectedItem().getId();

        if (idClient > 0) {
            con.updateClient(idClient, firstName, lastName, adress, email, phone);
             tableClient.setItems(con.getDataClients());
        } else {
            labelError.setText("You should not change this client!");
        }
    }

    @FXML
    private void handleViewBooksButton(ActionEvent event) {
        tableClientBooks.clear();
        int idClient = tableClient.getSelectionModel().getSelectedItem().getId();

        for (int i = 0; i < con.controllClientBooks(idClient).size(); i++) {
            tableClientBooks.appendText(con.controllClientBooks(idClient).get(i) + "\n");
        }

    }

}
