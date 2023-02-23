/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entites.Commentaire;
import entites.Status;
import java.sql.Connection;
import java.sql.SQLException;
import services.CommentaireService;
import services.StatusService;
import utils.MyDB;

/**
 *
 * @author msi
 */
public class test {
    public static void main(String[] args) {
        try {
            /*Status u = new Status(2, 21, "jcp", "bonsoir", "azertyuiop", 1);
            StatusService ps = new StatusService();
            //ps.ajouter(u);
            //ps.modifier(u);
            ps.supprimer(u);
            System.out.println(ps.recuperer(u));*/
            Commentaire u = new Commentaire(2,"Bon A Tous", 1, 1);
            CommentaireService ps = new CommentaireService();
            //ps.ajouter(u);
            ps.modifier(u);
            ps.supprimer(u);
            System.out.println(ps.recuperer(u));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
