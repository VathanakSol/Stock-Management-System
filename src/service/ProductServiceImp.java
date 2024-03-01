package service;

import model.Product;
import utils.Singleton;
import utils.Utils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductServiceImp implements ProductService {
    @Override
    public void display(List<Product> products, Scanner input) {
        int totalPages = (int) Math.ceil((double) products.size() / this.rowSize);
        int currentPage = 1;

        int startIndex = 0;
        int endIndex = Math.min(this.rowSize, products.size());

        while (true) {
            System.out.println("=========================|Display Product Here|==========================");
            System.out.println("=========================================================================");
            System.out.println("\tCODE\t\t\tNAME\t\t QUANTITY\t\t  PRICE");
            System.out.println("=========================================================================");
            for (int i = startIndex; i < endIndex; i++) {
                System.out.println(products.get(i));
            }

            // Prompt user for navigation input
            // Display products for the current page
            System.out.println("=========================================================================");
            System.out.println("Page " + currentPage + "/" + totalPages + "\t\t\t\t\t\t\t\t\t\tTotal Records: " + products.size());
            System.out.println("=========================================================================");
            System.out.print("""
                <P> previous \t\t\t\t\t\t\t\t\t<F> first page 
                <N> next \t\t\t\t\t\t\t\t\t\t<L> last page
                <B> Back to Menu
                page number to go to: 
                """);
            String userInput = input.next();
            System.out.println("=========================================================================");
            if (userInput.equalsIgnoreCase("p")) {
                // Go to previous page
                if (currentPage > 1) {
                    startIndex -= rowSize;
                    endIndex -= rowSize;
                    currentPage--;
                } else {
                    System.out.println("You are already on the first page.");
                }
            } else if (userInput.equalsIgnoreCase("n")) {
                // Go to next page
                if (currentPage < totalPages) {
                    startIndex += rowSize;
                    endIndex += rowSize;
                    currentPage++;
                } else {
                    System.out.println("You are already on the last page.");
                }
            } else if (userInput.equalsIgnoreCase("f")) {
                // Go to first page
                startIndex = 0;
                endIndex = Math.min(rowSize, products.size());
                currentPage = 1;
            } else if (userInput.equalsIgnoreCase("l")) {
                // Go to last page
                endIndex = products.size();
                startIndex = (totalPages - 1) * rowSize;
                currentPage = totalPages;
            } else if (userInput.equalsIgnoreCase("b")) {
                // Go back to menu
                return;
            } else {
                // Go to specific page
                try {
                    int pageNumber = Integer.parseInt(userInput);
                    if (pageNumber >= 1 && pageNumber <= totalPages) {
                        startIndex = (pageNumber - 1) * rowSize;
                        endIndex = Math.min(pageNumber * rowSize, products.size());
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

    int row = 2;
    private int rowSize = 2; // default row size
    @Override
    // Method to set the row size
    public void setRow(Scanner input) {
        while (true) {
            try {
                System.out.print("Enter the number of rows (0 for default of 2): ");
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
    }
    @Override
    public void read(List<Product> products, Scanner input) {
        while (true) {
            try {
                System.out.println("=========================|Display Product by Code Here|==========================");
                System.out.print("""
                    <B> Back to Menu
                    Enter ID:
                    """);
                String id = input.nextLine();

                if (id.equalsIgnoreCase("b")) {
                    // If user enters 'b', go back to the menu
                    return;
                }

                boolean found = false;
                for (Product product : products) {
                    if (id.equals(product.getId())) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("PRODUCT CODE: " + id + " DETAIL                   ");
                        System.out.println("--------------------------------------------------");
                        System.out.println("CODE:\t\t\t\t\t" + product.getId());
                        System.out.println("NAME:\t\t\t\t\t" + product.getName());
                        System.out.println("QUANTITY:\t\t\t\t" + product.getQty());
                        System.out.println("UNIT PRICE:\t\t\t\t" + product.getPrice() + "$");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Product with ID " + id + " not found.");
                }

                System.out.println("--------------------------------------------------");
                // Exit the loop if input and processing are successful
                break;
            } catch (Exception e) {
                // Catch any exceptions (e.g., InputMismatchException) and print an error message
                System.out.print("Invalid input! Please try again: ");
                input.nextLine(); // Clear the input buffer
            }
        }
    }
    @Override
    public void write(Scanner input, List<Product> products, String transaction) {
        Product product = new Product();

        // Auto-assign ID based on the current size of the list
        String id = "CSTAD-" + String.format("%03d", products.size() + 1);
        product.setId(id);

        System.out.print("Enter Name: ");
        String name = input.nextLine();
        product.setName(name);

        int qty;
        while (true) {
            System.out.println("Enter Quantity: ");
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
            System.out.println("Enter Price: ");
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

        products.add(product);

        // Call method sync with transaction file
        Utils.syncWithTransactionFile(products, transaction);
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
                removed = true;
                System.out.println("Product with ID: " + id + " removed successfully!");
            }
        }
        if (!removed) {
            System.out.println("Product with ID: " + id + " not found.");
        }
        Utils.syncWithTransactionFile(products, transaction);
    }
    @Override
    public void search(Scanner input, List<Product> products) {
        System.out.println("Enter Name: ");
        String name = input.nextLine();
        boolean found = false;

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (!found) {
                    System.out.println("=======================================================================================");
                    System.out.println("Products with name<" + product.getName() + "> Found:");
                    System.out.println("=======================================================================================");
                    found = true;
                }
                System.out.println("ID: " + product.getId() + "\t\t\tName: " + product.getName() + "\t\t\tQuantity: " + product.getQty() + "\t\t\tPrice: " + product.getPrice() + "$");
            }
        }

        if (!found) {
            System.out.println("Product with Name: " + name + " not found.");
        }
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
                updated = true;

                // Ask for confirmation
                System.out.print("Are you sure you want to update this product? (Y/N): ");
                String confirmation = input.nextLine();
                switch (confirmation.toLowerCase()) {
                    case "y", "Y" -> {
                        Utils.syncWithTransactionFile(products, transactionFile);
                        return; // Exit the method after updating
                    }

                    case "n", "N" -> {
                        // Revert changes
                        System.out.println("Update cancelled. Reverting changes...");
                        Utils.readFileToList(transactionFile, "");
                        return; // Exit the method without updating
                    }
                    default -> {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                }
            }
        }
        if (!updated) {
            System.out.println("Product with Code: " + id + " not found.");
        }
    }
    @Override
    public void commit(String transactionFile, String dataSource, Scanner input)  {
        File file = new File(transactionFile);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No data to commit.");
            return;
        }

        System.out.print("Do you want to commit the data? (Y/N): ");
        String confirmation = input.nextLine();
        if (confirmation.equalsIgnoreCase("y")) {
            Utils.exchangeData(transactionFile, dataSource);
            Utils.clearTransactionFile(transactionFile, "");
            System.out.println("Commit Successful!");
        } else if (confirmation.equalsIgnoreCase("n")) {
            System.out.println("Commit aborted.");
        } else {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
        }
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

    @Override
    public void random(Scanner input, List<Product> products, String transaction) {
        boolean validInput = false;
        int row = 0;

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

        long start = System.currentTimeMillis();
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("#.##"); // Format for two digits after the decimal point
        int startingIndex = products.size(); // Get the current size of the list
        for (int i = 0; i < row; i++) {
            Product product = new Product(); // Create a new product for each iteration
            String randomName = generateRandomName();
            int qty = random.nextInt(100);
            float price = Float.parseFloat(df.format(random.nextFloat() * 100.0f)); // Format the price
            // Generate product ID based on the current size of the list
            product.setId(String.format("CSTAD-%03d", startingIndex + i));
            product.setName(randomName);
            product.setQty(qty);
            product.setPrice(price);
            products.add(product); // Add the product to the provided list
        }
        long end = System.currentTimeMillis();
        long duration = Utils.findDuration(start, end);
        Utils.displayAnimation(duration);
        System.out.println(" for " + row + " records");
        // random into file
        Utils.syncWithTransactionFile(products, transaction);
    }
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
                    System.out.println("=========================================================================");

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
                            System.out.println("size of data from file " + Utils.readFileToList(transaction, "").size());
                            Utils.exchangeData(selectedFilePath, transaction);
                            Utils.readFileToList(selectedFilePath, "");
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