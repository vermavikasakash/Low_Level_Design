public class VendingMachine {

    private VendingMachineState state;

    public VendingMachine() {
        this.state = new NoMoneyState(this);
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void selectProduct() {
        state.selectProduct();
    }

    public void insertMoney() {
        state.insertMoney();
    }
}