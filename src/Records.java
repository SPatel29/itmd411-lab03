import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        females_savings_account();
        male_car_child();
        avg_income_over_50();
        location_counter();
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
        System.out.printf("\nAvg income for female employes rounded to two decimal points: $%.2f\n",
                (female_total_income / female_count));
        System.out.printf("\nAvg income for male employes rounded to two decimal points: $%.2f\n",
                (male_total_income / male_count));
        try {
            fw.write("Avg income for female employes: $" +
                    String.format("%.2f", female_total_income / female_count) + '\n');
            fw.write("Avg income for male employes: $" +
                    String.format("%.2f", male_total_income / male_count) + '\n');
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exisit");
        } catch (IOException ex) {
            System.out.println("IO Error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void females_savings_account() {
        int num_of_females = 0;
        Arrays.sort(records, new MortgageComparator());
        Arrays.sort(records, new SavingsAccountComparator());
        Arrays.sort(records, new SexComparator());
        for (int person = 0; person < records.length; person += 1) {
            if (records[person].get_sex().equals("FEMALE") && records[person].get_save_act().equals("YES")
                    && records[person].get_mortgage().equals("YES")) {

                num_of_females += 1;
            }
        }
        System.out.printf("\nNumber of females with a mortgage, savings account are: %d\n", num_of_females);
        try {
            fw.write("Number of females with a mortgage, savings account are: " + num_of_females + '\n');
        } catch (FileNotFoundException ex) {
            System.out.println("File could not be found. Check Typos");
        } catch (IOException ex) {
            System.out.println("IO Error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void male_car_child() {
        Arrays.sort(records, new SexComparator().thenComparing(new ChildComparator())
                .thenComparing(new LocationComparator()).thenComparing(new CarComparator()));

        int male_count_inner_city = 0;
        int male_count_town = 0;
        int male_count_rural = 0;
        int male_count_suburban = 0;

        for (int person = 0; person < records.length; person += 1) {
            if (records[person].get_sex().equals("MALE") && records[person].get_Car().equals("YES")
                    && records[person].get_children() == 1 && records[person].get_region().equals("INNER_CITY")) {
                male_count_inner_city += 1;
            } else if (records[person].get_sex().equals("MALE") && records[person].get_Car().equals("YES")
                    && records[person].get_children() == 1 && records[person].get_region().equals("TOWN")) {
                male_count_town += 1;
            } else if (records[person].get_sex().equals("MALE") && records[person].get_Car().equals("YES")
                    && records[person].get_children() == 1 && records[person].get_region().equals("RURAL")) {
                male_count_rural += 1;
            } else if (records[person].get_sex().equals("MALE") && records[person].get_Car().equals("YES")
                    && records[person].get_children() == 1 && records[person].get_region().equals("SUBURBAN")) {
                male_count_suburban += 1;
            }
        }
        System.out.printf("\nNumber of males with a car and 1 child only in inner city region: %d\n",
                male_count_inner_city);
        System.out.printf("\nNumber of males with a car and 1 child only in town region: %d\n",
                male_count_town);
        System.out.printf("\nNumber of males with a car and 1 child only in rural region: %d\n",
                male_count_rural);
        System.out.printf("\nNumber of males with a car and 1 child only in suburban region: %d\n",
                male_count_suburban);

        try {
            fw.write(
                    "Number of males with a car and 1 child only in inner city region: " + male_count_inner_city
                            + '\n');
            fw.write(
                    "Number of males with a car and 1 child only in town region: " + male_count_town + '\n');
            fw.write(
                    "Number of males with a car and 1 child only in location region: " + male_count_rural + '\n');
            fw.write(
                    "Number of males with a car and 1 child only in suburban region: " + male_count_suburban + '\n');
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Check Typos");
        } catch (IOException ex) {
            System.out.println("IO Error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void avg_income_over_50() {
        Arrays.sort(records, new AgeComparator());
        double income = 0;
        int quantity = 0;
        for (int person = 0; person < records.length; person += 1) {
            if (records[person].get_age() >= 50) {
                income += records[person].get_income();
                quantity += 1;
            }
        }
        System.out.printf("\nAverage income for employees greater than or equal to the age 50 is: $%.2f\n",
                (income / quantity));
        try {
            fw.write("Average income for employees greater than or equal to the age 50 is: $"
                    + String.format("%.2f", (income / quantity)) + '\n');
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Check Typos");
        } catch (IOException ex) {
            System.out.println("IO Error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void location_counter() {
        Arrays.sort(records, new AgeComparator());
        int inner_city_count = 0;
        int town_count = 0;
        int rural_count = 0;
        int suburban_count = 0;

        for (int person = 0; person < records.length; person += 1){
            if (records[person].get_region().equals("INNER_CITY"))
                inner_city_count += 1;
            
            else if (records[person].get_region().equals("TOWN")) 
                town_count += 1;
            
            else if (records[person].get_region().equals("RURAL")) 
                rural_count += 1;
            
            else if (records[person].get_region().equals("SUBURBAN")) 
                suburban_count += 1;   
        }
        System.out.printf("\nNumber of employees in INNER CITY region: %d\n", inner_city_count);
        System.out.printf("\nNumber of employees in TOWN region: %d\n", town_count);
        System.out.printf("\nNumber of employees in RURAL region: %d\n", rural_count);
        System.out.printf("\nNumber of employees in SUBURBAN region: %d\n", suburban_count);

        try {
            fw.write("Number of employees in INNER CITY region: " + String.format("%d", inner_city_count) + '\n');
            fw.write("Number of employees in TOWN region: " + String.format("%d", town_count) + '\n');
            fw.write("Number of employees in RURAL region: " + String.format("%d", rural_count) + '\n');
            fw.write("Number of employees in SUBURBAN region: " + String.format("%d", suburban_count) + '\n');

        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Check Typos");
        } catch (IOException ex) {
            System.out.println("IO Error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
