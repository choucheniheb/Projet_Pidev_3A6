/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.civilisation;
import entity.nourritures;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.civilisationService;
import service.nourrituresService;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class CivilisationcrudController implements Initializable {

    @FXML
    private TextArea descriptionbtn;
    @FXML
    private TextField nomcivilisationbtn;
    @FXML
    private TextField nommonubtn;
    @FXML
    private DatePicker datedebutbtn;
    @FXML
    private DatePicker datefinbtn;
    @FXML
    private Button ajoutercivibtn;
    @FXML
    private Button retourCivibtn;
    @FXML
    private ImageView imArt;
     private String path;
    
    File selectedFile;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterCivilisation(ActionEvent event) {
        
         if(nomcivilisationbtn.getText().isEmpty() || nommonubtn.getText().isEmpty() || descriptionbtn.getText().isEmpty() || datedebutbtn.getValue().toString().isEmpty() || datefinbtn.getValue().toString().isEmpty() ){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("remplir tous les champs");
            alert.showAndWait();
        }else{
            try {
            civilisation c = new civilisation(nomcivilisationbtn.getText() ,nommonubtn.getText() , descriptionbtn.getText() ,datedebutbtn.getValue().toString(),datefinbtn.getValue().toString(),4,path);
            civilisationService ps = new civilisationService();
            ps.ajouter(c);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Message");
            alert.setHeaderText(null);
            alert.setContentText("ajouter avec success");
            alert.showAndWait();
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void swForm2(ActionEvent event) {
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Civilisationajouteraffichage.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage primaryStage=(Stage) retourCivibtn.getScene().getWindow();;
                primaryStage.setScene(sc);
                primaryStage.setTitle("Login");
                primaryStage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void addImage(MouseEvent event) {
            //FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", ".jpg", ".png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a photo");
        fileChooser.setInitialDirectory(new File("C:\\Users\\nourh\\Desktop\\Projet_Pidev_3A6-nourhene\\src\\gui\\images"));
       // fileChooser.getExtensionFilters().add(imageFilter);
        Image image = null;
        try{
            image = new Image(fileChooser.showOpenDialog(null).toURI().toString());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        if(image != null){
            imArt.setImage(image);
            selectedFile=fileChooser.showOpenDialog(null);
            path=selectedFile.getAbsolutePath();
            System.out.println(path);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No photo Selected");
            alert.setHeaderText("Please select a photo");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }
    
}
