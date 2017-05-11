import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ugyan on 2017.05.08..
 */
public class MemoryDAO implements TodoDAO {

    public static final MemoryDAO INSTANCE = new MemoryDAO();

    private List<Task> database = new ArrayList<>();

    private static Integer id = 1;

    private MemoryDAO() {
    }

//    @Override
//    public Task returnTaskById(Integer id) {
//        return getDatabase()
//            .stream()
//            .filter(Task -> Task.getId().equals(id))
//            .findAny()
//            .orElse(null);
//    }

    @Override
    public List<Task> returnAll(String user) {

        return getDatabase()
            .stream()
            .filter(Task -> Task.getUser().equals(user))
            .collect(Collectors.toList());
    }

    @Override
    public List<Task> returnFinished(String user) {

        return getDatabase()
            .stream()
            .filter(Task -> Task.getCompletion())
            .filter(Task -> Task.getUser().equals(user))
            .collect(Collectors.toList());
    }

    @Override
    public List<Task> returnUnFinished(String user) {

        return getDatabase()
            .stream()
            .filter(Task -> !Task.getCompletion())
            .filter(Task -> Task.getUser().equals(user))
            .collect(Collectors.toList());

    }

    @Override
    public void addTask(String name, String user) {
        if (name.equals("")) { return; }
        Task newTask = new Task(id, name, false, user);
        id++;
        getDatabase().add(newTask);
    }

    @Override
    public void toggleStatus(Integer id) {
        Task task = null;
        for (Task current: getDatabase()) {
            if (current.getId().equals(id)) {
                task = current;
            }
        }
        task.setCompletion(!task.getCompletion());

    }

    @Override
    public void deleteTask(Integer id) {
        setDatabase(getDatabase()
                    .stream()
                    .filter(Task -> Task.getId() != id)
                    .collect(Collectors.toList()));
    }

    public List<Task> getDatabase() {
        return database;
    }

    public void setDatabase(List<Task> database) {
        this.database = database;
    }
}
