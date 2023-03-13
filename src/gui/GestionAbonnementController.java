/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Abonnement;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.AbonnementService;
import utils.MyDB;


/**
 * FXML Controller class
 *
 * @author wajdi
 */
public class GestionAbonnementController implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBox;
 //private Label myLabel;
	private String[] food = {"Silver","Gold","Platinium"};
    @FXML
    private Button AjouteAbonnementBtn;
    @FXML
    private DatePicker date_debutab_picker;
    @FXML
    private DatePicker date_expab_picker;
    private Pane formGestion;
    private Pane formAjout;
    private Pane formAffichage;
    @FXML
    private Button swRetourGestion2;
    @FXML
    private Button swRetourGestion1;
    @FXML
    private Pane formAjout1;
    @FXML
    private DatePicker date_debutab_picker1;
    @FXML
    private DatePicker date_expab_picker1;
    @FXML
    private ChoiceBox<?> myChoiceBox1;
    @FXML
    private Button AjouteAbonnementBtn1;
    @FXML
    private Button swajoutabonnement;
    @FXML
    private Button swaffichageabonnement;
    @FXML
    private Pane formGestion1;
    @FXML
    private Pane formAffichage1;
    @FXML
    private TableView<Abonnement> AbonnementTV;
    @FXML
    private TableColumn<Abonnement, String> id_abonnement;
    @FXML
    private TableColumn<Abonnement, String> date_debut;
    @FXML
    private TableColumn<Abonnement, String> date_exp;
    @FXML
    private TableColumn<Abonnement, String> categoriec;
    @FXML
    private TableColumn<Abonnement, String> id_utlisateur;
    @FXML
    private TableColumn<Abonnement, Button> delete;
   
    AbonnementService ps =new AbonnementService();
    @FXML
    private Pane formAjout2;
    @FXML
    private Button swRetourGestion3;
    @FXML
    private TableColumn<Abonnement, Button> qr_code;
    @FXML
    private Button createpdf;
    Connection cnx;
    @FXML
    private Label labelNameUser;
    @FXML
    private ImageView img_User;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            myChoiceBox.getItems().addAll(food);
            myChoiceBox.setOnAction(this::getFood);
             update();
              this.qr_code();
            this.delete();
        } catch (SQLException ex) {
            Logger.getLogger(GestionAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void delete() {
        
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Delete");
                        b.setOnAction((event) -> {
                       
                            try {
                                if (ps.supprimer(AbonnementTV.getItems().get(getIndex())))
                                {
                                    AbonnementTV.getItems().remove(getIndex());
                                    AbonnementTV.refresh();
                                }
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                            

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
    
    public void getFood(ActionEvent event) {
		
		String myFood = myChoiceBox.getValue();
		//myLabel.setText(myFood);
	}
    
    @FXML
    private void switchForm(ActionEvent event) {
        if (event.getSource() == swajoutabonnement) {
            formAjout2.setVisible(true);
            formGestion1.setVisible(false);
        } else if (event.getSource() == swaffichageabonnement) {
            formAffichage1.setVisible(true);
            formGestion1.setVisible(false);
        } else if (event.getSource() == swRetourGestion3) {
            formAjout2.setVisible(false);
            formGestion1.setVisible(true);
        }
        else if (event.getSource() == swRetourGestion1) {
            formAjout1.setVisible(false);
            formGestion1.setVisible(true);
        }
        
        else if (event.getSource() == swRetourGestion2) {
            formAffichage1.setVisible(false);
            formGestion1.setVisible(true);
        }
    }

    
    
               AbonnementService ps1 =new AbonnementService();
 
    @FXML
    private void AjouterAbonnement(ActionEvent event) {
        try {
            Abonnement a = new Abonnement(date_debutab_picker.getValue().toString(),date_expab_picker.getValue().toString(), myChoiceBox.getValue().toString(), 4);
            ps.ajouter(a);
             update();
            System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    
    private void update() throws SQLException {
        List<Abonnement> abonnement= ps.recuperer();
            ObservableList<Abonnement> olp= FXCollections.observableArrayList(abonnement);
            AbonnementTV.setItems(olp);
            id_abonnement.setCellValueFactory(new PropertyValueFactory("id_ab"));
            date_debut.setCellValueFactory(new PropertyValueFactory("date_ab"));
            date_exp.setCellValueFactory(new PropertyValueFactory("date_exp"));
            categoriec.setCellValueFactory(new PropertyValueFactory("categorie"));
            id_utlisateur.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
    }
    
    private void qr_code() {

        qr_code.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("qr code");
                        b.setOnAction((event) -> {
                            
                            AbonnementService rs=new AbonnementService();
                            Abonnement ab = new Abonnement();
                            try {
                                ab= rs.chercherAbonnement(AbonnementTV.getItems().get(getIndex()));
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionReservationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            String data = "id: "+ab.getId_ab()+" date de debut: "+ab.getDate_ab()+" date d'expiration: "+ab.getDate_exp()+" id utilisateur: "+ab.getId_utilisateur();
                            int size = 250;
                            String fileType = "png";
                            File qrFile = new File("C:\\Users\\msi\\Documents\\NetBeansProjects\\FinalPiDev\\src\\gui\\image\\qrcode." + fileType);
                            try {
                                // Créez une instance de la classe QRCodeWriter
                                QRCodeWriter qrCodeWriter = new QRCodeWriter();

                                // Définissez les paramètres d'encodage du QR code
                                ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
                                java.util.Map<EncodeHintType, Object> hints = new java.util.HashMap<EncodeHintType, Object>();
                                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                                hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);

                                // Encodez les données dans le QR code
                                BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size, hints);

                                // Enregistrez le QR code dans un fichier image
                                MatrixToImageWriter.writeToFile(bitMatrix, fileType, qrFile);

                                System.out.println("QR code généré avec succès !");
                                
                                JFrame frame = new JFrame();
                                ImageIcon icon = new ImageIcon("C:\\Users\\msi\\Documents\\NetBeansProjects\\FinalPiDev\\src\\gui\\image\\qrcode." + fileType);
                                JLabel label = new JLabel(icon);
                                frame.add(label);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.pack();
                                frame.setVisible(true); 
                            } catch (WriterException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    @FXML
   
        
    private void createpdf(ActionEvent event) throws SQLException {
        try {
       Document doc = new Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\msi\\Documents\\NetBeansProjects\\FinalPiDev\\src\\gui\\image\\Abonnement.pdf"));
       doc.open();
       
//       Image img = Image.getInstance("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\img\\Exchange.png12.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_RIGHT);
//       doc.add(img);
       
       doc.add(new Paragraph(" "));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("Liste des Abonnements", font);
       p.setAlignment(Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));
       
       PdfPTable tabpdf = new PdfPTable(3);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
          
       cell = new PdfPCell(new Phrase("date de debut d'abonnement", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("date d'expiration", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
        cell = new PdfPCell(new Phrase("categorie", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
   
       
       Connection con2 = MyDB.getInstance().getCnx();
       
        String req = "SELECT date_ab, date_exp, categorie_ab from abonnements";
        Statement st = con2.createStatement();
         ResultSet rs2 =  st.executeQuery(req);
      
       while(rs2.next()){
           System.out.println(rs2.getString("date_ab"));
           cell = new PdfPCell(new Phrase(rs2.getString("date_ab"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs2.getString("date_exp"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           cell = new PdfPCell(new Phrase(rs2.getString("categorie_ab"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           
       }
       
       doc.add(tabpdf);
       JOptionPane.showMessageDialog(null, "Success !!");
       doc.close();
       Desktop.getDesktop().open(new File("C:\\Users\\msi\\Documents\\NetBeansProjects\\FinalPiDev\\src\\gui\\image\\Abonnement.pdf"));
       
       Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your document has been saved as PDF !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void swProfileUser(MouseEvent event) {
    }
    
    
    }


    
