public class ReverseString {

    /**
     * Reverses a string using the StringBuilder class's built-in reverse() method.
     * @param str The string to reverse.
     * @return The reversed string.
     */
    public static String reverseStringUsingStringBuilder(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Reverses a string manually by iterating from the end and building a new string.
     * @param str The string to reverse.
     * @return The reversed string.
     */
    public static String reverseStringManual(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] characters = str.toCharArray();
        int left = 0;
        int right = characters.length - 1;

        while (left < right) {
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;

            left++;
            right--;
        }

        return new String(characters);
    }

    public static void main(String[] args) {
        String original = "Hello World!";
        String reversed = reverseStringUsingStringBuilder(original);
        System.out.println("Original: " + original);
        System.out.println("Reversed (Easiest): " + reversed);

        String originalManual = "Java Programming";
        String reversedManual = reverseStringManual(original);
        System.out.println("Original: " + originalManual);
        System.out.println("Reversed (Manual): " + reversedManual); // Output: gnimmargorP avaJ
    }
}
