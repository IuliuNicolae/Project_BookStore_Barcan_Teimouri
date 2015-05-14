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
    private String loginUser;
    private int amount, percent, idClient;

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

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }
}
