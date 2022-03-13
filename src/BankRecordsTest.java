import java.util.Scanner;

public class BankRecordsTest {
    public static void main(String[] args){
        if (play()){
            BankRecords bank = new BankRecords();
            bank.read_data();
            try{
                bank.process_data();
                bank.print_data();
            }
            catch (NullPointerException e){
                //needed exception because if incorrect file, path it was still processing data,
                //thus giving me a NullPointerException. As a result I needed to handle this error when processing
                //the data.
                System.out.println("\n\nEmpty File. Check contents or path of file again.");
            }
        }
        else
            System.out.println("\nGood bye!");
    }

    /*
        This method prints the menu for user and prompts them for their input.
        Input decides if they want to use the program or not
        Returns a boolean for us to use
     */
    public static boolean play(){
        System.out.println("\nHello, and welcome to the program.\n");
        System.out.println("This program reads a file and then gives us the data associated with the file\n");
        System.out.println("Would you like to get started?\n");
        System.out.println("Press Y to start and N to quit");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("y"))
            return true;
        else if(input.equalsIgnoreCase("n"))
            return false;
        else
            throw new IllegalArgumentException("Enter a valid input. Try again.");
    }
}
