/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
    UtilisateurService ps=new UtilisateurService();
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Utilisateur u=ps.chercherUtlisateur(TestFX.getId_user());
            firstNameProfile.setText(u.getNom_utilisateur());
            lastNameProfile.setText(u.getPrenom_utilisateur());
            userNameProfil.setText(u.getPseudo());
            dateBirthdayProfile.setText(u.getDate_naissance());
            phonePofile.setText(u.getNumero_telephone());
            emailProfile.setText(u.getMail_utilisateur());
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        //affichage liste users
        List<Utilisateur> utilisateur;
        try {
            utilisateur = ps.recuperer();
            for(Utilisateur u: utilisateur){
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
        }else if (event.getSource() == userListProfile) {
            formUserList.setVisible(true);
            formProfile.setVisible(false);
        }else if (event.getSource() == userListPassword) {
            formPassword.setVisible(false);
            formUserList.setVisible(true);
        }else if (event.getSource() == profileUserList) {
            formUserList.setVisible(false);
            formProfile.setVisible(true);
        }else if (event.getSource() == passwordUserList) {
            formPassword.setVisible(true);
            formUserList.setVisible(false);
        }
    }

    @FXML
    private void disconnect(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage primaryStage=(Stage) saveChangesPassword.getScene().getWindow();;
            primaryStage.setScene(sc);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void saveChange(ActionEvent event) {
        if (event.getSource() == saveChangesProfile) {
            try{
                Utilisateur u = new Utilisateur(TestFX.getId_user(), firstNameProfile.getText(), lastNameProfile.getText(), emailProfile.getText(), phonePofile.getText(), userNameProfil.getText(), dateBirthdayProfile.getText());
                UtilisateurService ps = new UtilisateurService();
                ps.modifierPofile(u);
                Alert alert;
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information Message");
                alert.setHeaderText(null);
                alert.setContentText("successfully Update account!");
                alert.showAndWait();
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }else if (event.getSource() == saveChangesPassword) {
            try{
                Connection con = MyDB.getInstance().getCnx();
                PreparedStatement pr;
                ResultSet res;
                Utilisateur u = new Utilisateur(TestFX.getId_user(),confirmPassword.getText());
                String req1 = "select mtp from utilisateurs where id_utilisateur= ? and mtp= ?";
                UtilisateurService ps = new UtilisateurService();
                pr = con.prepareStatement(req1);
                pr.setInt(1, TestFX.getId_user());
                pr.setString(2, currentPassword.getText());
                res = pr.executeQuery();
                if(res.next() && newPassword.getText().equals(confirmPassword.getText()) && newPassword.getText().length()>8 && newPassword.getText().length()<32){
                    ps.modifierPassword(u);
                    Alert alert;
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Update password!");
                    alert.showAndWait();
                    currentPassword.setText("");
                    newPassword.setText("");
                    confirmPassword.setText("");
                }else{
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Some think not Good please retry");
                    alert.showAndWait();
                    currentPassword.setText("");
                    newPassword.setText("");
                    confirmPassword.setText("");
                }
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
