/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.visites;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import services.visiteCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Afficher_VisitesController implements Initializable {

    
    @FXML
    private TableColumn<visites, Integer> idciv;
    @FXML
    private TableColumn<visites, Integer> idc;
    @FXML
    private TableColumn<visites, Integer> idv;
    @FXML
    private TableColumn<visites, Button> delete;
    @FXML
    private TableColumn<visites, Button> modifier;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    
    visiteCrud vc = new visiteCrud();
    @FXML
    private TableView<visites> VisiteTV;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<visites> visites;
            visites = vc.afficher();
            ObservableList<visites> olp = FXCollections.observableArrayList(visites);
            VisiteTV.setItems(olp);
            idciv.setCellValueFactory(new PropertyValueFactory("id_civilisation"));
            idc.setCellValueFactory(new PropertyValueFactory("id_circuit"));
            idv.setCellValueFactory(new PropertyValueFactory("id_visite"));
            

            this.delete();
            
            
            Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Gestion_Visite.fxml"));
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
                            if (vc.supprimer(VisiteTV.getItems().get(getIndex()).getId_visite()))
                             {
                                VisiteTV.getItems().remove(getIndex());
                                VisiteTV.refresh();
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
        
        
        
         
       
         
         
         
         
    
    
    
    
    
    
}

    