package view;

import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class ProductView {

    /*
     * first welcome of programming
     * welcome to CSTAD
     */
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
    //show menu
    public void showMenu() {
        Table table = new Table(3, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        CellStyle cellStyle1 = new CellStyle(CellStyle.HorizontalAlign.left);


        table.setColumnWidth(0, 40, 40);
        table.setColumnWidth(1, 40, 40);
        table.setColumnWidth(2, 40, 40);

        table.addCell("No", cellStyle);
        table.addCell("Description", cellStyle);
        table.addCell("Key", cellStyle);

        table.addCell("01.", cellStyle);
        table.addCell("L). Display", cellStyle1);
        table.addCell("L", cellStyle);

        table.addCell("02.", cellStyle);
        table.addCell("M). Random", cellStyle1);
        table.addCell("M", cellStyle);

        table.addCell("03.", cellStyle);
        table.addCell("W). Write", cellStyle1);
        table.addCell("W", cellStyle);

        table.addCell("04.", cellStyle);
        table.addCell("R). Read", cellStyle1);
        table.addCell("R", cellStyle);

        table.addCell("05.", cellStyle);
        table.addCell("E). Edit", cellStyle1);
        table.addCell("E", cellStyle);

        table.addCell("06.", cellStyle);
        table.addCell("D). Delete", cellStyle1);
        table.addCell("D", cellStyle);

        table.addCell("07.", cellStyle);
        table.addCell("S). Search", cellStyle1);
        table.addCell("S", cellStyle);

        table.addCell("08.", cellStyle);
        table.addCell("O). Set Row", cellStyle1);
        table.addCell("O", cellStyle);

        table.addCell("09.", cellStyle);
        table.addCell("C). Commit", cellStyle1);
        table.addCell("C", cellStyle);

        table.addCell("10.", cellStyle);
        table.addCell("K). Back Up", cellStyle1);
        table.addCell("K", cellStyle);

        table.addCell("11.", cellStyle);
        table.addCell("T). Restore", cellStyle1);
        table.addCell("T", cellStyle);

        table.addCell("12.", cellStyle);
        table.addCell("H). Help", cellStyle1);
        table.addCell("H", cellStyle);

        table.addCell("13.", cellStyle);
        table.addCell("E). Exit", cellStyle1);
        table.addCell("E", cellStyle);

        System.out.println();
        System.out.println(table.render());
        System.out.print("Enter Key: ");
    }
    // tell user if the user enter invalid input
    public void invalidInput() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 122, 122);
        table.addCell("Invalid Input", cellStyle);

        System.out.println();
        System.out.println(table.render());
    }


}
