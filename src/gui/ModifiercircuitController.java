/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.circuit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.circuitCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifiercircuitController implements Initializable {

    @FXML
    private TextField Point_de_depart;
    @FXML
    private TextField Nbr_Place;
    @FXML
    private TextField description;
    @FXML
    private TextField Nbr_Jour;
    @FXML
    private TextField Id_Uti;
    @FXML
    private TextField Nom_Cir;
    @FXML
    private Button Retour;
    @FXML
    private DatePicker Date_d;
    @FXML
    private DatePicker Date_F;
    @FXML
    private AnchorPane modifier;
    @FXML
    private Text id_circuit;
    @FXML
    private Button modifierB;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        modifierB.setOnAction(event -> {
        
        try {
            circuitCrud ps = new circuitCrud();
            
           ps.modifier(Point_de_depart.getText(), Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), Date_F.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), Integer.parseInt(Nbr_Place.getText()), description.getText(), Integer.parseInt(Nbr_Jour.getText()) , description.getText(), Integer.parseInt(Id_Uti.getText()), Nom_Cir.getText(), Integer.parseInt(id_circuit.getText()));
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Circuit.fxml"));
            Parent root = loader.load();
            
            Scene scene = modifier.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifiercircuitController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    public void setcircuit(circuit c) {
        Point_de_depart.setText(c.getPoint_depart_circuit());
         Nbr_Place.setText(Integer.toString(c.getNbr_place_dispo()));
          description.setText(c.getDescription_circuit());
           Nbr_Jour.setText(Integer.toString(c.getNbr_jour_circuit()));
            Id_Uti.setText(Integer.toString(c.getId_utilisateur()));
             Nom_Cir.setText(c.getNom_circuit());
                 Date_d.setValue(LocalDate.parse(c.getDate_debut_circuit(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    Date_F.setValue(LocalDate.parse(c.getDate_fin_circuit(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                id_circuit.setText(Integer.toString(c.getId_circuit()));

    }
    
    
    
    
//    
//    private void modifierB(ActionEvent event) {
//        
//        System.out.println("test");
//        try {
//            circuitCrud ps = new circuitCrud();
//            
//           ps.modifier(Point_de_depart.getText(), Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), Date_F.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), Integer.parseInt(Nbr_Place.getText()), description.getText(), Integer.parseInt(Nbr_Jour.getText()) , description.getText(), Integer.parseInt(Id_Uti.getText()), Nom_Cir.getText(), Integer.parseInt(id_circuit.getText()));
//            System.out.println("test2");
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Circuit.fxml"));
//            Parent root = loader.load();
//            
//            Scene scene = modifier.getScene();
//            scene.setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(ModifiercircuitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }
    
    
    
}
