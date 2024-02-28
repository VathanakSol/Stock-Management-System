package RenderTable;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableRender {
    public static void renderMenu(List<String> menu) {
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(3, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);

        table.setColumnWidth(0, 20, 21);
        table.setColumnWidth(1, 40, 45);
        table.setColumnWidth(2, 40, 45);
        table.addCell("No", cellStyle);
        table.addCell("Operation", cellStyle);
        table.addCell("Press Key ", cellStyle);

        for (int i = 0; i < menu.size(); i++) {
            String menuItem = menu.get(i);
            String[] parts = menuItem.split("\\)");
            if (parts.length == 2) {
                String operation = parts[1].trim();
                String pressKey = parts[0].trim() + ")";
                table.addCell(" " + (i + 1) + " >> ", cellStyle);
                table.addCell(operation, cellStyle);
                table.addCell(pressKey, cellStyle);
            }
        }
        System.out.println(table.render());
    }
}
