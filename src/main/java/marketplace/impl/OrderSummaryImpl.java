package marketplace.impl;

import marketplace.OrderSummary;

import java.math.BigDecimal;

public class OrderSummaryImpl implements OrderSummary {

    private Integer price;
    private BigDecimal qty;

    public OrderSummaryImpl(Integer price, BigDecimal qty) {
        this.price = price;
        this.qty = qty;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public BigDecimal getQty() {
        return qty;
    }
}
