import java.sql.*;

class Insert {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cvr_6741",
                "root",
                "root"
            );

            System.out.println("Database connected");

            Statement stmt = conn.createStatement();
  
      String sql = "INSERT INTO emp VALUES (103, \"sai\", 27000)";

            int rows = stmt.executeUpdate(sql);

            System.out.println(rows + " record inserted");

            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
