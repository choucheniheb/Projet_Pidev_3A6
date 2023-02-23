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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.civilisationService;
import services.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class NourriturescrudController implements Initializable {

    @FXML
    private TextField typenourriturebutton;
    @FXML
    private TextField nomnourriturebutton;
    @FXML
    private TextField originenourriturebutton;
    @FXML
    private TextField ingrediantnourriturebutton;
    @FXML
    private TextArea descriptionnourriture;
    @FXML
    private Button buttonajouternourriture;
    @FXML
    private TextField prixnourriturebutton1;
    @FXML
    private Button RetourAjouterbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterNourriture(ActionEvent event) {
        if(typenourriturebutton.getText().isEmpty() || nomnourriturebutton.getText().isEmpty() || originenourriturebutton.getText().isEmpty() || ingrediantnourriturebutton.getText().isEmpty() || descriptionnourriture.getText().isEmpty() || prixnourriturebutton1.getText().isEmpty()){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("remplir tous les champs");
            alert.showAndWait();
        }else{
            try {
            nourritures n = new nourritures(nomnourriturebutton.getText() ,originenourriturebutton.getText() , ingrediantnourriturebutton.getText() ,descriptionnourriture.getText(),typenourriturebutton.getText(), Double.parseDouble(prixnourriturebutton1.getText()),4,3);
            nourrituresService ps = new nourrituresService();
            ps.ajouter(n);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Message");
            alert.setHeaderText(null);
            alert.setContentText("ajouter avec success");
            alert.showAndWait();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void swForm(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ajouter nourriture.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) RetourAjouterbtn.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
   
   
   
}
