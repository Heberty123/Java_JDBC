package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

    public static void main(String[] argc){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from department");

            System.out.println("Results: \n");
            while(rs.next()){
                System.out.println("Id: " + rs.getInt("Id") + ", Name: " + rs.getString("Name"));
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }

        DB.closeStatement(st);
        DB.closeResultSet(rs);
        DB.closeConnection();
    }
}
