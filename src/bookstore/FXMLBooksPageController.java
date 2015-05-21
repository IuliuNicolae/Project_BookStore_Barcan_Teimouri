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
    private Button exitButton;

    @FXML
    private TableView<Book> tableBooks = new TableView<Book>();
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
    private TextField searchText;

    @FXML
    private TextField textFieldClientId;

    @FXML
    private TextArea textAreaOperations;
    @FXML
    private Label labelCurrentAmount;
    @FXML
    private Label labelDailyAmount;
    @FXML
    private Label labelError;
    DBConnection dbConnection = new DBConnection();
    private int currentAmount = 0;
    private int dailyAmount;
    private int temporaryAmountOfClient, temporaryQuantity;
    private final int percentCondition1 = 5000;
    ArrayList<String> array = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelError.setText("");
        tableBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableBooks.getColumns().addAll(Book.getColumn(tableBooks));
        tableBooks.setItems(dbConnection.getDataBooks());
        textFieldClientId.setText("0");
        dailyAmount = DataStorage.getDataStorage().getDailyAmount();
        labelDailyAmount.setText("Daily:" + " " + dailyAmount);
    }

    @FXML
    private void addBookHandle(ActionEvent event) {
        labelError.setText("");
        try {
            String title = textFieldTitle.getText();
            textFieldTitle.clear();
            String author = textFieldAuthor.getText();
            textFieldAuthor.clear();
            String ISBN = textFieldISBN.getText();
            textFieldISBN.clear();
            String genre = textFieldGenre.getText();
            textFieldGenre.clear();
            String temporaryQuantity = textFieldQuantity.getText();
            int quantity = Integer.valueOf(temporaryQuantity);
            textFieldQuantity.clear();
            String temporaryPrice = textFieldPrice.getText();
            int price = Integer.valueOf(temporaryPrice);
            textFieldPrice.clear();

            dbConnection.setBook(title, author, ISBN, genre, quantity, price);
            tableBooks.setItems(dbConnection.getDataBooks());

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
            Scene scene = new Scene(root, 630, 565);
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
        labelError.setText("");
        String isbn = tableBooks.getSelectionModel().getSelectedItem().getIsbn();
        temporaryQuantity = dbConnection.getQuantity(isbn);
        if (temporaryQuantity > 0) {

            int idClient = Integer.valueOf(textFieldClientId.getText());
            DataStorage.getDataStorage().setIdClient(idClient);
            dbConnection.sellBook(isbn);
            int percent = dbConnection.getPercent(idClient);
            DataStorage.getDataStorage().setPercent(percent);

            System.out.println(dbConnection.getTitleSellOperation(isbn) + " " + dbConnection.getPriceSellOperation(isbn));
            String currentBook = dbConnection.getTitleSellOperation(isbn) + " " + dbConnection.getPriceSellOperation(isbn);
            textAreaOperations.appendText(currentBook + "\n");
            array.add(currentBook);
            int currentPrice = calcPriceWithDiscount(dbConnection.getPriceSellOperation(isbn), percent);
            dbConnection.setAmountWithoutPercent(idClient, currentPrice);

            temporaryAmountOfClient = dbConnection.getAmountClient(idClient);
            System.out.print(temporaryAmountOfClient);
            if (temporaryAmountOfClient >= percentCondition1) {
                if (idClient != 0) {
                    dbConnection.modifyPercent(idClient);
                    temporaryAmountOfClient = temporaryAmountOfClient - percentCondition1;
                    dbConnection.setAmountWithPercent(idClient, temporaryAmountOfClient);
                    System.out.println("Your percent was modified");
                } else {
                    System.out.println("For this client percent is 0!");
                }
            } else {
                System.out.println("Your percent was not modified");
            }

            dailyAmount = dailyAmount + currentPrice;
            currentAmount = currentAmount + currentPrice;
            DataStorage.getDataStorage().setCurrentAmount(currentAmount);
            DataStorage.getDataStorage().setDailyAmount(dailyAmount);
            labelCurrentAmount.setText("Current:" + currentAmount);
            labelDailyAmount.setText("Daily:" + " " + dailyAmount);

            dbConnection.setClientBook(idClient, isbn);
            String initialer = DataStorage.getDataStorage().getLoginUser();
            dbConnection.setEmployeeBook(isbn, initialer);
            tableBooks.setItems(dbConnection.getDataBooks());

            try {
                FileOutputStream fos = new FileOutputStream("myfile");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(array);
                oos.close();
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            for (int i = 0; i < array.size(); i++) {
                System.out.println(array.get(i));
            }
        } else {
            labelError.setText("Is not possible to sel if quantity=0!");
        }
    }

    @FXML
    public void returnButtonHandle(ActionEvent event) {
        labelError.setText("");
        String isbn = tableBooks.getSelectionModel().getSelectedItem().getIsbn();
        int temporaryPrice = dbConnection.getPriceSellOperation(isbn);
        dbConnection.returnBook(isbn);
        tableBooks.setItems(dbConnection.getDataBooks());
        
        if(temporaryPrice<dailyAmount){
        dailyAmount=dailyAmount-temporaryPrice;
        labelDailyAmount.setText("Daily:" + " " + dailyAmount);
        }else{
        labelError.setText("You have to sold more books!!");
        }
    }

    @FXML
    public void handleChangeButton() {
        labelError.setText("");
        try {
            String isbn = tableBooks.getSelectionModel().getSelectedItem().getIsbn();
            int actualQuantity = Integer.valueOf(textFieldQuantity.getText());
            int actualPrice = Integer.valueOf(textFieldPrice.getText());
            dbConnection.changeBookStatus(isbn, actualQuantity, actualPrice);
            tableBooks.setItems(dbConnection.getDataBooks());
        } catch (Exception ex) {
            labelError.setText("Is not a integer!");
        }
    }

    @FXML
    public void searchISBNHandle(ActionEvent event) {
        String isbn = searchText.getText();
        tableBooks.setItems(dbConnection.searchISBN(isbn));
    }

    @FXML
    public void searchTitleHandle(ActionEvent event) {
        String title1 = searchText.getText() + "%";
        tableBooks.setItems(dbConnection.searchTitle(title1));
    }

    @FXML
    public void searchAuthorHandle(ActionEvent event) {
        String author1 = searchText.getText() + "%";
        tableBooks.setItems(dbConnection.searchAuthor(author1));
    }

    @FXML
    public void searchPrice1Handle(ActionEvent event) {

        tableBooks.setItems(dbConnection.searchPrice1());
    }

    @FXML
    public void searchPrice2Handle(ActionEvent event) {

        tableBooks.setItems(dbConnection.searchPrice2());
    }

    @FXML
    public void searchPrice3Handle(ActionEvent event) {

        tableBooks.setItems(dbConnection.searchPrice3());
    }

    @FXML
    public void printButtonHandle(ActionEvent event) {
        if (currentAmount != 0) {
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLNote.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 630, 565);
                stage.setScene(scene);
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

}
