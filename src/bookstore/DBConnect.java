/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

//import java.beans.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import java.sql.ResultSet;

public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    String firstName;

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

    public String getFirstName() {
        try {
            String query = "select firstName from employees";
            while (rs.next()) {
                firstName = rs.getString("firstName");
            }
        } catch (Exception ex) {
            System.out.println(ex + "LLLLLLL");
        }
        return firstName;
    }

    /**
     *
     * @return
     */
    public ObservableList<Employee> getData() {
        ObservableList<Employee> data = FXCollections.observableArrayList();
        try {
            String query1 = "select lastn, first,adress,email,phone,initialer,salary from employees";
            rs = st.executeQuery(query1);
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
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    public void setData( String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        try {
           
            st.executeUpdate("insert into employees(lastn,first,adress,email,phone,initialer,salary)"
                    + "  values("+"'" + s2 +"'"+ "," +"'"+ s3+"'" + "," +"'"+ s4 +"'"+ "," +"'"+ s5+"'" + "," +"'"+ s6+"'" + "," +"'"+ s7+"'" + "," +"'"+ s8+"'" + ")");

        } catch (Exception ex) {
            System.out.println(ex + "JJJ");
        }

    }

    public void deleteData(String initialer) {
        try {
          String query="delete from employees where initialer= ";
          st.executeUpdate("delete from employees where initialer= "+"'"+initialer+"'");
        } catch (Exception ex) {
            System.out.println(ex + "222");
        }
    }

}
