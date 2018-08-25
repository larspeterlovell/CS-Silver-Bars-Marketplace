package marketplace;

import marketplace.impl.OrderBoardImpl;

public interface OrderBoard {

    static final OrderBoard INSTANCE = new OrderBoardImpl();
    /**
     * Register an order
     *
     * @param order
     */
    void registerOrder(Order order);

    /**
     * Cancel an order
     *
     * @param order
     */
    void cancelOrder(Order order);

    /**
     * Get an order board summary as an order book
     *
     * @return an order book holding a sorted list for buy order book items
     * and a sorted list for sell order book items
     */
    OrderBook getOrderBoardSummary();

}
