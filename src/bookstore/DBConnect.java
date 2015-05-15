/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

//import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import java.sql.ResultSet;

public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String firstName, pass, lastNameEmployee, lastNameClient;
    private boolean isManager;

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

    // LOGIN TABLE COMMANDS************************************************************************************************************
    public String getPass(String id_user) {
        try {
            String query = "select pass from login where id_user=" + "'" + id_user + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                pass = rs.getString("pass");

            }
        } catch (Exception ex) {
            System.out.println(ex + "LLLLLLL");
        }
        return pass;

    }

    public Boolean getIsManagerValues(String id_user) {
        try {
            String query = "select isManager from login where id_user=" + "'" + id_user + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                isManager = rs.getBoolean("isManager");

            }
        } catch (Exception ex) {
            System.out.println(ex + "LLLLLLL");
        }
        return isManager;
    }

    public void setNewId(String id) {
        try {
            st.executeUpdate("insert into login(id_user) values(" + "'" + id + "'" + ")");
        } catch (Exception ex) {
            System.out.println(ex + "id id id");
        }
    }

    public void setPassword(String pass, String id_user) {

        try {

            st.executeUpdate("update login set pass=" + "'" + pass + "'" + ",isManager= false where id_user=" + "'" + id_user + "'");
        } catch (Exception ex) {
            System.out.println(ex + "  pass  pass");
        }
    }

    public void deleteIdUser(String id_user) {
        try {

            st.executeUpdate("delete from login where id_user= " + "'" + id_user + "'");
        } catch (Exception ex) {
            System.out.println(ex + "Delete user");
        }
    }

    /*   public String editWelcome(String id_user) {
     String name = null;
     try {
     st.executeQuery("select " + "'" + id_user + "'");
     } catch (Exception ex) {
     System.out.println(ex + "Delete user");
     }
     return name;
     }*/
//EMPLOYEE TABEL COMMANDS*****************************************************************************************************************
    public String getFirstNameLastEmployee() {
        try {
            String query = "select first from employees,login  where employees.initialer=login.id_user&& id_user=\n"
                    + "(select id_user from login order by id desc limit 1);";
            rs = st.executeQuery(query);
            while (rs.next()) {
                firstName = rs.getString("first");
            }
        } catch (Exception ex) {
            System.out.println(ex + "first name last employee");
        }
        return firstName;
    }

    public String getLastNameEmployee(String initialer) {
        try {
            String query = "select lastn from employees where initialer= '" + initialer + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                lastNameEmployee = rs.getString("lastn");
            }
        } catch (Exception ex) {
            System.out.println(ex + "last name");
        }
        return lastNameEmployee;
    }

    public void updateEmployee(String initialer, String lastName, String firstName,
            String adress, String email, String phone, String salary) {
        try {

            st.executeUpdate("update employees"
                    + "  set lastn =" + "'" + lastName + "'" + ",first = " + "'" + firstName + "'"
                    + ",adress =" + "'" + adress + "'" + ",email =" + "'" + email + "'" + ",phone =" + "'" + phone + "'"
                    + ",salary =" + "'" + salary + "'" + " where initialer=" + "'" + initialer + "'");

        } catch (Exception ex) {
            System.out.println(ex + "JJJ");
        }

    }

    protected ObservableList<Employee> getDataEmployees() {
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
                    String initialer = "???";
                    String salary = "$$$";
                    data.addAll(new Employee(first, lastname, adress, email, phone, initialer, salary));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    public int setDataEmployee(String lastName, String firstName, String adress, String email,
            String phone, String initials, String salary) {
        int controller;
        try {

            st.executeUpdate("insert into employees(lastn,first,adress,email,phone,initialer,salary)"
                    + "  values(" + "'" + lastName + "'" + "," + "'" + firstName + "'" + "," + "'"
                    + adress + "'" + "," + "'" + email + "'" + "," + "'" + phone + "'" + "," + "'" + initials + "'" + "," + "'"
                    + salary + "'" + ")");
            controller = 0;
        } catch (Exception ex) {
            System.out.println(ex + "JJJ");
            controller = 1;
        }
        return controller;

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
            System.out.println(ex + "sell book");
        }
    }

    public void changeBookStatus(String isbn, int quantity, int price) {
        try {
            st.executeUpdate("update books set quantity='" + quantity + "',price='" + price + "'" + "where isbn= " + "'" + isbn + "'");
        } catch (Exception ex) {
            System.out.println(ex + "update status");
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

    //Client TABLE COMMNAD *********************************************************************************************************************
    public ObservableList<Client> getDataClients() {
        ObservableList<Client> data = FXCollections.observableArrayList();
        try {
            String query = "select id,lastn, first,adress,email,phone,total,percent from client";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String lastn = rs.getString("lastn");
                String first = rs.getString("first");
                String adress = rs.getString("adress");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int total = rs.getInt("total");
                int percent = rs.getInt("percent");

                data.addAll(new Client(id, lastn, first, adress, email, phone, total, percent));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    public void setNewClient(int id, String lastName, String firstName, String adress, String email, String phone) {
        try {

            st.executeUpdate("insert into client(id,lastn,first,adress,email,phone,total,percent)"
                    + "  values(" + "'" + id + "'" + "," + "'" + lastName + "'" + "," + "'" + firstName + "'" + ","
                    + "'" + adress + "'" + "," + "'" + email + "'" + "," + "'" + phone + "', 0,2)");

        } catch (Exception ex) {
            System.out.println(ex + "set client error");
        }

    }

    public void setClientBook(int i1, String isbn) {
        try {

            st.executeUpdate("insert into client_buys_book(id,isbn)"
                    + "  values(" + "'" + i1 + "'" + "," + "'" + isbn + "'" + ")");

        } catch (Exception ex) {
            System.out.println(ex + "client buys");
        }

    }

    public void setEmployeeBook(String isbn, String initialer) {
        try {

            st.executeUpdate("insert into employee_sells_book(ISBN,initialer)"
                    + "  values(" + "'" + isbn + "'" + "," + "'" + initialer + "'" + ")");

        } catch (Exception ex) {
            System.out.println(ex + "employee sells");
        }

    }

    public ArrayList<String> controllEmployeeActivity(String initialer) {
        ArrayList<String> array = new ArrayList();

        try {
            String query = "select title from books, employee_sells_book where books.ISBN=employee_sells_book.ISBN"
                    + " && employee_sells_book.initialer=" + "'" + initialer + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String b = rs.getString("title");
                array.add(b);
            }
        } catch (Exception ex) {
            System.out.println("controll fell");
        }
        return array;
    }

    public ArrayList<String> controllClientBooks(int idClient) {
        ArrayList<String> array = new ArrayList();

        try {
            String query = "select title from books, client_buys_book where books.ISBN=client_buys_book.isbn"
                    + " && client_buys_book.id=" + "'" + idClient + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String b = rs.getString("title");
                array.add(b);
            }
        } catch (Exception ex) {
            System.out.println(ex + "controll books fell");
        }
        return array;
    }

    public String getLastNameClient(int id) {
        try {
            String query = "select lastn from client where id= '" + id + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                lastNameClient = rs.getString("lastn");
            }
        } catch (Exception ex) {
            System.out.println(ex + "last name");
        }
        return lastNameClient;
    }

    public int getPercent(int idClient) {
        int percent = 0;
        try {
            rs = st.executeQuery("select percent from client where id=" + "'" + idClient + "'");
            while (rs.next()) {
                percent = rs.getInt("percent");
            }
        } catch (Exception ex) {
            System.out.println(ex + " percent fell");
        }
        return percent;
    }
    public int getAmountClient(int idClient){
        int amount = 0;
        try {
            rs = st.executeQuery("select total from client where id=" + "'" + idClient + "'");
            while (rs.next()) {
                amount = rs.getInt("total");
            }
        } catch (Exception ex) {
            System.out.println(ex + " amount fell");
        }
        return amount;
    }
     public void modifyPercent(int idClient) {
       
        try {
             st.executeUpdate("update client set percent=percent+(5) where id=" + "'" + idClient + "'");
            
        } catch (Exception ex) {
            System.out.println(ex + " modify percent fell");
        }
        
    }

    public void setTotal(int idClient, int amount) {
        try {
            st.executeUpdate("update client set total=total+(" + "'" + amount + "'" + ")where id= " + "'" + idClient + "'");
        } catch (Exception ex) {
            System.out.println(ex + "set total");
        }
    }

    public void updateClient(int id, String lastName, String firstName,
            String adress, String email, String phone) {
        try {

            st.executeUpdate("update client"
                    + "  set lastn =" + "'" + lastName + "'" + ",first = " + "'" + firstName + "'"
                    + ",adress =" + "'" + adress + "'" + ",email =" + "'" + email + "'" + ",phone =" + "'" + phone + "'"
                    + " where id=" + id);

        } catch (Exception ex) {
            System.out.println(ex + "update client");
        }

    }
}
