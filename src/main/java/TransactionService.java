import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    public static List<Transaction> getDeposits(List<Transaction> list){
        List<Transaction> output = new ArrayList<>();
        for(Transaction t: list){
            if(t.getAmount() > 0){
                output.add(t);
            }
        }
        return output;
    }

    public static List<Transaction> getPayments(List<Transaction> list){
        List<Transaction> output = new ArrayList<>();
        for(Transaction t : list){
            if(t.getAmount() < 0){
                output.add(t);
            }
        }
        return output;

    }

    public static List<Transaction> searchByVendor(List<Transaction> list, String vendor){
        List<Transaction> output = new ArrayList<>();
        for(Transaction t: list){
            if(t.getVendor().toLowerCase().contains(vendor.toLowerCase())){
                output.add(t);
            }
        }
        return output;
    }
}
