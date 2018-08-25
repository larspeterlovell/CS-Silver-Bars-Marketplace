package marketplace;

import java.math.BigDecimal;

public interface OrderSummary {
    Integer getPrice();
    BigDecimal getQty();
}
