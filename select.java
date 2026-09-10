import java.sql.*;

class select {
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
  
     ResultSet rs=stmt.executequery("SELECT * from emp");
while(rs.next()){
System.out.println(rs.getINT(1)="\t");
System.out.println(rs.(rs.getString(3)));
}
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
