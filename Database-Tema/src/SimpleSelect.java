import java.sql.*;
public class SimpleSelect {

    private PreparedStatement statement;
    private Connection con;
    private String x, y;

    public String queryDatabase(String id) {
    	 try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb", "root", "");
            statement = con.prepareStatement(
                    "select * from student where id = ? ");
            statement.setString(3, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                x = rs.getString(1);
                System.out.print(x);
                System.out.print(" ");
                y = rs.getString(2);
                System.out.println(y);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
		return id;
    }

	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}
}
