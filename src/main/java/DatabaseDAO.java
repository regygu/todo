import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ugyan on 2017.05.11..
 */
public class DatabaseDAO implements TodoDAO {

    public static final DatabaseDAO INSTANCE = new DatabaseDAO();

    public static Connection connection = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.bfa);

    private DatabaseDAO() {
    }

    @Override
    public List<Task> returnAll(String user) {
        String query = "SELECT * FROM Tasks WHERE user = '" + user + "';";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("user");
                boolean completion = true;
                if (resultSet.getInt("completion") == 0) { completion = !completion; }
                result.add(new Task(id, name, completion, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.equals(null)) { return null; }
        return result;
    }

    @Override
    public List<Task> returnFinished(String user) {
        String query = "SELECT * FROM Tasks WHERE user = '" + user + "' AND completion = 1;";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("user");
                result.add(new Task(id, name, true, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Task> returnUnFinished(String user) {
        String query = "SELECT * FROM Tasks WHERE user = '" + user + "' AND completion = 0;";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("user");
                result.add(new Task(id, name, false, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void addTask(String name, String user) {
        String query = "INSERT INTO Tasks (name, completion, user) VALUES (\"" + name + "\"," + 0 + ",\"" + user + "\")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toggleStatus(Integer id) {
        String toggleStatus = "UPDATE Tasks SET completion = !completion WHERE id =" + id + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(toggleStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Integer id) {
        String deleteStatement = "DELETE FROM Tasks WHERE id =" + id + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
