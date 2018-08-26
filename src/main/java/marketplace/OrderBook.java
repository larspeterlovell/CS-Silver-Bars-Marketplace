package marketplace;

import java.math.BigDecimal;
import java.util.SortedMap;

public interface OrderBook {
    /**
     * Get a sorted key->value map as price->total qty for buy orders sorted on descending prices
     *
     * @return
     */
    SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtyBuyOrders();

    /**
     * Get a sorted key->value map as price->total qty for sell orders sorted on ascending prices
     *
     * @return
     */
    SortedMap<BigDecimal, BigDecimal> getPrice2TotalQtySellOrders();
}
