package service;

import model.Product;
import org.nocrala.tools.texttablefmt.Table;
import utils.Singleton;
import utils.TableUtils;
import utils.Utils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductServiceImp implements ProductService {
    private int rowSize = 2; // default row size
    @Override
    public void display(List<Product> products, Scanner input) {
        if (products.isEmpty()) {
            System.out.println("No products to display.");
            return;
        }

        int totalPages = (int) Math.ceil((double) products.size() / this.rowSize);
        int currentPage = 1;
        int totalRecord = products.size();

        while (true) {
            int startIndex = (currentPage - 1) * rowSize;
            int endIndex = Math.min(startIndex + rowSize, products.size());
            List<Product> pageProducts = products.subList(startIndex, endIndex);

            // Render only the products for the current page
            TableUtils.renderProductsToTable(pageProducts, rowSize, currentPage, totalPages, totalRecord); // Assuming this method can accept a list of products to display

            TableUtils.renderPaginationOption(); // Render pagination options
            System.out.print("Enter the option: ");
            String userInput = input.next();

            if (userInput.equalsIgnoreCase("p")) {
                // Go to previous page
                if (currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println("You are already on the first page.");
                }
            } else if (userInput.equalsIgnoreCase("n")) {
                // Go to next page
                if (currentPage < totalPages) {
                    currentPage++;
                } else {
                    System.out.println("You are already on the last page.");
                }
            } else if (userInput.equalsIgnoreCase("f")) {
                // Go to first page
                currentPage = 1;
            } else if (userInput.equalsIgnoreCase("l")) {
                // Go to last page
                currentPage = totalPages;
            } else if (userInput.equalsIgnoreCase("b")) {
                // Go back to menu
                return;
            } else {
                // Go to specific page
                try {
                    int pageNumber = Integer.parseInt(userInput);
                    if (pageNumber >= 1 && pageNumber <= totalPages) {
                        currentPage = pageNumber;
                    } else {
                        System.out.println("Invalid page number. Please enter a number between 1 and " + totalPages);
                    }
                } catch (NumberFormatException e) {
                    Singleton.getProductView().invalidInput();
                }
            }
        }
    }
    @Override
    // Method to set the row size
    public void setRow(Scanner input) {
        while (true) {
            try {
                System.out.print("Enter the number of rows: ");
                int inputRowSize = input.nextInt();
                if (inputRowSize == 0) {
                    inputRowSize = this.rowSize; // use the default value
                } else {
                    this.rowSize = inputRowSize; // set to user input
                }
                break; // valid input received, exit loop
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                input.nextLine(); // clear the scanner buffer
            }
        }
        System.out.println("Press any key");
        input.nextLine();
    }
    @Override
    public void read(List<Product> products, Scanner input) {
        while (true) {
            try {
                System.out.print("Enter ID: ");
                String id = input.nextLine();

                boolean found = false;
                for (Product product : products) {
                    if (id.equals(product.getId())) {
                        TableUtils.renderEachProduct(product, "Product Detail");
                        System.out.println("press any key");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Product with ID " + id + " not found.");
                }

                break;
            } catch (Exception e) {
                // Catch any exceptions (e.g., InputMismatchException) and print an error message
                System.out.print("Invalid input! Please try again: ");
                System.out.println("press any key");
                input.nextLine(); // Clear the input buffer
            }
        }
    }
    @Override
    public void write(Scanner input, List<Product> products, String filePath) {
        Product product = new Product();

        // Auto-assign ID based on the current size of the list
        String id = "CSTAD-" + String.format("%03d", products.size() + 1);
        product.setId(id);

        System.out.print("Enter Name: ");
        String name = input.nextLine();
        product.setName(name);

        int qty;
        while (true) {
            System.out.print("Enter Quantity: ");
            try {
                qty = input.nextInt();
                if (qty >= 0) {
                    break;
                } else {
                    System.out.println("Invalid quantity. Quantity must be a non-negative integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Quantity must be a non-negative integer.");
                input.next(); // Clear the invalid input
            }
        }
        product.setQty(qty);

        float price;
        while (true) {
            System.out.print("Enter Price: ");
            try {
                price = input.nextFloat();
                if (price >= 0) {
                    break;
                } else {
                    System.out.println("Invalid price. Price must be a non-negative number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Price must be a non-negative number.");
                input.next(); // Clear the invalid input
            }
        }
        product.setPrice(price);
        TableUtils.renderEachProduct(product, "Add New Product");

        boolean answer = Utils.confirm("Create", input);
        if (answer) {
            products.add(product);
        }

        // after write product
        // we need to write product in the list into transaction file
        Utils.listToFile(products, filePath);
        System.out.println("press any key");
        input.nextLine();
    }
    @Override
    public void delete(List<Product> products, Scanner input, String transaction) {
        System.out.print("Input ID that you want to remove: ");
        String id = input.nextLine();
        boolean removed = false;
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(id)) {
                iterator.remove(); // Remove the current product from the list
                TableUtils.renderEachProduct(product, "Delete Product");
                boolean answer = Utils.confirm("Delete", input);
                if (answer) {
                    removed = true;

                }
                System.out.println("Product with ID: " + id + " removed successfully!");
                System.out.println("Press any key");
                input.nextLine();
            }
        }
        if (!removed) {
            System.out.println("Product with ID: " + id + " not found.");
        }
        Utils.listToFile(products, transaction);
    }
    @Override
    public void search(Scanner input, List<Product> products) {
        System.out.println("Enter Name: ");
        String name = input.nextLine();
        boolean found = false;

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (!found) {
                    found = true;
                }
                System.out.println("ID: " + product.getId() + "\t\t\tName: " + product.getName() + "\t\t\tQuantity: " + product.getQty() + "\t\t\tPrice: " + product.getPrice() + "$");
            }
        }

        if (!found) {
            System.out.println("Product with Name: " + name + " not found.");
        }
        System.out.print("Press any key");
    }
    @Override
    public void edit(Scanner input, List<Product> products, String transactionFile) {
        System.out.println("Enter id:");
        String id = input.nextLine();
        boolean updated = false;

        for (Product product : products) {
            if (product.getId().equals(id)) {
                System.out.println("Updating...");
                // Update the details of the product
                System.out.print("Enter new name: ");
                String name = input.nextLine();
                product.setName(name);

                int qty = 0;
                boolean validQty = false;
                while (!validQty) {
                    try {
                        System.out.print("Enter new quantity: ");
                        qty = Integer.parseInt(input.nextLine());
                        validQty = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Please enter a valid integer.");
                    }
                }
                product.setQty(qty);

                float price = 0;
                boolean validPrice = false;
                while (!validPrice) {
                    try {
                        System.out.print("Enter new price: ");
                        price = Float.parseFloat(input.nextLine());
                        validPrice = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price. Please enter a valid floating-point number.");
                    }
                }
                product.setPrice(price);
                TableUtils.renderEachProduct(product, "Update");
                Utils.confirm("Update", input);
                updated = true;
            }
        }
        if (!updated) {
            System.out.println("Product with Code: " + id + " not found.");
        }
        System.out.println("Press any key");
        input.nextLine();

    }
    @Override
    public void commit(String originalFile, String destinationFile) {
        // call exchange method
        Utils.exchangeData(originalFile, destinationFile);
        Utils.clearTransactionFile(originalFile, "");
    }

    @Override
    public void backUp(String dir, String dataSourceFileName) {
        try {
            // Create a timestamp for the backup file
            String timestamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
            String backupFilePath = dir + File.separator + "backup_" + timestamp + ".dat";

            // Create the backup file
            File backupFile = new File(backupFilePath);

            if (backupFile.createNewFile()) {
                // Copy data from data source file to backup file
                try (BufferedReader reader = new BufferedReader(new FileReader(dataSourceFileName));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    }
                }
            } else {
                System.err.println("Failed to create backup file. File already exists: " + backupFilePath);
            }
        } catch (IOException e) {
            System.err.println("Error creating backup file: " + e.getMessage());
        }
    }

    // when random method called
    // I randomly data into the file
    // so this method is written data into a file
    @Override
    public void random(Scanner input, List<Product> products, String transaction) {
        boolean validInput = false;
        int row = 0;

        // validate row input by user
        while (!validInput) {
            try {
                System.out.print("Enter Row: ");
                row = input.nextInt();
                if (row <= 0) {
                    System.out.println("Invalid input! Row must be a positive integer.");
                } else {
                    validInput = true; // Set validInput to true if input is valid
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer for the row.");
                input.nextLine(); // Clear the input buffer
            }
        }
        // determine the starting time
        long start = System.currentTimeMillis();
        Random random = new Random();
        // Format for two digits after the decimal point
        DecimalFormat df = new DecimalFormat("#.##");
        // Get the current size of the list
        int startingIndex = products.size();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transaction, true))) {
            for (int i = 0; i < row; i++) {
                // Create a new product for each iteration
                Product product = new Product();
                String randomName = generateRandomName();
                int qty = random.nextInt(100);
                float price = Float.parseFloat(df.format(random.nextFloat() * 10.0f)); // Format the price
                // Generate product ID based on the current size of the list
                product.setId(String.format("CSTAD-%03d", startingIndex + i));
                product.setName(randomName);
                product.setQty(qty);
                product.setPrice(price);
                // Write product data to the file separated by commas
                writer.write(product.getId() + "," + product.getName() + "," + product.getQty() + "," + product.getPrice());
                writer.newLine();
            }
            writer.flush(); // Flush the buffer to ensure all data is written to the file

        } catch (IOException e) {
            e.printStackTrace();
        }
        // determine the ending time
        long end = System.currentTimeMillis();
        // calculate the duration from starting to ending
        long duration = Utils.findDuration(start, end);
        System.out.println("Need " + duration + " milliseconds to write into file ⚆_⚆");
        Utils.displayAnimation(duration);
        System.out.println();

        // after write data into a file I need to read from file (transaction file) into a list
        // call method that do the mention task
        long start1 = System.currentTimeMillis();
        readFileToList(products, transaction);
        long end1 = System.currentTimeMillis();
        long duration1 = Utils.findDuration(start1, end1);
        System.out.println("Need " + duration1 + " milliseconds to read from file into list ◉_◉");
        Utils.displayAnimation(duration);
        System.out.println();
        System.out.println("Press any key");
        input.nextLine();
        input.nextLine();
    }
    // this method is reading data from file we just random into a list
    public void readFileToList(List<Product> products, String transaction) {
        long start = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(transaction))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by comma and create a Product object
                String[] parts = line.split(",");
                if (parts.length == 4) { // Ensure that the line is correctly formatted
                    Product product = new Product();
                    product.setId(parts[0]);
                    product.setName(parts[1]);
                    product.setQty(Integer.parseInt(parts[2]));
                    product.setPrice(Float.parseFloat(parts[3]));
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
    }

    // method for random generate name
    private String generateRandomName() {
        String[] names = new String[]{"Sting", "Lay's", "Pepsi", "Coca", "Yogurt", "Candy", "Hanami", "Susi", "Milk", "Beer"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    @Override
    public void clearData(String dataSource) {
        try (PrintWriter writer = new PrintWriter(dataSource)) {
            // Write an empty string to the file
            writer.print("");
        } catch (IOException e) {
            System.err.println("Error clearing file: " + e.getMessage());
        }
    }

    public void restore(String backUpDir, String transaction, Scanner input) {
        while (true) {
            try {
                File directory = new File(backUpDir);
                // Check if the provided path is a directory
                if (!directory.isDirectory()) {
                    System.out.println("The provided path is not a directory.");
                    return;
                }

                // Get list of files in the directory
                File[] files = directory.listFiles();

                if (files != null && files.length > 0) {
                    int count = 0;
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                            count++;
                            System.out.println(count + ". " + files[i].getName());
                        }
                    }

                    if (count == 0) {
                        System.out.println("No files found in the directory: " + backUpDir);
                        return; // Exit the method if no files are found
                    }

                    // Prompt user to select a file
                    System.out.println("B. Back to Menu");
                    System.out.print("Enter option: ");
                    String userInput = input.nextLine();

                    if (userInput.equalsIgnoreCase("B")) {
                        return; // Exit the method if user wants to go back to the menu
                    }

                    int fileIndex = Integer.parseInt(userInput);
                    if (fileIndex >= 1 && fileIndex <= count) {
                        File selectedFile = null;
                        int currentIndex = 0;
                        for (File file : files) {
                            if (file.isFile()) {
                                currentIndex++;
                                if (currentIndex == fileIndex) {
                                    selectedFile = file;
                                    break;
                                }
                            }
                        }
                        if (selectedFile != null) {
                            String selectedFilePath = selectedFile.getPath();
                            // restore data from selectedFilePath into transaction file
                            System.out.println("size of data from file " + Utils.readFileToList(transaction).size());
                            Utils.exchangeData(selectedFilePath, transaction);
                            Utils.readFileToList(selectedFilePath);
                            System.out.println("You are restoring file " + selectedFile.getName() + "...");
                            return ;
                            // Return the path of the selected file
                        } else {
                            System.out.println("Invalid file number. Please enter a valid file number.");
                        }
                    } else {
                        System.out.println("Invalid file number. Please enter a valid file number.");
                    }
                } else {
                    System.out.println("No files found in the directory: " + backUpDir);
                    return ; // Exit the method if no files are found
                }
            } catch (NumberFormatException e) {
                Singleton.getProductView().invalidInput();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void helpMenu() {
        System.out.println("""
                +-----------------------------------------------------------------+
                !  1.    Press     l : Display product as table.                  !
                !  2.    Press     w : Create a new product.                      !
                !  3.    Press     r : View Product detail by code.               !
                !  4.    Press     e : Edit existing product by code.             !
                !  5.    Press     d : Deleting an existing product by name.      !
                !  6.    Press     s : Search an existing product by name.        !
                !  7.    Press     c : Commit transactional data.                 !
                !  8.    Press     k : Back data.                                 !
                !  9.    Press     t : Restore Data.                              !
                !  10.   Press     f : Navigate pagination to the first page.     !
                !  11.   Press     p : Navigate pagination to the previous page.  !
                !  12.   Press     n : Navigate pagination to the next page.      !
                !  13.   Press     l : Navigate pagination to the last page.      !
                !  14.   Press     h : Help.                                      !
                !  15.   Press     b : Step back by the application.              !
                !  16.   Press     x : Exit the application.                      !
                +-----------------------------------------------------------------+
                """);
    }
}