class ReportService {

    private UserRepository repository;

    public ReportService(UserRepository repository) {
        this.repository = repository;
    }
}