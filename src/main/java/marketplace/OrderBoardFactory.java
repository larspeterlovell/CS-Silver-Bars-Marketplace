package marketplace;

import marketplace.impl.OrderBoardImpl;

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
}
