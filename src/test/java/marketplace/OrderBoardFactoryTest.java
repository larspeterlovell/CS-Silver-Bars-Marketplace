package marketplace;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderBoardFactoryTest {

    @Test
    public void testCreateOrderBoard() {
        OrderBoard orderBoard = OrderBoardFactory.INSTANCE.createOrderBoard();
        assertNotNull(orderBoard);
    }

    @Test
    public void testCreateOrder() {
        Order order = OrderBoardFactory.INSTANCE.createOrder(OrderType.BUY, new BigDecimal("302"), new BigDecimal("3.5"), "userId1");
        assertNotNull(order);
        assertEquals(OrderType.BUY, order.getOrderType());
        assertEquals(new BigDecimal("302"), order.getPrice());
        assertEquals(new BigDecimal("3.5"), order.getQty());
        assertEquals("userId1", order.getUserId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNullOrderTypeThrows() {
        OrderBoardFactory.INSTANCE.createOrder(null, new BigDecimal("302"), new BigDecimal("3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNegativePriceThrows() {
        OrderBoardFactory.INSTANCE.createOrder(OrderType.BUY, new BigDecimal("-300"), new BigDecimal("3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderZeroPriceThrows() {
        OrderBoardFactory.INSTANCE.createOrder(OrderType.BUY, new BigDecimal("0"), new BigDecimal("3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNegativeQtyThrows() {
        OrderBoardFactory.INSTANCE.createOrder(OrderType.BUY, new BigDecimal("302"), new BigDecimal("-3.5"), "userId1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderZeroQtyThrows() {
        OrderBoardFactory.INSTANCE.createOrder(OrderType.BUY, new BigDecimal("302"), new BigDecimal("0"), "userId1");
    }

}
