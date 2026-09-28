public interface VendingMachineState {
// For now, we're only implementing two operations.
// Our states will be: NoMoneyState & MoneyInsertedState

    void insertMoney();

    void selectProduct();
}