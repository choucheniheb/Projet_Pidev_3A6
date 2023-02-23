/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Role;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.RoleService;
import services.UtilisateurService;
import test.TestFX;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Ihebc
 */
public class LoginController implements Initializable {

    @FXML
    private BorderPane Signin_form;
    @FXML
    private Button si_createAcountBtn;
    @FXML
    private Button si_loginBtn;
    @FXML
    private TextField si_username;
    @FXML
    private TextField si_password;
    @FXML
    private BorderPane signup_form;
    @FXML
    private Button su_loginAccountBtn;
    @FXML
    private TextField su_firstName;
    @FXML
    private TextField su_lastName;
    @FXML
    private Button su_signup_btn;
    @FXML
    private TextField su_email;
    @FXML
    private TextField su_phone;
    @FXML
    private TextField su_password;
    @FXML
    private ChoiceBox<String> su_role;
    @FXML
    private TextField su_pseudo;
    @FXML
    private DatePicker su_birthday;
    
    List<String> nom_roles = new ArrayList<>();
    RoleService rs=new RoleService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        su_role.getItems().addAll(recid());
    }
    
    private List<String> recid(){
        try {
            List<Role> roles = rs.recuperer();
            for(Role r: roles){
                nom_roles.add(r.getNomRole());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom_roles;
    }
    
    Connection con = MyDB.getInstance().getCnx();
    PreparedStatement pr;
    ResultSet res;

    @FXML
    private void loginAccount(ActionEvent event) {
        String req1 = "select pseudo,mtp,id_utilisateur from utilisateurs where pseudo= ? and mtp= ?";
        try {
            Alert alert;
            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();
            } else {
                pr = con.prepareStatement(req1);
                pr.setString(1, si_username.getText());
                pr.setString(2, si_password.getText());
                res = pr.executeQuery();
                if (res.next()) {
                    try {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("successfully Login");
                        alert.showAndWait();
                        TestFX.setId_user(res.getInt("id_utilisateur"));
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Profile.fxml"));
                        Parent root = loader.load();
                        Scene sc = new Scene(root);
                        Stage primaryStage=(Stage) si_loginBtn.getScene().getWindow();;
                        primaryStage.setScene(sc);
                        primaryStage.setTitle("Login");
                        primaryStage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username or password");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void switchForm(ActionEvent event) {
        if (event.getSource() == su_loginAccountBtn) {
            Signin_form.setVisible(true);
            signup_form.setVisible(false);
        } else if (event.getSource() == si_createAcountBtn) {
            Signin_form.setVisible(false);
            signup_form.setVisible(true);
        }
    }

    @FXML
    private void registerAccount(ActionEvent event) {
        try {
            Alert alert;
            if (su_pseudo.getText().isEmpty() || su_password.getText().isEmpty() || su_email.getText().isEmpty() || su_firstName.getText().isEmpty() || su_phone.getText().isEmpty() || su_role.getValue().isEmpty() || su_birthday.getValue().toString().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();
            } else {
                String req1 = "select pseudo from utilisateurs where pseudo= '" + su_pseudo.getText() + "'";
                pr = con.prepareStatement(req1);
                res = pr.executeQuery();
                if (res.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_pseudo.getText() + " is Already used");
                    alert.showAndWait();
                } else {
                    if (su_password.getText().length() < 8 && su_password.getText().length() > 32) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid password,atleast in 8 and 32 charecters needed");
                        alert.showAndWait();
                    } else {
                        Utilisateur u = new Utilisateur(rs.chercherRole(su_role.getValue()).getIdRole(), su_firstName.getText(), su_lastName.getText(), su_email.getText(), su_phone.getText(), su_pseudo.getText(), su_password.getText(), su_birthday.getValue().toString());
                        UtilisateurService ps = new UtilisateurService();
                        ps.ajouter(u);
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("successfully create new account!");
                        alert.showAndWait();
                        Signin_form.setVisible(true);
                        signup_form.setVisible(false);
                        su_birthday.setValue(null);
                        su_email.setText("");
                        su_firstName.setText("");
                        su_lastName.setText("");
                        su_password.setText("");
                        su_phone.setText("");
                        su_pseudo.setText("");
                    }
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
