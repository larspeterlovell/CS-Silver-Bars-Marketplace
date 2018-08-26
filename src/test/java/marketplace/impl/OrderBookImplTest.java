package marketplace.impl;

import marketplace.OrderBook;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NavigableMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class OrderBookImplTest {

    private OrderBook orderBook;

    @Before
    public void setup() {
        NavigableMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders = new TreeMap<BigDecimal, BigDecimal>().descendingMap();
        price2TotalQtyBuyOrders.put(new BigDecimal("306"), new BigDecimal("6.5"));
        price2TotalQtyBuyOrders.put(new BigDecimal("301"), new BigDecimal("3.5"));
        price2TotalQtyBuyOrders.put(new BigDecimal("303"), new BigDecimal("2.5"));
        NavigableMap<BigDecimal, BigDecimal> price2TotalQtySellOrders = new TreeMap<>();
        price2TotalQtySellOrders.put(new BigDecimal("305"), new BigDecimal("0.5"));
        price2TotalQtySellOrders.put(new BigDecimal("303"), new BigDecimal("3.5"));
        price2TotalQtySellOrders.put(new BigDecimal("304"), new BigDecimal("3.5"));
        orderBook = new OrderBookImpl(price2TotalQtyBuyOrders, price2TotalQtySellOrders);
    }

    @Test
    public void testGetBuyOrderSummary() {
        final String EXP_SUMMARY =
                "BUY:\n" +
                "6.5 kg for £306\n" +
                "2.5 kg for £303\n" +
                "3.5 kg for £301\n";
        assertEquals(EXP_SUMMARY, orderBook.getBuyOrderSummary());
    }

    @Test
    public void testGetSellOrderSummary() {
        final String EXP_SUMMARY =
                "SELL:\n" +
                "3.5 kg for £303\n" +
                "3.5 kg for £304\n" +
                "0.5 kg for £305\n";
        assertEquals(EXP_SUMMARY, orderBook.getSellOrderSummary());
    }
}
