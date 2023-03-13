/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.circuit;
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

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Afficher_CircuitController implements Initializable {

    @FXML
    private TableView<circuit> circuittv;
    @FXML
    private TableColumn<circuit, Integer> id;
    @FXML
    private TableColumn<circuit, String> pd;
    @FXML
    private TableColumn<circuit, String> df;
    @FXML
    private TableColumn<circuit, Integer> nbrp;
    @FXML
    private TableColumn<circuit, String> des;
    @FXML
    private TableColumn<circuit, Integer> nbrj;
    @FXML
    private TableColumn<circuit, Integer> idu;
    @FXML
    private TableColumn<circuit, String> nomc;
    @FXML
    private TableColumn<circuit, Button> delete;
    @FXML
    private TableColumn<circuit, Button> modifier;
    @FXML
    private TableColumn<circuit, String> dd;
    @FXML
    private Button Retours;
    /**
     * Initializes the controller class.
     */
    
    circuitCrud ps = new circuitCrud();
    @FXML
    private Label Retour;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        List<circuit> circuit;
            circuit = ps.afficher();
            ObservableList<circuit> olp = FXCollections.observableArrayList(circuit);
            circuittv.setItems(olp);
            id.setCellValueFactory(new PropertyValueFactory("id_circuit"));
            pd.setCellValueFactory(new PropertyValueFactory("point_depat_circuit"));
            df.setCellValueFactory(new PropertyValueFactory("date_fin_circuit"));
            nbrp.setCellValueFactory(new PropertyValueFactory("nbr_place_dispo"));
            des.setCellValueFactory(new PropertyValueFactory("description_circuit"));
            nbrj.setCellValueFactory(new PropertyValueFactory("nbr_jour_circuit"));
            idu.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
            nomc.setCellValueFactory(new PropertyValueFactory("nom_circuit"));
            dd.setCellValueFactory(new PropertyValueFactory("date_debut_circuit"));
            this.delete();
            this.modifier();
            
            
            
            
            Retours.setOnAction(Retours -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Gestion_Circuit.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Retours.getSource()).getScene().getWindow();
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
                            if (ps.supprimer(circuittv.getItems().get(getIndex()).getId_circuit())) {
                                circuittv.getItems().remove(getIndex());
                                circuittv.refresh();
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
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifiercircuit.fxml"));
                                Parent root = loader.load();
                                gui.ModifiercircuitController modifier = loader.getController();
                                circuit c = circuittv.getItems().get(getIndex());
                                 
                                modifier.setcircuit(c);
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






    
        


        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
