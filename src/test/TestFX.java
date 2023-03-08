/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.PasswordEncryption;
import utils.MyDB;

/**
 *
 * @author Ihebc
 */
public class TestFX extends Application {

    private static int id_user;

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        TestFX.id_user = id_user;
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = null;
        File inputFile = new File("password.txt");
        FileInputStream fileInputStream;
        String password = "", username = "";
        try {
            fileInputStream = new FileInputStream(inputFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            username = bufferedReader.readLine();
            password = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestFX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestFX.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(username + " " + password);
        if (username == null && password == null) {
            loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        } else {
            Connection con = MyDB.getInstance().getCnx();
            PreparedStatement pr;
            ResultSet res;
            String req1 = "select pseudo,mtp,id_utilisateur from utilisateurs where pseudo= ? and mtp= ?";
            try {
                pr = con.prepareStatement(req1);
                pr.setString(1, username);
                pr.setString(2, password);
                res = pr.executeQuery();
                if(res.next()){
                    TestFX.setId_user(res.getInt("id_utilisateur"));
                }
            } catch (Exception ex) {
                Logger.getLogger(TestFX.class.getName()).log(Level.SEVERE, null, ex);
            }
            loader = new FXMLLoader(getClass().getResource("../gui/Profile.fxml"));
        }
        try {
            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
