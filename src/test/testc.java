/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Utilisateur;
import entities.circuit;
import entities.evenements;
import entities.invites;
import java.sql.SQLException;
import services.circuitCrud;
import services.evenementCrud;
import services.inviteCrud;

/**
 *
 * @author MSI
 */
public class testc {
    
    
    
    public static void main(String[] args) {
        
        
         Utilisateur user = new Utilisateur(1, 1, "samar", "ajmi", "sama@gmail.com", "26404384", "SAMOURA", "ESPRIT", "2000-01-04");
         
//        evenements event = new evenements("test", "test", "2023-12-12", "test", 125.0, 1, "test", 1);
//
  //      evenementCrud ec = new evenementCrud() ;
         //ec.ajouter(event);
    //     ec.modifier("ZAAB", "test", "2023-12-12", "test", 125.0 , 1, "test", 1, 13);
        // ec.supprimer(1);
      //  System.out.println(ec.afficher());



     circuit c = new circuit("Mariotte", "2023-04-15", "2023-04-16", 70,"anniv", 4, user, "ANNIV");
         circuitCrud cc = new circuitCrud();
        cc.ajouter(c);
    //  cc.modifier("Mariotte", "2023-04-15", "2023-04-16", 70,"HI", 4,"anniv", 4, "ANNIV",6);;
     // cc.supprimer(8); 
      // System.out.println(cc.afficher());

 //         invites i = new invites("iheb", "ch", "motreb");
 //        inviteCrud ic = new inviteCrud();
 //         ic.ajouter(i);
  //      ic.modifier("omar", "saya", "motreb", 5);
  //         ic.supprimer(4);
   //         System.out.println(ic.afficher());


//  planning p = new planning(1,1 , "tej", "4 seasons", "tunis");
// planningCrud pc = new planningCrud();
//    pc.ajouter(p);
//        pc.modifier(1, 1, "tej", "4seasons", "Tunis",1);
//        pc.supprimer(1);
//System.out.println(pc.afficher());


//          visites v = new visites(1, 1);
//       visiteCrud vc = new visiteCrud();
//          vc.ajouter(v);
//        vc.modifier(1, 1, 1);
//      vc.supprimer(1);
//        System.out.println(vc.afficher());
    }
    
    
    
    
}







