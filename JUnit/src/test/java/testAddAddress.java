import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testAddAddress {
    @Test
    public void testNameBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testLocationBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testPhoneNumBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testCityBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testDistrictBlank() {
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

