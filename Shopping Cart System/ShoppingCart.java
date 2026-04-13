import java.util.*;
import java.io.*;
class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotal() {
        return price * quantity;
    }
    public void display() {
        System.out.println(id + " | " + name + " | Rs." + price +
        " | Qty: " + quantity + " | Total: Rs." + getTotal());
    }
}
public class ShoppingCart {
    static ArrayList<Product> cart = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== SHOPPING CART SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Quantity");
            System.out.println("4. View Cart");
            System.out.println("5. Generate Bill");
            System.out.println("6. Save History");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Enter valid number!");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> updateQuantity();
                case 4 -> viewCart();
                case 5 -> generateBill();
                case 6 -> saveHistory();
                case 7 -> System.out.println("Thank you!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }
    static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Product p : cart) {
            if (p.getId() == id) {
                System.out.println("Product already in cart!");
                return;
            }
        }
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        cart.add(new Product(id, name, price, qty));
        System.out.println("Product added!");
    }
    static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        int id = sc.nextInt();
        Iterator<Product> it = cart.iterator();
        while (it.hasNext()) {
            Product p = it.next();
            if (p.getId() == id) {
                it.remove();
                System.out.println("Product removed!");
                return;
            }
        }
        System.out.println("Product not found!");
    }
    static void updateQuantity() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        for (Product p : cart) {
            if (p.getId() == id) {
                System.out.print("Enter new quantity: ");
                int qty = sc.nextInt();
                p.setQuantity(qty);
                System.out.println("Quantity updated!");
                return;
            }
        }
        System.out.println("Product not found!");
    }
    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("\n--- CART ITEMS ---");
        for (Product p : cart) {
            p.display();
        }
    }
    static void generateBill() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        double total = 0;
        System.out.println("\n===== BILL =====");
        for (Product p : cart) {
            p.display();
            total += p.getTotal();
        }
        System.out.println("----------------------");
        System.out.println("Total Amount: Rs." + total);
    }
    static void saveHistory() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bill.txt", true))) {
            double total = 0;
            bw.write("----- New Purchase -----\n");
            for (Product p : cart) {
                bw.write(p.getName() + " | " + p.getQuantity() +
                        " | Rs." + p.getTotal());
                bw.newLine();
                total += p.getTotal();
            }
            bw.write("Total: Rs." + total);
            bw.newLine();
            bw.write("------------------------\n");
            System.out.println("Purchase saved!");
        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }
}
