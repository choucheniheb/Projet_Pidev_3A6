package gui;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import entity.BlogComment;
import static gui.PostdetailsController.comment;

public class CommentarticleController implements Initializable {
    @javafx.fxml.FXML
    private Label lblComment;
    @javafx.fxml.FXML
    private Label lblOwner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BlogComment Comment = comment;
        lblComment.setText(Comment.getComment());
        //lblOwner.setText(comment.getUser().getUsername() +"                                     "+ comment.getPostDate());
    }
}
