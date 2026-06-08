package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReminderDAO {

    public ReminderDAO() {
    try {
        Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("SQLite JDBC driver not found", e);
    }
    }

    private static final String DB_URL = "jdbc:sqlite:reminders.db";

    public void insert(Reminder r) throws SQLException {
        String sql = "INSERT INTO reminders(name, dueDate, priority) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getName());
            stmt.setString(2, r.getDueDate().toString());
            stmt.setInt(3, r.getPriority());


            stmt.executeUpdate();

            // Get ID
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                r.setId(keys.getInt(1));
            }
        }
    }

    public void delete(Reminder r) throws SQLException {
        String sql = "DELETE FROM reminders WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();
        }
    }

    public ArrayList<Reminder> loadAll() throws SQLException {
        ArrayList<Reminder> list = new ArrayList<>();
        String sql = "SELECT name, dueDate, priority, id FROM reminders";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reminder r = new Reminder(
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("dueDate")),
                        rs.getInt("priority"),
                        rs.getInt("id")
                );
                list.add(r);
            }
        }
        return list;
    }
    public void initializeDB() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS reminders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
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