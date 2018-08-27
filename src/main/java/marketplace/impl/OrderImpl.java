package marketplace.impl;

import marketplace.Order;
import marketplace.OrderType;

import java.math.BigDecimal;

public class OrderImpl implements Order {

    private final OrderType orderType;
    private final BigDecimal price;
    private final BigDecimal qty;
    private final String userId;

    /**
     * Order constructor
     *
     * @param orderType
     * @param price, only non zero positive prices are allowed
     * @param qty, only non zero positive quantities are allowed
     * @param userId
     * @return Order
     * @throws IllegalArgumentException for any invalid order parameters
     */
    public OrderImpl(OrderType orderType, BigDecimal price, BigDecimal qty, String userId) {
        this.orderType = orderType;
        this.price = price;
        this.qty = qty;
        this.userId = userId;
        validate();
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

    private void validate() {
        if (orderType == null) {
            throw new IllegalArgumentException("The order type is null");
        }
        // Only non zero positive prices allowed
        if (price.signum() != 1) {
            throw new IllegalArgumentException("The order price " + price + " is invalid");
        }
        // Only non zero positive quantites allowed
        if (qty.signum() != 1) {
            throw new IllegalArgumentException("The order quantity " + qty + " is invalid");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderImpl order = (OrderImpl) o;

        if (orderType != order.orderType) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (qty != null ? !qty.equals(order.qty) : order.qty != null) return false;
        return userId != null ? userId.equals(order.userId) : order.userId == null;
    }

    @Override
    public int hashCode() {
        int result = orderType != null ? orderType.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
