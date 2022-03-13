import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BankRecords extends Client{
    static BankRecords[] records = new BankRecords[600];   //holds 600 BankRecords objects
    static ArrayList<String> lst = new ArrayList<>();
    private String id;
    private int age;
    private String sex;
    private String region;
    private double income;
    private String married;
    private int children;
    private String car;
    private String save_act;
    private String current_act;
    private String mortgage;
    private String prep;

    //getters
    public String get_id() {return id;}
    public int get_age() {return age;}
    public String get_sex() {return sex;}
    public String get_region() {return region;}
    public double get_income() {return income;}
    public String get_married() {return married;}
    public int get_children() {return children;}
    public String get_Car() {return car;}
    public String save_act() {return save_act;}
    public String get_current_act() {return current_act;}
    public String get_mortgage() {return mortgage;}
    public String get_prep() {return prep;}

    //setters
    public void set_id(String new_id) {id = new_id;}
    public void set_age(int new_age) {age = new_age;}
    public void set_sex(String new_sex) {sex = new_sex;}
    public void set_region(String new_region) {region = new_region;}
    public void set_income(double new_income) {income = new_income;}
    public void set_married(String new_married) {married = new_married;}
    public void set_children(int new_children) {children = new_children;}
    public void set_car(String new_car) {car = new_car;}
    public void set_save_act(String new_save_act) {save_act = new_save_act;}
    public void set_current_act(String new_current_act) {current_act = new_current_act;}
    public void set_mortgage(String new_mortgage) {mortgage = new_mortgage;}
    public void set_prep(String new_prep) {prep = new_prep;}

    //below are the abstract methods from Abstract Client class
    //all abstract methods must be overrided.

    /*
        This method reads the data of the file and adds each data seperated by the commas into the list
        with the help of the the split method to get us those values and then the add method belonging to the list.
        Each index of the array is commented with the data value that belongs to it
     */
    @Override
    public void read_data(){
        try{
            File file = new File("bank-Detail(1).csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String[] temp_arr;
            while ((line = br.readLine()) != null){
                temp_arr = line.split(","); //split returns an array of those values split by commas.
                // Setting temp_arr equal to the array created by the split method makes temp_arr POINT to that array
                // Shallow copy, not deep copy of array

                lst.add(temp_arr[0]);   // id
                lst.add(temp_arr[1]);   // age
                lst.add(temp_arr[2]);   // sex
                lst.add(temp_arr[3]);   // region
                lst.add(temp_arr[4]);   // income
                lst.add(temp_arr[5]);   // married
                lst.add(temp_arr[6]);   // children
                lst.add(temp_arr[7]);   // car
                lst.add(temp_arr[8]);   // save_act
                lst.add(temp_arr[9]);  // current_act
                lst.add(temp_arr[10]);  // mortgage
                lst.add(temp_arr[11]);  // set_prep
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found. Please checking path of file.");
        }
        catch (Exception e){
            System.out.println("An error happened regarding your file");
        }
    }

    /*
        Process_data method gets the data from all list elements and then adds it to array
        Inside the for loop we make a BankRecords object so it can use the setter methods to initalize our class fields
        We then store that object inside the array since that object has its fields initalized for us to use.
        Each lst element needs to be offsetted inside the for loop to get the particular value.
     */
    @Override
    public void process_data() {
        int count = 0;
        for (int i =0; i < lst.size(); i+=12){      //adding by 12 each increment b/c that is offset till next id
            BankRecords temp_obj = new BankRecords();
            temp_obj.set_id(lst.get(i));
            temp_obj.set_age(Integer.parseInt(lst.get(i + 1))); //need to make into int because age is int
            temp_obj.set_sex(lst.get(i + 2));   //adding offsets to get our particular values.
            temp_obj.set_region(lst.get(i + 3));
            temp_obj.set_income(Double.parseDouble(lst.get(i + 4)));
            temp_obj.set_married(lst.get(i + 5));
            temp_obj.set_children(Integer.parseInt(lst.get(i + 6)));
            temp_obj.set_car(lst.get(i + 7));
            temp_obj.set_save_act(lst.get(i + 8));
            temp_obj.set_current_act(lst.get(i + 9));
            temp_obj.set_mortgage(lst.get(i + 10));
            temp_obj.set_prep(lst.get(i + 11));
            records[count] = temp_obj;
            count += 1; //increment count to modify the next element of the records object array
        }
    }

    /*
        this methods just prints all of our data in a clean table like format. This method uses the printf
        method to nicely format our characters. The values infront of the s indicate how many characters wide
        it should be when deciding the right-alignment. We also use the records array since each element in that
        array is a BankRecords object, as a result we can use its methods.
     */
    @Override
    public void print_data() {
        print_headings();
        for (int i = 0; i < 25; i += 1){    //increment by 25 because we want the first 25 individuals
            System.out.printf("%4s\t\t%4s\t\t%6s\t\t%12s\t%16s\t%10s\n", records[i].get_id(), records[i].get_age(),
            records[i].get_sex(), records[i].get_region(), records[i].get_income(),
            records[i].get_mortgage());
        }
        print_data_50();
    }

    /*
        This method is the same as the prtin_data, but it prints another 25 participants.
        Uses the same techinques, but also asks for user input if they would want to read another 25 or just end
        the program. User input is also handled properly.
     */
    public void print_data_50(){
        System.out.println("\n\n\nWould you like to print 25 more? Y or N\n");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("y")){
            print_headings();
            for (int i = 25; i < 50; i += 1){    //increment by 25 because we want the first 25 individuals
                System.out.printf("%4s\t\t%4s\t\t%6s\t\t%12s\t%16s\t%10s\n", records[i].get_id(), records[i].get_age(),
                        records[i].get_sex(), records[i].get_region(), records[i].get_income(),
                        records[i].get_mortgage());
            }
            System.out.println("\n\n\nGoodbye!");
        }
        else if(input.equalsIgnoreCase("n"))
            System.out.println("\nGoodbye!");
        else
            throw new IllegalArgumentException("Please enter Y or N. Try again");
    }

    public void print_headings(){
        System.out.printf("%6s\t\t%6s\t\t%6s\t\t%12s\t%16s\t%14s\n", "ID:", "Age:", "Sex:", "Region:", "Income:",
                "Mortgage:");
        System.out.println("======================================================================================");
    }
}