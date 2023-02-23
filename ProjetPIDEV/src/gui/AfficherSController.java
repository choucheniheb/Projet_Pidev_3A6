/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Status;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AfficherSController implements Initializable {
    
     @FXML
    private TableColumn<Status, Integer> id_status ;
    @FXML
    private TableColumn<Status, String> titre_status;
    @FXML
    private TableColumn<Status, String> contenu_status;
    @FXML
    private TableColumn<Status, String> date_status;
    @FXML
    private TableColumn<Status, Integer> nbr_like;
    @FXML
    private TableColumn<Status, String> media_status;
    @FXML
    private TableColumn<Status, Integer> id_utilisateur;
    @FXML
    private TableColumn<Status, ?> Delete;
    @FXML
    private TableColumn<Status, ?> Modifier;
    @FXML
    private TableView<?> ecran;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
