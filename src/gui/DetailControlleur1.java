/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.civilisation;
import entity.nourritures;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class DetailControlleur1 implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label monu;
    @FXML
    private Label datedebut;
    @FXML
    private Label datefin;
    @FXML
    private TextArea description;

    /**
     * Initializes the controller class.
     */
    civilisation c;
    civilisation civilisationInstance;
    
     public interface ModifyListener {
        void onInfoSentModify( civilisation civilisationInstance );
    }
    private ModifyListener listener;

    public void setModifyListner(ModifyListener listener) {
        this.listener = listener;
    }
    
       
    public void setDialogPane(civilisation u){
        this.c=u;
    name.setText(u.getNom_civilisation());
    System.out.println("aff nom"+u.getNom_civilisation());
    monu.setText(u.getNom_monument());
    System.out.println("aff monu"+u.getNom_monument());
    datedebut.setText(u.getDate_debut_civilisation());
    System.out.println("aff datedebut"+u.getDate_debut_civilisation());
    datefin.setText(u.getDate_fin_civilisation());
    System.out.println("aff datefin"+u.getDate_fin_civilisation());
    description.setText(u.getDescription_civilisation());
    System.out.println("aff desc"+u.getDescription_civilisation());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
