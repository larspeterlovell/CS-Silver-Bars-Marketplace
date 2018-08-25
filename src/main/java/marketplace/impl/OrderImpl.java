package marketplace.impl;

import marketplace.Order;
import marketplace.OrderType;

import java.math.BigDecimal;

public class OrderImpl implements Order {

    private final OrderType orderType;
    private final BigDecimal price;
    private final BigDecimal qty;
    private final String userId;

    public OrderImpl(OrderType orderType, BigDecimal price, BigDecimal qty, String userId) {
        this.orderType = orderType;
        this.price = price;
        this.qty = qty;
        this.userId = userId;
    }

    @Override
    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal getQty() {
        return qty;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderImpl order = (OrderImpl) o;

        if (orderType != order.orderType) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        return qty != null ? qty.equals(order.qty) : order.qty == null;
    }

    @Override
    public int hashCode() {
        int result = orderType != null ? orderType.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        return result;
    }
}