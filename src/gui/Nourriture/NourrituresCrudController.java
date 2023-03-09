/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Nourriture;

import Services.nourrituresService;
import entities.Nourritures;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class NourrituresCrudController implements Initializable {

    @FXML
    private TextArea descriptionnourriture;
    @FXML
    private Button buttonajouternourriture;
    @FXML
    private Button RetourAjouterbtn;
    @FXML
    private TextField nomnourriturebutton;
    @FXML
    private TextField originenourriturebutton;
    @FXML
    private TextField ingrediantnourriturebutton;
    @FXML
    private TextField typenourriturebutton;
    @FXML
    private TextField prixnourriturebutton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterNourriture(ActionEvent event) throws SQLException {
        
         if(typenourriturebutton.getText().isEmpty() || nomnourriturebutton.getText().isEmpty() || originenourriturebutton.getText().isEmpty() || ingrediantnourriturebutton.getText().isEmpty() || descriptionnourriture.getText().isEmpty() || prixnourriturebutton1.getText().isEmpty()){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("remplir tous les champs");
            alert.showAndWait();
        }else{
             Nourritures n = new Nourritures(nomnourriturebutton.getText() ,originenourriturebutton.getText() , ingrediantnourriturebutton.getText() ,descriptionnourriture.getText(),typenourriturebutton.getText(), Double.parseDouble(prixnourriturebutton1.getText()),4,3);
             nourrituresService ps = new nourrituresService();
             ps.ajouter(n);
             Alert alert;
             alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("information Message");
             alert.setHeaderText(null);
             alert.setContentText("ajouter avec success");
             alert.showAndWait();
        }
    }

    @FXML
    private void swForm(ActionEvent event) {
    }
    
}
