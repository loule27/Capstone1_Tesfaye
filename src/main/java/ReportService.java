import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ReportService {
    public static List<Transaction> getMonthToDate(List<Transaction> list){
        //get today's month and year
        YearMonth today = YearMonth.now();
        List<Transaction> output = new ArrayList<>();
        for(Transaction t: list){
            if(YearMonth.from(t.getDate()).equals(today)) {
                output.add(t);
            }
        }
        return output;
    }
    public static List<Transaction> getPreviousMonth(List<Transaction> list){
        YearMonth previous = YearMonth.now().minusMonths(1);
        List<Transaction> output = new ArrayList<>();
        for(Transaction t : list){
            if (YearMonth.from(t.getDate()).equals(previous)){
                output.add(t);
            }
        }
        return output;
    }
    public static List<Transaction> getYearToDate(List<Transaction> list){
        int currentYear = LocalDate.now().getYear();
        List<Transaction> output = new ArrayList<>();
        for(Transaction t: list){
            if(t.getDate().getYear() == currentYear){
                output.add(t);
            }
        }
        return output;
    }
    public static List<Transaction> getPreviousYear(List<Transaction> list){
        int previousYear = LocalDate.now().getYear() -1;
        List<Transaction> output = new ArrayList<>();
        for(Transaction t : list){
            if(t.getDate().getYear() == previousYear){
                output.add(t);
            }
        }
        return output;




    }
    public static List<Transaction> customSearch(List<Transaction> list, String startDate, String endDate,
                                                 String description, String vendor, String amount){
        List<Transaction> output = new ArrayList<>();
        for(Transaction t: list){
            boolean filter = true;

            if(!startDate.isBlank()){
                LocalDate start = LocalDate.parse(startDate);
                if (t.getDate().isBefore(start)){
                    filter = false;
                }
            }
            if(!endDate.isBlank()){
                LocalDate end = LocalDate.parse(endDate);
                if(t.getDate().isAfter(end)){
                    filter = false;
                }
            }
            if(!description.isBlank()){
                if(!t.getDescription().toLowerCase().contains(description.toLowerCase())){
                    filter = false;
                }
            }
            if(!vendor.isBlank()){
                if(!t.getVendor().toLowerCase().contains(vendor.toLowerCase())){
                    filter = false;
                }
                }
            if(!amount.isBlank()){
                try {
                    double searchAmount = Double.parseDouble(amount);
                    if (t.getAmount() != searchAmount) {
                        filter = false;
                    }
                }
                    catch(NumberFormatException e){
                        System.out.println("Invalid amount ignored. ");
                    }
                }
            if(filter){
                output.add(t);
            }
        }
        return  output;

        }
    }

