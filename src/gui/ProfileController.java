/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Permission;
import entities.Role;
import entities.Utilisateur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import services.PasswordEncryption;
import services.PermissionService;
import services.RoleService;
import services.UtilisateurService;
import test.TestFX;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Ihebc
 */
public class ProfileController implements Initializable {

    @FXML
    private BorderPane formProfile;
    @FXML
    private Button editProfileProfile;
    @FXML
    private HBox pofileProfile;
    @FXML
    private HBox profileToFomPassword;
    @FXML
    private HBox settingsProfile;
    @FXML
    private HBox disconnectProfile;
    @FXML
    private TextField firstNameProfile;
    @FXML
    private TextField lastNameProfile;
    @FXML
    private TextField userNameProfil;
    @FXML
    private TextField dateBirthdayProfile;
    @FXML
    private TextField phonePofile;
    @FXML
    private TextField emailProfile;
    @FXML
    private Button saveChangesProfile;
    @FXML
    private BorderPane formPassword;
    @FXML
    private Button editProfilePassword;
    @FXML
    private HBox passwordToFomProfile;
    @FXML
    private HBox passwordPassword;
    @FXML
    private HBox settingsPasword;
    @FXML
    private HBox disconnectPassword;
    @FXML
    private TextField currentPassword;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField confirmPassword;
    @FXML
    private Button saveChangesPassword;
    @FXML
    private BorderPane formUserList;
    @FXML
    private Button editProfilePassword1;
    @FXML
    private HBox settingsPasword1;
    @FXML
    private HBox disconnectPassword1;
    @FXML
    private TableView<Utilisateur> userListTv;
    @FXML
    private TableColumn<Utilisateur, String> firstNameTv;
    @FXML
    private TableColumn<Utilisateur, String> lastNameTv;
    @FXML
    private TableColumn<Utilisateur, String> emailTv;
    @FXML
    private TableColumn<Utilisateur, String> phoneNumberTv;
    @FXML
    private TableColumn<Utilisateur, String> pseudoTv;
    @FXML
    private TableColumn<Utilisateur, String> birthdayTv;
    @FXML
    private TableColumn<Utilisateur, String> roleTv;
    @FXML
    private TableColumn<Utilisateur, Button> deleteTv;

    /**
     * Initializes the controller class.
     */
    UtilisateurService ps = new UtilisateurService();
    @FXML
    private HBox userListProfile;
    @FXML
    private HBox userListPassword;
    @FXML
    private HBox userListUser;
    @FXML
    private HBox passwordUserList;
    @FXML
    private HBox profileUserList;
    @FXML
    private HBox addRolesProfile;
    @FXML
    private HBox addRolesPassword;
    @FXML
    private HBox addRolesUserList;
    @FXML
    private BorderPane formAddRoles;
    @FXML
    private Button editProfileProfile1;
    @FXML
    private HBox pofileAddRoles;
    @FXML
    private HBox PasswordAddRoles;
    @FXML
    private HBox settingsAddRoles;
    @FXML
    private HBox userListAddRoles;
    @FXML
    private HBox addRolesAddRoles;
    @FXML
    private HBox disconnectProfile1;
    @FXML
    private TextField rolesName;
    @FXML
    private TextArea rolesDescription;
    @FXML
    private VBox vboxPermission;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button saveRole;
    @FXML
    private ImageView imageProfile;
    @FXML
    private ImageView imageProfile1;
    @FXML
    private ImageView imageProfile2;
    @FXML
    private ImageView imageProfile3;
    @FXML
    private Label nomEtPrenom;
    @FXML
    private Label nomEtPrenom1;
    @FXML
    private Label nomEtPrenom2;
    @FXML
    private Label nomEtPrenom3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update();
    }

    private void delete() {

        deleteTv.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (ps.supprimer(userListTv.getItems().get(getIndex()))) {
                                    userListTv.getItems().remove(getIndex());
                                    userListTv.refresh();
                                    
                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    @FXML
    private void switchForm(MouseEvent event) {
        if (event.getSource() == profileToFomPassword) {
            formPassword.setVisible(true);
            formProfile.setVisible(false);
        } else if (event.getSource() == passwordToFomProfile) {
            formPassword.setVisible(false);
            formProfile.setVisible(true);
        } else if (event.getSource() == userListProfile) {
            formUserList.setVisible(true);
            formProfile.setVisible(false);
        } else if (event.getSource() == userListPassword) {
            formPassword.setVisible(false);
            formUserList.setVisible(true);
        } else if (event.getSource() == profileUserList) {
            formUserList.setVisible(false);
            formProfile.setVisible(true);
        } else if (event.getSource() == passwordUserList) {
            formPassword.setVisible(true);
            formUserList.setVisible(false);
        } else if (event.getSource() == pofileAddRoles) {
            formProfile.setVisible(true);
            formAddRoles.setVisible(false);
        } else if (event.getSource() == PasswordAddRoles) {
            formPassword.setVisible(true);
            formAddRoles.setVisible(false);
        } else if (event.getSource() == userListAddRoles) {
            formUserList.setVisible(true);
            formAddRoles.setVisible(false);
        } else if (event.getSource() == addRolesUserList) {
            formUserList.setVisible(false);
            formAddRoles.setVisible(true);
        } else if (event.getSource() == addRolesPassword) {
            formAddRoles.setVisible(true);
            formPassword.setVisible(false);
        } else if (event.getSource() == addRolesProfile) {
            formAddRoles.setVisible(true);
            formProfile.setVisible(false);
        }
    }

    @FXML
    private void disconnect(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage = (Stage) saveChangesPassword.getScene().getWindow();
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
            File file = new File("password.txt");
            File emptyFile = new File(file.getParent(), "empty.txt");
            emptyFile.createNewFile();
            file.delete();
            emptyFile.renameTo(file);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void saveChange(ActionEvent event) throws Exception {
        if (event.getSource() == saveChangesProfile) {
            try {
                Utilisateur u = new Utilisateur(TestFX.getId_user(), firstNameProfile.getText(), lastNameProfile.getText(), emailProfile.getText(), phonePofile.getText(), userNameProfil.getText(), dateBirthdayProfile.getText());
                UtilisateurService ps = new UtilisateurService();
                ps.modifierPofile(u);
                Alert alert;
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information Message");
                alert.setHeaderText(null);
                alert.setContentText("successfully Update account!");
                alert.showAndWait();
                update();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == saveChangesPassword) {
            try {
                Connection con = MyDB.getInstance().getCnx();
                PreparedStatement pr;
                ResultSet res;
                Utilisateur u = new Utilisateur(TestFX.getId_user(), confirmPassword.getText());
                String req1 = "select mtp from utilisateurs where id_utilisateur= ? and mtp= ?";
                UtilisateurService ps1 = new UtilisateurService();
                pr = con.prepareStatement(req1);
                pr.setInt(1, TestFX.getId_user());
                pr.setString(2, PasswordEncryption.encryptPassword(currentPassword.getText(), "hidden tunisia"));
                res = pr.executeQuery();
                System.out.println(TestFX.getId_user());
                if (res.next() && newPassword.getText().equals(confirmPassword.getText()) && newPassword.getText().length() > 7 && newPassword.getText().length() < 33) {
                    ps1.modifierPassword(u);
                    Alert alert;
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Update password!");
                    alert.showAndWait();
                    currentPassword.setText("");
                    newPassword.setText("");
                    confirmPassword.setText("");
                } else {
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("password not Good please retry");
                    alert.showAndWait();
                    currentPassword.setText("");
                    newPassword.setText("");
                    confirmPassword.setText("");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        update();
    }

    @FXML
    private void saveRole(ActionEvent event) {
        Role r = new Role();
        RoleService rs = new RoleService();
        r.setNomRole(rolesName.getText());
        r.setDiscriptionRole(rolesDescription.getText());
        List<Permission> p = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                String nom = checkBox.getText();
                int valeur = (int) checkBox.getUserData();
                boolean estCoche = checkBox.isSelected();
                if (estCoche == true) {
                    Permission p1 = new Permission();
                    p1.setIdPermission(valeur);
                    p.add(p1);
                }
            }
        }
        r.setPermission(p);
        try {
            rs.ajouter(r);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully Add Role!");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File imageFile;

    @FXML
    public void handle(ActionEvent event) {
        // Ouvrir une boîte de dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        imageFile = fileChooser.showOpenDialog(null);

        if (imageFile != null) {
            try {
                UtilisateurService us = new UtilisateurService();
                // Lire l'image et la stocker dans un dossier temporaire
                Image image = new Image(new FileInputStream(imageFile));

                // Insérer le chemin d'accès de l'image dans la base de données
                us.ajouterImage(imageFile.getAbsolutePath());
                System.out.println(imageFile.getAbsolutePath());

                // Afficher l'image dans l'interface utilisateur
                imageProfile.setImage(image);
                imageProfile1.setImage(image);
                imageProfile2.setImage(image);
                imageProfile3.setImage(image);
            } catch (IOException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void update() {
        try {
            PermissionService p = new PermissionService();
            List<Permission> per = p.recuperer();
            int i = 0, j = 0;
            for (Permission p1 : per) {
                CheckBox checkBox = new CheckBox(p1.getNomPermission());
                checkBox.setSelected(false); // Ne pas cocher automatiquement
                checkBox.setUserData(p1.getIdPermission());
                if (i % 6 == 0 && i != 0) {
                    i = 0;
                    j++;
                }
                GridPane.setConstraints(checkBox, i, j); // Définir la position de la checkbox dans le GridPane
                i++;
                gridPane.getChildren().add(checkBox); // Ajouter la checkbox au GridPane
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }

        //chercher par id
        try {
            Utilisateur u = ps.chercherUtlisateur(TestFX.getId_user());
            firstNameProfile.setText(u.getNom_utilisateur());
            lastNameProfile.setText(u.getPrenom_utilisateur());
            userNameProfil.setText(u.getPseudo());
            dateBirthdayProfile.setText(u.getDate_naissance());
            phonePofile.setText(u.getNumero_telephone());
            emailProfile.setText(u.getMail_utilisateur());
            File file = new File(u.getImage_user());
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageProfile.setImage(image);
            imageProfile1.setImage(image);
            imageProfile2.setImage(image);
            imageProfile3.setImage(image);
            nomEtPrenom.setText(u.getNom_utilisateur() + " " + u.getPrenom_utilisateur());
            nomEtPrenom1.setText(u.getNom_utilisateur() + " " + u.getPrenom_utilisateur());
            nomEtPrenom2.setText(u.getNom_utilisateur() + " " + u.getPrenom_utilisateur());
            nomEtPrenom3.setText(u.getNom_utilisateur() + " " + u.getPrenom_utilisateur());
            Role r = ps.chercherRoleUtlisateur(u.getId_utilisateur());
            System.out.println(r.getNomRole());
            formProfile.setVisible(true);
            formAddRoles.setVisible(false);
            formPassword.setVisible(false);
            formUserList.setVisible(false);
            if ("Admin".equals(r.getNomRole())) {
                userListProfile.setVisible(true);
                userListPassword.setVisible(true);
                userListUser.setVisible(true);
                addRolesProfile.setVisible(true);
                addRolesPassword.setVisible(true);
            } else {
                userListProfile.setVisible(false);
                userListPassword.setVisible(false);
                userListUser.setVisible(false);
                addRolesProfile.setVisible(false);
                addRolesPassword.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //affichage liste users
        List<Utilisateur> utilisateur;
        try {
            utilisateur = ps.recuperer();
            for (Utilisateur u : utilisateur) {
                u.setNom_Role(ps.chercherRoleUtlisateur(u.getId_utilisateur()).getNomRole());
            }
            ObservableList<Utilisateur> olp = FXCollections.observableArrayList(utilisateur);
            userListTv.setItems(olp);
            firstNameTv.setCellValueFactory(new PropertyValueFactory("nom_utilisateur"));
            lastNameTv.setCellValueFactory(new PropertyValueFactory("prenom_utilisateur"));
            emailTv.setCellValueFactory(new PropertyValueFactory("mail_utilisateur"));
            phoneNumberTv.setCellValueFactory(new PropertyValueFactory("numero_telephone"));
            pseudoTv.setCellValueFactory(new PropertyValueFactory("pseudo"));
            birthdayTv.setCellValueFactory(new PropertyValueFactory("date_naissance"));
            roleTv.setCellValueFactory(new PropertyValueFactory("nom_Role"));
            this.delete();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }

}
