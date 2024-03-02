package utils;

import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class TableUtils {
    public static void renderProductsToTable(List<Product> productList, int rows, int currentPage, int totalPage, int totalRecord) {
        Table table = new Table(4, BorderStyle.UNICODE_BOX_HEAVY_BORDER,
                ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        CellStyle cellStyleRight = new CellStyle(CellStyle.HorizontalAlign.right);
        CellStyle cellStyleLeft = new CellStyle(CellStyle.HorizontalAlign.left);

        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 30, 30);
        table.setColumnWidth(2, 30, 30);
        table.setColumnWidth(3, 30, 30);

        // Add headers
        table.addCell("ID", cellStyle);
        table.addCell("Name", cellStyle);
        table.addCell("Quantity", cellStyle);
        table.addCell("Price", cellStyle);

        // Add data rows
        for (int i = 0; i < Math.min(productList.size(), rows); i++) {
            Product product = productList.get(i);
            table.addCell(product.getId(), cellStyle);
            table.addCell(product.getName(), cellStyle);
            table.addCell(Integer.toString(product.getQty()), cellStyle);
            table.addCell(String.format("%.2f$", product.getPrice()), cellStyle);
        }

        table.addCell("Page " + currentPage + "/" + totalPage, cellStyleLeft, 2);
        table.addCell("Total record: " + totalRecord, cellStyleRight, 2);


        System.out.println(table.render());
    }
    public static void renderPaginationOption() {
        Table table = new Table(2, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.SURROUND);
        CellStyle cellStyle1 = new CellStyle(CellStyle.HorizontalAlign.right);

        table.setColumnWidth(0, 61, 61);
        table.setColumnWidth(1, 61, 61);

        table.addCell("F). First Page");
        table.addCell("L). Last Page", cellStyle1);
        table.addCell("P). Previous Page");
        table.addCell("N). Next Page", cellStyle1);
        table.addCell("B). Back to Menu");
        table.addCell("");
        table.addCell("Enter number to go to specific page");
        System.out.println(table.render());
    }
    /*
     * show the result after have modified
     * such as write(add product), read(read by code), search, and edit
     */
    public static void renderEachProduct(Product product, String msg) {
        Table table = new Table(2, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 30, 30);

        table.addCell(msg, cellStyle, 2);
        table.addCell("Code");
        table.addCell(product.getId());
        table.addCell("Name");
        table.addCell(product.getName());
        table.addCell("Quantity");
        table.addCell(String.valueOf(product.getQty()));
        table.addCell("Price");
        table.addCell(String.valueOf(product.getPrice()));
        System.out.println(table.render());

    }

}
