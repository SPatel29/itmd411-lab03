import java.util.Comparator;

public class LocationComparator implements Comparator<BankRecords>{
    public int compare(BankRecords o1, BankRecords o2){
        return o1.get_region().compareTo(o2.get_region());
    }
    
}
