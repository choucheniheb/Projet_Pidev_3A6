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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Accueil_Circuit_EvenementController implements Initializable {

    @FXML
    private Button Gestion_Circuit;
    @FXML
    private Button Gestion_Invite;
    @FXML
    private Button Gestion_Planning;
    @FXML
    private Button Gestion_Evenement;
    @FXML
    private Button Gestion_Visite;
    @FXML
    private Button Information;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Gestion_Circuit.setOnAction(Gestion_Circuit -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestion_Circuit.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Gestion_Circuit.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        Gestion_Invite.setOnAction(Gestion_Invite -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestion_Invite.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Gestion_Invite.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        Gestion_Planning.setOnAction(Gestion_Planning -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestion_planning.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Gestion_Planning.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        
        Gestion_Evenement.setOnAction(Gestion_Evenement -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestion_Evenement.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Gestion_Evenement.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        
        Gestion_Visite.setOnAction(Gestion_Visite -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestion_Visite.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Gestion_Visite.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
                Information.setOnAction(Information -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RandomTunisiaFacts.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Information.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });
        
        
        
        

        

        

       

    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }

    @FXML
    private void swAcVis(ActionEvent event) {
    }
        
    }    
    

