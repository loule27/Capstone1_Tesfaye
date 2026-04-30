
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class TransactionFileManager {

    public static List<Transaction> getTransactions(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
            String line;
            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()) {continue;}

                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0].trim());
                LocalTime time = LocalTime.parse(parts[1].trim());
                String description = parts[2].trim();
                String vendor = parts[3].trim();
                double amount = Double.parseDouble(parts[4].trim());

                transactions.add(new Transaction(date, time, description,vendor, amount));

            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println("Problem with reading file");
        }
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
        return transactions;
    }

    public static void updateTransaction(Transaction t){
        try{
            File file = new File("src/main/resources/transactions.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            if(file.length() > 0){
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.write(String.format("%s|%s|%s|%s|%.2f", t.getDate(),
                    t.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    t.getDescription(), t.getVendor(), t.getAmount()));

            fileWriter.close();
        }
        catch(IOException ex){
            System.out.println("Error writing to file.");

        }
    }
}
