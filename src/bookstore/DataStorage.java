/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

/**
 *
 * @author Iuliu
 */
public class DataStorage {

    private static DataStorage myDataStorage;
    private boolean logAsManager;
    private String id_user;
    private String loginUser;

    public DataStorage() {
    }

    public static DataStorage getDataStorage() {
        if (myDataStorage == null) {
            myDataStorage = new DataStorage();
        }
        return myDataStorage;
    }

    public void setLogAsManager(boolean value) {
        this.logAsManager = value;
    }

    public boolean getLogAsManager() {
        return logAsManager;
    }
    public void setId_User(String id_user){
    this.id_user=id_user;
    }
    public String getId_user(){
    return id_user;
    }
    public void setLoginUser(String loginUser){
    this.loginUser=loginUser;
    }
    public String getLoginUser(){
    return loginUser;
    }
}
