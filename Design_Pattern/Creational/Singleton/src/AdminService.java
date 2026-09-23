class AdminService {

    private UserRepository repository;

    public AdminService(UserRepository repository) {
        this.repository = repository;
    }
}