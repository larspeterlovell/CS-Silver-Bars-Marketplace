package marketplace.impl;

import marketplace.Order;
import marketplace.OrderBoard;
import marketplace.OrderBook;
import marketplace.OrderType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderBoardImpl implements OrderBoard {

    private final Queue<Order> orders = new ConcurrentLinkedQueue<>();

    @Override
    public void registerOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Input order is null when registering order");
        }
        orders.add(order);
    }

    @Override
    public boolean cancelOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Input order is null when cancelling order");
        }
        return orders.remove(order);
    }

    @Override
    public OrderBook getOrderBoardSummary() {
        Stream<Order> orderStream = orders.stream();
        Order[] orderArray = orderStream.toArray(Order[]::new);
        Stream<Order> orderStreamBuyOrders = Stream.of(orderArray);
        NavigableMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders = getPrice2TotalQty(orderStreamBuyOrders, OrderType.BUY).descendingMap();
        Stream<Order> orderStreamSellOrders = Stream.of(orderArray);
        NavigableMap<BigDecimal, BigDecimal> price2TotalQtySellOrders = getPrice2TotalQty(orderStreamSellOrders, OrderType.SELL);
        return new OrderBookImpl(price2TotalQtyBuyOrders, price2TotalQtySellOrders);
    }

    private NavigableMap<BigDecimal, BigDecimal> getPrice2TotalQty(Stream<Order> stream, OrderType orderType) {
        Map<BigDecimal, BigDecimal> map = stream.filter(order -> orderType == order.getOrderType()).collect(
                Collectors.groupingBy(Order::getPrice, Collectors.mapping(Order::getQty,
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        return new TreeMap<>(map);
    }
}
