import java.util.Comparator;

public class ChildComparator implements Comparator<BankRecords>{
    public int compare(BankRecords o1, BankRecords o2){
        return o1.get_children() - o2.get_children();
    }
}
