/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.circuit;
import entities.evenements;
import entities.invites;
import entities.planning;
import entities.visites;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.circuitCrud;
import services.evenementCrud;
import services.inviteCrud;
import services.planningCrud;
import services.visiteCrud;

/**
 *
 * @author MSI
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Code pour initialiser votre interface

        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Accueil_Circuit_Evenement.fxml"));

        // Créer une scène à partir de la racine
        Scene scene = new Scene(root);

        // Définir les propriétés de la scène et l'afficher
        primaryStage.setTitle("Mon application JavaFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        
        
        
        launch(args);

        
        
        
        
        
        
        
       
    }

   
    
}



  
       
 //            evenements event = new evenements("test", "type", "test", 125.0, 1, "hjhjhj", 4);
  //      evenementCrud ec = new evenementCrud() ;
   //     ec.ajouter(event);
//        ec.modifier("test", "test", "2023-12-12", "tat", 125.3, 1, "hjhjhj", 4, 1);
//         ec.supprimer(1);
 //          System.out.println(ec.afficher());

 
 
  //     circuit c = new circuit("Mariotte", "2023-04-15", "2023-04-16", 70,"anniv", 4, 4, "ANNIV");
//         circuitCrud cc = new circuitCrud();
//        cc.ajouter(c);
//        cc.modifier("Mariotte", "2023-04-15", "2023-04-16", 70, "annnniv", 4, "anniv", 4, "ANNIV", 1);
//       cc.supprimer(1); pas possible lier a planning
 //        System.out.println(cc.afficher());

//          invites i = new invites("dhia", "ajmi", "motreb");
//         inviteCrud ic = new inviteCrud();
//          ic.ajouter(i);
//        ic.modifier("omar", "saya", "motreb", 2);
//           ic.supprimer(1);
//            System.out.println(ic.afficher());


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





















//
//import entities.Permission;
//import entities.Role;
//import entities.Utilisateur;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import services.PermissionService;
//import services.RoleService;
//import services.UtilisateurService;
//
//
///**
// *
// * @author Skander
// */
//public class Test {
//    
//    
//    public static void main(String[] args) {
//       
//        try {
//            //Utilisateur u = new Utilisateur(2, "iheb", "chouchen", "chouchen.iheb@esprit.tn", "26836763", "ihebch", "iheb@1122", "2000-07-07");
//            //UtilisateurService ps = new UtilisateurService();
//            //ps.ajouter(u);
//            //ps.modifier(u);
//            //ps.supprimer(u);
//            //System.out.println(ps.recuperer(u));
//            /*Role r= new Role(15,"mod", "moderator");
//            RoleService ps=new RoleService();
//            List<Permission> p = new ArrayList<>();
//            Permission pe1=new Permission(11, "perm1", "descriptionPermission1", 1);
//            Permission pe2=new Permission(12, "perm2", "descriptionPermission2", 1);
//            p.add(pe1);
//            p.add(pe2);
//            r.setPermission(p);
//            ps.ajouter(r);
//            ps.modifier(r);
//            System.out.println(ps.recuperer(r));*/
//            Permission u = new Permission(13,"perm_ajouter", "permet de ajouter les utiliateur", 1);
//            PermissionService ps = new PermissionService();
//            //ps.ajouter(u);
//            //ps.modifier(u);
//            ps.supprimer(u);
//            System.out.println(ps.recuperer(u));
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    
//}
