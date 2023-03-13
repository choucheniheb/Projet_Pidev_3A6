/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.evenements;
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
public class CadrevController implements Initializable {

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
    private final String[] colors = {"#bc9f1e", "#bc9f1e", "#bc9f1e", "#bc9f1e"};

    public void setData(evenements a) throws SQLException {
        Connection cnx = MyDB.getInstance().getCnx();
        String s = "select titre_evenement,description_evenement,image from evenements where id_evenement= " + a.getId_evenement();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        String titre_evenement = null, description_evenement = null, image = null;
        while (rs.next()) {
            titre_evenement = rs.getString("titre_evenement");
            description_evenement = rs.getString("description_evenement");
            image = rs.getString("image");
        }
//    Image image = new Image(getClass().getResourceAsStream()):
//    bookinage, setimage (image)

        
        nom_utilisateur.setText(a.getTitre_evenement() );
        text_avis.setText(a.getDescription_evenement());
        box_avis.setStyle("-fx-background-color: " + colors[(int) (Math.random() * colors.length)] + ";"
                + " -fx-background-radius: 15;"
                + "-fx-effect: dropShadown(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0,10) ;");
        
        File fileim=new File(image);
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(fileim);
            Image image11 = SwingFXUtils.toFXImage(bufferedImage, null);
            image_user.setImage(image11);
        } catch (IOException ex) {
            Logger.getLogger(CadrevController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
