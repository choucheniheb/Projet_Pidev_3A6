/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceAvis;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private TextArea texte_avis;
    @FXML
    private Button AjouterReclamationBtn;
    @FXML
    private TextField id_utilisateur;
    @FXML
    private TextField rate_reclamation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
@FXML
    private void AjouterAvis(ActionEvent event) {
        if(texte_avis.getText().isEmpty() || rate_reclamation.getText().isEmpty() || id_utilisateur.getText().isEmpty()){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("remplir tous les champs");
            alert.showAndWait();
        }else{
            try {
            Avis t  = new Avis((texte_avis.getText()),Float.parseFloat(rate_reclamation.getText()),Integer.parseInt(id_utilisateur.getText()));
            ServiceAvis ps = new ServiceAvis();
            ps.ajouter(t);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("informatin Message");
            alert.setHeaderText(null);
            alert.setContentText("ajouter avec success");
            alert.showAndWait();
            System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }
   

} 