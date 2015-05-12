/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.Serializable;

/**
 *
 * @author Iuliu
 */
public class User implements Serializable {
    private String userName;
    private String passUser;
    public User(){
    }
    public User(String userNAme,String passUser){
    this.userName=userName;
    this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }
}
