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
//import java.sql.ResultSet;

public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/book_store?user=manager&password=manager_pass");
            
            st = (Statement) con.createStatement();
        } catch (SQLException ex) {
            System.out.println("Error: mysql" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: class not found:"+ ex);
        }
    }

    public void getData() {
        try {
            String query = "insert into employees values (1,2,3,4,5,6,7,8)";
          //  st.executeUpdate("insert into employees(employee_id,lastName,FirstName,adress,email,phone,jobTitle,salary)"
              //    + "  values(" + 2 + "," + 2 + "," + 3 + "," + 4 + "," + 5 + "," + 6 + "," + 7 + "," + 9 + ")");
           // rs = st.executeQuery(query);
          /*  while (rs.next()) {
                String id = rs.getString("child_id");
                String name = rs.getString("child_name");
                
                
                String country = rs.getString("country");
                System.out.println("ID: " + id + " Name: " + name + " Country: " + country + " Adress: ");
            }*/

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void setData(String s1,String s2,String s3,String s4,String s5, String s6,String s7,String s8){
         try {
            String query = "insert into employees values (1,2,3,4,5,6,7,8)";
            st.executeUpdate("insert into employees(employee_id,lastName,FirstName,adress,email,phone,jobTitle,salary)"
                  + "  values(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + "," + s7 + "," + s8 + ")");
        

        } catch (Exception ex) {
            System.out.println(ex+"JJJ");
        }
       
        
    }

}
