/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.civilisation;
import entity.nourritures;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.civilisationService;
import service.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class Popup_civilisation_InfoModifController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private Button modifycivi;
    @FXML
    private Button closecivi;
    @FXML
    private TextField name;
    @FXML
    private TextField monu;
    @FXML
    private TextField datedebut;
    @FXML
    private TextField datefin;

    /**
     * Initializes the controller class.
     */
    civilisation u;

  
   public interface ModifyListener {

        void onInfo(Boolean t);
    }
    private ModifyListener listener;

    public void setModifyListner(ModifyListener listener) {
        this.listener = listener;
    }                            

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    civilisation civilisation=new civilisation();

    @FXML
    private void modify(ActionEvent event) throws SQLException {
            civilisationService rs = new civilisationService();

        
        civilisation civi = new civilisation(civilisation.getId_civilisation(), name.getText(), monu.getText(), description.getText(), datedebut.getText(), datefin.getText(), civilisation.getId_utilisateur());
        listener.onInfo(true);
        System.out.println("aaaaaaaaaaaaaaaaaaa:" + civilisation.getId_civilisation() + " " + name.getText() + " " + monu.getText() + " " + description.getText() + " " + datedebut.getText() + " " + datefin.getText() + " " + civilisation.getId_utilisateur() );
        System.out.println("gui.ModifynourrControlleur.modify()");
        rs.modifier(civi);
        System.out.println("modify successe");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagecivilisation.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) modifycivi.getScene().getWindow();;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        

    }//Stage stage = (Stage) modify_Button.getScene().getWindow();
        //stage.close();

    }
     

    @FXML
    private void clickClose(ActionEvent event) {
          name.setText(null);
        monu.setText(null);
        datedebut.setText(null);
        datefin.setText(null);
        description.setText(null);
        
    Stage stage = (Stage) closecivi.getScene().getWindow();
    stage.close();
    }
    
       public void setDialogPane(civilisation c){
           
        this.civilisation=c;
        
    name.setText(c.getNom_civilisation());

    
   monu.setText(c.getNom_monument());
   
   description.setText(c.getDescription_civilisation());

   datedebut.setText(c.getDate_debut_civilisation());

datefin.setText(c.getDate_fin_civilisation());

 
        
}
 
}
