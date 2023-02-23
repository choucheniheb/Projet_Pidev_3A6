/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.nourritures;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.delete;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class AffichagenourrituresController implements Initializable {

    @FXML
    private TableColumn<nourritures, Integer> idnourriturecol;
    @FXML
    private TableColumn<nourritures, String> nomnourriturecol;
    @FXML
    private TableColumn<nourritures, String> originenourriturecol;
    @FXML
    private TableColumn<nourritures, String> ingrediantcol;
    @FXML
    private TableColumn<nourritures, String> descriptionnourrcol;
    @FXML
    private TableView<nourritures> tableviewnourr;
    @FXML
    private TableColumn<nourritures, String> typenourricol;

     // instance database Service 
    nourrituresService ps = new nourrituresService();
    @FXML
    private TableColumn<nourritures, Integer> idutilisateurcol;
    @FXML
    private TableColumn<nourritures, Integer> idcivilisationcol;
    @FXML
    private TableColumn<nourritures, Button> delete;
    @FXML
    private Text lbaff;
    @FXML
    private Button retourlistenourr;
    @FXML
    private Button modifiernourr;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<nourritures> nourritures;
        try {
            nourritures = ps.recuperer();
            ObservableList<nourritures> olp = FXCollections.observableArrayList(nourritures);
            tableviewnourr.setItems(olp);
            idnourriturecol.setCellValueFactory(new PropertyValueFactory("id_nourriture"));
            idutilisateurcol.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
            idcivilisationcol.setCellValueFactory(new PropertyValueFactory("id_civilisation"));
            nomnourriturecol.setCellValueFactory(new PropertyValueFactory("nom_nourriture"));
            originenourriturecol.setCellValueFactory(new PropertyValueFactory("origine_nourriture"));
            ingrediantcol.setCellValueFactory(new PropertyValueFactory("ingrediant"));
            descriptionnourrcol.setCellValueFactory(new PropertyValueFactory("description_nourriture"));
            typenourricol.setCellValueFactory(new PropertyValueFactory("type_nourriture"));
            this.delete();
        } catch (SQLException ex) {
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
                                if (ps.supprimer(tableviewnourr.getItems().get(getIndex()))) {
                                    tableviewnourr.getItems().remove(getIndex());
                                    tableviewnourr.refresh();

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
    private void swFormrtnourr(ActionEvent event) {
        
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Nourriturescrud.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) retourlistenourr.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void swFormmodnourr(ActionEvent event) {
    }

   
}
