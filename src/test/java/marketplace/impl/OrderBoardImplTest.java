package marketplace.impl;

import marketplace.Order;
import marketplace.OrderBoard;
import marketplace.OrderBook;
import marketplace.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OrderBoardImplTest {

    private OrderBoard orderBoard;

    @Before
    public void setup() {
        orderBoard = new OrderBoardImpl();
    }

    @Test
    public void testGetOrderBookSummaryRegisteredOrders() {
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.5"), "userId1"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("306"), new BigDecimal("7.5"), "userId2"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("301"), new BigDecimal("5.5"), "userId4"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("1.5"), "userId4"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("304"), new BigDecimal("1.0"), "userId5"));
        OrderBook orderBook = orderBoard.getOrderBoardSummary();
        SortedMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders = orderBook.getPrice2TotalQtyBuyOrders();
        assertNotNull(price2TotalQtyBuyOrders);
        assertEquals(2, price2TotalQtyBuyOrders.size());
        Iterator<Map.Entry<BigDecimal, BigDecimal>> price2QtyIterBuyOrders =  price2TotalQtyBuyOrders.entrySet().iterator();
        assertTrue(price2QtyIterBuyOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterBuyOrders.next(), new BigDecimal("304"), new BigDecimal("1.0"));
        assertTrue(price2QtyIterBuyOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterBuyOrders.next(), new BigDecimal("303"), new BigDecimal("8.0"));
        SortedMap<BigDecimal, BigDecimal> price2TotalQtySellOrders = orderBook.getPrice2TotalQtySellOrders();
        assertNotNull(price2TotalQtySellOrders);
        assertEquals(3, price2TotalQtySellOrders.size());
        Iterator<Map.Entry<BigDecimal, BigDecimal>> price2QtyIterSellOrders =  price2TotalQtySellOrders.entrySet().iterator();
        assertTrue(price2QtyIterSellOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterSellOrders.next(), new BigDecimal("301"), new BigDecimal("5.5"));
        assertTrue(price2QtyIterSellOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterSellOrders.next(), new BigDecimal("303"), new BigDecimal("3.0"));
        assertTrue(price2QtyIterSellOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterSellOrders.next(), new BigDecimal("306"), new BigDecimal("7.5"));
    }

    @Test
    public void testGetOrderBookSummaryCancelOrders() {
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.5"), "userId1"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("306"), new BigDecimal("7.5"), "userId2"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("301"), new BigDecimal("5.5"), "userId4"));
        orderBoard.registerOrder(new OrderImpl(OrderType.SELL, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("1.5"), "userId4"));
        orderBoard.registerOrder(new OrderImpl(OrderType.BUY, new BigDecimal("304"), new BigDecimal("1.0"), "userId5"));
        orderBoard.cancelOrder(new OrderImpl(OrderType.BUY, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        orderBoard.cancelOrder(new OrderImpl(OrderType.SELL, new BigDecimal("303"), new BigDecimal("3.0"), "userId3"));
        OrderBook orderBook = orderBoard.getOrderBoardSummary();
        SortedMap<BigDecimal, BigDecimal> price2TotalQtyBuyOrders = orderBook.getPrice2TotalQtyBuyOrders();
        assertNotNull(price2TotalQtyBuyOrders);
        assertEquals(2, price2TotalQtyBuyOrders.size());
        Iterator<Map.Entry<BigDecimal, BigDecimal>> price2QtyIterBuyOrders =  price2TotalQtyBuyOrders.entrySet().iterator();
        assertTrue(price2QtyIterBuyOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterBuyOrders.next(), new BigDecimal("304"), new BigDecimal("1.0"));
        assertTrue(price2QtyIterBuyOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterBuyOrders.next(), new BigDecimal("303"), new BigDecimal("5.0"));
        SortedMap<BigDecimal, BigDecimal> price2TotalQtySellOrders = orderBook.getPrice2TotalQtySellOrders();
        assertNotNull(price2TotalQtySellOrders);
        assertEquals(2, price2TotalQtySellOrders.size());
        Iterator<Map.Entry<BigDecimal, BigDecimal>> price2QtyIterSellOrders =  price2TotalQtySellOrders.entrySet().iterator();
        assertTrue(price2QtyIterSellOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterSellOrders.next(), new BigDecimal("301"), new BigDecimal("5.5"));
        assertTrue(price2QtyIterSellOrders.hasNext());
        assertPrice2QtyEntry(price2QtyIterSellOrders.next(), new BigDecimal("306"), new BigDecimal("7.5"));
    }

    @Test
    public void testOrderBoardWithMultipleThreads() throws Exception{
        final int NO_OF_THREADS_PER_ACTION_TYPE = 10;
        final int TOT_NO_OF_THREADS = NO_OF_THREADS_PER_ACTION_TYPE * 2;
        CountDownLatch allThreadsInitialisedLatch = new CountDownLatch(1);
        CountDownLatch allThreadsFinishedLatch = new CountDownLatch(TOT_NO_OF_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(TOT_NO_OF_THREADS);
        for (int i = 0; i < NO_OF_THREADS_PER_ACTION_TYPE; i++) {
            final int no = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        allThreadsInitialisedLatch.await();
                        orderBoard.registerOrder(createBulkOrder(OrderType.BUY, no));
                        orderBoard.registerOrder(createBulkOrder(OrderType.SELL, no));
                        orderBoard.cancelOrder(createBulkOrder(OrderType.BUY, no));
                        orderBoard.cancelOrder(createBulkOrder(OrderType.SELL, no));
                        orderBoard.registerOrder(createBulkOrder(OrderType.BUY, no));
                        orderBoard.registerOrder(createBulkOrder(OrderType.SELL, no));
                        allThreadsFinishedLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        allThreadsInitialisedLatch.await();
                        orderBoard.getOrderBoardSummary();
                        orderBoard.getOrderBoardSummary();
                        orderBoard.getOrderBoardSummary();
                        allThreadsFinishedLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(3000);
        allThreadsInitialisedLatch.countDown();
        allThreadsFinishedLatch.await();
        // Verify the order book
        OrderBook orderBook = orderBoard.getOrderBoardSummary();
        SortedMap<BigDecimal, BigDecimal> price2QtyBuyOrders = orderBook.getPrice2TotalQtyBuyOrders();
        SortedMap<BigDecimal, BigDecimal> price2QtySellOrders = orderBook.getPrice2TotalQtySellOrders();
        assertNotNull(price2QtyBuyOrders);
        assertNotNull(price2QtySellOrders);
        assertEquals(NO_OF_THREADS_PER_ACTION_TYPE, price2QtyBuyOrders.size());
        assertEquals(NO_OF_THREADS_PER_ACTION_TYPE, price2QtySellOrders.size());
        Iterator<Map.Entry<BigDecimal, BigDecimal>> price2QtyIterBuyOrders =  price2QtyBuyOrders.entrySet().iterator();
        Iterator<Map.Entry<BigDecimal, BigDecimal>> price2QtyIterSellOrders =  price2QtySellOrders.entrySet().iterator();
        for (int no = NO_OF_THREADS_PER_ACTION_TYPE - 1; no >= 0; no--) {
            assertTrue(price2QtyIterBuyOrders.hasNext());
            assertBulkOrderPrice2QtyEntry(price2QtyIterBuyOrders.next(), no);
        }
        for (int no = 0; no < NO_OF_THREADS_PER_ACTION_TYPE; no++) {
            assertTrue(price2QtyIterSellOrders.hasNext());
            assertBulkOrderPrice2QtyEntry(price2QtyIterSellOrders.next(), no);
        }
    }

    private Order createBulkOrder(OrderType orderType, int no) {
        return new OrderImpl(orderType, getBulkOrderPrice(no), getBulkOrderQty(no), "userId1");
    }

    private BigDecimal getBulkOrderPrice(int no) {
        return new BigDecimal(300 + no);
    }

    private BigDecimal getBulkOrderQty(int no) {
        return new BigDecimal(String.valueOf(no) + ".5");
    }

    private void assertBulkOrderPrice2QtyEntry(Map.Entry<BigDecimal, BigDecimal> price2QtyEntry, int no) {
        assertPrice2QtyEntry(price2QtyEntry, getBulkOrderPrice(no), getBulkOrderQty(no));
    }

    private void assertPrice2QtyEntry(Map.Entry<BigDecimal, BigDecimal> price2QtyEntry, BigDecimal expPrice, BigDecimal expQty) {
        assertEquals(expPrice, price2QtyEntry.getKey());
        assertEquals(expQty, price2QtyEntry.getValue());
    }

}
