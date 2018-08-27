package marketplace.impl;

import marketplace.Order;
import marketplace.OrderBoard;
import marketplace.OrderBook;
import marketplace.OrderType;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderBoardImpl implements OrderBoard {

    private final Queue<Order> orders = new ConcurrentLinkedQueue<>();

    @Override
    public void registerOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("The order is null");
        }
        orders.add(order);
    }

    @Override
    public boolean cancelOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("The order is null");
        }
        return orders.remove(order);
    }

    @Override
    public OrderBook getOrderBook() {
        Order[] orderArray = orders.parallelStream().toArray(Order[]::new);
        NavigableMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders = getPrice2TotalQty(Stream.of(orderArray), OrderType.BUY).descendingMap();
        NavigableMap<BigDecimal, BigDecimal> price2TotalQtySellOrders = getPrice2TotalQty(Stream.of(orderArray), OrderType.SELL);
        return new OrderBookImpl(price2TotalQtyBuyOrders, price2TotalQtySellOrders);
    }

    @Override
    public String getOrderBookSummary() {
        OrderBook orderBook = getOrderBook();
        SortedMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders = orderBook.getPrice2TotalQtyBuyOrders();
        SortedMap<BigDecimal, BigDecimal> price2TotalQtySellOrders = orderBook.getPrice2TotalQtySellOrders();
        return "BUY:\n" + getOrderSummary(price2TotalQtyBuyOrders) + "SELL:\n" + getOrderSummary(price2TotalQtySellOrders);
    }

    private String getOrderSummary(SortedMap<BigDecimal, BigDecimal> price2TotalQty) {
        String summary = "";
        for (Map.Entry<BigDecimal, BigDecimal> price2QtyEntry : price2TotalQty.entrySet()) {
            summary += price2QtyEntry.getValue().toPlainString() + " kg for £" + price2QtyEntry.getKey().toPlainString() + "\n";
        }
        return summary;
    }

    private NavigableMap<BigDecimal, BigDecimal> getPrice2TotalQty(Stream<Order> stream, OrderType orderType) {
        Map<BigDecimal, BigDecimal> map = stream.filter(order -> orderType == order.getOrderType()).collect(
                Collectors.groupingBy(Order::getPrice, Collectors.mapping(Order::getQty,
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        return new TreeMap<>(map);
    }
}
