import java.util.List;

/**
 * Created by ugyan on 2017.05.08..
 */
public interface TodoDAO {

    List<Task> returnAll(String user);
    List<Task> returnFinished(String user);
    List<Task> returnUnFinished(String user);
    void addTask(String name, String user);
    void toggleStatus(Integer id);
    void deleteTask(Integer id);

}
