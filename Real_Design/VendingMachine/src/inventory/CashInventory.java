package inventory;

import enums.Denomination;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class CashInventory {

    private final Map<Denomination, Integer> cash;
    private final Denomination[] denominations = Denomination.values();

    public CashInventory() {
        cash = new HashMap<>();
    }

    // methods
    public void addCash(Denomination denomination, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        cash.merge(denomination, quantity, Integer::sum);
          /*
    cash.merge(denomination, quantity, Integer::sum); is equivalent to :
    if (cash.containsKey(denomination)) {
        cash.put(denomination, cash.get(denomination) + quantity);
    } else {
        cash.put(denomination, quantity);
    }
    * */
    }

    // transfer entire map to inventory
    public void addCash(Map<Denomination, Integer> cashToAdd) {

        for (Map.Entry<Denomination, Integer> entry : cashToAdd.entrySet()) {

            addCash(entry.getKey(), entry.getValue());
        }
    }

    // calculate exact  denominations
    public Map<Denomination, Integer> calculateChange(int amount) {

        Map<Denomination, Integer> change = new HashMap<>();

        if (!findChange(0, amount, change)) {
            throw new IllegalStateException("Cannot make change");
        }

        return change;
    }

    // dispense change
    public Map<Denomination, Integer> dispenseChange(Map<Denomination, Integer> change) {

        for (Map.Entry<Denomination, Integer> entry : change.entrySet()) {

            Denomination denomination = entry.getKey();
            int quantity = entry.getValue();

            int available = cash.getOrDefault(denomination, 0);

            if (available < quantity) {
                throw new IllegalStateException("Insufficient cash");
            }

            cash.put(denomination, available - quantity);
        }

        return change;
    }

    private boolean findChange(int index, int remaining, Map<Denomination, Integer> change) {

        if (remaining == 0) {
            return true;
        }

        if (remaining < 0 || index == denominations.length) {
            return false;
        }

        Denomination denomination = denominations[index];

        int available = cash.getOrDefault(denomination, 0);


        for (int count = 0; count <= available; count++) {

            int newRemaining = remaining - (count * denomination.getValue());

            if (count > 0) {
                change.put(denomination, count);
            }

            if (findChange(index + 1, newRemaining, change)) {
                return true;
            }

            change.remove(denomination);
        }

        return false;
    }
}
