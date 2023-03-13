package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
        public Connection getCnx() {
        return cnx;
    }
    
    private String url="jdbc:mysql://localhost:3306/projet";
    private String user="root";
    private String password="";
    private Connection cnx;
    private static DataSource instance;
    
    private DataSource() {
        try{
            cnx = DriverManager.getConnection(url,user,password);
            System.out.println("connection etablie");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance==null)
            instance=new DataSource();
        return instance;
    }

}
