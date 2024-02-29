package service;
import model.Product;
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
    }

    @Override
    public void helpMenu() {

    }
}