package marketplace;

public interface OrderBoard {

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
     * @return true if order was found and cancelled, false otherwise
     */
    boolean cancelOrder(Order order);

    /**
     * Get an order board summary as an order book object
     *
     * @return an order book holding a price->total qty maps for buy orders and sell orders respectively
     */
    OrderBook getOrderBoardSummary();

}
