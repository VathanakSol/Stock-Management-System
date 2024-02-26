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


}
