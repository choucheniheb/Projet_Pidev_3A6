///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package test;

import entities.Abonnement;
import entities.Reservation;
import entities.SMSSender;
//
//
import java.sql.Connection;
import java.sql.SQLException;
import services.AbonnementService;
import services.ReservationService;
import utils.MyDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//
///**
// *
 /* @author wajdi
// */
public class test {
    public static void main(String[] args) throws SQLException {
       
       try {
          Reservation r = new Reservation("2023-03-22", 5.5, 1, 4, 25);
          ReservationService rs = new ReservationService();
        rs.ajouter(r);
         //SMSSender ss = new SMSSender();
       //ss.SMSSENDER(4);
//////           ps.modifier(r);
rs.supprimer(r);
        System.out.println(rs.recuperer());
////            Abonnement a = new Abonnement("2023-03-20", "2024-03-20", "silver",4);
////            AbonnementService ps = new AbonnementService();
////            ps.ajouter(a);
//////           ps.modifier(a);
//////            ps.supprimer(a);
////            System.out.println(ps.recuperer());
       } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    }
    
    }

