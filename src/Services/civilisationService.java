package Services;

import entities.Civilisation;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class civilisationService implements IService<Civilisation> {
    Connection cnx;
    public civilisationService() throws SQLException {
        cnx = MyDB.getInstance().getCnx();
    }




    @Override
    public void ajouter(Civilisation t) throws SQLException {
         String req = "INSERT INTO Civilisation(nom_civilisation, nom_monument, description_civilisation, date_debut_civilisation,date_fin_civilisation,id_utilisateur) VALUES (?,?,?,?,?,?)";

              
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_civilisation());
        ps.setString(2, t.getNom_monument());
        ps.setString(3, t.getDescription_civilisation());
        ps.setString(4,  t.getDate_debut_civilisation());
        ps.setString(5, t.getDate_fin_civilisation());
        ps.setInt(6, t.getId_utilisateur());
       
        ps.executeUpdate();
   
    }

    @Override
    public void modifier(Civilisation t) throws SQLException {
       
        String req = "UPDATE civilisation SET nom_civilisation = ?,nom_monument= ?,description_civilisation = ?,date_debut_civilisation = ?,date_fin_civilisation = ?,id_utilisateur = ? where id_civilisation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_civilisation());
        ps.setString(2, t.getNom_monument());
        ps.setString(3, t.getDescription_civilisation());
        ps.setString(4, t.getDate_debut_civilisation());
        ps.setString(5, t.getDate_fin_civilisation());
        ps.setInt(6, t.getId_utilisateur());
        ps.setInt(7, t.getId_civilisation());
       
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Civilisation t) throws SQLException {

            PreparedStatement s = cnx.prepareStatement("delete from Civilisation where  id_civilisation=?");
            s.setInt(1, t.getId_civilisation());
            s.executeUpdate();
            System.out.println("succesful ");
    }
    
    
    
    @Override
    public List<Civilisation> recuperer() throws SQLException {
         List<Civilisation> Civilisations = new ArrayList<>();
        String s = "select * from Civilisation";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Civilisation u = new Civilisation();
            u.setId_civilisation(rs.getInt("id_civilisation"));
            u.setNom_civilisation(rs.getString("Nom_civilisation"));
            u.setNom_monument(rs.getString("Nom_monument"));
            u.setDescription_civilisation(rs.getString("Description_civilisation"));
            u.setDate_debut_civilisation(rs.getString("Date_debut_civilisation"));
            u.setDate_fin_civilisation(rs.getString("Date_fin_civilisation"));
            u.setId_civilisation(rs.getInt("id_utilisateur"));
           
            Civilisations.add(u);
        }
        return Civilisations;
    }
}
