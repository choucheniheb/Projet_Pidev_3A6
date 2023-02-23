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
public class CivilisationajouteraffichageController implements Initializable {

    @FXML
    private Button ajcivibtn;
    @FXML
    private Button affcivibtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void swFormajcivi(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Civilisationcrud.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) ajcivibtn.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    

    @FXML
    private void swFormaffcivi(ActionEvent event) {

      try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Affichagecivilisation.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) affcivibtn.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    }
    

