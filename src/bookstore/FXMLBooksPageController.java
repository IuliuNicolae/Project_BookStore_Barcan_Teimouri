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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLBooksPageController implements Initializable {

    @FXML
    private TableView<Book> table1 = new TableView<Book>();
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
    private Button exitButton;
    @FXML
    private TextField searchText;
    @FXML
    private TextArea textAreaOperations;
    @FXML
    private Label labelCurrentAmount;
    @FXML
    private Label labelDailyAmount;
    @FXML
    private TextField textFieldClientId;
            
    private int currentAmount = 0;
    private int dailyAmount = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DBConnect con = new DBConnect();
        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.getDataBooks());
        textFieldClientId.setText("0");
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

    @FXML
    private void handleBackButton(ActionEvent event) {
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
    private void handleExitButton(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void sellBookHandle(ActionEvent event) {
        String isbn = table1.getSelectionModel().getSelectedItem().getIsbn();
        String initialer =DataStorage.getDataStorage().getLoginUser();
        DBConnect con = new DBConnect();
        con.sellBook(isbn);

        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.getDataBooks());
        System.out.println(con.getTitleSellOperation(isbn) + " " + con.getPriceSellOperation(isbn));
        String item = con.getTitleSellOperation(isbn) + " " + con.getPriceSellOperation(isbn);
        textAreaOperations.appendText(item + "\n");
        int currentPrice = calcPriceWithDiscount(con.getPriceSellOperation(isbn), 0);
        currentAmount = currentAmount + currentPrice;
        dailyAmount = dailyAmount + currentPrice;
        labelCurrentAmount.setText("Current amount is " +" "+ currentAmount);
        labelDailyAmount.setText("Daily amount is " +" "+ dailyAmount);
        
        int i1=Integer.valueOf(textFieldClientId.getText());
        con.setClientBook(i1, isbn);
        con.setEmployeeBook(isbn, initialer);
    }

    @FXML
    public void returnButtonHandle(ActionEvent event) {
        String isbn = table1.getSelectionModel().getSelectedItem().getIsbn();

        DBConnect con = new DBConnect();
        con.returnBook(isbn);
        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.getDataBooks());
    }

    @FXML
    public void searchISBNHandle(ActionEvent event) {
        String isbn = searchText.getText();
        DBConnect con = new DBConnect();

        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.searchISBN(isbn));
    }

    @FXML
    public void searchTitleHandle(ActionEvent event) {
        String title1 = searchText.getText() + "%";
        DBConnect con = new DBConnect();

        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.searchTitle(title1));
    }

    @FXML
    public void searchAuthorHandle(ActionEvent event) {
        String author1 = searchText.getText() + "%";
        DBConnect con = new DBConnect();

        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.searchAuthor(author1));
    }

    @FXML
    public void printButtonHandle(ActionEvent event) {
        if (currentAmount != 0) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLNote.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Kvito");
                stage.setScene(new Scene(root1));
                stage.show();
                currentAmount = 0;
                labelCurrentAmount.setText("Current amount is " + currentAmount);
                textAreaOperations.clear();
            } catch (Exception ex) {
                Logger.getLogger(FXMLBooksPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }labelCurrentAmount.setText(" Current amount");
    }

    public static int calcPriceWithDiscount(int price, int discount) {
        int finalPrice = price - (price * discount);
        return finalPrice;
    }
}
