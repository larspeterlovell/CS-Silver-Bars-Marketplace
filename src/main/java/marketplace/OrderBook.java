package marketplace;

import java.math.BigDecimal;
import java.util.SortedMap;

/**
 * An order book which holds the aggregated buy and sell orders respectively per price level
 */
public interface OrderBook {
    /**
     * Get a sorted map with prices as the map keys and the total qty as the map values for buy orders sorted on descending prices
     *
     * @return a sorted price->total qty map for buy order sorted on descending prices
     */
    SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtyBuyOrders();

    /**
     * Get a sorted map with prices as the map keys and the total qty as the map values for sell orders sorted on ascending prices
     *
     * @return a sorted price->total qty map for sell order sorted on ascending prices
     */
    SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtySellOrders();
}
