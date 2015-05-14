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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLNoteController implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Label labelEntireAmount;
    @FXML
    private Label labelPaidAmount;
    @FXML
    private Label labelDiscount;
    @FXML
    private Label labelVAT;
    @FXML
    private Label labelNameEmployee;
    @FXML
    private Label labelNameClient;
    private float vat;
    private int amount, percent, entireAmount, discount, idClient;
    private String initialer, lastNameEmployee, lastNameClient;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        DBConnect con = new DBConnect();
        initialer = DataStorage.getDataStorage().getLoginUser();
        idClient = DataStorage.getDataStorage().getIdClient();
        amount = DataStorage.getDataStorage().getAmount();
        percent = DataStorage.getDataStorage().getPercent();
        entireAmount = (100 * amount) / (100 - percent);
        discount = entireAmount - amount;
        vat = (amount * 6) / 100;
        
        lastNameEmployee=con.getLastNameEmployee(initialer);
        lastNameClient=con.getLastNameClient(idClient);
        
        labelNameClient.setText(lastNameClient);
        labelEntireAmount.setText(entireAmount + "");
        labelDiscount.setText(discount + "");
        labelPaidAmount.setText(amount + "");
        labelVAT.setText(vat + "");
        labelNameEmployee.setText("On duty: " +lastNameEmployee );
        System.out.println(lastNameEmployee);
        System.out.println(initialer);
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
