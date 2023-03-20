import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
    public boolean isBlank(String input) {
        return input.trim() == "";
    }

    public boolean checkEmail(String input) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    public boolean checkFirstCharacter(String input) {
        return input.charAt(0) != ' ';
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for a valid phone number
        String regex = "\\d{10}";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Match the regular expression with the input phone number
        Matcher matcher = pattern.matcher(phoneNumber);

        // Return true if the phone number matches the regular expression, false otherwise
        return matcher.matches();
    }
}
