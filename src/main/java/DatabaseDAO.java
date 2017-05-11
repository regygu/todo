import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by ugyan on 2017.05.11..
 */
public class DatabaseDAO implements TodoDAO {

    public static final DatabaseDAO INSTANCE = new DatabaseDAO();

    public static Connection connection;

    private DatabaseDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Todo?useSSL=false", "root", "root");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


//    @Override
//    public Task returnTaskById(Integer id) {
//        return null;
//    }

    @Override
    public List<Task> returnAll(String user) {
        String query = "SELECT * FROM Tasks WHERE user = '" + user + "';";
        try {
            Statement statement = connection.createStatement();
            ResultSet reultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

    @Override
    public List<Task> returnFinished(String user) {
        String query = "SELECT * FROM Tasks WHERE user = '" + user + "' AND completion = 1;";
        try {
            Statement statement = connection.createStatement();
            ResultSet reultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Task> returnUnFinished(String user) {
        String query = "SELECT * FROM Tasks WHERE user = '" + user + "' AND completion = 0;";
        try {
            Statement statement = connection.createStatement();
            ResultSet reultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public static String resultSetToJson(Connection connection, String query) {
        List<Map<String, Object>> listOfMaps = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.query(connection, query, new MapListHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Couldn't query the database.", se);
        }
        return new Gson().toJson(listOfMaps);
    }
}
