package marketplace;

import java.util.List;

public interface OrderBook {
    List<OrderBookItem> getOrderBookItems(OrderType orderType);
}
