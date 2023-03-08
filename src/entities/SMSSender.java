/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.MyDB;

/**
 *
 * @author wajdi
 */
public class SMSSender {
   
    
    Connection cnx ; 
    public SMSSender() {
    cnx = MyDB.getInstance().getCnx(); 
}
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "ACcc354458dd9c6ecca15900bb58fd5107";
  public static final String AUTH_TOKEN = "66120dff551be7dd5e583c32fe34d92c";

  public  void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
     PhoneNumber clientPhoneNumber = new PhoneNumber("+21658749090");
        Message message = Message.creator(clientPhoneNumber, new PhoneNumber(""), "Votre commande a été bien reçue").create();
        System.out.println(message.getSid());
  }
  
      public void SMSSENDER(int id_utilisateur) {
    // Remplacez les informations de compte et de numéro de téléphone par les vôtres
    String accountSid = "ACcc354458dd9c6ecca15900bb58fd5107";
    String authToken = "66120dff551be7dd5e583c32fe34d92c";
 
      try {
        // Récupérer le numéro de téléphone de l'utilisateur à partir de la base de données
        String clientPhoneNumber = recupererUserPhone(id_utilisateur);
        
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
            new PhoneNumber("+216"+clientPhoneNumber),
            new PhoneNumber("+15075288342"),
            "Votre réservation est confirmée"
        ).create();
        
        System.out.println("SID du message : " + message.getSid());
    } catch (Exception ex) {
        System.out.println("Erreur : " + ex.getMessage());
    }

      }
      
      
      public String recupererUserPhone(int id_utilisateur) throws SQLException {
    try {
        String req = "SELECT numero_telephone from utilisateurs WHERE id_utilisateur=? " ;
        PreparedStatement pst = cnx.prepareStatement(req);

        pst.setInt(1, id_utilisateur);
        ResultSet rs = pst.executeQuery();
        
        // Si le numéro de téléphone existe, renvoyer sa valeur
        if (rs.next()) {
            return rs.getString("numero_telephone");
        } else {
            throw new SQLException("Numéro de téléphone introuvable pour l'utilisateur avec l'ID " + id_utilisateur);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération du numéro de téléphone de l'utilisateur : " + ex.getMessage());
        throw ex;
    }
}
}

