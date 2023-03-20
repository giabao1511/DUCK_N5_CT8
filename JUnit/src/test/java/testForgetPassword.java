import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testForgetPassword {
    @Test
    public void testEmailBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testValidEmail() {
        Login login = new Login();
        boolean result = login.checkEmail("giabao@gmail.com");
        assertTrue(result);
    }
}

