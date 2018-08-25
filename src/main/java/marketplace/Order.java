package marketplace;

import java.math.BigDecimal;

public interface Order {
    String getUserId();
    BigDecimal getQty();
    Integer getPrice();
    OrderType getOrderType();
}
