/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.civilisation;
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
import service.civilisationService;



/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherCIVIController implements Initializable {

    @FXML
    private HBox scrollAfficherAvis;
    
    private List<civilisation>civilisation ;
    civilisationService sa=new civilisationService();

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            civilisation= sa.recuperer();
            for(civilisation c: civilisation){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cadrecivi.fxml"));
                HBox cardBox = fxmlLoader.load();
                CadreciviController cadreciviController= fxmlLoader.getController();
                System.out.println(c);
                cadreciviController.setData(c);
                scrollAfficherAvis.getChildren().add(cardBox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCIVIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCIVIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
