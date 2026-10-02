package payments;

import enums.Denomination;
import enums.PaymentStatus;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Getter
public class PaymentResult {
    private PaymentStatus status;
    private int remainingAmount;
    private int change;
    @Setter
    private Map<Denomination, Integer> changeBreakdown;
}
