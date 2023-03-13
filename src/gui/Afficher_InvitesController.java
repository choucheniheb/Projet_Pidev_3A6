/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.evenements;
import entity.invites;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.evenementCrud;
import service.inviteCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Afficher_InvitesController implements Initializable {

    @FXML
    private TableColumn<invites, Integer> id;
    @FXML
    private TableColumn<invites, String> nom;
    @FXML
    private TableColumn<invites, String> prenom;
    @FXML
    private TableColumn<invites, String> type;
    @FXML
    private TableColumn<invites, Button> delete;
    @FXML
    private TableColumn<invites, Button> modifier;
    @FXML
    private Button Retour;
    @FXML
    private TableView<invites> invitetv;

    /**
     * Initializes the controller class.
     */
    
        inviteCrud ps = new inviteCrud();
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    List<invites> invites;
            invites = ps.afficher();
            ObservableList<invites> olp = FXCollections.observableArrayList(invites);
            invitetv.setItems(olp);
            id.setCellValueFactory(new PropertyValueFactory("id_invite"));
            nom.setCellValueFactory(new PropertyValueFactory("nom_invite"));
            prenom.setCellValueFactory(new PropertyValueFactory("prenom_invite"));
            type.setCellValueFactory(new PropertyValueFactory("type_invite"));
   
            this.delete();
            this.modifier();
            
            Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Gestion_Invite.fxml"));
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
    
    
    
    
    private void delete() {

        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            if (ps.supprimer(invitetv.getItems().get(getIndex()).getId_invite())) {
                                invitetv.getItems().remove(getIndex());
                                invitetv.refresh();
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
        
        
        
         
       
         
         private void modifier() {
            modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier_invite.fxml"));
                                Parent root = loader.load();
                                gui.Modifier_inviteController modifier = loader.getController();
                                invites c = invitetv.getItems().get(getIndex());
                                 
                                modifier.setinvite(c);
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(Afficher_CircuitController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }
         
         
    
    
    
    
    
    
}