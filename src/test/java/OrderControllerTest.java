import org.example.Order;
import org.example.OrderController;
import org.example.OrderDao;
import org.example.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    private OrderController orderController;
    private OrderService orderServiceMock;
    private OrderDao orderDaoMock;

    @BeforeEach
    public void setup() {
        orderServiceMock = mock(OrderService.class);
        orderDaoMock = mock(OrderDao.class);
        orderController = new OrderController(orderServiceMock);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order(/* remplissez avec des données appropriées */);
        orderController.createOrder(order);
        verify(orderServiceMock, times(1)).createOrder(order);
    }
}
