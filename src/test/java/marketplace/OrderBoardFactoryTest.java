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
}
