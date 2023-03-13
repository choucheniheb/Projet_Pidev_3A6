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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.civilisationService;
import service.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class elementController1 implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label note;
      @FXML
    private Button btnmodif;

    @FXML
    private Button btnsupprimer;
    
civilisation civilisationInstance;
civilisationService rs = new civilisationService();

    @FXML
    private void AddRdv(MouseEvent event) {
    }
    /**
     * Initializes the controller class.
     */


public interface PopupListener {
        void onInfoSent(Boolean j);
    }
    private PopupListener listener;

    public void setListener(PopupListener listener) {
        this.listener = listener;
    }

public  void  setValue(civilisation c){
    this.civilisationInstance=c;
    name.setText(c.getNom_civilisation());
     System.out.println("heeeelooo hoey"+c.getNom_civilisation());
    note.setText(c.getNom_monument());
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
       


    @FXML
    private void onClick(MouseEvent event) throws IOException {
          Stage detail =(Stage) ((Node)event.getSource()).getScene().getWindow();
        double x=detail.getX();
        double y=detail.getY();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("popup_civilisation_Info.fxml"));
            DialogPane  detailPage=loader.load();

            DetailControlleur1 DetailControlleur1 =loader.getController();
            
            DetailControlleur1.setDialogPane(civilisationInstance);
            Dialog<ButtonType> dialog =new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);

            dialog.setDialogPane(detailPage);
            
            Optional<ButtonType> clickButtonp=dialog.showAndWait();
            dialog.setTitle("detail");

       
    }
     
    @FXML
    void modify(ActionEvent event) {
          civilisation c= new civilisation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup_civilisation_InfoModif.fxml"));
        try {
            Parent root = loader.load();
            Popup_civilisation_InfoModifController popup_civilisation_InfoModifController = loader.getController();
            popup_civilisation_InfoModifController.setModifyListner(new Popup_civilisation_InfoModifController.ModifyListener() {
                
                @Override
                public void onInfo(Boolean g) {
                    if (listener != null) {
                        System.out.println("tfffhgfgfhghghgh");
                        listener.onInfoSent(true);
                    }
                }

            });
            popup_civilisation_InfoModifController.setDialogPane(civilisationInstance);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier la catégorie");
            stage.initOwner(name.getScene().getWindow());
            stage.initOwner(note.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

            /* String newName = modifynourrControlleur .getname();
        String newName1 = modifynourrControlleur .getorigine();
        String newName2 = modifynourrControlleur .getingrediants();
        String newName3 = modifynourrControlleur .getprix();
        String newName4 = modifynourrControlleur .gettype();
        String newName5 = modifynourrControlleur .getdescription();
        if (newName != null) {
            n.setNom_nourriture(newName);
                        n.setOrigine_nourriture(newName1);
                            n.setIngrediant(newName2);
                             n.setPrix_nourriture(Double.valueOf( newName3));
                             n.setType_nourriture(newName4);
                             n.setDescription_nourriture(newName5);
                             
                             
            n.update();
            SetValue(n);
        }*/
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    

    @FXML
    void supprimercivilisation(ActionEvent event) throws SQLException {
  civilisationService c = new civilisationService();

        c.supprimer(civilisationInstance);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagecivilisation.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) btnsupprimer.getScene().getWindow();;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
   

}

