

package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaException;

/**
 * FXML Controller class
 *
 */
public class AjouterNourritureController implements Initializable {

    @FXML
    private Button goToNourrituresBtn;
    @FXML
    private Button goTocivilisationbtn;
    @FXML
    private Button changeMusicBtn;
    @FXML
    private MenuItem changeMusicMenuItem;

    private static final String DEFAULT_MEDIA_URL = "file:///C:/Users/msi/Documents/NetBeansProjects/FinalPiDev/src/gui/image/music.mp3";
    private MediaPlayer mediaPlayer;
    private FileChooser fileChooser;
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
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Music File");
        // Create a Media object from the MP3 file URL
        Media media = new Media(DEFAULT_MEDIA_URL);

        // Create a MediaPlayer object from the Media object
        mediaPlayer = new MediaPlayer(media);

        // Start playing the media
        mediaPlayer.play();
    }

    @FXML
    private void swFormN(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NourritureAjoutetaffichage.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) goToNourrituresBtn.getScene().getWindow();
            ;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void swFormC(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Civilisationajouteraffichage.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) goTocivilisationbtn.getScene().getWindow();
            ;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
 @FXML
private void changeMusic(ActionEvent event) {
    // Open file chooser to select music file
    File selectedFile = fileChooser.showOpenDialog(null);
    
    if (selectedFile != null) {
        // Stop current media player
        mediaPlayer.stop();

        try {
            // Create new media player with selected file
            Media media = new Media(selectedFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            // Start playing the new media
            mediaPlayer.play();
        } catch (MediaException e) {
            System.out.println("Error creating media player: " + e.getMessage());
        }
    }
}

    @FXML
    private void swProfileUser(MouseEvent event) {
    }


}

