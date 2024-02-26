import controller.ProductController;
import model.Product;
import utils.Singleton;
import utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // variable for storing location of file transaction
        String transactionFile = "src/data/transaction.dat";
        // variable for storing location of file data source
        String dataSource = "src/data/data_source.dat";
        // variable for storing location of dir back up
        String dir = "src/backup";

        // checking if the user commit before exit the program
        // if forget to commit tell them to commit or not
        Utils.checkingCommit(input, transactionFile, dataSource);

        // read data from data source into transaction file
        Utils.exchangeData(dataSource, transactionFile, "Retrieving data into Transaction File: ");

        List<Product> productsList = new ArrayList<>();

        boolean running = true;
        while (running) {
            running = Singleton.getProductController().display(input, productsList, transactionFile, dataSource, dir);
        }
    }
}
