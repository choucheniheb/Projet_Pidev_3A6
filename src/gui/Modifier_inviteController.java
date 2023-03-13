/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.evenements;
import entity.invites;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.evenementCrud;
import service.inviteCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Modifier_inviteController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField type;
    @FXML
    private Button modifier_Invité;
    @FXML
    private Button Retour;
    @FXML
    private Text idinvite;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    /**
     * Initializes the controller class.
     */
    public void setinvite(invites c) {
        idinvite.setText(Integer.toString(c.getId_invite()));
        nom.setText(c.getNom_invite());
        prenom.setText(c.getPrenom_invite());
        type.setText(c.getType_invite());
        
        
        
       

    }
            
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        modifier_Invité.setOnAction(event -> {
        
        try {
            inviteCrud ps = new inviteCrud(); 
          ps.modifier(nom.getText(), prenom.getText(), type.getText(), Integer.parseInt(idinvite.getText()) );
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Invites.fxml"));
            Parent root = loader.load();
            
            Scene scene = modifier_Invité.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Modifier_inviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        });
        
        
        Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Afficher_Invites.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Retour.getSource()).getScene().getWindow();
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
    
    
   
    
    
    
    
    }
