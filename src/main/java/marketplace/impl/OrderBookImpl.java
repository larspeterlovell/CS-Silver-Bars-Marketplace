package marketplace.impl;

import marketplace.OrderBook;

import java.math.BigDecimal;
import java.util.SortedMap;

public class OrderBookImpl implements OrderBook {

    private final SortedMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders;
    private final SortedMap<BigDecimal, BigDecimal> price2TotalQtySellOrders;

    public OrderBookImpl(SortedMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders, SortedMap<BigDecimal, BigDecimal> price2TotalQtySellOrders) {
        this.price2TotalQtyBuyOrders = price2TotalQtyBuyOrders;
        this.price2TotalQtySellOrders = price2TotalQtySellOrders;
    }

    @Override
    public SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtyBuyOrders() {
        return price2TotalQtyBuyOrders;
    }

    @Override
    public SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtySellOrders() {
        return price2TotalQtySellOrders;
    }
}
