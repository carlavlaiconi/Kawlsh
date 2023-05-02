import com.example.magazinBun.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    void testGettersAndSetters() {
        Product product = new Product(1, "Shampoo", "10.99", "Brand X");
        assertEquals(1, product.getId());
        assertEquals("Shampoo", product.getName());
        assertEquals("10.99", product.getPrice());
        assertEquals("Brand X", product.getBrand());

        product.setId_product(2);
        product.setName("Conditioner");
        product.setPrice("12.99");
        product.setBrand("Brand Y");

        assertEquals(2, product.getId_product());
        assertEquals("Conditioner", product.getName());
        assertEquals("12.99", product.getPrice());
        assertEquals("Brand Y", product.getBrand());
    }

    @Test
    public void testProductConstructor() {
        Product product = new Product(1, "Apple", "1.99", "Fruit Inc");
        assertEquals(1, product.getId());
        assertEquals("Apple", product.getName());
        assertEquals("1.99", product.getPrice());
        assertEquals("Fruit Inc", product.getBrand());
    }


}