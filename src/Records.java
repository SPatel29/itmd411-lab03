import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;

public class Records extends BankRecords {
    static FileWriter fw = null;

    public Records() {
        try {
            fw = new FileWriter("bankrecords.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File was not found. Check typos");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Soemthign went wrong when trying to read the file");
        }
    }

    public static void main(String[] args) {
        Records br = new Records();
        br.read_data();
        br.process_data();
        AvgComp();
        try {
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void AvgComp() {
        Arrays.sort(records, new SexComparator());
        int male_count = 0, female_count = 0;
        double male_total_income = 0, female_total_income = 0;
        for (int person = 0; person < records.length; person += 1) {
            if (records[person].get_sex().equals("FEMALE")) {
                female_count += 1;
                female_total_income += records[person].get_income();
            } else {
                male_count += 1;
                male_total_income += records[person].get_income();
            }
        }
        System.out.printf("Avg income for female employes rounded to two decimal points: $%.2f\n",
                (female_total_income / female_count));
        System.out.printf("Avg income for male employes rounded to two decimal points: $%.2f\n",
                (male_total_income / male_count));
        try {
            fw.write("Avg income for female employes: $" +
                    (female_total_income / female_count) + '\n');
            fw.write("Avg income for male employes: $" +
                    (male_total_income / male_count) + '\n');
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exisit");
        } catch (IOException ex) {
            System.out.println("IO Error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
