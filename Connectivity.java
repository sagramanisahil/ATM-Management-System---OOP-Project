package atm.management.system;

import java.sql.*;

public class Connectivity  {
    Connection connection;
    Statement statement;
    PreparedStatement pstmt;
    public Connectivity(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem","root","kumarsahil@04");
            statement = connection.createStatement();


            System.out.println("DB Connection Established");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args){
//        new Connectivity();
//    }
}
