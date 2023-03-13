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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class elementController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label note;
    @FXML
    private Button btnmodif;

    @FXML
    private Button btnsupprimer;

    nourritures nourrituresInstance;

    nourrituresService rs = new nourrituresService();

    public void SetValue(nourritures n) {
        nourrituresInstance = n;
        name.setText(n.getNom_nourriture());
        System.out.println("heeeelooo hoey" + n.getOrigine_nourriture());
        note.setText(n.getOrigine_nourriture());
    }

    public interface PopupListener {

        void onInfoSent(Boolean j);
    }
    private PopupListener listener;

    public void setListener(PopupListener listener) {
        this.listener = listener;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onClick(MouseEvent event) throws IOException {
        Stage detail = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double x = detail.getX();
        double y = detail.getY();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("popup_nourriture_Info.fxml"));
        DialogPane detailPage = loader.load();

        DetailControlleur DetailControlleur = loader.getController();

        DetailControlleur.setDialogPane(nourrituresInstance);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UNDECORATED);

        dialog.setDialogPane(detailPage);

        Optional<ButtonType> clickButton = dialog.showAndWait();
        dialog.setTitle("detail");

    }

    @FXML
    private void AddRdv(MouseEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {
        nourritures n = new nourritures();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup_nourriture_InfoModif.fxml"));
        try {
            Parent root = loader.load();
            ModifynourrControlleur modifynourrControlleur = loader.getController();
            modifynourrControlleur.setModifyListner(new ModifynourrControlleur.ModifyListener() {
                @Override
                public void onInfo(Boolean g) {
                    if (listener != null) {
                        System.out.println("tfffhgfgfhghghgh");
                        listener.onInfoSent(true);
                    }
                }

            });
            modifynourrControlleur.setDialogPane(nourrituresInstance);
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
    private void supprimernourriture(ActionEvent event) throws SQLException {
        nourrituresService n = new nourrituresService();

        n.supprimer(nourrituresInstance);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BAffichagenourritures.fxml"));
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
