import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Solution {


    private int id;
    private String created;
    private String updated;
    private String description;
    private int exerciseID;
    private long userID;

    public Solution(){}

    public Solution(String created, String updated, String description, int exerciseID, long userID) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exerciseID = exerciseID;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    static public Exercise[] loadAllSolutions(Connection conn) throws SQLException {
        ArrayList<Solution> solution = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exerciseID = resultSet.getInt("exercise_id");
            loadedSolution.userID = resultSet.getLong("users_id");
            solution.add(loadedSolution);}
        Exercise[] sGArray= new Exercise[solution.size()];
        sGArray = solution.toArray(sGArray);
        return sGArray;
    }


    static public Solution loadSolutionById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM solution where id=?";
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exerciseID = resultSet.getInt("exercise_id");
            loadedSolution.userID = resultSet.getLong("users_id");
            return loadedSolution;
        }
        return null;
    }


    public void saveToDB(Connection connection) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
            String generatedColumns[] = { "ID" };
            PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.created);
            preparedStatement.setString(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exerciseID);
            preparedStatement.setLong(5, this.userID);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE solution SET created = ?, updated = ?, descrption = ?, exercise_id = ?, useres_id = ? where id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, this.created);
            preparedStatement.setString(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exerciseID);
            preparedStatement.setLong(5, this.userID);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }
}
