package marketplace.impl;

import marketplace.OrderBookItem;

import java.math.BigDecimal;

public class OrderBookItemImpl implements OrderBookItem {

    private final BigDecimal price;
    private final BigDecimal qty;

    public OrderBookItemImpl(BigDecimal price, BigDecimal qty) {
        this.price = price;
        this.qty = qty;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal getQty() {
        return qty;
    }
}
