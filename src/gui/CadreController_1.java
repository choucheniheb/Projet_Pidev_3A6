/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.nourritures;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javax.imageio.ImageIO;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class CadreController_1 implements Initializable {

    @FXML
    private VBox box_avis;
   
    @FXML
    private Label text_avis;
    @FXML
    private ImageView image_user;
    @FXML
    private Label nom_utilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private final String[] colors = {"#B9E5FF", "#BDB2FE", "#FB9AA8", "#FF5056"};

    public void setData(nourritures a) throws SQLException {
//    Image image = new Image(getClass().getResourceAsStream()):
//    bookinage, setimage (image)

     
        nom_utilisateur.setText(a.getNom_nourriture());
        text_avis.setText(a.getDescription_nourriture());
        box_avis.setStyle("-fx-background-color: " + colors[(int) (Math.random() * colors.length)] + ";"
                + " -fx-background-radius: 15;"
                + "-fx-effect: dropShadown(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0,10) ;");
        
        File fileim=new File(a.getImage());
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(fileim);
            Image image11 = SwingFXUtils.toFXImage(bufferedImage, null);
            image_user.setImage(image11);
        } catch (IOException ex) {
            Logger.getLogger(CadreController_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

