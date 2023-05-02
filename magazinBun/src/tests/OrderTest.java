import com.example.magazinBun.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testOrder() {
        Order order = new Order(1, "123 Main St.", "Product A, Product B");
        assertEquals(1, order.getId_user());
        assertEquals("Product A, Product B", order.getProductList());
        assertFalse(order.isConfirmed());

        order.setId_user(2);
        order.setProductList("Product C, Product D");
        order.setConfirmed(true);

        assertEquals(2, order.getId_user());
        assertEquals("Product C, Product D", order.getProductList());
        assertTrue(order.isConfirmed());

        order.setConfirm(false);
        assertFalse(order.getConfirm());
        order.setConfirm(true);
    }
}