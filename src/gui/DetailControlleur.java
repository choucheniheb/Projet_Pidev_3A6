/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.nourritures;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import service.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class DetailControlleur implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label origine;
    @FXML
    private Label ingrediants;
    @FXML
    private Label prix;
    @FXML
    private Label type;
    @FXML
    private TextArea description;

    /**
     * Initializes the controller class.
     */
    
    nourritures n;
    nourritures nourrituresInstance;
public interface AddListener {
        void onInfoSentAdd( Boolean var) throws SQLException;
    }
    private AddListener listener;

    public void setAddListner(AddListener listener) {
        this.listener = listener;
    }
   
    public void setDialogPane(nourritures u){
        this.n=u;
        
        System.out.println("aff nom");
        
    name.setText(u.getNom_nourriture());
      System.out.println("aff orrigne"+u.getNom_nourriture());
    origine.setText(u.getOrigine_nourriture());
      System.out.println("aff origine"+u.getOrigine_nourriture());
    ingrediants.setText(u.getIngrediant());
      System.out.println("aff ingrediants"+u.getIngrediant());
    prix.setText(String.valueOf(u.getPrix_nourriture()));
      System.out.println("aff prix"+u.getPrix_nourriture());
    description.setText(u.getDescription_nourriture());
      System.out.println("aff nourri"+u.getDescription_nourriture());
        
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    
    
}
