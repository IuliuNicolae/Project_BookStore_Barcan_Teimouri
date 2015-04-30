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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLBooksPageController implements Initializable {

   @FXML
    private  TableView<Book> table1 = new TableView<Book>();
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private TextField textField6;
    @FXML
    private TextField textField7;
    @FXML
    private Button sellBookButton;
    @FXML
    private Button addBookButton;
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField searchText;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnect con = new DBConnect();
        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.getDataBooks());
    }

    @FXML
    private void addBookHandle(ActionEvent event) {
        try {
            String q1 = textField1.getText();
            textField1.clear();
            String q2 = textField2.getText();
            textField2.clear();
            String q3 = textField3.getText();
            textField3.clear();
            String q4 = textField4.getText();
            textField4.clear();
            String q5 = textField5.getText();
            int i5 = Integer.valueOf(q5);
            textField5.clear();
            String q6 = textField6.getText();
            int i6 = Integer.valueOf(q6);
            textField6.clear();
            String q7 = textField7.getText();
            textField7.clear();

            DBConnect con = new DBConnect();
            con.setBook(q1, q2, q3, q4, i5, i6, q7);
            table1.getColumns().addAll(Book.getColumn(table1));
            table1.setItems(con.getDataBooks());
            
        } catch (Exception ex) {
            System.out.println("Is not a integer!!");
        }
    }

    private void sellButtonHandle(ActionEvent event) {
         DBConnect con = new DBConnect();
        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.getDataBooks());
    }


    @FXML
    private void handleBackButton(ActionEvent event) {
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
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public static ObservableList<Book> getExemple() {
        ObservableList<Book> data = FXCollections.observableArrayList();
        data.addAll(new Book("1", "2", "3", "4", 5, 6, "7"));
        return data;
    }

    @FXML
    private void sellBookHandle(ActionEvent event) {
    }

   
}
