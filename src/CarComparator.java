import java.util.Comparator;

public class CarComparator implements Comparator<BankRecords>{
    public int compare(BankRecords o1, BankRecords o2){
        return o1.get_Car().compareTo(o2.get_Car());
    }
}
