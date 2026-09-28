public class NoMoneyState implements VendingMachineState {
    private VendingMachine machine;

    public NoMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    // What should happen when we're in NO_MONEY?
    @Override
    public void insertMoney() {
        System.out.println("Money inserted");

        machine.setState(new MoneyInsertedState(machine));
    }

    @Override
    public void selectProduct() {
        System.out.println("Please add money");
    }
    // NoMoneyState doesn't need:
    // if (state.equals("NO_MONEY")) because the class itself represents the NO_MONEY state.
    // That's the whole point.
}