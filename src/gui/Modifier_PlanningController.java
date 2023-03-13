/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.evenements;
import entity.planning;
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
import service.planningCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Modifier_PlanningController implements Initializable {

    @FXML
    private TextField idcir;
    @FXML
    private TextField idev;
    @FXML
    private TextField resto;
    @FXML
    private TextField hotel;
    @FXML
    private TextField emplace;
    @FXML
    private Button Modifier_Planning;
    private Button Retour;
    @FXML
    private Text idplanning;
    @FXML
    private Text id_planning;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;
    @FXML
    private Button Retour1;

    /**
     * Initializes the controller class.
     */
 
    
    
public void setplanning(planning c) {
        idcir.setText(Integer.toString(c.getId_circuit()));
        idev.setText(Integer.toString(c.getId_evenement()));
        hotel.setText(c.getHotel());
       resto.setText(c.getResto());
       emplace.setText(c.getEmplacement());
       idplanning.setText(Integer.toString(c.getId_planning()));

    
       

    }
            
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        Modifier_Planning.setOnAction(event -> {
        
        try {
            planningCrud ps = new planningCrud();
            
           
           
         ps.modifier(Integer.parseInt(idcir.getText()), Integer.parseInt(idev.getText()), resto.getText(), hotel.getText(), emplace.getText() , Integer.parseInt(idplanning.getText()));
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Planning.fxml"));
            Parent root = loader.load();
            Scene scene = Modifier_Planning.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        });
        
        
        Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Afficher_Planning.fxml"));
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
      