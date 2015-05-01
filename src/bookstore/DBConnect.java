/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

//import java.beans.Statement;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import java.sql.ResultSet;

public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs, rs1;
    String firstName, title;

    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/book_store?user=manager&password=manager_pass");

            st = (Statement) con.createStatement();
        } catch (SQLException ex) {
            System.out.println("Error: mysql" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: class not found:" + ex);
        }
    }
//EMPLOYEE TABEL COMMANDS

    public String getFirstName() {
        try {
            String query = "select firstName from employees";
            rs = st.executeQuery(query);
            while (rs.next()) {
                firstName = rs.getString("firstName");
            }
        } catch (Exception ex) {
            System.out.println(ex + "LLLLLLL");
        }
        return firstName;
    }

    public ObservableList<Employee> getDataEmployees() {
        ObservableList<Employee> data = FXCollections.observableArrayList();
        try {
            String query = "select lastn, first,adress,email,phone,initialer,salary from employees";
            String query1 = "select lastn, first,adress,email,phone,initialer from employees";
            if (DataStorage.getDataStorage().getLogAsManager() == true) {
                rs = st.executeQuery(query);
                while (rs.next()) {
                    String lastname = rs.getString("lastn");
                    String first = rs.getString("first");
                    String adress = rs.getString("adress");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String initialer = rs.getString("initialer");
                    String salary = rs.getString("salary");

                    data.addAll(new Employee(first, lastname, adress, email, phone, initialer, salary));

                }

            } else {
                rs = st.executeQuery(query1);
                while (rs.next()) {
                    String lastname = rs.getString("lastn");
                    String first = rs.getString("first");
                    String adress = rs.getString("adress");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String initialer = rs.getString("initialer");
                    String salary ="$$$";
                    data.addAll(new Employee(first, lastname, adress, email, phone, initialer, salary));
                }
            }
            }catch (Exception ex) {
            System.out.println(ex);
        }
            return data;
        }

    

    public void setData(String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        try {

            st.executeUpdate("insert into employees(lastn,first,adress,email,phone,initialer,salary)"
                    + "  values(" + "'" + s2 + "'" + "," + "'" + s3 + "'" + "," + "'" + s4 + "'" + "," + "'" + s5 + "'" + "," + "'" + s6 + "'" + "," + "'" + s7 + "'" + "," + "'" + s8 + "'" + ")");

        } catch (Exception ex) {
            System.out.println(ex + "JJJ");
        }

    }

    public void deleteData(String initialer) {
        try {

            st.executeUpdate("delete from employees where initialer= " + "'" + initialer + "'");
        } catch (Exception ex) {
            System.out.println(ex + "222");
        }
    }

    //BOOK TABEL COMMANDS
    public void setBook(String s2, String s3, String s4, String s5, Integer s6, Integer s7, String s8) {
        try {

            st.executeUpdate("insert into books(title,author,ISBN,genre,quantity,price,initialer)"
                    + "  values(" + "'" + s2 + "'" + "," + "'" + s3 + "'" + "," + "'" + s4 + "'" + "," + "'" + s5 + "'" + "," + "'" + s6 + "'" + "," + "'" + s7 + "'" + "," + "'" + s8 + "'" + ")");

        } catch (Exception ex) {
            System.out.println(ex + "JJJ");
        }

    }

    public ObservableList<Book> getDataBooks() {
        ObservableList<Book> data = FXCollections.observableArrayList();
        try {
            String query = "select title,author,ISBN,genre,quantity,price,initialer from books";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String ISBN = rs.getString("isbn");
                String genre = rs.getString("genre");
                Integer quantity = rs.getInt("quantity");
                Integer price = rs.getInt("price");
                String initialer = rs.getString("initialer");

                data.addAll(new Book(title, author, ISBN, genre, quantity, price, initialer));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    public void returnBook(String isbn) {
        try {

            st.executeUpdate("update books set quantity=quantity+(1)where isbn= " + "'" + isbn + "'");
        } catch (Exception ex) {
            System.out.println(ex + "222");
        }
    }

    public void sellBook(String isbn) {
        try {

            st.executeUpdate("update books set quantity=quantity-(1)where isbn= " + "'" + isbn + "'");
        } catch (Exception ex) {
            System.out.println(ex + "222");
        }
    }

    public ObservableList<Book> searchISBN(String isbn) {
        ObservableList<Book> data = FXCollections.observableArrayList();
        try {
            rs = st.executeQuery("select title,author,ISBN,genre,quantity,price,initialer from books where ISBN like " + "'" + isbn + "'");
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String ISBN = rs.getString("isbn");
                String genre = rs.getString("genre");
                Integer quantity = rs.getInt("quantity");
                Integer price = rs.getInt("price");
                String initialer = rs.getString("initialer");

                data.addAll(new Book(title, author, ISBN, genre, quantity, price, initialer));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;

    }

    public ObservableList<Book> searchTitle(String title1) {
        ObservableList<Book> data = FXCollections.observableArrayList();
        try {
            rs = st.executeQuery("select title,author,ISBN,genre,quantity,price,initialer from books where title like " + "'" + title1 + "'");
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String ISBN = rs.getString("isbn");
                String genre = rs.getString("genre");
                Integer quantity = rs.getInt("quantity");
                Integer price = rs.getInt("price");
                String initialer = rs.getString("initialer");

                data.addAll(new Book(title, author, ISBN, genre, quantity, price, initialer));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;

    }

    public ObservableList<Book> searchAuthor(String author1) {
        ObservableList<Book> data = FXCollections.observableArrayList();
        try {
            rs = st.executeQuery("select title,author,ISBN,genre,quantity,price,initialer from books where author like " + "'" + author1 + "'");
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String ISBN = rs.getString("isbn");
                String genre = rs.getString("genre");
                Integer quantity = rs.getInt("quantity");
                Integer price = rs.getInt("price");
                String initialer = rs.getString("initialer");

                data.addAll(new Book(title, author, ISBN, genre, quantity, price, initialer));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;

    }

    public int getPriceSellOperation(String isbn) {
        int price = 0;
        try {
            rs = st.executeQuery("select price from books where ISBN like " + "'" + isbn + "'");
            while (rs.next()) {
                price = rs.getInt("price");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return price;
    }

    public String getTitleSellOperation(String isbn) {
        String title = null;
        try {
            rs = st.executeQuery("select title from books where ISBN like " + "'" + isbn + "'");
            while (rs.next()) {
                title = rs.getString("title");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return title;
    }
}
