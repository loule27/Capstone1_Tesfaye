import java.time.LocalDate;
import java.util.Scanner;

public class InputValidator {

    //simple universal validators
    public static boolean isNotBlank(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidAmount(String input) {
        try {
            double value = Double.parseDouble(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //Retry menu for managing required and optional user inputs
    private static String RetryMenu(boolean allowSkip, Scanner kb) {
        System.out.println("  1. Try again");
        if (allowSkip) {
            System.out.println("  2. Skip this field");
            System.out.println("  3. Go back");
        } else {
            System.out.println("  2. Go back");
        }
        System.out.print("  Enter choice: ");

        while (true) {
            String choice = kb.nextLine().trim();
            if (choice.equals("1")) {return "1";}
            if (choice.equals("2")) {return "2";}
            if (allowSkip && choice.equals("3")) {return "3";}
            System.out.println(Color.RED + "  Invalid option." + Color.RESET);
            System.out.print("  Enter choice: ");
        }
    }



    // Required — blank not allowed
    public static String checkNotBlank(String prompt, Scanner kb) {
        while (true) {
            System.out.print(prompt);
            String value = kb.nextLine().trim();

            if (isNotBlank(value)) {return value;}

            System.out.println(Color.RED
                    + "  This field cannot be blank." + Color.RESET);
            String choice = RetryMenu(false, kb);
            if (choice.equals("2")) {return null;} //go back
        }
    }

    // Required positive number
    public static Double checkValidAmount(Scanner kb) {
        while (true) {
            System.out.print(Color.CYAN + "  Enter amount: " + Color.RESET);
            String input = kb.nextLine().trim();

            if (isValidAmount(input)) {return Double.parseDouble(input);}

            System.out.println(Color.RED
                    + "  Please enter a valid positive number." + Color.RESET);
            String choice = RetryMenu(false, kb);
            if (choice.equals("2")) {return null;}
        }
    }

    // Optional date — blank means skip this field
    public static String checkOptionalDate(String prompt, Scanner kb) {
        while (true) {
            System.out.print(prompt);
            String value = kb.nextLine().trim();

            if (value.isEmpty()) return "";
            if (isValidDate(value)) return value;

            System.out.println(Color.RED
                    + "  Invalid date format. Use YYYY-MM-DD." + Color.RESET);
            String choice = RetryMenu(true, kb);
            if (choice.equals("2")) {return "";} //skip
            if (choice.equals("3")) {return null;}
        }
    }

    // Optional amount — blank means skip this field
    public static String checkOptionalAmount(Scanner kb) {
        while (true) {
            System.out.print(Color.CYAN + "  Amount:      " + Color.RESET);
            String value = kb.nextLine().trim();

            if (value.isEmpty()) {return "";}
            if (isValidAmount(value)) {return value;}

            System.out.println(Color.RED
                    + "  Invalid amount. Enter a number."
                    + Color.RESET);
            String choice = RetryMenu(true, kb);
            if (choice.equals("2")) {return "";}
            if (choice.equals("3")) {return null;}
        }
    }
}