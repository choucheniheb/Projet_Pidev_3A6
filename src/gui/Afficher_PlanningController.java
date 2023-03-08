/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenements;
import entities.invites;
import entities.planning;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.evenementCrud;
import services.inviteCrud;
import services.planningCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Afficher_PlanningController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML
    private TableColumn<planning, Integer> idp;
    @FXML
    private TableColumn<planning, Integer> idc;
    @FXML
    private TableColumn<planning, Integer> ide;
    @FXML
    private TableColumn<planning, String> resto;
    @FXML
    private TableColumn<planning, String > hotel;
    @FXML
    private TableColumn<planning, String> empla;
    @FXML
    private TableColumn<planning, Button> delete;
    @FXML
    private TableColumn<planning, Button> modifier;
    @FXML
    private Button Retour;
    @FXML
    private TableView<planning> planningtv;
    
    
    planningCrud ps = new planningCrud();
   
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   List<planning> planning;
            planning = ps.afficher();
            ObservableList<planning> olp = FXCollections.observableArrayList(planning);
            planningtv.setItems(olp);
            idp.setCellValueFactory(new PropertyValueFactory("id_planning"));
            idc.setCellValueFactory(new PropertyValueFactory("id_circuit"));
            ide.setCellValueFactory(new PropertyValueFactory("id_evenement"));
            resto.setCellValueFactory(new PropertyValueFactory("resto"));
            hotel.setCellValueFactory(new PropertyValueFactory("hotel"));
            empla.setCellValueFactory(new PropertyValueFactory("emplacement"));

            this.delete();
            this.modifier();
            
            Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Gestion_planning.fxml"));
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
                            if (ps.supprimer(planningtv.getItems().get(getIndex()).getId_planning())) {
                                planningtv.getItems().remove(getIndex());
                                planningtv.refresh();
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
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier_Planning.fxml"));
                                Parent root = loader.load();
                                gui.Modifier_PlanningController modifier = loader.getController();
                                planning c = planningtv.getItems().get(getIndex());
                                 
                                modifier.setplanning(c);
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(Afficher_PlanningController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
         
         
    
    
    
    
    
    
}
