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

    public DataStorage() {
    }

    public static DataStorage getDataStorage() {
        if (myDataStorage == null) {
            myDataStorage = new DataStorage();
        }
        return myDataStorage;
    }
    public void setLogAsManager(boolean value){
    this.logAsManager=value;
    }
    public boolean getLogAsManager(){
    return logAsManager;
    }
}