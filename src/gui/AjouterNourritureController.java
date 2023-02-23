/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class AjouterNourritureController implements Initializable {

    @FXML
    private Button goToNourrituresBtn;
    @FXML
    private Button goTocivilisationbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void swFormN(ActionEvent event) {
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/NourritureAjoutetaffichage.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) goToNourrituresBtn.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void swFormC(ActionEvent event) {
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Civilisationajouteraffichage.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) goTocivilisationbtn.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}
