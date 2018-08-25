package marketplace.impl;

import marketplace.OrderBoard;
import marketplace.OrderBook;
import marketplace.OrderBookItem;
import marketplace.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderBoardTest {

    private OrderBoard orderBoard;

    @Before
    public void setup() {
        orderBoard = new OrderBoardImpl();
        addOrders();
    }

    private void addOrders() {
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.5"), "userId1"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("306"), new BigDecimal("7.5"), "userId2"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("301"), new BigDecimal("5.5"), "userId4"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("1.5"), "userId4"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("304"), new BigDecimal("1.0"), "userId5"));
    }

    @Test
    public void testGetOrderBookSummaryRegisteredOrders() {
        OrderBook orderBook = orderBoard.getOrderBoardSummary();
        List<OrderBookItem> orderBookItems = orderBook.getOrderBookItems(OrderType.BUY);
        assertNotNull(orderBookItems);
        assertEquals(2, orderBookItems.size());
        assertOrderBookItem(orderBookItems.get(0), new BigDecimal("303"), new BigDecimal("8.0"));
        assertOrderBookItem(orderBookItems.get(1), new BigDecimal("304"), new BigDecimal("1.0"));
        orderBookItems = orderBook.getOrderBookItems(OrderType.SELL);
        assertNotNull(orderBookItems);
        assertEquals(3, orderBookItems.size());
        assertOrderBookItem(orderBookItems.get(0), new BigDecimal("301"), new BigDecimal("5.5"));
        assertOrderBookItem(orderBookItems.get(1), new BigDecimal("303"), new BigDecimal("3.0"));
        assertOrderBookItem(orderBookItems.get(2), new BigDecimal("306"), new BigDecimal("7.5"));
    }

    @Test
    public void testGetOrderBookSummaryCancelOrders() {
        orderBoard.cancelOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.cancelOrder(new OrderImpl(OrderType.SELL, new BigDecimal("303"), new BigDecimal("3.0"), "userId6"));
        OrderBook orderBook = orderBoard.getOrderBoardSummary();
        List<OrderBookItem> orderBookItems = orderBook.getOrderBookItems(OrderType.BUY);
        assertNotNull(orderBookItems);
        assertEquals(2, orderBookItems.size());
        assertOrderBookItem(orderBookItems.get(0), new BigDecimal("303"), new BigDecimal("5.0"));
        assertOrderBookItem(orderBookItems.get(1), new BigDecimal("304"), new BigDecimal("1.0"));
        orderBookItems = orderBook.getOrderBookItems(OrderType.SELL);
        assertNotNull(orderBookItems);
        assertEquals(2, orderBookItems.size());
        assertOrderBookItem(orderBookItems.get(0), new BigDecimal("301"), new BigDecimal("5.5"));
        assertOrderBookItem(orderBookItems.get(1), new BigDecimal("306"), new BigDecimal("7.5"));
    }

    private void assertOrderBookItem(OrderBookItem orderBookItem, BigDecimal expPrice, BigDecimal expQty) {
        assertNotNull(orderBookItem);
        assertEquals(expPrice, orderBookItem.getPrice());
        assertEquals(expQty, orderBookItem.getQty());
    }
}
