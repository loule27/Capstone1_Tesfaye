import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class HomeScreen {
    public static void displayHome(List<Transaction> transactions, Scanner kb) {
        while (true) {
            Main.printHeader("HOME");

            System.out.println(Color.CYAN + "  What would you like to do?" + Color.RESET);
            System.out.println();
            System.out.println(Color.GREEN  + "  D" + Color.RESET + "  ->  Log a Deposit");
            System.out.println(Color.RED    + "  P" + Color.RESET + "  ->  Log a Payment");
            System.out.println(Color.YELLOW + "  L" + Color.RESET + "  ->  View Ledger");
            System.out.println(Color.DIM    + "  X" + Color.RESET + "  ->  Exit LedgerPro");
            System.out.println();
            System.out.println(Color.CYAN + "-".repeat(60) + Color.RESET);
            System.out.print(Color.BOLD + "  Command: " + Color.RESET);

            String input = kb.nextLine().trim().toUpperCase();

            switch (input) {
                case "D":
                    processDeposit(transactions, kb);
                    break;
                case "P":
                    processPayment(transactions, kb);
                    break;
                case "L":
                    Ledger.displayScreen(transactions, kb);
                    break;
                case "X":
                    System.out.println();
                    System.out.println(Color.CYAN + Main.center("Thank you for using LedgerPro. Goodbye!", 60) + Color.RESET);
                    System.out.println();
                    System.exit(0);
                    break;
                default:
                    System.out.println(Color.RED + "  Invalid option. Please enter D, P, L, or X." + Color.RESET);
            }
        }
    }

    private static void processDeposit(List<Transaction> transactions, Scanner kb){

        String description = InputValidator.checkNotBlank("Enter description: ", kb);
        if(description == null) {return;}
        String vendor = InputValidator.checkNotBlank("Enter vendor: ", kb);
        if(vendor == null) {return;}
        Double amount = InputValidator.checkValidAmount(kb);
        if(amount == null) {return;}

        Transaction deposit  = new Transaction(LocalDate.now(),
                LocalTime.now(), description, vendor, amount);

        transactions.add(0, deposit);
        TransactionFileManager.updateTransaction(deposit);
        System.out.println("Deposit added.");

    }
    private static void processPayment(List <Transaction> transactions, Scanner kb){
        String description = InputValidator.checkNotBlank("Enter description: ", kb);
        if(description == null) {return;}
        String vendor = InputValidator.checkNotBlank("Enter vendor: ", kb);
        if(vendor == null) {return;}
        Double amount = InputValidator.checkValidAmount(kb);
        if(amount == null) {return;}

        Transaction t = new Transaction(
                LocalDate.now(), LocalTime.now(),
                description, vendor, -amount);

        transactions.add(0, t);
        TransactionFileManager.updateTransaction(t);
        System.out.println("Payment processed.");

    }

}
