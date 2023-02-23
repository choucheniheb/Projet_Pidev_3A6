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
public class NourritureAjoutetaffichageController implements Initializable {

    @FXML
    private Button ajouternourriture1;
    @FXML
    private Button AffichageNour1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void swFormAjN(ActionEvent event) {
        if (event.getSource() == ajouternourriture1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Nourriturescrud.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage = (Stage) ajouternourriture1.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }else if(event.getSource() == AffichageNour1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Affichagenourritures.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage = (Stage) AffichageNour1.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}
