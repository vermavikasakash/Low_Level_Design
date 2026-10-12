public class TurnTVOff implements Command {

    private TV tv;

    public TurnTVOff(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}