/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamations;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceReclamation;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjouterReclamationController implements Initializable {
   @FXML
    private TextField type_reclamationTf;
    @FXML
    private TextArea text_reclamationTa;
    @FXML
        private TextField Id_UtilisateurTf;
    Connection cnx2 ;
    @FXML
    private Button AjouterReclamationBtn;
    
    public AjouterReclamationController() {
        cnx2 = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void AjouterReclamtion(ActionEvent event) {
        if(Id_UtilisateurTf.getText().isEmpty() || text_reclamationTa.getText().isEmpty() || type_reclamationTf.getText().isEmpty()){ 
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("remplir tous les champs");
            alert.showAndWait();
        }else{
            try {
            Reclamations t  = new Reclamations(Integer.parseInt(Id_UtilisateurTf.getText()),text_reclamationTa.getText(),type_reclamationTf.getText());
            ServiceReclamation ps = new ServiceReclamation();
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
    
