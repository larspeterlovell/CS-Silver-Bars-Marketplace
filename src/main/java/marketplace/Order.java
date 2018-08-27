package marketplace;

import java.math.BigDecimal;

public interface Order {
    /**
     * Get the order type BUY or SELL
     *
     * @return OrderType
     */
    OrderType getOrderType();

    /**
     * Get the order price in £/kg
     *
     * @return The price in £/kg as a BigDecimal
     */
    BigDecimal getPrice();

    /**
     * Get the order quantity in kg
     *
     * @return The quantity in kg as a BigDecimal
     */
    BigDecimal getQty();

    /**
     * Get the user id of the order
     *
     * @return String
     */
    String getUserId();
}
