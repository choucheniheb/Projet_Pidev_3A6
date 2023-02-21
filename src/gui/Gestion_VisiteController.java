/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Gestion_VisiteController implements Initializable {

    @FXML
    private Button Ajouter_une_visite;
    @FXML
    private Button Afficher_les_visites;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        Ajouter_une_visite.setOnAction(Ajouter_une_visite -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Ajouter_visite.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Ajouter_une_visite.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        Afficher_les_visites.setOnAction(Afficher_les_visites -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Afficher_Visites.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Afficher_les_visites.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        Retour.setOnAction(retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Accueil_Circuit_Evenement.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) retour.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        
        
    }    
    
}
