/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author nourh
 */
public class VideoController implements Initializable {

    @FXML
    private WebView webView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String videoId = "YOUR_YOUTUBE_VIDEO_ID_HERE";
        String url1 = "https://www.youtube.com/watch?v=OtJVufo3IrA&t=29s/embed/" + videoId + "?autoplay=1";
        webView.getEngine().load(url1);
        // TODO
    }    
    
 

    
}
