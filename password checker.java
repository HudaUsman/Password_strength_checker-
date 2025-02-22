import java.util.Scanner;

public class Main {
    // List of commonly used passwords (partial for demonstration)
    private static final String[] COMMON_PASSWORDS = {
        "password", "123456", "123456789", "qwerty", "abc123", "password123"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.println("\nPassword: " + password);
        System.out.println("Strength: " + evaluatePassword(password));

        scanner.close();
    }

    public static String evaluatePassword(String password) {
        int score = 0;

        // Length check
        if (password.length() >= 12) score++;
        if (password.length() >= 16) score++; // Extra point for very strong length

        // Character variety
        if (password.matches(".*[A-Z].*")) score++; // Uppercase
        if (password.matches(".*[a-z].*")) score++; // Lowercase
        if (password.matches(".*[0-9].*")) score++; // Numbers
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) score++; // Special characters

        // Check for common passwords
        for (String common : COMMON_PASSWORDS) {
            if (password.toLowerCase().contains(common)) {
                return "❌ Very Weak (Common Password Used)";
            }
        }

        // Entropy Calculation (Simplified)
        double entropy = calculateEntropy(password);
        if (entropy > 50) score++;

        // Return Strength Level
        return getStrengthLabel(score);
    }

    public static double calculateEntropy(String password) {
        int charsetSize = 0;
        if (password.matches(".*[a-z].*")) charsetSize += 26;
        if (password.matches(".*[A-Z].*")) charsetSize += 26;
        if (password.matches(".*[0-9].*")) charsetSize += 10;
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) charsetSize += 20;

        return password.length() * (Math.log(charsetSize) / Math.log(2));
    }

    public static String getStrengthLabel(int score) {
        switch (score) {
            case 0:
            case 1: return "❌ Very Weak";
            case 2: return ⚠️ Weak";
            case 3: return "🟡 Moderate";
            case 4: return "🟢 Strong";
            case 5:
            case 6: return "💪 Very Strong";
            default: return "Unknown Strength";
        }
    }
}
