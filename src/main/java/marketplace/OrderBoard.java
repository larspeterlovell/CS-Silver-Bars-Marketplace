package marketplace;

/**
 * An order board to and from which orders can be registered and cancelled.
 * Users of the order board can retrieve a snapshot of the current buy and sell orders aggregated
 * per price levels as an OrderBook object or as an order book summary on a default display format.
 */
public interface OrderBoard {
    /**
     * Register an order to the order board
     *
     * @param order
     */
    void registerOrder(Order order);

    /**
     * Cancel an order from the order board
     *
     * @param order
     * @return true if order existed on teh order board and it was cancelled, false otherwise
     */
    boolean cancelOrder(Order order);

    /**
     * Get a snapshot of the order board's current buy and sell orders as a OrderBook object
     *
     * @return an OrderBook holding a snapshot of the current buy and sell orders respectively of the order board
     */
    OrderBook getOrderBook();

    /**
     * Get a snapshot of the order board's current buy and sell orders aggregated per price level on a default display format, e.g.
     *  BUY:
     *  1.0 kg for £304
     *  8.0 kg for £303
     *  SELL:
     *  5.5 kg for £301
     *  3.0 kg for £303
     *  7.5 kg for £306
     *
     * @return an order book summary, on a default display format, holding a snapshot of the current buy and sell orders respectively of the order board
     */
    String getOrderBookSummary();
}
