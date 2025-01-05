
package sendfile.server;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MessageDAO {
    public void saveMessage(int userId, String content) {
        String query = "INSERT INTO Messages (UserId, Content) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, content);
            stmt.executeUpdate();
            System.out.println("Tin nhắn đã được lưu.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> getMessages() {
        List<String> messages = new ArrayList<>();
        String query = "SELECT * FROM Messages ORDER BY Timestamp ASC";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String message = "[" + rs.getTimestamp("Timestamp") + "] User " 
                                 + rs.getInt("UserId") + ": " 
                                 + rs.getString("Content");
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
