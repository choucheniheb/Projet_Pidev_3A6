/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.nourritures;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.delete;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import service.CompareNom;
import service.CompareNote;
import service.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class AffichagenourrituresController implements Initializable {
   @FXML
    private AnchorPane pnlOverview;

     // instance database Service 
    nourrituresService ps = new nourrituresService();
    @FXML
    private Text lbaff;
    @FXML
    private Button retourlistenourr;
    @FXML
    private Button modifiernourr;
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
    
    ObservableList<nourritures> nourrituresListe;
    public void remplirliste(ObservableList<nourritures> nourrituresListe){
        nombre.setText(String.valueOf(nourrituresListe.size()));
            ArrayList<Node> nodes = new ArrayList<>();
        System.out.println(nourrituresListe.size());
        if(nourrituresListe.size()>=1){
            for (int i = 0; i < nourrituresListe.size(); i++) {
                try {

                    final int j = i;
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("Itemn.fxml"));

                    nodes.add(  loader.load());
                    elementController f=loader.getController();
                    f.setListener(new elementController .PopupListener() {
                        @Override
                        public void onInfoSent( Boolean var)  {
                            if (var) {
                                
                                
                                System.out.println("ffjgffhgfgfgfhhg");
                                pnItems.getChildren().clear();

                                try {
                                    getFromDb();
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(AffichagenourrituresController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }

                    });
                    System.out.println("hhhhzzzz "
                 );
                    f.SetValue(nourrituresListe.get(i));
                    //give the items some effect


                    pnItems.getChildren().add(nodes.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            pnItems.getChildren().removeAll();


        }
  
    }
     

    @FXML
    private void swFormmodnourr(ActionEvent event) {
    }

    @FXML
    private void triUsername(MouseEvent event) {
         FXCollections.sort(nourrituresListe, new CompareNom());
            pnItems.getChildren().removeAll();
            pnItems.getChildren().clear();
            remplirliste(nourrituresListe);
    }

    @FXML
    private void triNote(MouseEvent event) {
        FXCollections.sort(nourrituresListe, new CompareNote());
            pnItems.getChildren().removeAll();
            pnItems.getChildren().clear();
            remplirliste(nourrituresListe);
    }

    @FXML
   
    private void rechercher(KeyEvent event) {
        FilteredList<nourritures> filterData = new FilteredList<>(nourrituresListe, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(candida -> {
                if (newvalue == null || newvalue.isEmpty()) {

                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (String.valueOf(candida.getNom_nourriture()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }if (String.valueOf(candida.getOrigine_nourriture()).indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });
            pnItems.getChildren().removeAll();

            SortedList<nourritures > sortedList = new SortedList<>(filterData);
          // sortedList.comparatorProperty().bind(pnItems.);
            pnItems.getChildren().clear();

            remplirliste(sortedList);

           // table.setItems(sortedList);


        });

        
    }

  ArrayList<Node> nodes;
    @FXML
    private void swFormrtnourr(ActionEvent event) {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterNourriture.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) retourlistenourr.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

/*  @FXML
    private void moveToChart(ActionEvent event) throws IOException {
          FXMLLoader loader =new FXMLLoader(getClass().getResource("../Chart/Barchart.fxml"));
        Parent root =loader.load();
        barChartControlleur send1 = loader.getController();

        send1.sendData(candidaturesListe);
        pnlOverview.set setCenter(root);

        FXMLLoader loader =new FXMLLoader(getClass().getResource("../Barchart.fxml"));
        Pane root =loader.load();
        BarchartController send1 = loader.getController();

        send1.sendData(nourrituresListe);
        pnlOverview.getChildren().setAll(root);
     

    }*/

       public void getFromDb() throws SQLException {

            nourrituresListe= (ObservableList<nourritures>) ps.recuperer();
            System.out.println("hhhheeeyyy");
            remplirliste(nourrituresListe);


    }

    @FXML
    private void moveToChart(ActionEvent event) throws IOException {
          FXMLLoader loader =new FXMLLoader(getClass().getResource("Barchart.fxml"));
        Pane root =loader.load();
        BarchartController send1 = loader.getController();

        send1.sendData(nourrituresListe);
        pnlOverview.getChildren().setAll(root);
    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }
   
    
    
}
