import java.sql.*;

class ConnDemo {
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

            String sql = "CREATE TABLE emp (" +
                         "id INT PRIMARY KEY, " +
                         "name VARCHAR(50), " +
                         "salary DOUBLE)";

            stmt.executeUpdate(sql);

            System.out.println("Employee table created");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
