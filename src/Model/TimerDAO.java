package Model;

import java.sql.*;
import java.time.LocalDateTime;

public class TimerDAO {
    public TimerDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not found", e);
        }
    }

    private static final String DB_URL = "jdbc:sqlite:timers.db";

    public void setTimer(LocalDateTime target) throws SQLException {
        clear();
        String sql = "INSERT OR REPLACE INTO timers(name, targetDate) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Timer");
            stmt.setString(2, target.toString());
            stmt.executeUpdate();
        }

    }

    public void clear() throws SQLException {
        String sql = "DELETE FROM timers WHERE name = 'Timer'";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }

    public String getTimer() throws SQLException {
        String sql = "SELECT targetDate FROM timers WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "Timer");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("targetDate");
                }
            }
        }
        return null;
    }

    public void initializeDB() throws SQLException {
        String sql = """
        CREATE TABLE IF NOT EXISTS timers (
            name TEXT PRIMARY KEY,
            targetDate TEXT NOT NULL
        );
    """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}

