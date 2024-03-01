package view;


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

    }
}
