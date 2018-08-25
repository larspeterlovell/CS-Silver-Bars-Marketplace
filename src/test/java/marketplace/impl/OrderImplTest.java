package marketplace.impl;

import marketplace.OrderType;

import java.math.BigDecimal;

public class OrderImplTest {

    private OrderImpl order;

    @Before
    public void setup() {
        order = new OrderImpl("lplovell", new BigDecimal(3.5), new BigDecimal(303), OrderType.BUY);
    }

    @Test
    public void testGetQty() {
        Assert
    }
}
