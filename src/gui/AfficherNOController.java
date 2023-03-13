/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.nourritures;
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
import service.nourrituresService;


/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherNOController implements Initializable {

    @FXML
    private HBox scrollAfficherAvis;
    
    private List<nourritures> lAvis;
    nourrituresService sa=new nourrituresService();

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lAvis= sa.recuperer();
            for(int i=0;i<lAvis.size();i++){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cadre.fxml"));
                HBox cardBox = fxmlLoader.load();
                CadreController_1 cadreController= fxmlLoader.getController();
                cadreController.setData(lAvis.get(i));
                scrollAfficherAvis.getChildren().add(cardBox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherNOController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherNOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
