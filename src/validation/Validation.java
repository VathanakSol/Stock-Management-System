package validation;

import java.util.Scanner;

public class Validation {
    public int inputIntValidation(){
        Scanner input = new Scanner(System.in);
        int value=0;
        while (true){
            try{
                value = input.nextInt();
                break;
            }catch(Exception exception){
                System.out.print("Input must be integer only ");
                input.nextLine();
            }
        }
        return value;
    }

<<<<<<< HEAD
=======
    public String inputStringValidation() {
        Scanner input = new Scanner(System.in);
        String opt;

        while (true) {
            try {
                opt = input.nextLine();
                break;

            } catch (Exception exception) {
                System.out.print("Input must be a valid string. Try again: ");
                input.nextLine(); // Consume the invalid input
            }
        }
        return opt;
    }
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc

}
