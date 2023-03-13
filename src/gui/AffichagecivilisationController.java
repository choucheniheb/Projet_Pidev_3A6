/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.civilisation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CompareNom1;
import service.CompareNote1;
import service.civilisationService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class AffichagecivilisationController implements Initializable {

    civilisationService ps = new civilisationService();
    private Button retourcivibtn;
    @FXML
    private Button retourlistenourr;
    @FXML
    private Button modifiernourr;

    @FXML
    private AnchorPane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private TextField searchBox;
    @FXML
    private ImageView imageView;
    @FXML
    private Label nombre;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button videobtn;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    @FXML
    private void onImageViewClicked() {
        comboBox.getItems().add("ffff");
        System.out.println("ffff");
        comboBox.setVisible(!comboBox.isVisible());
    }

    private ImageView img;

    void closePage(MouseEvent event) {
        Stage stage = (Stage) img.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getFromDb();
        } catch (SQLException ex) {
            Logger.getLogger("hhheeelloo");
        }
    }
    // TODO

    ObservableList<civilisation> civilisationListe;

    public void remplirliste(ObservableList<civilisation> civilisationListe) {
        nombre.setText(String.valueOf(civilisationListe.size()));
        ArrayList<Node> nodes = new ArrayList<>();
        System.out.println(civilisationListe.size());
        if (civilisationListe.size() >= 1) {
            for (int i = 0; i < civilisationListe.size(); i++) {
                try {

                    final int j = i;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Itemc.fxml"));

                    nodes.add(loader.load());
                    elementController1 f = loader.getController();
                    f.setListener(new elementController1.PopupListener() {
                        @Override
                        public void onInfoSent(Boolean var) {
                            if (var) {

                                System.out.println("ffjgffhgfgfgfhhg");
                                pnItems.getChildren().clear();

                                try {
                                    getFromDb();

                                } catch (SQLException ex) {
                                    Logger.getLogger(AffichagecivilisationController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }

                    });
                    System.out.println("hhhhzzzz "
                    );
                    f.setValue(civilisationListe.get(i));
                    //give the items some effect

                    pnItems.getChildren().add(nodes.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            pnItems.getChildren().removeAll();

        }

    }

    private void swFormretourcivi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Civilisationcrud.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) retourcivibtn.getScene().getWindow();;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void swFormrtnourr(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterNourriture.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) retourlistenourr.getScene().getWindow();;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void swFormmodnourr(ActionEvent event) {
    }

    @FXML
    private void triUsername(MouseEvent event) {
        FXCollections.sort(civilisationListe, new CompareNom1());
        pnItems.getChildren().removeAll();
        pnItems.getChildren().clear();
        remplirliste(civilisationListe);
    }

    @FXML
    private void triNote(MouseEvent event) {
        FXCollections.sort(civilisationListe, new CompareNote1());
        pnItems.getChildren().removeAll();
        pnItems.getChildren().clear();
        remplirliste(civilisationListe);
    }

    @FXML
    private void rechercher(KeyEvent event) {
        FilteredList<civilisation> filterData = new FilteredList<>(civilisationListe, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(candida -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (String.valueOf(candida.getNom_civilisation()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (String.valueOf(candida.getNom_monument()).indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });
            pnItems.getChildren().removeAll();

            SortedList<civilisation> sortedList = new SortedList<>(filterData);
            // sortedList.comparatorProperty().bind(pnItems.);
            pnItems.getChildren().clear();

            remplirliste(sortedList);

            // table.setItems(sortedList);
        });

    }
    ArrayList<Node> nodes;

    public void getFromDb() throws SQLException {

        civilisationListe = (ObservableList<civilisation>) ps.recuperer();
        System.out.println("hhhheeeyyy");
        remplirliste(civilisationListe);

    }

    @FXML
    void swformvideo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("video.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) videobtn.getScene().getWindow();;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }
}
