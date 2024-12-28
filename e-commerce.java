import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Main class for the E-Commerce System
public class ECommerceSystem {
    private List<Product> products;
    private List<User> users;
    private Map<User, List<Order>> userOrders;

    public ECommerceSystem() {
        products = new ArrayList<>();
        users = new ArrayList<>();
        userOrders = new HashMap<>();
    }

    public void addProduct(String name, double price) {
        products.add(new Product(name, price));
        System.out.println("Product added: " + name + " at $" + price);
    }

    public void registerUser(String name) {
        users.add(new User(name));
        userOrders.put(new User(name), new ArrayList<>());
        System.out.println("User registered: " + name);
    }

    public void placeOrder(User user, List<Product> orderedProducts) {
        if (!users.contains(user)) {
            System.out.println("User not registered.");
            return;
        }
        
        Order order = new Order(user, orderedProducts);
        userOrders.get(user).add(order);
        System.out.println("Order placed by " + user.getName() + ": " + orderedProducts);
    }

    public void viewOrderHistory(User user) {
        if (!users.contains(user)) {
            System.out.println("User not registered.");
            return;
        }
        
        List<Order> orders = userOrders.get(user);
        System.out.println("Order History for " + user.getName() + ":");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void displayProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        ECommerceSystem ecommerce = new ECommerceSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nE-Commerce System");
            System.out.println("1. Add Product");
            System.out.println("2. Register User");
            System.out.println("3. Place Order");
            System.out.println("4. View Order History");
            System.out.println("5. Display Products");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    ecommerce.addProduct(productName, productPrice);
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    ecommerce.registerUser(userName);
                    break;
                case 3:
                    System.out.print("Enter user name: ");
                    String orderUserName = scanner.nextLine();
                    User orderUser = ecommerce.findUser(orderUserName);
                    
                    if (orderUser != null) {
                        List<Product> orderedProducts = new ArrayList<>();
                        while (true) {
                            ecommerce.displayProducts();
                            System.out.print("Enter product name to order (or 'done' to finish): ");
                            String orderedProductName = scanner.nextLine();

                            if (orderedProductName.equalsIgnoreCase("done")) {
                                break;
                            }

                            Product product = ecommerce.findProduct(orderedProductName);
                            if (product != null) {
                                orderedProducts.add(product);
                                System.out.println("Added " + product.getName() + " to your order.");
                            } else {
                                System.out.println("Product not found.");
                            }
                        }
                        ecommerce.placeOrder(orderUser, orderedProducts);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter user name: ");
                    String historyUserName = scanner.nextLine();
                    User historyUser = ecommerce.findUser(historyUserName);
                    
                    if (historyUser != null) {
                        ecommerce.viewOrderHistory(historyUser);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 5:
                    ecommerce.displayProducts();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private User findUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    private Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}

// Class representing a Product
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

// Class representing a User
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Class representing an Order
class Order {
    private User user;
    private List<Product> products;

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append(user.getName()).append("'s Order: ");
        
        for (Product product : products) {
            orderDetails.append(product.getName()).append(", ");
        }
        
        return orderDetails.toString().replaceAll(", $", "") + " | Total: $" + calculateTotal();
    }

    private double calculateTotal() {
        double total = 0.0;
        
        for (Product product : products) {
            total += product.getPrice();
        }
        
        return total;
    }
}
