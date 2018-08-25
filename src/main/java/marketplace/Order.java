package marketplace;

import java.math.BigDecimal;

public interface Order {
    OrderType getOrderType();
    BigDecimal getPrice();
    BigDecimal getQty();
    String getUserId();
}
