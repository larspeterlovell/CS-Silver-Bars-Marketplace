package marketplace.impl;

import marketplace.*;

import java.math.BigDecimal;
import java.util.*;

public class OrderBoardImpl implements OrderBoard {

    private final SortedMap<BigDecimal, List<Order>> price2BuyOrders = new TreeMap<>();
    private final SortedMap<BigDecimal, List<Order>> price2SellOrders = new TreeMap<>();

    @Override
    public synchronized void registerOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Input order is null when registering order");
        }
        Map<BigDecimal, List<Order>> price2Orders = getPrice2Orders(order.getOrderType());
        BigDecimal price = order.getPrice();
        List<Order> orders = price2Orders.get(price);
        if (orders == null) {
            orders = new LinkedList<Order>();
            price2Orders.put(price, orders);
        }
        orders.add(order);
    }

    @Override
    public synchronized void cancelOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Input order is null when cancelling order");
        }
        Map<BigDecimal, List<Order>> price2Orders = getPrice2Orders(order.getOrderType());
        BigDecimal price = order.getPrice();
        List<Order> orders = price2Orders.get(price);
        if (orders != null) {
            orders.remove(order);
            if (orders.isEmpty()) {
                price2Orders.remove(price);
            }
        }
    }

    @Override
    public synchronized OrderBook getOrderBoardSummary() {
        OrderBookImpl orderBook = new OrderBookImpl();
        orderBook.add(OrderType.BUY, getOrderBookItems(OrderType.BUY));
        orderBook.add(OrderType.SELL, getOrderBookItems(OrderType.SELL));
        return orderBook;
    }

    private List<OrderBookItem> getOrderBookItems(OrderType orderType) {
        if (orderType == null) {
            throw new IllegalArgumentException("Input order type is null when getting order book items");
        }
        SortedMap<BigDecimal, List<Order>> price2Orders = getPrice2Orders(orderType);
        List<OrderBookItem> orderBookItemList = new ArrayList<>();

        for (BigDecimal price : price2Orders.keySet()) {
            List<Order> orders = price2Orders.get(price);
            BigDecimal totalQty = orders.stream().map(order -> order.getQty()).reduce(BigDecimal.ZERO, BigDecimal::add);
            orderBookItemList.add(new OrderBookItemImpl(price, totalQty));
        }
        return orderBookItemList;
    }

    private SortedMap<BigDecimal, List<Order>> getPrice2Orders(OrderType orderType) {
        if (orderType == null) {
            throw new IllegalArgumentException("Input order type is null when price to order list map");
        }
        return orderType == OrderType.BUY ? price2BuyOrders : price2SellOrders;
    }
}
