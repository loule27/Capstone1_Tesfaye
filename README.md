# LedgerPro — Personal Transaction Tracker

A Java console application for tracking personal deposits and payments.
LedgerPro lets you log transactions, browse your ledger, and run date-based
or custom reports from a clean, color-coded terminal interface.

## Features

- Log deposits and payments with vendor, description, and amount
- Browse all transactions, deposits only, or payments only
- Filter transactions by month, year, or custom search criteria
- Color-coded display — deposits in green, payments in red
- All transactions are saved to a CSV file and reloaded on startup

## How to Run

### Prerequisites
- Java 17 or higher

### From IntelliJ
1. Open the project
2. Run `Main.java`

### From the Command Line
```bash
javac src/main/java/*.java -d out
java -cp out Main
```
