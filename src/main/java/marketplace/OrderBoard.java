package marketplace;

import java.util.List;
import java.util.Map;

public interface OrderBoard {
    void registerOrder(Order order);
    void cancelOrder(Order order);
    Map<OrderType, List<OrderSummary>> getOrderBoardSummary();

}
