import java.util.HashMap;
import java.util.Map;

public class RemoteControl {

    private Map<Integer, Command> buttons = new HashMap<>();

    public void setCommand(int button, Command command) {
        buttons.put(button, command);
    }

    public void pressButton(int button) {
        Command command = buttons.get(button);

        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command assigned to button " + button);
        }
    }
}