/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Reservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.ReservationService;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entities.SMSSender;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author wajdi
 */
public class GestionReservationController implements Initializable {

    private Pane formAffichage;
    private Button swRetourGestion1;
    @FXML
    private TableView<Reservation> ReservationTV;
    @FXML
    private TableColumn<Reservation, Integer> id_reservation;
    @FXML
    private TableColumn<Reservation, String> titre_evenement;
    @FXML
    private TableColumn<Reservation, String> Date_reservation;
    @FXML
    private TableColumn<Reservation, Double> prix_reservation;
    @FXML
    private TableColumn<Reservation, Integer> id_evenement;
    @FXML
    private TableColumn<Reservation, Integer> id_utilisateur;
    @FXML
    private TableColumn<Reservation, Integer> id_abonnement;

    /**
     * Initializes the controller class.
     */
    ReservationService ps = new ReservationService();
    @FXML
    private Button jouez;
    @FXML
    private TableColumn<?, ?> delete;
    private Pane formAjout;
    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private DatePicker date_res_picker;
    @FXML
    private Button swRetourGestion;
    @FXML
    private Button AjouteReservationBtn;
    private Pane formGestion;
    @FXML
    private Button swajoutreservation;
    @FXML
    private Button swaffichagereservation;
    private Label myLabel;
    private String[] food = {"circuit", "evenement"};
    private String[] prix = {"50.5", "100.00", "150.00"};
    @FXML
    private ChoiceBox<String> prix_res;
    @FXML
    private Pane formAffichage1;
    @FXML
    private Button swRetourGestion2;
    @FXML
    private Pane formGestion1;
    @FXML
    private Pane formAjout1;

    ReservationService rs = new ReservationService();
    @FXML
    private TableColumn<Reservation, Button> qr_code;
    Connection cnx;
    @FXML
    private Button createpdf;
    @FXML
    private Button Quizbtn;
    
    
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            myChoiceBox.getItems().addAll(rs.chercherEvenement());
            prix_res.getItems().addAll(prix);
            prix_res.setOnAction(this::getPrix);
            update();
            this.delete();
            this.qr_code();
        } catch (SQLException ex) {
            Logger.getLogger(GestionReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchForm(ActionEvent event) {
        if (event.getSource() == swajoutreservation) {
            formAjout1.setVisible(true);
            formGestion1.setVisible(false);
        } else if (event.getSource() == swaffichagereservation) {
            formAffichage1.setVisible(true);
            formGestion1.setVisible(false);
        } else if (event.getSource() == swRetourGestion) {
            formAjout1.setVisible(false);
            formGestion1.setVisible(true);
        } else if (event.getSource() == swRetourGestion1) {
            formAjout.setVisible(false);
            formGestion.setVisible(true);
        } else if (event.getSource() == swRetourGestion2) {
            formAffichage1.setVisible(false);
            formGestion1.setVisible(true);
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
                                if (ps.supprimer(ReservationTV.getItems().get(getIndex()))) {
                                    ReservationTV.getItems().remove(getIndex());
                                    ReservationTV.refresh();
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
        myLabel.setText(myFood);
    }

    public void getPrix(ActionEvent event) {
        String myPrix = myChoiceBox.getValue();
//        myLabel.setText(myPrix);
    }

    ReservationService ps1 = new ReservationService();

    @FXML
    private void AjouterReservation(ActionEvent event) {
        try {
            Reservation r = new Reservation(date_res_picker.getValue().toString(), Double.parseDouble(prix_res.getValue()), 1, 4, 19);
            ps1.ajouter(r);
            System.out.println(ps1.recuperer());
            SMSSender ss = new SMSSender();
            ss.SMSSENDER(4);
            update();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void switchForm(MouseEvent event) {
    }

    private void update() throws SQLException {
        List<Reservation> reservation = ps.recuperer();
        ObservableList<Reservation> olp = FXCollections.observableArrayList(reservation);
        ReservationTV.setItems(olp);
        id_reservation.setCellValueFactory(new PropertyValueFactory("id_res"));
        titre_evenement.setCellValueFactory(new PropertyValueFactory("titre_evenement"));
        Date_reservation.setCellValueFactory(new PropertyValueFactory("date_res"));
        prix_reservation.setCellValueFactory(new PropertyValueFactory("prix_res"));
        id_evenement.setCellValueFactory(new PropertyValueFactory("id_evenement"));
        id_utilisateur.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
        id_abonnement.setCellValueFactory(new PropertyValueFactory("id_ab"));
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
                            
                            ReservationService rs=new ReservationService();
                            Reservation res = new Reservation();
                            try {
                                res= rs.chercherReservation(ReservationTV.getItems().get(getIndex()));
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionReservationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            String data = "id: "+res.getId_res()+" date reservation: "+res.getDate_res()+" prix reservation: "+res.getPrix_res();
                            int size = 250;
                            String fileType = "png";
                            File qrFile = new File("C:\\Users\\wajdi\\OneDrive\\Images\\qrcode." + fileType);
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
                                ImageIcon icon = new ImageIcon("C:\\Users\\wajdi\\OneDrive\\Images\\qrcode." + fileType);
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
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\wajdi\\Documents\\NetBeansProjects\\projet_pidev_3a6\\src\\gui\\images\\pdf.pdf"));
       doc.open();
       
//       Image img = Image.getInstance("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\img\\Exchange.png12.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_RIGHT);
//       doc.add(img);
       
       doc.add(new Paragraph(" "));
       Font font = new Font(FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("Liste des reservations", font);
       p.setAlignment(Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));
       
       PdfPTable tabpdf = new PdfPTable(2);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
          
       cell = new PdfPCell(new Phrase("date_res", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("prix_res", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
   
       
       Connection con1 = MyDB.getInstance().getCnx();;
       String req = "SELECT date_res,prix_res from reservations";
        Statement st = con1.createStatement();
        ResultSet rs1 =  st.executeQuery(req);
        
        
        
        
       while(rs1.next()){
           System.out.println(rs1.getString("date_res"));
           cell = new PdfPCell(new Phrase(rs1.getString("date_res"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs1.getString("prix_res"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           
       }
       
       doc.add(tabpdf);
       JOptionPane.showMessageDialog(null, "Success !!");
       doc.close();
       Desktop.getDesktop().open(new File("C:\\Users\\wajdi\\Documents\\NetBeansProjects\\projet_pidev_3a6\\src\\gui\\images\\pdf.pdf"));
       
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
    private void jouersw(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/jeu.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) jouez.getParent().getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                
            }
                
    }

    @FXML
    private void Quizsw(ActionEvent event) {
         try {
             Stage primaryStage=new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz_animaux.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Abonnement");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   
  
               
        
   

    
    
    
    
  
    
    

}
