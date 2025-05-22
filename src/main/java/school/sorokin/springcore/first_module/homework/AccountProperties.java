package school.sorokin.springcore.first_module.homework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record AccountProperties(@Value("${account.default-amount}") double defaultAmount,
                                @Value("${account.transfer-commission}") double transferCommission) {
}
