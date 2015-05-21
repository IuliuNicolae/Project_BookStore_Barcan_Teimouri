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
    @FXML
    TextArea textAreaNote;
    private float vat;
    private int currentAmount, percent, entireAmount, discount, idClient;
    private String initialer, lastNameEmployee, lastNameClient;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        DBConnection dbConnection = new DBConnection();
        initialer = DataStorage.getDataStorage().getLoginUser();
        idClient = DataStorage.getDataStorage().getIdClient();
        currentAmount = DataStorage.getDataStorage().getCurrentAmount();
        percent = DataStorage.getDataStorage().getPercent();
        entireAmount = (100 * currentAmount) / (100 - percent);
        discount = entireAmount - currentAmount;
        vat = (currentAmount * 6) / 100;

        lastNameEmployee = dbConnection.getLastNameEmployee(initialer);
        lastNameClient = dbConnection.getLastNameClient(idClient);

        labelNameClient.setText(lastNameClient);
        labelEntireAmount.setText(entireAmount + "");
        labelDiscount.setText(discount + "");
        labelPaidAmount.setText(currentAmount + "");
        labelVAT.setText(vat + "");
        labelNameEmployee.setText("On duty: " + lastNameEmployee);
        System.out.println(lastNameEmployee);
        System.out.println(initialer);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("myfile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (ArrayList<String>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {

            textAreaNote.appendText(arrayList.get(i)+"\n");
        }
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
      try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBooksPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,630,565);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger(FXMLMainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
