public class TurnTVOn implements Command {

    private TV tv;

    public TurnTVOn(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}