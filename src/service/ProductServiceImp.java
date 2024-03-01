package service;

import model.Product;
<<<<<<< HEAD
import utils.Utils;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
public class ProductServiceImp implements ProductService {
    @Override
    public void displayAllProducts(List<Product> products, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Split using commas
                if (parts.length >= 4) { // Check if there are enough elements in the array
                    String id = parts[0].trim(); // Trim to remove leading/trailing whitespace
                    String name = parts[1].trim();
                    int qty = Integer.parseInt(parts[2].trim());
                    float price = Float.parseFloat(parts[3].trim());
                    // Create a new product for each line in the file
                    Product product = new Product();
                    product.setId(Integer.parseInt(id));
                    product.setName(name);
                    product.setQty(qty);
                    product.setPrice(price);
                    products.add(product); // Add the product to the list
                    // Print product details
                    System.out.println(product.getId() + "\t\t" + product.getName() + "\t\t" + product.getQty() + "\t\t" + product.getPrice() + "$");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void displayByCode(String fileName, Scanner input) {
        System.out.println("Enter ID");
        String id = input.nextLine();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Searching for product with ID: " + id);
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Assuming each line in the file represents a product with fields separated by commas
                String productId = parts[0];
                if (Objects.equals(productId, id)) {
                    System.out.println("Product Found:");
                    System.out.println("Code: " + productId);
                    System.out.println("Name: " + parts[1]);
                    System.out.println("Quantity: " + parts[2]);
                    System.out.println("Price: " + parts[3] + "$");
                    found = true;
                    break; // Stop searching once the product is found
                }
            }
            if (!found) {
                System.out.println("Product with ID " + id + " not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createNewProduct(Scanner input, List<Product> products, String fileName) {
        Product newProduct = new Product();
        System.out.print("Enter ID: ");
        input.nextLine();
        String id = input.nextLine();
        newProduct.setId(Integer.parseInt(id));
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        newProduct.setName(name);
        System.out.println("Enter Quantity: ");
        int qty = input.nextInt();
        newProduct.setQty(qty);
        System.out.println("Enter Price: ");
        float price = input.nextFloat();
        newProduct.setPrice(price);
        // Write product details to file
        // use true to append the data avoid override data in the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // write each detail product into a file
            writer.newLine();
            writer.write(newProduct.getId() + "," + newProduct.getName() + "," + newProduct.getQty() + "," + newProduct.getPrice());
            System.out.println("New product added successfully!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void removeProduct(List<Product> products, Scanner input, String fileName) {
        System.out.println("Input ID that you want to remove: ");
        input.nextLine();
        String id = input.nextLine();
        boolean removed = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder updatedContent = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    removed = true;
                    System.out.println("Product with ID: " + id + " removed Successfully!");
                    continue;
                }
                updatedContent.append(line).append(System.lineSeparator());
            }

            if (!removed) {
                System.out.println("Product with ID: " + id + " not found.");
                return;
            }

            // Write updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(updatedContent.toString());
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void searchByName(Scanner input, List<Product> products, String fileName) {

        System.out.println("Enter Name: ");
        input.nextLine();
        String name = input.nextLine();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(name)) { // Assuming the name is at index 1 in each line
                    System.out.println("Product Found:");
                    System.out.println("ID: " + parts[0]); // Assuming the ID is at index 0 in each line
                    System.out.println("Name: " + parts[1]);
                    System.out.println("Quantity: " + parts[2]); // Assuming the quantity is at index 2 in each line
                    System.out.println("Price: " + parts[3]); // Assuming the price is at index 3 in each line
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Product with Name: " + name + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void updateByCode(Scanner input, List<Product> products, String fileName) {
        System.out.println("Enter id:");
        input.nextLine();
        String id = input.nextLine();
        boolean updated = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    System.out.println("Updating...");
                    // Update the details of the product
                    System.out.println("Enter new name");
                    String newName = input.nextLine();
                    parts[1] = newName;

                    System.out.println("new qty");
                    int newQty = input.nextInt();
                    parts[2] = String.valueOf(newQty);

                    System.out.println("new price");
                    float newPrice = input.nextFloat();
                    parts[3] = String.valueOf(newPrice);
                    updated = true;
                }
                updatedContent.append(String.join(",", parts)).append(System.lineSeparator());
            }

            if (!updated) {
                System.out.println("Product with Code: " + id + " not found.");
                return;
            }

            // Write updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(updatedContent.toString());
                System.out.println("Product with Code: " + id + " updated successfully!");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void commit(String transactionFile, String dataSource)  {
        // we need to set to true cuz after transfer data from transaction file to data source file
        // we clear the transaction file
        Utils.exchangeData(transactionFile, dataSource, "");
    }

    @Override
    public void backUp(String dir, String dataSourceFileName) {
        try {
            // Create a timestamp for the backup file
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
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
    public void random(Scanner input, String transactionFile, String dataSource) {
        System.out.print("Enter Row: ");
        int row = input.nextInt();
        Instant start = Instant.now();
        Utils.exchangeData(transactionFile, dataSource, "Write to Transaction Read to Data Source: ");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionFile, true))) {
            Random random = new Random();

            for (int i = 0; i < row; i++) {
                String randomName = generateRandomName();
                int value1 = random.nextInt(100);
                double value2 = random.nextDouble() * 100.0;
                String line = String.format("CSTAD-%03d, %s, %d, %.1f", i, randomName, value1, value2);
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Duration of starting random read and write is (" + duration.toMillis() + ")milliseconds");
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
=======

import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import validation.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
=======
import utils.Singleton;
import utils.Utils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6

public class ProductServiceImp implements ProductService {
    Validation validation = new Validation();
    Scanner input = new Scanner(System.in);
    private List<Product> productList = new ArrayList<>(); // Store stock data in a list
    // Display all product
    public List<Product> getDisplayAllProduct(int page) {
        // Simply return the stock list
        return productList;
    }

    @Override
<<<<<<< HEAD
    public void displayAllProducts() {
        System.out.println("Stock Management System");

=======
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
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
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
<<<<<<< HEAD
    public void createNewProduct() {
        System.out.println("Write name of product to stock : ");
        String name = input.nextLine();
        System.out.println("Write price of product to stock : ");
        float unitPrice = Float.parseFloat(input.nextLine());
        System.out.println("Write quantity of product to stock : ");
        int qty = validation.inputIntValidation();

        LocalDate currentDate = LocalDate.now(); // Initialize currentDate
        String nextCode = String.valueOf(productList.size() + 1);
        productList.add(new Product(nextCode, name, unitPrice, qty,  currentDate ));

        System.out.println("Insert stock successfully!");
    }


    @Override
    public void removeProduct() {
        System.out.print("Input Stock's codeOfProduct: ");
        String codeOfProduct = input.nextLine();

        Product productToDelete = findProductByCodeOfProduct(codeOfProduct);

        if (productToDelete != null) {
            String productName = productToDelete.getName();
            productList.remove(productToDelete);
            System.out.println("Deleting Stock: " + productName);
            System.out.println("Delete Stock codeOfProduct: " + codeOfProduct + " Successfully!");
        } else {
            System.out.println("There is no Stock codeOfProduct: " + codeOfProduct);
        }
=======
    public void random(Scanner input, List<Product> products, String transaction) {
        boolean validInput = false;
        int row = 0;
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6

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
<<<<<<< HEAD
    public void searchByName() {
        System.out.print("Enter the CodeOfProduct of the product you want to read: ");
        String codeOfProduct = input.nextLine();

        Product productToRead = findProductByCodeOfProduct(codeOfProduct);

        if (productToRead != null) {
            System.out.println("Product ID: " + productToRead.getCodeOfProduct());
            System.out.println("Product Name: " + productToRead.getName());
            System.out.println("Unit Price: " + productToRead.getPrice());
            System.out.println("Quantity: " + productToRead.getQty());
            System.out.println("Imported Date: " + productToRead.getCurrentDate());
        } else {
            System.out.println("Product with codeOfProduct " + codeOfProduct + " not found.");
        }

>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
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
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
    }

    @Override
    public void helpMenu() {
<<<<<<< HEAD
<<<<<<< HEAD

    }
}
=======
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
    public void updateStockProductAll() {
        System.out.println("-> Input new stock's product name : ");
        String name = input.nextLine();
        System.out.println("-> Input new stock's product quantity : ");
        int qty = Integer.parseInt(input.nextLine());
        System.out.println("-> Input new stock's product price : ");
        float unitPrice = Float.parseFloat(input.nextLine());
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();
        LocalDate currentDate = LocalDate.now(); // Initialize currentDate

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);
        if (stockToUpdate != null) {
            stockToUpdate.setName(name);
            stockToUpdate.setQty(qty);
            stockToUpdate.setPrice(unitPrice);
            stockToUpdate.setCurrentDate(currentDate); // Set the current date
            System.out.println("Update stock's product all successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }

    public void updateStockProductName() {
        System.out.print("-> Input new stock's product name: ");
        String product = input.nextLine();
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);

        if (stockToUpdate != null) {
            stockToUpdate.setName(product);
            System.out.println("Update stock's product name successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }

    public void updateStockQty() {
        System.out.print("-> Input new stock's quantity: ");
        int qty = validation.inputIntValidation();
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);

        if (stockToUpdate != null) {
            stockToUpdate.setQty(qty);
            System.out.println("Update stock's quantity successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }
    public void updateStockPrice() {
        System.out.println("-> Input new stock's product price : ");
        float unitPrice = Float.parseFloat(input.nextLine());
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);

        if (stockToUpdate != null) {
            stockToUpdate.setPrice(unitPrice);
            System.out.println("Update stock's price successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }

    // Helper method to find a stock by its ID
    private Product findProductByCodeOfProduct(String codeOfProduct) {
        for (Product product : productList) {
            if (Objects.equals(product.getCodeOfProduct(), codeOfProduct)) {
                return product;
            }
        }
        return null; // Stock with the given ID not found
    }
}
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
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
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
