import controller.ProductController;
import model.Product;
import utils.Singleton;
import utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // variable for storing location of file transaction
        String transaction = "src/data/transaction.dat";
        // variable for storing location of file data source
        String dataSource = "src/data/data_source.dat";
        // variable for storing location of dir back up
        String dir = "src/backup";

        Singleton.getProductView().welcome();
        ArrayList<Product> products;

        /*
         * we are checking the committing here
         * if the commit have committed ?
         */
        Utils.checkingCommit(input,transaction, dataSource);
        // reading data from file into list
        products = Utils.readFileToList(dataSource);
        Utils.listToFile(products, transaction);
        System.out.println(products.size());

        // read data from data source into transaction file
        boolean running = true;
        while (running) {
            running = Singleton.getProductController().display(input, products, transaction, dataSource, dir);
        }
    }
}
