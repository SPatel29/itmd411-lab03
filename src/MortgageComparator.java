import java.util.Comparator;

public class MortgageComparator implements Comparator<BankRecords>{
    public int compare(BankRecords o1, BankRecords o2){
        return o1.get_mortgage().compareTo(o2.get_mortgage());
    }
}
