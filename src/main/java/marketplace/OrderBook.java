package marketplace;

import java.math.BigDecimal;
import java.util.SortedMap;

public interface OrderBook {
    /**
     * Get a sorted key->value map as price->total qty for buy orders sorted on descending prices (keys)
     *
     * @return a sorted price->total qty map
     */
    SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtyBuyOrders();

    /**
     * Get a sorted key->value map as price->total qty for sell orders sorted on ascending prices (keys)
     *
     * @return a sorted price->total qty map
     */
    SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtySellOrders();

    /**
     * Get a summary of the orderbook as a string for buy orders
     *
     * @return a summary of the orderbook as a string for buy orders
     */
    String getBuyOrderSummary();

    /**
     * Get a summary of the orderbook as a string for sell orders
     *
     * @return a summary of the orderbook as a string for sell orders
     */
    String getSellOrderSummary();
}
