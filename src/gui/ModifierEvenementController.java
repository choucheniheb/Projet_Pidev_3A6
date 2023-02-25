/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenements;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.evenementCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField type;
    @FXML
    private TextField lieux;
    @FXML
    private TextField prix;
    @FXML
    private TextField invite;
    @FXML
    private TextField utilisateur;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date;
    @FXML
    private Button Retour;
    @FXML
    private Button modifierB;
    @FXML
    private Text id_evenement;
    @FXML
    private AnchorPane modifier;

    /**
     * Initializes the controller class.
     */
     
    public void setevenement(evenements c) {
        id_evenement.setText(Integer.toString(c.getId_evenement()));
        titre.setText(c.getTitre_evenement());
        type.setText(c.getType_evenement());
        date.setValue(LocalDate.parse(c.getDate_evenement(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lieux.setText(c.getLieux_evenement());
        prix.setText(Double.toString(c.getPrix_evenement()));
        invite.setText(Integer.toString(c.getId_invite()));
        description.setText(c.getDescription_evenement());
        utilisateur.setText(Integer.toString(c.getId_utilisateur()));

        
        
       

    }
            
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        modifierB.setOnAction(event -> {
        
        try {
            evenementCrud ps = new evenementCrud();
            
           ps.modifier(titre.getText(), type.getText(), date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), lieux.getText(), Double.parseDouble(prix.getText()), Integer.parseInt(invite.getText()), description.getText(), Integer.parseInt(utilisateur.getText()), Integer.parseInt(id_evenement.getText()));
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Evenement.fxml"));
            Parent root = loader.load();
            
            Scene scene = modifier.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        });
        
        
        Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Afficher_Circuit.fxml"));
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
    
    
   
    
    
    
    
    }
       
    

