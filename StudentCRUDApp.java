import java.sql.*;

public class StudentCRUDApp {

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/cvr_6741";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, USERNAME, PASSWORD)) {

            createTable(conn);

            createStudent(conn, 1, "Alice", 20);
            createStudent(conn, 2, "Bob", 22);

            readStudents(conn);

            updateStudent(conn, 1, "Alice Smith", 21);

            readStudents(conn);

            deleteStudent(conn, 2);

            readStudents(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create table
    private static void createTable(Connection conn) throws SQLException {

        String createTableSQL =
                "CREATE TABLE IF NOT EXISTS Student (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(20), " +
                "age INT)";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully.");
        }
    }

    // Create student
    private static void createStudent(
            Connection conn, int id, String name, int age)
            throws SQLException {

        String insertSQL =
                "INSERT INTO Student(id, name, age) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);

            ps.executeUpdate();

            System.out.println("Student created successfully.");
        }
    }

    // Read students
    private static void readStudents(Connection conn) throws SQLException {

        String selectSQL = "SELECT * FROM Student";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            System.out.println("Students:");

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println(
                        "Id: " + id +
                        ", Name: " + name +
                        ", Age: " + age);
            }

            System.out.println();
        }
    }

    // Update student
    private static void updateStudent(
            Connection conn, int id, String name, int age)
            throws SQLException {

        String updateSQL =
                "UPDATE Student SET name = ?, age = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(updateSQL)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);

            ps.executeUpdate();

            System.out.println("Student updated successfully.");
        }
    }

    // Delete student
    private static void deleteStudent(
            Connection conn, int id) throws SQLException {

        String deleteSQL =
                "DELETE FROM Student WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(deleteSQL)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Student deleted successfully.");
        }
    }
}

