/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Commentaire;
import entites.Status;
import static java.awt.SystemColor.text;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.CommentaireService;
import services.StatusService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AfficheStatutController implements Initializable {

    @FXML
    private Button swFormStatusBtn;
    @FXML
    private TextArea textstatut;
    @FXML
    private Button ajouterCommBtn;
    @FXML
    private Button RetourStatusBtn;
    @FXML
    private AnchorPane formGestionBlog;
    @FXML
    private AnchorPane formStatus;
    @FXML
    private AnchorPane formCommentaire;
    @FXML
    private Button swFormStatusToComBtn;
    @FXML
    private Button retourCommToStatusBtn;
    @FXML
    private Button pubstat;
    @FXML
    private TextArea textStatusTa;
    @FXML
    private TextField titreStatusTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void swForm(ActionEvent event) {
        if (event.getSource() == RetourStatusBtn) {
            formGestionBlog.setVisible(true);
            formStatus.setVisible(false);
        }else if (event.getSource() == swFormStatusToComBtn) {
            formCommentaire.setVisible(true);
            formStatus.setVisible(false);
        }else if (event.getSource() == swFormStatusBtn) {
            formStatus.setVisible(true);
            formGestionBlog.setVisible(false);
        }else if (event.getSource() == retourCommToStatusBtn) {
            formStatus.setVisible(true);
            formCommentaire.setVisible(false);
        }
    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        try {
            Commentaire u = new Commentaire(textstatut.getText(), 1, 1);
            CommentaireService ps = new CommentaireService();
            ps.ajouter(u);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void ajouterStatus(ActionEvent event) {
        try {
            Status u = new Status(20, titreStatusTf.getText(), textStatusTa.getText(), "/rym/download", 1);
            StatusService ps = new StatusService();
            ps.ajouter(u);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   
    
    
}
