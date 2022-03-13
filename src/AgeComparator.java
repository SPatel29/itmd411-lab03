import java.util.Comparator;

public class AgeComparator implements Comparator<BankRecords>{
    public int compare(BankRecords o1, BankRecords o2){
        return o1.get_age() - o2.get_age();
    }
}
