public class Main {

    public static void main(String[] args) {

        TV tv = new TV();

        Command turnOn = new TurnTVOn(tv);
        Command turnOff = new TurnTVOff(tv);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(1, turnOn);
        remote.setCommand(2, turnOff);

        remote.pressButton(1);
        remote.pressButton(2);
    }
}