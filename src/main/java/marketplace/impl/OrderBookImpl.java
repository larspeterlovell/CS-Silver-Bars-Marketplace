package marketplace.impl;

import marketplace.OrderBook;
import marketplace.OrderBookItem;
import marketplace.OrderType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderBookImpl implements OrderBook {

    private final Map<OrderType, List<OrderBookItem>> orderType2OrderBookItems;

    public OrderBookImpl() {
        orderType2OrderBookItems = new HashMap<>();
        orderType2OrderBookItems.put(OrderType.BUY, new LinkedList<>());
        orderType2OrderBookItems.put(OrderType.SELL, new LinkedList<>());
    }

    @Override
    public List<OrderBookItem> getOrderBookItems(OrderType orderType) {
        if (orderType == null) {
            throw new IllegalArgumentException("Input order type is null when getting order book items");
        }
        return orderType2OrderBookItems.get(orderType);
    }

    protected void add(OrderType orderType, List<OrderBookItem> orderBookItems) {
        if (orderType == null || orderBookItems == null) {
            throw new IllegalArgumentException("Input order type or order book items is null when adding order book items");
        }
        List<OrderBookItem> existingOrderBookItems = getOrderBookItems(orderType);
        existingOrderBookItems.addAll(orderBookItems);
    }

}
