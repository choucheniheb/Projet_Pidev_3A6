/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.circuit;
import entity.evenements;
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
import javafx.stage.Stage;
import service.circuitCrud;
import service.evenementCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Afficher_EvenementController implements Initializable {

    @FXML
    private TableView<evenements> evenementtv;
    @FXML
    private TableColumn<evenements, Integer> id;
    @FXML
    private TableColumn<evenements, String> titre;
    @FXML
    private TableColumn<evenements, String> type;
    @FXML
    private TableColumn<evenements, String> date;
    @FXML
    private TableColumn<evenements, String> lieux;
    @FXML
    private TableColumn<evenements, Double> prix;
    @FXML
    private TableColumn<evenements, Integer> idinv;
    @FXML
    private TableColumn<evenements, String> desc;
    @FXML
    private TableColumn<evenements, Integer> iduti;
    @FXML
    private TableColumn<evenements, Button> delete;
    @FXML
    private TableColumn<evenements, Button> modifier;
    @FXML
    private Button Retour;
    
    /**
     * Initializes the controller class.
     */
    
    evenementCrud ps = new evenementCrud();
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<evenements> evenement;
            evenement = ps.afficher();
            ObservableList<evenements> olp = FXCollections.observableArrayList(evenement);
            evenementtv.setItems(olp);
            id.setCellValueFactory(new PropertyValueFactory("id_evenement "));
            titre.setCellValueFactory(new PropertyValueFactory("titre_evenement"));
            type.setCellValueFactory(new PropertyValueFactory("type_evenement"));
            date.setCellValueFactory(new PropertyValueFactory("date_evenement"));
            lieux.setCellValueFactory(new PropertyValueFactory("lieux_evenement"));
            prix.setCellValueFactory(new PropertyValueFactory("prix_evenement"));
            idinv.setCellValueFactory(new PropertyValueFactory("id_invite "));
            desc.setCellValueFactory(new PropertyValueFactory("description_evenement"));
            iduti.setCellValueFactory(new PropertyValueFactory("id_utilisateur "));
            this.delete();
            this.modifier();
            
            Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Gestion_Evenement.fxml"));
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
                            if (ps.supprimer(evenementtv.getItems().get(getIndex()).getId_evenement())) {
                                evenementtv.getItems().remove(getIndex());
                                evenementtv.refresh();
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
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
                                Parent root = loader.load();
                                gui.ModifierEvenementController modifier = loader.getController();
                                evenements c = evenementtv.getItems().get(getIndex());
                                 
                                modifier.setevenement(c);
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
