/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.civilisation;
import entities.nourritures;
import java.sql.SQLException;
import services.civilisationService;
import services.nourrituresService;


/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) {
       
        try {
            nourritures n = new nourritures("jjjjj", "aaaaaa", "iiiiii", "ddddd", "ttttt", 2.5, 4, 3);
            nourrituresService ps = new nourrituresService();
            ps.ajouter(n);
//            civilisation c = new civilisation(6,"nourhene" , "doga", "belle", "2023-02-21","2024-02-21",4);
//            civilisationService ps = new civilisationService();
//            //ps.ajouter(c);
//           // ps.modifier(c);
//             ps.supprimer(c);
//            System.out.println(ps.recuperer(c));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
