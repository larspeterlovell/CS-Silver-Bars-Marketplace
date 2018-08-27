package marketplace.impl;

import marketplace.OrderType;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderImplTest {

    @Test
    public void testCreateOrder() {
        OrderImpl order = new OrderImpl(OrderType.BUY, new BigDecimal("302"), new BigDecimal("3.5"), "userId1");
        assertNotNull(order);
        assertEquals(OrderType.BUY, order.getOrderType());
        assertEquals(new BigDecimal("302"), order.getPrice());
        assertEquals(new BigDecimal("3.5"), order.getQty());
        assertEquals("userId1", order.getUserId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNullOrderTypeThrows() {
        new OrderImpl(null, new BigDecimal("302"), new BigDecimal("3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNegativePriceThrows() {
        new OrderImpl(OrderType.BUY, new BigDecimal("-300"), new BigDecimal("3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderZeroPriceThrows() {
        new OrderImpl(OrderType.BUY, new BigDecimal("0"), new BigDecimal("3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNegativeQtyThrows() {
        new OrderImpl(OrderType.BUY, new BigDecimal("302"), new BigDecimal("-3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderZeroQtyThrows() {
        new OrderImpl(OrderType.BUY, new BigDecimal("302"), new BigDecimal("0"), "userId1");
    }

}
