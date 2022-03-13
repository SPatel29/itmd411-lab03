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
        }
    }

    public static void main(String[] args) {
        Records br = new Records();
        br.read_data();
        br.process_data();
        AvgComp(); // counts avergae income of female and male employees
        females_savings_account(); // counts females that have a savings account
        male_car_child(); // counts males that have car, 1 kid and per region
        avg_income_over_50(); // counts average income over 50
        location_counter(); // counts location of all employees
        try {
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * gets average income of all employees
     * diffentiating from sex using its sex comparator
     * This method takes no arguments and returns no value
     * 
     */
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

    /*
     * Counts females that have savings account and a mortgage
     * This method takes no arguments and does not return a value
     */
    private static void females_savings_account() {
        int num_of_females = 0;
        // The below sorts the array by sex first, then sorts by savings account,
        // then sorts by mortgage
        Arrays.sort(records,
                new SexComparator().thenComparing(new SavingsAccountComparator())
                        .thenComparing(new MortgageComparator()));
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

    /*
     * The below method counts males that have a car, have 1 child only and
     * seperates them by regions
     * This method takes no arguments and returns nothing
     */
    private static void male_car_child() {
        // The below first sorts by sex, then by number of children
        // then by location, then by if they have a car or not
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
        System.out.printf("\nNumber of males with a car and 1 child only in INNER CITY region: %d\n",
                male_count_inner_city);
        System.out.printf("\nNumber of males with a car and 1 child only in TOWN region: %d\n",
                male_count_town);
        System.out.printf("\nNumber of males with a car and 1 child only in RURAL region: %d\n",
                male_count_rural);
        System.out.printf("\nNumber of males with a car and 1 child only in SUBURBAN region: %d\n",
                male_count_suburban);

        try {
            fw.write(
                    "Number of males with a car and 1 child only in INNER CITY region: " + male_count_inner_city
                            + '\n');
            fw.write(
                    "Number of males with a car and 1 child only in TOWN region: " + male_count_town + '\n');
            fw.write(
                    "Number of males with a car and 1 child only in LOCATION region: " + male_count_rural + '\n');
            fw.write(
                    "Number of males with a car and 1 child only in SUBURBAN region: " + male_count_suburban + '\n');
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Check Typos");
        } catch (IOException ex) {
            System.out.println("IO Error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * The below gets the average income of people over the age of 50
     */
    private static void avg_income_over_50() {
        // The below sorts by age
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

    // The method below counts how many employees in each locatino
    // This method takes no arguments and returns no value
    private static void location_counter() {
        // The below sorts by location
        Arrays.sort(records, new LocationComparator());
        int inner_city_count = 0;
        int town_count = 0;
        int rural_count = 0;
        int suburban_count = 0;

        for (int person = 0; person < records.length; person += 1) {
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
