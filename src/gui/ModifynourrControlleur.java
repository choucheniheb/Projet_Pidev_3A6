/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class ModifynourrControlleur implements Initializable {

    @FXML
    private TextArea description;

    @FXML
    private Button modify_Button;

    @FXML
    private Button closeButton;

    @FXML
    private TextField name;

    @FXML
    private TextField origine;

    @FXML
    private TextField ingrediants;

    @FXML
    private TextField prix;

    @FXML
    private TextField type;

//nourritures n;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    nourritures nourriture = new nourritures();

    public interface ModifyListener {

        void onInfo(Boolean t);
    }
    private ModifyListener listener;

    public void setModifyListner(ModifyListener listener) {
        this.listener = listener;
    }

    @FXML
    private void modify(ActionEvent event) throws SQLException {
        nourrituresService rs = new nourrituresService();

        Double prixValue = Double.valueOf(prix.getText());
        nourritures nour = new nourritures(nourriture.getId_nourriture(), name.getText(), origine.getText(), ingrediants.getText(), description.getText(), type.getText(), prixValue, nourriture.getId_utilisateur(), nourriture.getId_civilisation());
        listener.onInfo(true);
        System.out.println("aaaaaaaaaaaaaaaaaaa:" + nourriture.getId_nourriture() + " " + name.getText() + " " + origine.getText() + " " + ingrediants.getText() + " " + description.getText() + " " + type.getText() + " " + prixValue + " " + nourriture.getId_utilisateur() + " " + nourriture.getId_civilisation());
        System.out.println("gui.ModifynourrControlleur.modify()");
        rs.modifier(nour);
        System.out.println("modify successe");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BAffichagenourritures.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) modify_Button.getScene().getWindow();;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        

    }//Stage stage = (Stage) modify_Button.getScene().getWindow();
        //stage.close();


    }

    public void setDialogPane(nourritures u) {
        this.nourriture = u;

        name.setText(u.getNom_nourriture());

        origine.setText(u.getOrigine_nourriture());

        ingrediants.setText(u.getIngrediant());

        prix.setText(String.valueOf(u.getPrix_nourriture()));

        type.setText(u.getType_nourriture());
        
        description.setText(u.getDescription_nourriture());

    }

    @FXML
    private void clickClose(ActionEvent event) {
        name.setText(null);
        origine.setText(null);
        ingrediants.setText(null);
        description.setText(null);
        prix.setText("");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
