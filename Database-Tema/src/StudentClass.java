import java.sql.*;

public class StudentClass {
   
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/studentdb";


   static final String USER = "root";
   static final String PASS = "";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      
      Class.forName("com.mysql.cj.jdbc.Driver");

      
      System.out.println("Conexiune la noua baza...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Conectare cu succes!");
      
      
  
      stmt = conn.createStatement();
      String sql = "update student set nume = 'Davidescu' " + "where id in(2, 4)"; // casatorie
      stmt.executeUpdate(sql);
      System.out.println("Am adaugat valoarea noua cu succes!");
      
      stmt = conn.createStatement();
      String sql2 = "delete from student " + "where id = 1"; 
      stmt.executeUpdate(sql2);
      System.out.println("Am sters valoarea cu succes cu succes!");

      sql = "select id, nume, prenume, nr_matricol from student";
      ResultSet rs = stmt.executeQuery(sql);


      while (rs.next()) {
          System.out.println(rs.getString("nume") + " " + rs.getString("prenume") + " " + rs.getString("nr_matricol"));
      }
      
      rs.close();
   }catch(SQLException se) {
	   
      se.printStackTrace();
      
   }catch(Exception e) {
   
      e.printStackTrace();
   }finally {
      
      try {
         if(stmt!=null)
            conn.close();
      } catch(SQLException se) {
      }
      try {
         if(conn!=null)
            conn.close();
      }catch(SQLException se) {
         se.printStackTrace();
      }
   }
   System.out.println("Goodbye!");
}
}