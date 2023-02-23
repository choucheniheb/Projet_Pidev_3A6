/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author msi
 */
public class CommentaireService implements IService<Commentaire>{
    
    Connection cnx ;
   
    public CommentaireService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commentaire t) throws SQLException {
        String req = "INSERT INTO Commentaires (contenu_commentaire, id_utilisateur, id_status) VALUES ('"+t.getContenu_commentaire()+"',"+t.getId_utilisateur()+","+t.getId_status()+");";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Commentaire t) throws SQLException {
        String req = "UPDATE Commentaires SET contenu_commentaire = ?,id_utilisateur = ?,id_status = ? where id_commentaire = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getContenu_commentaire());
        ps.setInt(2, t.getId_utilisateur());
        ps.setInt(3, t.getId_status());
        ps.setInt(4, t.getId_commentaire());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Commentaire t) throws SQLException {
        String req = "DELETE FROM Commentaires WHERE id_commentaire = "+t.getId_commentaire();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Commentaire> recuperer(Commentaire t) throws SQLException {
        List<Commentaire> myList = new  ArrayList<> ();
        String requete = "select * from Commentaires " ;
        Statement st = cnx.createStatement() ;
        ResultSet rs = st.executeQuery(requete);
        while(rs.next())
        {
            Commentaire s = new  Commentaire() ;
            s.setId_commentaire(rs.getInt(1));
            s.setContenu_commentaire(rs.getString(2));
            s.setDate_commentaire(rs.getString(3));
            s.setId_utilisateur(rs.getInt(4));
            s.setId_status(rs.getInt(5));
            myList.add(s) ;
        }
        return myList;
    }
    
}
