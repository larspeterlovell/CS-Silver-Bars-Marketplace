package marketplace;

import marketplace.impl.OrderBoardImpl;
import marketplace.impl.OrderImpl;

import java.math.BigDecimal;

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
     * @return Order
     */
    public Order createOrder(OrderType orderType, BigDecimal price, BigDecimal qty, String userId) {
        return new OrderImpl(orderType, price, qty, userId);
    }
}
