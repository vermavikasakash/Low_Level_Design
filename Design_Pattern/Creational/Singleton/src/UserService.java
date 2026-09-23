public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = UserRepository.getInstance();
    }

    public void createUser(int id, String name, String email) {

        User user = new User(id, name, email);

        userRepository.save(user);
    }

    public User getUser(int id) {

        return userRepository.findById(id);
    }

    public void updateUser(int id, String name, String email) {

        User user = userRepository.findById(id);

        if (user != null) {
            user.setName(name);
            user.setEmail(email);
        }
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}