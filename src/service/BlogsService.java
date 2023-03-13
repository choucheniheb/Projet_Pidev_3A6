package service;

import entity.BlogArticles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import test.TestFX;
import utils.DataSource;

public class BlogsService {
    public void insertArticle(BlogArticles per){
        String request = "insert into blog_article(title,contents,category,author,date) values('"+per.getTitle()+"','"+per.getContents()+",'"+per.getCategory()+",'"+per.getAuthor()+",'"+per.getDate()+"')";
        try{
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(request);      //insert delete et update (executeQuery for select)
            System.out.println("Article ajouté.");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    //autre methode
    public void ajouterArticle(BlogArticles per){
        String request = "insert into blog_article(title,contents,category,author,date,image) values"+"(?,?,?,?,?,?)";
        try{
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(request);
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            System.out.println(date);
            pst.setString(1, per.getTitle());
            pst.setString(2, per.getContents());
            pst.setString(3, per.getCategory());
            pst.setString(4, per.getAuthor());
            pst.setDate(5,  per.getDate());
            pst.setString(6, per.getImage());
            pst.executeUpdate();
            System.out.println("Article inséré.");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public static List<BlogArticles> listerArticles(){
        List<BlogArticles> myList = new ArrayList();
        String request = "select * from blog_article";
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
                BlogArticles p = new BlogArticles();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("title"));
                p.setContents(rs.getString("contents"));
                p.setCategory(rs.getString("category"));
                p.setAuthor(rs.getString("author"));
                p.setDate(rs.getDate("date"));
                p.setImage(rs.getString("image"));
                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return myList;
    }
    public void deleteArticle(int id){

        String request = "DELETE FROM blog_article WHERE id =?";
        try{
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1,id);
            if(pst.executeUpdate()!=0){
                System.out.println("Article supprimé");}
            else{
                System.out.println("Article n'existe pas.");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void updateArticle (BlogArticles blog , int id){
        String request = "update blog_article set title=? , contents=? , category=? , author=? , date=? " +"where id="+id;
        try{
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(request);
            pst.setString(1, blog.getTitle());
            pst.setString(2, blog.getContents());
            pst.setString(3, blog.getCategory());
            pst.setString(4, blog.getAuthor());
            pst.setDate(5, blog.getDate());
            pst.executeUpdate();
            System.out.println("Article updated");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void addLike(int id){
        String request = "insert into like_dislike(likes,dislike,id_user,id_status) values (1,0,"+TestFX.getId_user()+","+id+")";
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            st.executeUpdate(request);
            System.out.println("Like added");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void removeLike(){
        String request = "update like_dislike set likes = "+ 0 +" where id="+TestFX.getId_user();
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            st.executeUpdate(request);
            System.out.println("Like remove");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void adddiLike(int id){
        String request = "insert into like_dislike(likes,dislike,id_user,id_status) values (0,1,"+TestFX.getId_user()+","+id+")";
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            st.executeUpdate(request);
            System.out.println("disLike added");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void removedisLike(){
        String request = "update like_dislike set dislike="+0+" where id="+TestFX.getId_user();
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            st.executeUpdate(request);
            System.out.println("disLike remove");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public int recLike(int id){
        String request = "select likes from like_dislike where id_status = "+id;
        int s=0;
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            ResultSet res=st.executeQuery(request);
            while(res.next()){
                s+=res.getInt("likes");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } 
        System.out.println(s);
        return s;
    }
    
    public int recdisLike(int id){
        String request = "select dislike from like_dislike where id_status = "+id;
        int s=0;
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            ResultSet res=st.executeQuery(request);
            while(res.next()){
                s+=res.getInt("dislike");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } 
        System.out.println(s);
        return s;
    }
    
    public boolean rechercheLike(int id){
        String request = "select likes from like_dislike where id_user = "+id;
        int s=0;
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            ResultSet res=st.executeQuery(request);
            while(res.next()){
                s=res.getInt("dislike");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } 
        return s==1;
    }
    
    public boolean recherchedisLike(int id){
        String request = "select dislike from like_dislike where id_user = "+id;
        int s=0;
        try{
            Statement st = DataSource.getInstance().getCnx().prepareStatement(request);
            ResultSet res=st.executeQuery(request);
            while(res.next()){
                s=res.getInt("dislike");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } 
        return s==1;
    }
    
    
}
