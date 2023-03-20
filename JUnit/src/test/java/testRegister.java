import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testRegister {
    @Test
    public void testEmailBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testNameBlank() {
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
    public void testFirstCharacterName() {
        Login login = new Login();
        boolean result = login.checkFirstCharacter(" giabao");
        assertFalse(result);
    }

    @Test
    public void testPasswordBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testPasswordLength() {
        Register register = new Register();
        boolean result = register.checkLengthInput("abcxyz123", 8);
        assertTrue(result);
    }

    @Test
    public void testConfirmPasswordBlank() {
        Login login = new Login();
        boolean result = login.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testConfirmPasswordLength() {
        Register register = new Register();
        boolean result = register.checkLengthInput("abcxyz123", 8);
        assertTrue(result);
    }

    @Test
    public void testPasswordMatch() {
        Register register = new Register();
        boolean result = register.checkMatchString("abcxyz123", "abcxyz123");
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

