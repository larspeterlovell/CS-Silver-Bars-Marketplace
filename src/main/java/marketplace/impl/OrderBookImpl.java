package marketplace.impl;

import marketplace.OrderBook;

import java.math.BigDecimal;
import java.util.Map;
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

    @Override
    public String getBuyOrderSummary() {
        return "BUY:\n" + getOrderSummary(getPrice2TotalQtyBuyOrders());
    }

    @Override
    public String getSellOrderSummary() {
        return "SELL:\n" + getOrderSummary(getPrice2TotalQtySellOrders());
    }

    private String getOrderSummary(SortedMap<BigDecimal, BigDecimal> price2TotalQty) {
        String summary = "";
        for (Map.Entry<BigDecimal, BigDecimal> price2QtyEntry : price2TotalQty.entrySet()) {
            summary += price2QtyEntry.getValue().toPlainString() + " kg for £" + price2QtyEntry.getKey().toPlainString() + "\n";
        }
        return summary;
    }
}
