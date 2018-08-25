package marketplace.impl;

import marketplace.OrderBookItem;
import marketplace.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderBookImplTest {

    private OrderBookImpl orderBook;

    @Before
    public void setup() {
        orderBook = new OrderBookImpl();
    }

    @Test
    public void testAdd() {
        orderBook.add(OrderType.BUY, Arrays.asList(new OrderBookItemImpl(new BigDecimal("303"), new BigDecimal("3.5"))));
        orderBook.add(OrderType.SELL, Arrays.asList(new OrderBookItemImpl(new BigDecimal("301"), new BigDecimal("5.5"))));
        orderBook.add(OrderType.BUY, Arrays.asList(new OrderBookItemImpl(new BigDecimal("304"), new BigDecimal("3.0"))));
        List<OrderBookItem> orderBookItems = orderBook.getOrderBookItems(OrderType.BUY);
        assertNotNull(orderBookItems);
        assertEquals(2, orderBookItems.size());
        assertOrderBookItem(orderBookItems.get(0), new BigDecimal("303"), new BigDecimal("3.5"));
        assertOrderBookItem(orderBookItems.get(1), new BigDecimal("304"), new BigDecimal("3.0"));
        orderBookItems = orderBook.getOrderBookItems(OrderType.SELL);
        assertNotNull(orderBookItems);
        assertEquals(1, orderBookItems.size());
        assertOrderBookItem(orderBookItems.get(0), new BigDecimal("301"), new BigDecimal("5.5"));
    }

    private void assertOrderBookItem(OrderBookItem orderBookItem, BigDecimal expPrice, BigDecimal expQty) {
        assertNotNull(orderBookItem);
        assertEquals(expPrice, orderBookItem.getPrice());
        assertEquals(expQty, orderBookItem.getQty());
    }
}
