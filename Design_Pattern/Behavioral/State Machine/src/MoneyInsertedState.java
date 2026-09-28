public class MoneyInsertedState implements VendingMachineState {
    private VendingMachine machine;

    public MoneyInsertedState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Money already inserted");
    }

    @Override
    public void selectProduct() {
        System.out.println("Select product and check price");
    }
    // Again, no: if (state.equals("MONEY_INSERTED"))
    //because we're already inside MoneyInsertedState.
}