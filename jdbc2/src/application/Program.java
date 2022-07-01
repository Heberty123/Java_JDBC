package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] argc){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("INSERT INTO seller " +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Heberty");
            st.setString(2, "hebertyprado22005@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("04/10/2002").getTime()));
            st.setDouble(4, 4899.90);
            st.setInt(5, 1);

            int rowAffected = st.executeUpdate();

            rs = st.getGeneratedKeys();

            System.out.println("rows affected: " + rowAffected);

            while(rs.next()){
                System.out.println("Well done ! id create: " + rs.getInt(1));
            }


        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.closeConnection();
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
