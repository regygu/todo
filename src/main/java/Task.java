/**
 * Created by ugyan on 2017.05.08..
 */
public class Task {

    private Integer id;
    private String name;
    private boolean completion;
    private String user;

    public Task(Integer id, String name, boolean completion, String user) {
        this.id = id;
        this.name = name;
        this.completion = completion;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCompletion() {
        return completion;
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }
}
