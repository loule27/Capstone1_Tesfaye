import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        List<Transaction> transactions = TransactionFileManager.getTransactions();
        HomeScreen.displayHome(transactions, kb);
    }

    public static void printHeader(String screenName) {
        System.out.println();
        System.out.println(Color.CYAN + "=".repeat(60) + Color.RESET);
        System.out.println(Color.BOLD + Color.CYAN +
                center("L E D G E R  P R O", 60) + Color.RESET);
        System.out.println(Color.DIM +
                center("Personal Transaction Tracker", 60) + Color.RESET);
        System.out.println(Color.CYAN + "-".repeat(60) + Color.RESET);
        System.out.println(Color.BOLD +
                center(screenName, 60) + Color.RESET);
        System.out.println(Color.CYAN + "=".repeat(60) + Color.RESET);
        System.out.println();
    }

    // Centers text within a given width by padding with spaces on the left
    public static String center(String text, int width) {
        if (text.length() >= width) {return text;}
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text;
    }


}
