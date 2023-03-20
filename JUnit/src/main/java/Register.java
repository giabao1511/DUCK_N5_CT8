public class Register {
    public boolean checkLengthInput(String input, int length) {
        return input.length() >= length;
    }

    public boolean checkMatchString(String input1, String input2) {
        return input1 == input2;
    }
}
