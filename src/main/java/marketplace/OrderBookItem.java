package marketplace;

import java.math.BigDecimal;

public interface OrderBookItem {
    BigDecimal getPrice();
    BigDecimal getQty();
}
