/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenements;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import services.evenementCrud;


/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherevController implements Initializable {

    @FXML
    private HBox scrollAfficherAvis;
    
    private List<evenements> evenement;
    evenementCrud sa=new evenementCrud();

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            evenement= sa.afficher();
            for(int i=0;i<evenement.size();i++){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cadrev.fxml"));
                HBox cardBox = fxmlLoader.load();
                CadrevController cadreController= fxmlLoader.getController();
                cadreController.setData(evenement.get(i));
                scrollAfficherAvis.getChildren().add(cardBox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherevController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherevController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
