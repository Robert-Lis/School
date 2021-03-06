import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection;

    public DBConnection(String url, String user, String password) {
        try {
            this.connection = (Connection) DriverManager.getConnection(url, user, password);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
