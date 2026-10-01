public class Main {

    public static void main(String[] args) {

        SupportHandler manager = new ManagerSupport();

        SupportHandler l2 = new L2Support(manager);

        SupportHandler l1 = new L1Support(l2);

        l1.handle("PASSWORD_RESET");

        l1.handle("DATABASE_ERROR");

        l1.handle("PRODUCTION_DOWN");

        l1.handle("UNKNOWN_ISSUE");
    }
}