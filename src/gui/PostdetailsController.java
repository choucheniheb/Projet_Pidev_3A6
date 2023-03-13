package gui;

import service.*;
import entity.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.Rating;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import static test.TestFX.getId_user;
import static gui.ItemBController.Posta;
import static gui.ItemBController.ayja;
//import javax.swing.text.Document;
import utils.DataSource;

public class PostdetailsController implements Initializable {

    @javafx.fxml.FXML
    private Label lblCategory;
    @javafx.fxml.FXML
    private Pane paneContent;
    @javafx.fxml.FXML
    private Label lblOwner;
    @javafx.fxml.FXML
    private Label lblCreationDate;
    @javafx.fxml.FXML
    private Pane paneComment;
    @javafx.fxml.FXML
    private Label lblCurrency;
    @javafx.fxml.FXML
    private Label lblTitle;
    @javafx.fxml.FXML
    private ImageView imNft;
    @javafx.fxml.FXML
    private VBox boxComment;
    @javafx.fxml.FXML
    private TextField tfComment;
    @javafx.fxml.FXML
    private ScrollPane scrollPaneComments;

    @javafx.fxml.FXML
    private Button btnComment;
    public static BlogComment comment;
    @javafx.fxml.FXML
    private Button barticle;
    @javafx.fxml.FXML
    private Button apdf;
    @FXML
    private Label likeNbLb;
    @FXML
    private Label dislikeNbLb;
    @FXML
    private ImageView likeBtn;
    @FXML
    private ImageView dislikeBtn;
    BlogsService bs = new BlogsService();
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(Post.toString());
        String id;
        lblTitle.setText(Posta.getTitle());
        lblCategory.setText(Posta.getCategory());
        lblCreationDate.setText(Posta.getDate() + "");

        id = String.valueOf(Posta.getId());
        lblOwner.setText(Posta.getAuthor());
        String zarga = Posta.getImage();
        System.out.println(zarga);
        //Integer.valueOf(id)
        Posta.setId(Integer.valueOf(id));
        lblCurrency.setText(Posta.getContents());
        System.out.println(Posta.getId());
        System.out.println(bs.recLike(Posta.getId())+" "+bs.recdisLike(Posta.getId()));
        likeNbLb.setText(Integer.toString(bs.recLike(Posta.getId())));
        dislikeNbLb.setText(Integer.toString(bs.recdisLike(Posta.getId())));
        //---------------------------------------------------------------------------------------------

        //  rating.setRating(4);
        try {
            FileInputStream inputstream = new FileInputStream(Posta.getImage());
            System.out.println(ayja);
            Image image = new Image(inputstream);
            imNft.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        BlogsCommentsService articleCommentService = new BlogsCommentsService();
        List<BlogComment> comments = articleCommentService.showCommentsByArticle(Posta);
        if (comments.size() == 0) {
            Label label = new Label("Be the the first comment");
            label.setTextFill(Color.web("white"));
            boxComment.getChildren().add(label);
        } else {
            Node nodes[] = new Node[comments.size()];
            for (int i = 0; i < comments.size(); i++) {
                try {
                    comment = comments.get(i);
                    System.out.println(comment.toString());
                    nodes[i] = FXMLLoader.load(getClass().getResource("Commentarticle.fxml"));
                    boxComment.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    static String censor(String text,
            String word) {

        // Break down sentence by ' ' spaces
        // and store each individual word in
        // a different list
        String[] word_list = text.split("\\s+");

        // A new string to store the result
        String result = "";

        // Creating the censor which is an asterisks
        // "*" text of the length of censor word
        String stars = "";
        for (int i = 0; i < word.length(); i++) {
            stars += '*';
        }

        // Iterating through our list
        // of extracted words
        int index = 0;
        for (String i : word_list) {
            if (i.compareTo(word) == 0) // changing the censored word to
            // created asterisks censor
            {
                word_list[index] = stars;
            }
            index++;
        }

        // join the words
        for (String i : word_list) {
            result += i + ' ';
        }

        return result;
    }

    @javafx.fxml.FXML
    public void addComment(ActionEvent actionEvent) throws IOException {
        if (!tfComment.getText().isEmpty()) {
            BlogsCommentsService articleCommentService = new BlogsCommentsService();
            BlogComment articleComment = new BlogComment();
            String com = tfComment.getText();
            String[] bad = {"fuck", "bad1", "khalil", "sisi", "bad2", "projet", "java"};
            for (int i = 0; i < bad.length; i++) {

                com = censor(com, bad[i]);
                articleComment.setComment(com);
            }

            articleComment.setArticle(Posta);
            Date now = new Date();
            articleComment.setPostDate(now);
            articleComment.setUser(getId_user());
            articleCommentService.addComment(articleComment);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty comment");
            alert.setHeaderText("tyektb ay hkeya ");
            alert.showAndWait();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("postdetails.fxml"));
        Parent root2 = loader.load();

        btnComment.getScene().setRoot(root2);
    }

    @Deprecated
    @FXML
    public void backlist(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeArticles.fxml"));
        Parent root2 = loader.load();

        barticle.getScene().setRoot(root2);
    }

    @FXML
    public void pdfarticle(ActionEvent actionEvent) {
        DataSource cnx = DataSource.getInstance();

        PreparedStatement pst = null;
        ResultSet rs = null;
        String guery = " select * from blog_article";
        try {

            pst = cnx.getCnx().prepareStatement(guery);
            rs = pst.executeQuery();

            String file_name = Posta.getTitle() + ".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(file_name));

            doc.open();
            com.itextpdf.text.Image imgl = com.itextpdf.text.Image.getInstance("images/Blogs/LogoV1.png");
            imgl.scaleAbsoluteWidth(100);
            imgl.scaleAbsoluteHeight(100);
            imgl.setAlignment(com.itextpdf.text.Image.ALIGN_RIGHT);
            doc.add(imgl);
            //   doc.add(new Paragraph(" "));
            //  doc.add(new Paragraph(" "));

            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(Posta.getImage());
            img.scaleAbsoluteWidth(350);
            img.scaleAbsoluteHeight(290);
            img.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(Posta.getTitle(), FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.LIGHT_GRAY)));
            doc.add(new Paragraph(Posta.getCategory(), FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.LIGHT_GRAY)));

            doc.add(img);
            Paragraph Pargr = new Paragraph();
            doc.add(new Paragraph(" "));
            Pargr = (new Paragraph(Posta.getAuthor(), FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.DARK_GRAY)));
            doc.add(Pargr);

            doc.add(new Paragraph(" "));
            //     doc.add(new Paragraph(Posta.getTitle(),FontFactory.getFont(FontFactory.TIMES_BOLD,20,BaseColor.LIGHT_GRAY)));
            //   doc.add(new Paragraph(Posta.getCategory(),FontFactory.getFont(FontFactory.COURIER_BOLD,20,BaseColor.LIGHT_GRAY)));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(Posta.getContents(), FontFactory.getFont(FontFactory.TIMES, 30, BaseColor.BLACK)));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(30);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase(Posta.getAuthor(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBackgroundColor(BaseColor.BLACK);
            doc.add(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(Posta.getDate()), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBackgroundColor(BaseColor.BLACK);
            doc.add(cell);

            System.out.println("done");
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PostdetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PostdetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PostdetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PostdetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Formation details exported to PDF Sheet");
        alert.show();

    }


    @FXML
    public void screenshot(MouseEvent mouseEvent) {
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = Posta.getTitle() + "." + format;

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle captureRect = new Rectangle(0, 0, screenSize.width / 2, screenSize.height / 2);
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("A partial screenshot saved!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void likeFk(MouseEvent event) {
        if(!bs.rechercheLike(getId_user())){
            bs.addLike(Posta.getId());
        }else{
            bs.removeLike();
        }
    }

    @FXML
    private void dislikeFk(MouseEvent event) {
        if(!bs.recherchedisLike(getId_user())){
            bs.adddiLike(Posta.getId());
        }else{
            bs.removedisLike();
        }
    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }
}
