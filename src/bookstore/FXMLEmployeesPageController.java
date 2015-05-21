/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iuliu
 */
public class FXMLEmployeesPageController implements Initializable {

    @FXML
    private TableView<Employee> tableEmployees = new TableView<Employee>();
    @FXML
    private TextField textLastName;
    @FXML
    private TextField textFirstName;
    @FXML
    private TextField textAdress;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textPhone;
    @FXML
    private TextField textInitials;
    @FXML
    private TextField textSalary;
    @FXML
    private Button buttonRegister;
    @FXML
    private Label labelError;
    @FXML
    private Button buttonClose;

    @FXML
    private TextArea textViewActivity;
    private final DBConnection dbConnection = new DBConnection();
    private boolean isManager;

    @FXML
    private Button changeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelError.setText(null);
        buttonRegister.setVisible(false);
        changeButton.setVisible(false);
        textLastName.setVisible(false);
        textFirstName.setVisible(false);
        textAdress.setVisible(false);
        textEmail.setVisible(false);
        textPhone.setVisible(false);
        textInitials.setVisible(false);
        textSalary.setVisible(false);
        tableEmployees.getColumns().addAll(Employee.getColumn(tableEmployees));
        tableEmployees.setItems(dbConnection.getDataEmployees());
        labelError.setTextFill(Color.RED);
        isManager = DataStorage.getDataStorage().getLogAsManager();

    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        labelError.setText(null);
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 630, 565);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger(FXMLMainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleQuitButton(ActionEvent event) {

        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleNewButton(ActionEvent event) {
        labelError.setText(null);
        if (isManager == true) {
            changeButton.setVisible(false);
            buttonRegister.setVisible(true);
            textLastName.setVisible(true);
            textFirstName.setVisible(true);
            textAdress.setVisible(true);
            textEmail.setVisible(true);
            textPhone.setVisible(true);
            textInitials.setVisible(true);
            textSalary.setVisible(true);
        } else {
            labelError.setText("Access denied!");
        }
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        labelError.setText(null);
        if (isManager == true) {
            buttonRegister.setVisible(false);
            changeButton.setVisible(false);
            textLastName.setVisible(false);
            textFirstName.setVisible(false);
            textAdress.setVisible(false);
            textEmail.setVisible(false);
            textPhone.setVisible(false);
            textInitials.setVisible(false);
            textSalary.setVisible(false);
            String initialer = tableEmployees.getSelectionModel().getSelectedItem().getInitialer();
            if ("manager".equals(initialer)) {
                labelError.setText("Is not possible to delete this row!");
            } else {
                System.out.println(initialer);
                dbConnection.deleteData(initialer);
                dbConnection.deleteIdUser(initialer);
                tableEmployees.setItems(dbConnection.getDataEmployees());
            }
        } else {
            labelError.setText("Access denied");
        }
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) {
        labelError.setText(null);
        if (isManager == true) {
            try {
                String lastName = textLastName.getText();
                textLastName.clear();
                String firstName = textFirstName.getText();
                textFirstName.clear();
                String adress = textAdress.getText();
                textAdress.clear();
                String email = textEmail.getText();
                textEmail.clear();
                String temporaryPhone = textPhone.getText();
                int phone = Integer.valueOf(temporaryPhone);
                textPhone.clear();
                String initials = textInitials.getText();
                textInitials.clear();
                String temporarySalary = textSalary.getText();
                int salary = Integer.valueOf(temporarySalary);
                textSalary.clear();

                dbConnection.setNewId(initials);
                int controller = dbConnection.setDataEmployee(lastName, firstName, adress, email, phone, initials, salary);
                if (controller == 1) {
                    labelError.setText("Initials exist!!");

                } else {
                    tableEmployees.setItems(dbConnection.getDataEmployees());
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLPassword.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Password");
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception ex) {
                        Logger.getLogger(FXMLBooksPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception ex) {
                labelError.setText("Is not a integer!");
            }
        } else {
            labelError.setText("Access denied");
        }
    }

    @FXML
    private void handlePrintButton(ActionEvent event) {
        labelError.setText(null);

        if (isManager == true) {
            buttonRegister.setVisible(false);
            changeButton.setVisible(true);
            textLastName.setVisible(true);
            textFirstName.setVisible(true);
            textAdress.setVisible(true);
            textEmail.setVisible(true);
            textPhone.setVisible(true);
            textSalary.setVisible(true);
        } else {
            labelError.setText("Acces denied!");
        }
    }

    @FXML
    private void handleChangeButton(ActionEvent event) {
        labelError.setText(null);
        if (isManager == true) {
            try {
                String lastName = textLastName.getText();
                textLastName.clear();
                String firstName = textFirstName.getText();
                textFirstName.clear();
                String adress = textAdress.getText();
                textAdress.clear();
                String email = textEmail.getText();
                textEmail.clear();
                String temporaryPhone = textPhone.getText();
                int phone = Integer.valueOf(temporaryPhone);
                textPhone.clear();
                String temporarySalary = textSalary.getText();
                int salary = Integer.valueOf(temporarySalary);
                textSalary.clear();
                String initialer = tableEmployees.getSelectionModel().getSelectedItem().getInitialer();

                dbConnection.updateEmployee(initialer, lastName, firstName, adress, email, phone, salary);
                tableEmployees.setItems(dbConnection.getDataEmployees());
            } catch (Exception ex) {
                System.out.println("Is not a integer!");
                labelError.setText("Is not a integer!");
            }
        } else {
            labelError.setText("Acces denied!");
        }
    }

    @FXML
    public void handleViewActivityButton(ActionEvent event) {
        textViewActivity.clear();
        if (isManager == true) {
            String initialer = tableEmployees.getSelectionModel().getSelectedItem().getInitialer();
            for (int i = 0; i < dbConnection.controllEmployeeActivity(initialer).size(); i++) {
                textViewActivity.appendText(dbConnection.controllEmployeeActivity(initialer).get(i) + "\n");
            }

        } else {
            labelError.setText("Acces denied!");
        }
    }

    @FXML
    public void graphicEmployeesHandler(ActionEvent event) {
        if (isManager == true) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLGraphicEmployees.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Graphic of employees activity");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ex) {
                Logger.getLogger(FXMLBooksPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            labelError.setText("Acces denied!");
        }
    }
}
