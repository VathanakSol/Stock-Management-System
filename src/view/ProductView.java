package view;

<<<<<<< HEAD
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
=======
import java.util.Scanner;

>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
public class ProductView {
    public void welcome() {
        System.out.println("*====================================================================================*");
        System.out.println("*====================================================================================*\n"+
                "*   ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     * \n" +
                "*  ██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    *\n" +
                "*  ██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    *\n" +
                "*  ██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    *\n" +
                "*  ╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    *\n" +
                "*   ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    *\n" +
                "*====================================================================================*");
    }
    public void showMenu() {
<<<<<<< HEAD
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
        System.out.print("""
                "*====================================================================================*")
                *No                              Description                         Key              *
                "*====================================================================================*")
                *1.                              L). Display                         L                *
                *2.                              M). Random                          M                *
                *3.                              W). Write                           W                *
                *4.                              R). Read                            R                *
                *5.                              E). Edit                            E                *
                *6.                              D). Delete                          D                *
                *7.                              S). Search                          S                *
                *8.                              O). Set Row                         O                *
                *9.                              C). Commit                          C                *
                *10.                             K). Back Up                         K                *
                *11.                             T). Restore                         T                *
                *12.                             H). Help                            H                *
                *13.                             E). Exit                            X                *
                "*====================================================================================*")
                """);
        System.out.print("Enter Key: ");
    }

    public void invalidInput() {
        System.out.println("========================================================================");
        System.out.println("*                               |Invalid Input|                         *");
        System.out.println("========================================================================");
        System.out.println();
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
    }
}
