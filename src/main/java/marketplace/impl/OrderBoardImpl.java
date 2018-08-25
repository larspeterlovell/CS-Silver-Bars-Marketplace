package marketplace.impl;

import marketplace.OrderBoard;
import marketplace.Order;
import marketplace.OrderSummary;
import marketplace.OrderType;

import java.math.BigDecimal;
import java.util.*;

public class OrderBoardImpl implements OrderBoard {

    private Map<Integer, List<Order>> price2BuyOrders = new TreeMap<Integer, List<Order>>();
    private Map<Integer, List<Order>> price2SellOrders = new TreeMap<Integer, List<Order>>();

    public synchronized void registerOrder(Order order) {
        Map<Integer, List<Order>> price2Orders = getPrice2Orders(order.getOrderType());
        Integer price = order.getPrice();
        List<Order> orders = price2Orders.get(price);
        if (orders == null) {
            orders = new LinkedList<Order>();
            price2Orders.put(price, orders);
        }
        orders.add(order);
    }

    public synchronized void cancelOrder(Order order) {
        Map<Integer, List<Order>> price2Orders = getPrice2Orders(order.getOrderType());
        Integer price = order.getPrice();
        List<Order> orders = price2Orders.get(price);
        if (orders != null) {
            orders.remove(order);
        }
    }

    public synchronized Map<OrderType, List<OrderSummary>> getOrderBoardSummary() {
        Map<OrderType, List<OrderSummary>> orderBoardSummary = new HashMap<>();
        OrderType orderType = OrderType.BUY;
        Map<Integer, List<Order>> price2Orders = getPrice2Orders(orderType);
        orderBoardSummary.put(orderType, getOrderSummaryList(price2Orders));
        orderType = OrderType.SELL;
        price2Orders = getPrice2Orders(orderType);
        orderBoardSummary.put(orderType, getOrderSummaryList(price2Orders));
        return orderBoardSummary;
    }

    private List<OrderSummary> getOrderSummaryList(Map<Integer, List<Order>> price2Orders) {
        List<OrderSummary> orderSummaryList = new ArrayList<>();
        for (Integer price : price2Orders.keySet()) {
            BigDecimal totalQty = BigDecimal.ZERO;
            List<Order> orders = price2Orders.get(price);
            for (Order order : orders) {
                totalQty = totalQty.add(order.getQty());
            }
            orderSummaryList.add(new OrderSummaryImpl(price, totalQty));
        }
        return orderSummaryList;
    }

    private Map<Integer, List<Order>> getPrice2Orders(OrderType orderType) {
        return orderType == OrderType.BUY ? price2BuyOrders : price2SellOrders;
    }
}
