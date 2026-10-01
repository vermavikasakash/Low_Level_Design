public class L1Support implements SupportHandler {

    private SupportHandler nextHandler;

    public L1Support(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(String issue) {

        if (issue.equals("PASSWORD_RESET")) {
            System.out.println("L1 Support handled the issue");
            return;
        }

        if (nextHandler != null) {
            nextHandler.handle(issue);
        } else {
            System.out.println("No handler could resolve the issue");
        }
    }
}