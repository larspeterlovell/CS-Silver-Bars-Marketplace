package marketplace;

import java.math.BigDecimal;

public interface Order {
    /**
     * Get the order type of the order
     *
     * @return OrderType
     */
    OrderType getOrderType();

    /**
     * Get the price of the order
     *
     * @return BigDecimal
     */
    BigDecimal getPrice();

    /**
     * Get the quantity of the order
     *
     * @return BigDecimal
     */
    BigDecimal getQty();

    /**
     * Get the user id of the order
     *
     * @return String
     */
    String getUserId();
}
