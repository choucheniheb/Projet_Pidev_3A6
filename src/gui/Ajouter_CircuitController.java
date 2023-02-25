/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.circuit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.circuitCrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Ajouter_CircuitController implements Initializable {

    @FXML
    private Button Valider;
    @FXML
    private Button Retour;
    @FXML
    private TextField Point_de_depart;
    @FXML
    private DatePicker Date_F;
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
    private DatePicker Date_d;

    /**
     * Initializes the controller class.
     */
    
    
     Connection cnx2 ;
    
    public Ajouter_CircuitController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
        Valider.setOnAction(c  -> {

            if ( "".equals(Date_F.getValue()) || "".equals(Point_de_depart.getText())  || "".equals (Nbr_Place.getText())  || "".equals (description.getText())  || "".equals (Nbr_Jour.getText())  || "".equals (Nom_Cir.getText())  ||  "".equals(Date_d.getValue())  || "".equals (Id_Uti.getText()) )  {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Veuillez remplir tous les champs correctement!");

                alert.show();

            } else {

                try {

                    circuitCrud ajout = new circuitCrud();
                    circuit ci = new circuit(Point_de_depart.getText(),Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) , Date_F.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Integer.parseInt(Nbr_Place.getText()), description.getText(), Integer.parseInt(Nbr_Jour.getText()), Integer.parseInt(Id_Uti.getText()), Nom_Cir.getText());
                    
                    //verification de l'unicite par le nom de la demande le budget la description et la date limite 
                    String sql = "SELECT * FROM circuits WHERE point_depat_circuit = ? AND date_debut_circuit = ? AND date_fin_circuit = ? AND nbr_place_dispo = ? AND description_circuit = ? AND nbr_jour_circuit = ? AND id_utilisateur = ? AND nom_circuit = ?";
                    PreparedStatement stmt;
                    stmt = cnx2.prepareStatement(sql);
                    stmt.setString(1, ci.getPoint_depart_circuit());
                    stmt.setString(2, ci.getDate_debut_circuit());
                    stmt.setString(3, ci.getDate_fin_circuit());
                    stmt.setInt(4, ci.getNbr_place_dispo());                    
                    stmt.setString(5, ci.getDescription_circuit());
                    stmt.setInt(6, ci.getNbr_jour_circuit());
                    stmt.setInt(7, ci.getId_utilisateur());
                    stmt.setString(8, ci.getNom_circuit());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Ce Circuit existe deja!");

                        alert.show();
                        return; 
                    } else {

                        ajout.ajouter(ci);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Circuit ajoutée avec succés!");

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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Gestion_Circuit.fxml"));
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
