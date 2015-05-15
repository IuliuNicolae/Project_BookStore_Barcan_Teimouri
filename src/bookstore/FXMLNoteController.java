/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    @FXML TextArea textAreaNote;
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
           ArrayList<String> arraylist= new ArrayList<String>();
        try
        {
            FileInputStream fis = new FileInputStream("myfile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
         }catch(IOException ioe){
             ioe.printStackTrace();
             return;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
          }
        for(String tmp: arraylist){
            System.out.println(tmp);
               textAreaNote.appendText(tmp + "\n");
        }
   }
        
    

    @FXML
    public void handleExitButton(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
