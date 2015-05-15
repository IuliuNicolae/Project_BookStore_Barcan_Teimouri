/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    private TextField textFieldTitle;
    @FXML
    private TextField textFieldAuthor;
    @FXML
    private TextField textFieldISBN;
    @FXML
    private TextField textFieldGenre;
    @FXML
    private TextField textFieldQuantity;
    @FXML
    private TextField textFieldPrice;
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
    DBConnect con = new DBConnect();
    private int currentAmount = 0;
    private int dailyAmount = 0;
    private int temporaryAmountOfClient;
    private final int percentCondition1 = 5000;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table1.getColumns().addAll(Book.getColumn(table1));
        table1.setItems(con.getDataBooks());
        textFieldClientId.setText("0");
    }

    @FXML
    private void addBookHandle(ActionEvent event) {
        try {
            String q1 = textFieldTitle.getText();
            textFieldTitle.clear();
            String q2 = textFieldAuthor.getText();
            textFieldAuthor.clear();
            String q3 = textFieldISBN.getText();
            textFieldISBN.clear();
            String q4 = textFieldGenre.getText();
            textFieldGenre.clear();
            String q5 = textFieldQuantity.getText();
            int i5 = Integer.valueOf(q5);
            textFieldQuantity.clear();
            String q6 = textFieldPrice.getText();
            int i6 = Integer.valueOf(q6);
            textFieldPrice.clear();
            String q7 = textField7.getText();
            textField7.clear();

            con.setBook(q1, q2, q3, q4, i5, i6, q7);
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
         ArrayList<String> array = new ArrayList<>();
        String isbn = table1.getSelectionModel().getSelectedItem().getIsbn();
        int idClient = Integer.valueOf(textFieldClientId.getText());
        DataStorage.getDataStorage().setIdClient(idClient);
        con.sellBook(isbn);
        int percent = con.getPercent(idClient);
        DataStorage.getDataStorage().setPercent(percent);

        System.out.println(con.getTitleSellOperation(isbn) + " " + con.getPriceSellOperation(isbn));
        String currentBook = con.getTitleSellOperation(isbn) + " " + con.getPriceSellOperation(isbn);
        textAreaOperations.appendText(currentBook + "\n");
        array.add(currentBook);
        int currentPrice = calcPriceWithDiscount(con.getPriceSellOperation(isbn), percent);
        con.setTotal(idClient, currentPrice);

        temporaryAmountOfClient = con.getAmountClient(idClient);
        System.out.print(temporaryAmountOfClient);
        if (temporaryAmountOfClient >= percentCondition1) {
            con.modifyPercent(idClient);
            temporaryAmountOfClient = temporaryAmountOfClient - percentCondition1;
            con.setTotal(idClient, temporaryAmountOfClient);
            System.out.println("Your percent was modified");
        } else {
            System.out.println("Your percent was not modified");
        }

        dailyAmount = dailyAmount + currentPrice;
        currentAmount = currentAmount + currentPrice;
        DataStorage.getDataStorage().setAmount(currentAmount);
        labelCurrentAmount.setText("Current amount is " + " " + currentAmount);
        labelDailyAmount.setText("Daily amount is " + " " + dailyAmount);

        con.setClientBook(idClient, isbn);
        String initialer = DataStorage.getDataStorage().getLoginUser();
        con.setEmployeeBook(isbn, initialer);
        table1.setItems(con.getDataBooks());
        
          try {
            FileOutputStream fos = new FileOutputStream("myfile");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(array);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    

    @FXML
    public void returnButtonHandle(ActionEvent event) {
        String isbn = table1.getSelectionModel().getSelectedItem().getIsbn();

        con.returnBook(isbn);
        table1.setItems(con.getDataBooks());
    }

    @FXML
    public void handleChangeButton() {
        String isbn = table1.getSelectionModel().getSelectedItem().getIsbn();
        int actualQuantity = Integer.valueOf(textFieldQuantity.getText());
        int actualPrice = Integer.valueOf(textFieldPrice.getText());
        con.changeBookStatus(isbn, actualQuantity, actualPrice);
        table1.setItems(con.getDataBooks());
    }

    @FXML
    public void searchISBNHandle(ActionEvent event) {
        String isbn = searchText.getText();
        table1.setItems(con.searchISBN(isbn));
    }

    @FXML
    public void searchTitleHandle(ActionEvent event) {
        String title1 = searchText.getText() + "%";
        table1.setItems(con.searchTitle(title1));
    }

    @FXML
    public void searchAuthorHandle(ActionEvent event) {
        String author1 = searchText.getText() + "%";
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
        }
        labelCurrentAmount.setText(" Current amount");
    }

    public static int calcPriceWithDiscount(int price, int discount) {
        int finalPrice = Math.abs(price - ((price * discount) / 100));
        return finalPrice;
    }

    public static void serializeArray() {}
      
}
