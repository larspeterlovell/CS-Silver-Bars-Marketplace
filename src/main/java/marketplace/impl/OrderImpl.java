package marketplace.impl;

import marketplace.Order;
import marketplace.OrderType;

import java.math.BigDecimal;

public class OrderImpl implements Order {

    private String userId;
    private BigDecimal qty;
    private Integer price;
    private OrderType orderType;

    public OrderImpl(String userId, BigDecimal qty, Integer price, OrderType orderType) {
        this.userId = userId;
        this.qty = qty;
        this.price = price;
        this.orderType = orderType;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public BigDecimal getQty() {
        return qty;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderImpl order = (OrderImpl) o;

        if (userId != null ? !userId.equals(order.userId) : order.userId != null) return false;
        if (qty != null ? !qty.equals(order.qty) : order.qty != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        return orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        return result;
    }
}
