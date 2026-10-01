// both implementation are correct
public class ManagerSupport implements SupportHandler {

    @Override
    public void handle(String issue) {

        if (issue.equals("PRODUCTION_DOWN")) {
            System.out.println("Manager handled the issue");
        } else {
            System.out.println("No handler could resolve the issue");
        }
    }
}

//public class ManagerSupport implements SupportHandler {
//
//    private SupportHandler nextHandler;
//
//    public ManagerSupport(SupportHandler nextHandler) {
//        this.nextHandler = nextHandler;
//    }
//
//    @Override
//    public void handle(String issue) {
//
//        if (issue.equals("PRODUCTION_DOWN")) {
//            System.out.println("Manager handled the issue");
//            return;
//        }
//
//        if (nextHandler != null) {
//            nextHandler.handle(issue);
//        } else {
//            System.out.println("No handler could resolve the issue");
//        }
//    }
//}