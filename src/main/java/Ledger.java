import java.util.List;
import java.util.Scanner;

public class Ledger {
    public static void displayScreen(List<Transaction> transactions, Scanner kb) {
        while (true) {
            Main.printHeader("LEDGER");

            System.out.println(Color.CYAN + "  Browse your transactions:" + Color.RESET);
            System.out.println();
            System.out.println(Color.WHITE  + "  A" + Color.RESET + "  ->  All Transactions");
            System.out.println(Color.GREEN  + "  D" + Color.RESET + "  ->  Deposits Only");
            System.out.println(Color.RED    + "  P" + Color.RESET + "  ->  Payments Only");
            System.out.println(Color.CYAN   + "  R" + Color.RESET + "  ->  Reports & Search");
            System.out.println(Color.DIM    + "  H" + Color.RESET + "  ->  Back to Home");
            System.out.println();
            System.out.println(Color.CYAN + "-".repeat(60) + Color.RESET);
            System.out.print(Color.BOLD + "  Command: " + Color.RESET);

            String input = kb.nextLine().trim().toUpperCase();

            switch (input) {
                case "A":
                    printTransactions(transactions);
                    break;
                case "D":
                    printTransactions(TransactionService.getDeposits(transactions));
                    break;
                case "P":
                    printTransactions(TransactionService.getPayments(transactions));
                    break;
                case "R":
                    showReports(transactions, kb);
                    break;
                case "H":
                    return;
                default:
                    System.out.println(Color.RED + "  Invalid option." + Color.RESET);
            }
        }
    }

    private static void printTransactions(List<Transaction> transactions) {
        System.out.println();

        if (transactions.isEmpty()) {
            System.out.println(Color.YELLOW +
                    ("  No transactions found.") + Color.RESET);
            System.out.println();
            return;
        }

        System.out.println(Color.CYAN + "-".repeat(79) + Color.RESET);
        System.out.println(Color.BOLD + String.format(
                "  %-12s %-10s %-20s %-18s %10s",
                "Date", "Time", "Description", "Vendor", "Amount") + Color.RESET);
        System.out.println(Color.CYAN + "-".repeat(79) + Color.RESET);

        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            System.out.println("  " + t);
            if (t.getAmount() > 0)
            {totalDeposits += t.getAmount();}
            else
            {totalPayments += t.getAmount();}
        }

        System.out.println(Color.CYAN + "-".repeat(79) + Color.RESET);
        System.out.println(Color.GREEN  +
                String.format("  Total Deposits:  $%.2f", totalDeposits) + Color.RESET);
        System.out.println(Color.RED    +
                String.format("  Total Payments:  $%.2f", totalPayments) + Color.RESET);
        System.out.println(Color.BOLD   +
                String.format("  Current Balance: $%.2f",
                        totalDeposits + totalPayments) + Color.RESET);
        System.out.println();
    }
    private static void showReports(List<Transaction> transactions, Scanner kb) {
        while (true) {
            Main.printHeader("REPORTS & SEARCH");

            System.out.println(Color.CYAN + "  Pre-defined Reports:" + Color.RESET);
            System.out.println();
            System.out.println(Color.YELLOW + "  1" + Color.RESET + "  ->  Month to Date");
            System.out.println(Color.YELLOW + "  2" + Color.RESET + "  ->  Previous Month");
            System.out.println(Color.YELLOW + "  3" + Color.RESET + "  ->  Year to Date");
            System.out.println(Color.YELLOW + "  4" + Color.RESET + "  ->  Previous Year");
            System.out.println();
            System.out.println(Color.CYAN + "  Custom Search:" + Color.RESET);
            System.out.println();
            System.out.println(Color.WHITE  + "  5" + Color.RESET + "  ->  Search by Vendor");
            System.out.println(Color.WHITE  + "  6" + Color.RESET + "  ->  Custom Search");
            System.out.println();
            System.out.println(Color.DIM    + "  0" + Color.RESET + "  ->  Back to Ledger");
            System.out.println();
            System.out.println(Color.CYAN + "-".repeat(60) + Color.RESET);
            System.out.print(Color.BOLD + "  Command: " + Color.RESET);

            String input = kb.nextLine().trim();

            switch (input) {
                case "1":
                    printTransactions(ReportService.getMonthToDate(transactions));
                    break;
                case "2":
                    printTransactions(ReportService.getPreviousMonth(transactions));
                    break;
                case "3":
                    printTransactions(ReportService.getYearToDate(transactions));
                    break;
                case "4":
                    printTransactions(ReportService.getPreviousYear(transactions));
                    break;
                case "5":
                    String vendor = InputValidator.checkNotBlank("  Enter vendor name: ", kb);
                    if (vendor == null) break;
                    printTransactions(TransactionService.searchByVendor(transactions, vendor));
                    break;
                case "6":
                    customSearch(transactions, kb);
                    break;
                 case "0":
                    return;
                default:
                    System.out.println(Color.RED + "  Invalid option." + Color.RESET);
            }


        }
    }
    private static void customSearch(List<Transaction> transactions, Scanner kb) {
        Main.printHeader("CUSTOM SEARCH");
        System.out.println(Color.DIM + "  Press Enter to skip any field." + Color.RESET);
        System.out.println();

        String startDate = InputValidator.checkOptionalDate(
                Color.CYAN + "  Start Date " + Color.RESET
                        + Color.DIM + "(YYYY-MM-DD): " + Color.RESET, kb);
        if (startDate == null) {return;}

        String endDate = InputValidator.checkOptionalDate(
                Color.CYAN + "  End Date   " + Color.RESET
                        + Color.DIM + "(YYYY-MM-DD): " + Color.RESET, kb);
        if (endDate == null){ return;}

        System.out.print(Color.CYAN + "  Description: " + Color.RESET);
        String description = kb.nextLine().trim();

        System.out.print(Color.CYAN + "  Vendor:      " + Color.RESET);
        String vendor = kb.nextLine().trim();

        String amount = InputValidator.checkOptionalAmount(kb);
        if (amount == null) {return;}

        List<Transaction> results = ReportService.customSearch(
                transactions, startDate, endDate, description, vendor, amount);

        printTransactions(results);
    }

}
