/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.civilisation;
import entities.nourritures;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.civilisationService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class AffichagecivilisationController implements Initializable {

    @FXML
    private TableColumn<civilisation,Integer> idcivicol;
    @FXML
    private TableColumn<civilisation,Integer> idutilicol;
    @FXML
    private TableColumn<civilisation,String> nomcivilcol;
    @FXML
    private TableColumn<civilisation,String> nommoncol;
    @FXML
    private TableColumn<civilisation,String> descriptioncivicol;
    @FXML
    private TableColumn<civilisation,String> datedebutcol;
    @FXML
    private TableColumn<civilisation,String> datefincol;
    @FXML
    private TableColumn<civilisation, Button> delete;
    @FXML
    private TableView<civilisation> tableviewcivi;
     civilisationService ps =new civilisationService();
    @FXML
    private Button retourcivibtn;
    @FXML
    private Button modifiercivibtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         List<civilisation> civilisation;
        try {
            civilisation = ps.recuperer();
            ObservableList<civilisation> olp = FXCollections.observableArrayList(civilisation);
            tableviewcivi.setItems(olp);
            idcivicol.setCellValueFactory(new PropertyValueFactory("id_civilisation"));
            idutilicol.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
            nomcivilcol.setCellValueFactory(new PropertyValueFactory("nom_civilisation"));
            nommoncol.setCellValueFactory(new PropertyValueFactory("nom_monument"));
            descriptioncivicol.setCellValueFactory(new PropertyValueFactory("description_monument"));
            datedebutcol.setCellValueFactory(new PropertyValueFactory("date_debut_civilisation"));
            datefincol.setCellValueFactory(new PropertyValueFactory("date_fin_civilisation"));
            this.delete();
        } 
        catch (SQLException ex) {
            Logger.getLogger(AffichagenourrituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                            try {
                                if (ps.supprimer(tableviewcivi.getItems().get(getIndex()))) {
                                    tableviewcivi.getItems().remove(getIndex());
                                    tableviewcivi.refresh();

                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    @FXML
    private void swFormretourcivi(ActionEvent event) {
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Civilisationcrud.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) retourcivibtn.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void swFormmodicivi(ActionEvent event) {
    }
    
}
