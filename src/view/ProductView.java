package view;

<<<<<<< HEAD
import java.util.Scanner;

public class ProductView {
    public int showMenu(Scanner input) {
        int choice;
        System.out.println();
        System.out.println("-+".repeat(20) + "<\tMenu\t>" + "+-".repeat(20));
        System.out.println("1. Display");
        System.out.println("2. Create Products");
        System.out.println("3. Remove Products");
        System.out.println("4. Update");
        System.out.println("5. Search");
        System.out.println("6. Commit");
        System.out.println("7. Back Up");
        System.out.println("8. Random");
        System.out.println("9. Delete all data");
        System.out.println("10. Exit");
        System.out.println("-+".repeat(50));
        System.out.print("Please choose the option: ");
        choice = input.nextInt();
        System.out.println("-+".repeat(50));

        return choice;
=======
public class ProductView {
    public void showMenu() {
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
    }
}
