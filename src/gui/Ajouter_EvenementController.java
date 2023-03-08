/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import entities.evenements;
import entities.invites;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JPanel;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import services.evenementCrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Ajouter_EvenementController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField type;
    @FXML
    private TextField lieux;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date;
    @FXML
    private Button Ajouter_Evenement;
    @FXML
    private Button Retour;
    @FXML
    private SwingNode swingNode;

    private String path;
    File selectedFile;
    /**
     * Initializes the controller class.
     */

    Connection cnx2;
    @FXML
    private ImageView imArt;

    public Ajouter_EvenementController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    Utilisateur user = new Utilisateur(1, 1, "samar", "ajmi", "sama@gmail.com", "26404384", "SAMOURA", "ESPRIT", "2000-01-04");
    invites inv = new invites(1, "DHIA", "AJMI", "MOTREB");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JMapViewer mapViewer = new JMapViewer();

        // Set the initial position of the map
        mapViewer.setDisplayPosition(new Coordinate(34.8516, 10.7605), 7);

// Add a mouse listener to the mapViewer
        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the coordinates of the clicked point
                //Coordinate clickedPoint = mapViewer.getPosition(e.getPoint());
                ICoordinate clickedPoint = mapViewer.getPosition(e.getPoint());
                //org.openstreetmap.gui.jmapviewer.Coordinate clickedPoint = mapViewer.getPosition(e.getPoint());

                // Do something with the clicked coordinates, e.g. create a marker
                MapMarkerDot marker = new MapMarkerDot((Coordinate) clickedPoint);
                mapViewer.addMapMarker(marker);

                // Perform reverse geocoding to get the name of the country
                String countryName = null;
                try {
                    String urlString = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="
                            + clickedPoint.getLat() + "&lon=" + clickedPoint.getLon();
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0");

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONObject json = new JSONObject(response.toString());
                    countryName = json.getString("display_name");
                    // Extract the country name from the display name
                    int index = countryName.lastIndexOf(",");
                    if (index != -1) {
                        countryName = countryName.substring(index + 2);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Print the name of the country
                System.out.println("Selected country: " + countryName);
                lieux.setText(countryName);

            }
        });

        // Create a JPanel and add the mapViewer to it
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mapViewer, BorderLayout.CENTER);

        // Set the JPanel as the content of the SwingNode
        swingNode.setContent(panel);

        Ajouter_Evenement.setOnAction(e -> {

            String descriptionText = description.getText().toLowerCase();
            if (descriptionText.contains("shit") || descriptionText.contains("fuck") || descriptionText.contains("test")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La description contient des mots interdits!");
                alert.show();
            } else if ("".equals(titre.getText()) || "".equals(type.getText()) || "".equals(lieux.getText()) || "".equals(prix.getText()) || "".equals(description.getText()) || "".equals(date.getValue())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Veuillez remplir tous les champs correctement!");

                alert.show();

            } else {

                try {

                    evenementCrud ajout = new evenementCrud();
                    evenements ev = new evenements(titre.getText(), type.getText(), date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), lieux.getText(), Double.parseDouble(prix.getText()), inv, description.getText(), user, path);

                    //verification de l'unicite par le nom de la demande le budget la description et la date limite 
                    String sql = "SELECT * FROM evenements WHERE Titre_evenement = ? AND Type_evenement = ? AND Date_evenement = ? AND Lieux_evenement = ? AND Prix_evenement = ? AND Id_invite = ? AND Description_evenement = ? AND Id_utilisateur = ?";
                    PreparedStatement stmt;
                    stmt = cnx2.prepareStatement(sql);
                    stmt.setString(1, ev.getTitre_evenement());
                    stmt.setString(2, ev.getType_evenement());
                    stmt.setString(3, ev.getDate_evenement());
                    stmt.setString(4, ev.getLieux_evenement());
                    stmt.setDouble(5, ev.getPrix_evenement());
                    stmt.setInt(6, ev.getId_invite());
                    stmt.setString(7, ev.getDescription_evenement());
                    stmt.setInt(8, ev.getId_utilisateur());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Evenement existe deja!");

                        alert.show();
                        return;
                    } else {

                        ajout.ajouter(ev);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Evenement ajoutée avec succés!");

                        alert.show();

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Ajouter_CircuitController.class.getName()).log(Level.SEVERE, null, ex);
                }

//                    // si elle n'existe pas on procede a l ajout 
//                    ajoutDemandeService.ajouterDemandeService(ds);
//
//            Publication p = new Publication(Integer.parseInt(proprietaire.getText()), libelle.getText(),datePub.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Description.getText(),cat.getValue());
//
//            PublicationCrud test = new PublicationCrud() ;
            }
        }
        );

        Retour.setOnAction(Retour -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Gestion_Evenement.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) Retour.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });

    }

    @FXML
    private void addImage(javafx.scene.input.MouseEvent event) {

        //FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", ".jpg", ".png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a photo");
        fileChooser.setInitialDirectory(new File("C:\\Users\\MSI\\Desktop\\Projet_Pidev_3A6\\src\\gui\\image"));
        //fileChooser.getExtensionFilters().add(imageFilter);
        Image image = null;
        try {
            image = new Image(fileChooser.showOpenDialog(null).toURI().toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (image != null) {
            imArt.setImage(image);
            selectedFile = fileChooser.showOpenDialog(null);
            path = selectedFile.getAbsolutePath();
            System.out.println(path);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No photo Selected");
            alert.setHeaderText("Please select a photo");
            alert.showAndWait();
        }
    }

}
