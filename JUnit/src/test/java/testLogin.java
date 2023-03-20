import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testLogin {
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

    @Test
    public void testFirstCharacterEmail() {
        Login login = new Login();
        boolean result = login.checkFirstCharacter(" giabao@gmail.com");
        assertFalse(result);
    }

    @Test
    public void testPasswordBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testFirstCharacterPhoneNum() {
        Login login = new Login();
        boolean result = login.checkFirstCharacter(" 0112113");
        assertFalse(result);
    }

    @Test
    public void testValidPhoneNum() {
        Login login = new Login();
        boolean result = login.isValidPhoneNumber("0339253073");
        assertTrue(result);
    }
}

