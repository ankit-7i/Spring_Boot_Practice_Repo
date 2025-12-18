package com.product_inventory;



import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.product_inventory.config.JdbcConfig;
import com.product_inventory.dao.ProductCrud;
import com.product_inventory.model.Product;


public class ProductInventoryApp {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(JdbcConfig.class);

        NamedParameterJdbcTemplate template =
                ctx.getBean(NamedParameterJdbcTemplate.class);

        ProductCrud crud = new ProductCrud(template);
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== Product Inventory Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Product by ID");
            System.out.println("3. View All Products");
            System.out.println("4. Update Product Price");
            System.out.println("5. Update Stock Quantity");
            System.out.println("6. Delete Product");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Product Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Enter Brand: ");
                    String brand = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Stock Quantity: ");
                    int stock = sc.nextInt();

                    Product p = new Product(id, name, brand, price, stock);
                    crud.addProduct(p);
                    System.out.println("Product added successfully");
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int searchId = sc.nextInt();
                    Product product = crud.getProductById(searchId);
                    System.out.println("Product details displayed");
                    System.out.println(product);
                    break;

                case 3:
                    List<Product> products = crud.getAllProducts();
                    System.out.println("All Products:");
                    products.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();

                    crud.updatePrice(pid, newPrice);
                    System.out.println("Product price updated");
                    break;

                case 5:
                    System.out.print("Enter Product ID: ");
                    int stockId = sc.nextInt();

                    System.out.print("Enter New Stock Quantity: ");
                    int newStock = sc.nextInt();

                    crud.updateStock(stockId, newStock);
                    System.out.println("Stock quantity updated");
                    break;

                case 6:
                    System.out.print("Enter Product ID: ");
                    int deleteId = sc.nextInt();

                    crud.deleteProduct(deleteId);
                    System.out.println("Product deleted");
                    break;

                case 7:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}

