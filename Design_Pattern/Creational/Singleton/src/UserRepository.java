import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static UserRepository instance;
    private List<User> users = new ArrayList<>();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    public void save(User user) {
        users.add(user);
    }

    public User findById(int id) {

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public List<User> findAll() {
        return users;
    }

    public void deleteById(int id) {

        users.removeIf(user -> user.getId() == id);
    }
}