public class L2Support implements SupportHandler {

    private SupportHandler nextHandler;

    public L2Support(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(String issue) {

        if (issue.equals("DATABASE_ERROR")) {
            System.out.println("L2 Support handled the issue");
            return;
        }

        if (nextHandler != null) {
            nextHandler.handle(issue);
        } else {
            System.out.println("No handler could resolve the issue");
        }
    }
}