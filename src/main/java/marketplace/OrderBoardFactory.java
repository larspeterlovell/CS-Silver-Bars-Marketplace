package marketplace;

import marketplace.impl.OrderBoardImpl;
import marketplace.impl.OrderImpl;

import java.math.BigDecimal;

/**
 * With this singleton factory class you can create an order board instance and create orders
 */
public enum OrderBoardFactory {
    INSTANCE;

    /**
     * Create an instance of an order board
     *
     * @return OrderBoard
     */
    public OrderBoard createOrderBoard() {
        return new OrderBoardImpl();
    }

    /**
     * Create an instance of an order
     *
     * @param orderType
     * @param price, only non zero positive prices are allowed
     * @param qty, only non zero positive quantities are allowed
     * @param userId
     * @return Order
     * @throws IllegalArgumentException for any invalid order parameters
     */
    public Order createOrder(OrderType orderType, BigDecimal price, BigDecimal qty, String userId) {
        return new OrderImpl(orderType, price, qty, userId);
    }
}
