import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UnlockUser {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/maze";
        String username = "sonar";
        String password = "P@88345!";
        
        try {
            Class.forName("org.postgresql.Driver");
            
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                // Unlock the admin user by setting active = true
                String sql = "UPDATE users SET active = true WHERE \"userName\" = 'admin'";
                
                try (Statement stmt = conn.createStatement()) {
                    int rowsAffected = stmt.executeUpdate(sql);
                    System.out.println("Successfully unlocked admin user. Rows affected: " + rowsAffected);
                }
            }
            
            System.out.println("User unlock operation completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Error unlocking user: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
