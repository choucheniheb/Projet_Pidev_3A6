/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.circuit;
import entities.evenements;
import entities.planning;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.circuitCrud;
import services.evenementCrud;
import services.planningCrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Ajouter_PlanningController implements Initializable {

     @FXML
    private ChoiceBox<Integer> idcir;
    @FXML
    private ChoiceBox<Integer> idev;
    @FXML
    private TextField resto;
    @FXML
    private TextField hotel;
    @FXML
    private TextField emplace;
    @FXML
    private Button Ajouter_Planning;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    
    
    Connection cnx2 ;
    
    public Ajouter_PlanningController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    idcir.getItems().addAll(recidCircuit());
    idev.getItems().addAll(recidEvenement());

Ajouter_Planning.setOnAction(p  -> {

            if (  "".equals(idcir.getValue())  || "".equals (idev.getValue())  || "".equals (resto.getText())  || "".equals (hotel.getText())  || "".equals (emplace.getText()) )   {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Veuillez remplir tous les champs correctement!");

                alert.show();

            } else {

                try {

                    planningCrud ajout = new planningCrud();
                   planning pl = new planning(Integer.parseInt(idcir.getValue().toString()), Integer.parseInt(idev.getValue().toString()), resto.getText(), hotel.getText(), emplace.getText());
                    //verification de l'unicite par le nom de la demande le budget la description et la date limite 
                    String sql = "SELECT * FROM planning WHERE id_circuit = ? AND id_evenement = ? AND resto = ?  AND hotel = ? AND emplacement = ? ";
                    PreparedStatement stmt;
                    stmt = cnx2.prepareStatement(sql);
                    stmt.setInt(1, pl.getId_circuit());
                    stmt.setInt(2, pl.getId_evenement());
                    stmt.setString(3, pl.getResto());
                    stmt.setString(4, pl.getHotel()); 
                    stmt.setString(5, pl.getEmplacement());
                    
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Planning existe deja!");

                        alert.show();
                        return; 
                    } else {

                        ajout.ajouter(pl);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Planning ajoutée avec succés!");

                        alert.show();

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Ajouter_CircuitController.class.getName()).log(Level.SEVERE, null, ex);
                }

//                    // si elle n'existe pas on procede a l ajout 
//                    ajoutDemandeService.ajouterDemandeService(ds);
//
//            Publication p = new Publication(Integer.parseInt(proprietaire.getText()), libelle.getText(),datePub.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Description.getText(),cat.getValue());
//
//            PublicationCrud test = new PublicationCrud() ;
            }
        }
        );
        
        
        
        
        
        Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Gestion_planning.fxml"));
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
    
    
     private List<Integer> recidCircuit(){
         circuitCrud rs = new circuitCrud();
         List<Integer> id=new ArrayList<>();
         List<circuit> circuit = rs.afficher();
         for(circuit r: circuit){
             id.add(r.getId_circuit());
         }
        return id;
    }
     
     private List<Integer> recidEvenement(){
         evenementCrud rs = new evenementCrud();
         List<Integer> id=new ArrayList<>();
         List<evenements> evenements = rs.afficher();
         for(evenements r: evenements){
             id.add(r.getId_evenement());
         }
        return id;
    }
     
     
    
}
