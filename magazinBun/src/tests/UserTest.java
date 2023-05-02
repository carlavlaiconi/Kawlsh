import com.example.magazinBun.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testConstructor() {
        User user = new User("username", "email@example.com", "password");
        assertEquals("username", user.getName());
        assertEquals("email@example.com", user.getEmail());
        assertEquals("password", user.getParola());
    }


}
