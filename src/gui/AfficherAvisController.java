/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceAvis;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherAvisController implements Initializable {

    @FXML
    private TableView<Avis> AvisTv;
    @FXML
    private TableColumn<Avis, Integer> id_avisTv;
    @FXML
    private TableColumn<Avis, Integer> id_utilisateurTv;
    @FXML
    private TableColumn<Avis, String> text_avisTv;
    @FXML
    private TableColumn<Avis, String> date_avisTv;
    @FXML
    private TableColumn<Avis, Float> rate_avisTv;
    @FXML
    private Label welcomeLb;

    // instance database Service 
    ServiceAvis ps = new ServiceAvis();
    @FXML
    private TableColumn<Avis, Button> delete;
     @FXML
        private TableColumn<Avis, Button> modifier;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Avis> Avis;
        try {
            Avis = ps.recuperer();
            ObservableList<Avis> olp = FXCollections.observableArrayList(Avis);
            AvisTv.setItems(olp);
            id_avisTv.setCellValueFactory(new PropertyValueFactory("id_avis"));
            id_utilisateurTv.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
            text_avisTv.setCellValueFactory(new PropertyValueFactory("text_avis"));
            date_avisTv.setCellValueFactory(new PropertyValueFactory("date_avis"));
            rate_avisTv.setCellValueFactory(new PropertyValueFactory("rate_avis"));
            this.delete();
            this.modifier();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAvisController.class.getName()).log(Level.SEVERE, null, ex);
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
                                if (ps.supprimer(AvisTv.getItems().get(getIndex()))) {
                                    AvisTv.getItems().remove(getIndex());
                                    AvisTv.refresh();

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
    private void modifier() {
            modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierAvis.fxml"));
                                Parent root = loader.load();
                                gui.ModifierAvisController modifier = loader.getController();
                                Avis c = AvisTv.getItems().get(getIndex());
                                 
                                modifier.setAvis(c);
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherAvisController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    public void setData(String Message) {
        welcomeLb.setText("Welcomme " + Message);

    }

}
