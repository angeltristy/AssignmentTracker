package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AssignmentDAO {
    public AssignmentDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not found", e);
        }
    }

    private static final String DB_URL = "jdbc:sqlite:assignments.db";

    public void insert(Assignment a) throws SQLException {
        String sql = "INSERT INTO assignments(name, course, status, priority, dueDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getName());
            stmt.setString(2, a.getCourseCode());
            stmt.setString(3, a.getStatus());
            stmt.setInt(4, a.getPriority());
            stmt.setString(5, a.getDueDate().toString());

            stmt.executeUpdate();
        }
    }

    public ArrayList<Assignment> loadAll() throws SQLException {
        ArrayList<Assignment> list = new ArrayList<>();
        String sql = "SELECT * FROM assignments";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Assignment a = new Assignment(
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("dueDate")),
                        rs.getString("status"),
                        rs.getInt("priority"),
                        rs.getString("course")
                );
                list.add(a);
            }
        }
        return list;
    }
    public void initializeDB() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS assignments (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    course TEXT NOT NULL,
                    status TEXT NOT NULL,
                    priority INTEGER NOT NULL,
                    dueDate TEXT NOT NULL
                );
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();) {
                 stmt.execute(sql);
        } catch (SQLException e) {
                 throw new RuntimeException("Failed to initialize database");
        }



    }
}