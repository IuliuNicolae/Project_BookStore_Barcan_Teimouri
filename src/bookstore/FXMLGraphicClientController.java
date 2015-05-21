/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLGraphicClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChartClient;
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    private int idClient;
    private int temporaryCountGenre;
    private String temporaryGenre;
    DBConnection dbConnection = new DBConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
idClient=DataStorage.getDataStorage().getIdClient();
System.out.println("idClient= "+idClient);
        for (int i = 0; i < dbConnection.getFavoriteGenre(idClient).size(); i++) {
            temporaryGenre = dbConnection.getFavoriteGenre(idClient).get(i);
            System.out.println(temporaryGenre);
            temporaryCountGenre = dbConnection.getCountGenre(idClient, temporaryGenre);
            data.add(new PieChart.Data(temporaryGenre, temporaryCountGenre));
        }
        pieChartClient.setData(data);
    }

}
