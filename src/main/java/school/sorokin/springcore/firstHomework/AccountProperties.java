package school.sorokin.springcore.firstHomework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record AccountProperties(double defaultAmount, double transferCommission) {
    public AccountProperties(@Value("${account.default-amount}") double defaultAmount,
                             @Value("${account.transfer-commission}") double transferCommission) {
        this.defaultAmount = defaultAmount;
        this.transferCommission = transferCommission;
    }
}
