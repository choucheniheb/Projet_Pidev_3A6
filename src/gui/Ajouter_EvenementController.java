/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenements;
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
import services.evenementCrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author MSI
 */




public class Ajouter_EvenementController implements Initializable {

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
    private Button Ajouter_Evenement;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    
    
    Connection cnx2 ;
    
    public Ajouter_EvenementController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
        Ajouter_Evenement.setOnAction(e  -> {

            if (  "".equals(titre.getText())  || "".equals (type.getText())  || "".equals (lieux.getText())  || "".equals (prix.getText())  || "".equals (invite.getText())  ||    "".equals (utilisateur.getText())  || "".equals (description.getText())  ||  "".equals(date.getValue()) )  {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Veuillez remplir tous les champs correctement!");

                alert.show();

            } else {

                try {

                    evenementCrud ajout = new evenementCrud();
                   evenements ev = new evenements(titre.getText(), type.getText(), date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), lieux.getText(), Double.parseDouble(prix.getText()), Integer.parseInt(invite.getText()), description.getText(), Integer.parseInt(utilisateur.getText()));

                    //verification de l'unicite par le nom de la demande le budget la description et la date limite 
                    String sql = "SELECT * FROM evenements WHERE Titre_evenement = ? AND Type_evenement = ? AND Date_evenement = ? AND Lieux_evenement = ? AND Prix_evenement = ? AND Id_invite = ? AND Description_evenement = ? AND Id_utilisateur = ?";
                    PreparedStatement stmt;
                    stmt = cnx2.prepareStatement(sql);
                    stmt.setString(1, ev.getTitre_evenement());
                    stmt.setString(2, ev.getType_evenement());
                    stmt.setString(3, ev.getDate_evenement());
                    stmt.setString(4, ev.getLieux_evenement());                    
                    stmt.setDouble(5, ev.getPrix_evenement());
                    stmt.setInt(6, ev.getId_invite());
                    stmt.setString(7, ev.getDescription_evenement());
                    stmt.setInt(8, ev.getId_utilisateur());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Evenement existe deja!");

                        alert.show();
                        return; 
                    } else {

                        ajout.ajouter(ev);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Evenement ajoutée avec succés!");

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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Gestion_Evenement.fxml"));
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
