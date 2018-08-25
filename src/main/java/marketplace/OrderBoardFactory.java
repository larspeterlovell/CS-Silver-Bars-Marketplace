package marketplace;

import marketplace.impl.OrderBoardImpl;

public enum OrderBoardFactory {
    INSTANCE;

    public OrderBoard createOrderBoard() {
        return new OrderBoardImpl();
    }
}
