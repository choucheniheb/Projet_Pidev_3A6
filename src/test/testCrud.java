/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Civilisation;
import entities.Nourritures;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Services.civilisationService;
import Services.nourrituresService;





 
 // @author Nourh
 
public class testCrud {
    
    
    public static void main(String[] args) throws SQLException {
       
        
            Nourritures n = new Nourritures("jjjjj", "aaaaaa", "iiiiii", "ddddd", "ttttt", 2.5, 4, 3);
            nourrituresService ps = new nourrituresService();
            ps.ajouter(n);
//            civilisation c = new civilisation(6,"nourhene" , "doga", "belle", "2023-02-21","2024-02-21",4);
//            civilisationService ps = new civilisationService();
//            //ps.ajouter(c);
//           // ps.modifier(c);
//             ps.supprimer(c);
//            System.out.println(ps.recuperer(c));
       
    }
    
}  


