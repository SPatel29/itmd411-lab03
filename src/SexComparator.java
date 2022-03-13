import java.util.Comparator;

public class SexComparator implements Comparator<BankRecords>{

    public int compare(BankRecords o1, BankRecords o2){
        int result = o1.get_sex().compareTo(o2.get_sex());
        return result;
    }
}