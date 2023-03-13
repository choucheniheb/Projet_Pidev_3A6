/*
 * To change this license header, choose License Headers in Project Properties.


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.nourritures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Skander
 */
public class nourrituresService implements IService<nourritures> {

    Connection cnx;

    public nourrituresService() {
        cnx = MyDB.getInstance().getCnx();
    }

    /**
     *
     * @param n
     * @throws SQLException
     */
    @Override
    public void ajouter(nourritures t) throws SQLException {

        String req = "INSERT INTO nourritures(nom_nourriture,origine_nourriture,ingrediant,description_nourriture,type_nourriture,prix_nourriture,id_utilisateur,id_civilisation,image) VALUES('"
                + t.getNom_nourriture() + "','" + t.getOrigine_nourriture() + "','" + t.getIngrediant() + "','" + t.getDescription_nourriture() + "','" + t.getType_nourriture() + "'," + t.getPrix_nourriture() + "," + t.getId_utilisateur() + "," + t.getId_civilisation() + ", ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getImage());
        ps.executeUpdate();
    }

    @Override
    public void modifier(nourritures t) throws SQLException {
        String req = "UPDATE nourritures SET nom_nourriture = ?,origine_nourriture = ?,ingrediant = ?,description_nourriture = ?,type_nourriture = ?,prix_nourriture = ? ,id_utilisateur = ? ,id_civilisation = ? where id_nourriture = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_nourriture());
        ps.setString(2, t.getOrigine_nourriture());
        ps.setString(3, t.getIngrediant());
        ps.setString(4, t.getDescription_nourriture());
        ps.setString(5, t.getType_nourriture());
        ps.setDouble(6, t.getPrix_nourriture());
        ps.setInt(7, t.getId_utilisateur());
        ps.setInt(8, t.getId_civilisation());
        ps.setInt(9, t.getId_nourriture());

        ps.executeUpdate();

    }

    @Override
    public Boolean supprimer(nourritures n) throws SQLException {
        Boolean ok = false;
        try {
            String req = "DELETE FROM nourritures WHERE id_nourriture  = " + n.getId_nourriture();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            ok = true;
        } catch (SQLException ex) {
            Logger.getLogger(civilisationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public List<nourritures> recuperer() throws SQLException {
        ObservableList<nourritures> nourritures = FXCollections.observableArrayList();
        String s = "select * from nourritures";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);

        while (rs.next()) {
            nourritures t = new nourritures();
            t.setPrix_nourriture(rs.getDouble("prix_nourriture"));
            t.setNom_nourriture(rs.getString("nom_nourriture"));
            t.setOrigine_nourriture(rs.getString("origine_nourriture"));
            t.setIngrediant(rs.getString("ingrediant"));
            t.setDescription_nourriture(rs.getString("description_nourriture"));
            t.setType_nourriture(rs.getString("type_nourriture"));
            t.setId_nourriture(rs.getInt("id_nourriture"));
            t.setId_utilisateur(rs.getInt("id_utilisateur"));
            t.setId_civilisation(rs.getInt("id_civilisation"));
            t.setImage(rs.getString(10));
            nourritures.add(t);
        }

        return nourritures;
    }

}
