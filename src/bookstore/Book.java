/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Iuliu
 */
public class Book {

    private final StringProperty title = new SimpleStringProperty(this, "title");
    private final StringProperty author = new SimpleStringProperty(this, "author");
    private final StringProperty isbn = new SimpleStringProperty(this, "isbn");
    private final StringProperty genre = new SimpleStringProperty(this, "genre");
    private final IntegerProperty quantity = new SimpleIntegerProperty(this, "quantity");
    private final IntegerProperty price = new SimpleIntegerProperty(this, "price");
    private final StringProperty initialer = new SimpleStringProperty(this, "initialer");

    public StringProperty titleProperty() {
        return title;
    }

    public final String getTitle() {
        return titleProperty().get();
    }

    public final void setTitle(String title) {
        titleProperty().set(title);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public final String getAuthor() {
        return authorProperty().get();
    }

    public final void setAuthor(String author) {
        authorProperty().set(author);
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public final String getIsbn() {
        return isbnProperty().get();
    }

    public final void setIsbn(String isbn) {
        isbnProperty().set(isbn);
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public final String getGenre() {
        return genreProperty().get();
    }

    public final void setGenre(String genre) {
        genreProperty().set(genre);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public final int getQuantity() {
        return quantityProperty().get();
    }

    public final void setQuantity(int quantity) {
        quantityProperty().set(quantity);
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public final int getPrice() {
        return priceProperty().get();
    }

    public final void setPrice(int price) {
        priceProperty().set(price);
    }

    public StringProperty initialerProperty() {
        return initialer;
    }

    public String getInitialer() {
        return initialerProperty().get();
    }

    public final void setInitialer(String initialer) {
        initialerProperty().set(initialer);
    }

    public Book() {
    }

    public Book(String title, String author, String isbn, String genre, int quantity, int price, String initialer) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setGenre(genre);
        setQuantity(quantity);
        setPrice(price);
        setInitialer(initialer);
    }

    public static ArrayList<TableColumn<Book, ?>> getColumn(TableView table) {
        int i;
        ArrayList<TableColumn<Book, ?>> columns = new ArrayList<TableColumn<Book, ?>>();
             String[] columnNames = {"Title of Book", "Author of Book", "ISBN", "Genre", "Quantity", "Price", "Initialer of Client"};
        String[] variableNames = {"title", "author", "isbn", "genre", "quantity", "price", "initialer"};
        Integer[] column_width = {33, 33, 33, 33, 33, 33, 33};

        i = 0;
        TableColumn<Book, String> titleCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Book, String> authorCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Book, String> isbnCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Book, String> genreCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Book, Integer> quantityCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Book, Integer> priceCol = new TableColumn<>(columnNames[i++]);
        TableColumn<Book, String> initialerCol = new TableColumn<>(columnNames[i++]);
        
         i = 0;
        titleCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        authorCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        genreCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        priceCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));
        initialerCol.setCellValueFactory(new PropertyValueFactory<>(variableNames[i++]));

       i = 0;
        titleCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        authorCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        isbnCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        genreCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        quantityCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        priceCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        initialerCol.prefWidthProperty().bind(table.prefWidthProperty().divide(400 / column_width[i++]));
        
        columns.add(titleCol);
        columns.add(authorCol);
        columns.add(isbnCol);
        columns.add(genreCol);
        columns.add(quantityCol);
        columns.add(priceCol);
        columns.add(initialerCol);
        return columns;
    }

}
