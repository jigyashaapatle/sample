package main;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
import dao.CustomerDAO;
import dao.InventoryDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.SalesReportDAO;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.Product;
import service.CustomerService;
import service.InventoryService;
import service.OrderService;
import service.ProductService;
import service.SalesReportService;
 
public class Main {
 

	public static void main(String args[]) {
	        Scanner scanner = new Scanner(System.in);
	        ProductDAO productDao = new ProductDAO();
	        CustomerDAO customerDao = new CustomerDAO();
	        OrderDAO orderDao = new OrderDAO();
	        InventoryService inventoryService = new InventoryService();
	        OrderService orderService = new OrderService(orderDao);
	        CustomerService customerService = new CustomerService(customerDao);
	        ProductService productService = new ProductService(productDao);
	        boolean exit = true;
	        // Step 1: Show all products first
	        while(exit) {
	        System.out.println("Enter your choice");
	        System.out.println("1. Product Catalog Viewer");
	        System.out.println("2. Customer Management");
	        System.out.println("3. Order Placement");
	        System.out.println("4. Inventory Check");
	        System.out.println("5. Sales Report Generator");
	        System.out.println("6. exit");
            int c = scanner.nextInt();
            scanner.nextLine();

	        switch(c) {
	        case 1:
	        	System.out.println("\n---- All Available Products ----");
	            List<Product> allProducts = productService.getAllProducts();
	            if (allProducts.isEmpty()) {
	                System.out.println("No products found.");
	            } else {
	                allProducts.forEach(System.out::println);
	            }
 
	            // Step 2: Ask user if they want filtering
	            System.out.print("\nWould you like to apply filters? (yes/no): ");
	            String choice = scanner.nextLine().trim().toLowerCase();
	            if (!choice.equals("yes")) {
	                System.out.println("Exiting without filtering.");
	                scanner.close();
	                return;
	            }
 
	            // Step 3: Ask for specific filter criteria
	            System.out.print("Enter brand filter (or press Enter to skip): ");
	            String brand = scanner.nextLine().trim();
	            if (brand.isEmpty()) brand = null;
 
	            System.out.print("Enter category filter (or press Enter to skip): ");
	            String category = scanner.nextLine().trim();
	            if (category.isEmpty()) category = null;
 
	            Double minPrice = null, maxPrice = null;
	            System.out.print("Enter minimum price (or press Enter to skip): ");
	            String minInput = scanner.nextLine().trim();
	            if (!minInput.isEmpty()) {
	                try {
	                    minPrice = Double.parseDouble(minInput);
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid minimum price. Ignoring.");
	                }
	            }
 
	            System.out.print("Enter maximum price (or press Enter to skip): ");
	            String maxInput = scanner.nextLine().trim();
	            if (!maxInput.isEmpty()) {
	                try {
	                    maxPrice = Double.parseDouble(maxInput);
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid maximum price. Ignoring.");
	                }
	            }
 
	            // Step 4: Fetch and display filtered products
	            System.out.println("\n---- Filtered Product Catalog ----");
	            List<Product> filteredProducts = productService.getFilteredProducts(brand, category, minPrice, maxPrice);
	            if (filteredProducts.isEmpty()) {
	                System.out.println("No products found with the selected filters.");
	            } else {
	                filteredProducts.forEach(System.out::println);
	            }
	        break;
	        case 2:
	        	System.out.println("\n--- Customer Management ---");
	            System.out.println("1. Add New Customer");
	            System.out.println("2. Search for Customers");
	            System.out.print("Enter choice: ");
	            int choice1 = Integer.parseInt(scanner.nextLine());
 
	            if (choice1 == 1) {
	                System.out.print("Enter First Name: ");
	                String firstName = scanner.nextLine().trim();
	                System.out.print("Enter Last Name: ");
	                String lastName = scanner.nextLine().trim();
	                System.out.print("Enter Phone: ");
	                String phone = scanner.nextLine().trim();
	                System.out.print("Enter Email: ");
	                String email = scanner.nextLine().trim();
	                System.out.print("Enter Street: ");
	                String street = scanner.nextLine().trim();
	                System.out.print("Enter City: ");
	                String city = scanner.nextLine().trim();
	                System.out.print("Enter State: ");
	                String state = scanner.nextLine().trim();
	                System.out.print("Enter Zip Code: ");
	                String zipCode = scanner.nextLine().trim();
 
	                customerService.addCustomer(firstName, lastName, phone, email, street, city, state, zipCode);
	                System.out.println("Customer successfully added!");
	            } else if (choice1 == 2) {
	                System.out.print("Enter search keyword (Name, Email, or City): ");
	                String keyword = scanner.nextLine().trim();
	                List<Customer> customers = customerService.searchCustomers(keyword);
 
	                if (customers.isEmpty()) {
	                    System.out.println("No customers found for '" + keyword + "'.");
	                } else {
	                    customers.forEach(System.out::println);
	                }
	            } else {
	                System.out.println("Invalid choice. Please try again.");
	            }
 
	        break;

	        case 3:
	        	 System.out.println("\n--- Order Placement ---");
	             System.out.print("Enter Customer ID: ");
	             int customerId = scanner.nextInt();
	             System.out.print("Enter Store ID: ");
	             int storeId = scanner.nextInt();
	             System.out.print("Enter Staff ID: ");
	             int staffId = scanner.nextInt();
	             System.out.print("Enter Order Status (1-Pending, 2-Processing, 3-Rejected, 4-Completed): ");
	             int orderStatus = scanner.nextInt();
	             System.out.print("Enter Required Date (YYYY-MM-DD): ");
	             String requiredDateStr = scanner.next();
	             Date requiredDate = Date.valueOf(requiredDateStr);
 
	             List<OrderItem> orderItems = new ArrayList<>();
	             System.out.println("\nEnter Order Items:");
 
	             while (true) {
	                 System.out.print("Enter Product ID (or -1 to finish): ");
	                 int productId = scanner.nextInt();
	                 if (productId == -1) break;
 
	                 System.out.print("Enter Quantity: ");
	                 int quantity = scanner.nextInt();
 
	                 System.out.print("Enter List Price: ");
	                 double listPrice = scanner.nextDouble();
 
	                 System.out.print("Enter Discount (or 0 if none): ");
	                 double discount = scanner.nextDouble();
 
	                 orderItems.add(new OrderItem(productId, quantity, listPrice, discount));
	             }
 
	             boolean orderPlaced = orderService.placeOrder(customerId, storeId, staffId, orderStatus, requiredDate, orderItems);
	             if (orderPlaced) {
	                 System.out.println("Order successfully placed!");
	             } else {
	                 System.out.println("Order placement failed.");
	             }
                break;
	        case 4:
	        	System.out.print("Enter Store ID: ");
	            int storeId1 = scanner.nextInt();
	            System.out.print("Enter Product ID: ");
	            int productId1 = scanner.nextInt();
	            System.out.print("Enter Quantity Requested: ");
	            int requestedQuantity = scanner.nextInt();
 
	            // Call the service method instead of direct DAO interaction
	            System.out.println(inventoryService.checkStockAvailability(storeId1, productId1, requestedQuantity));
	        	break;
	        case 5:
	        	 SalesReportService reportService = new SalesReportService();
	             reportService.generateSalesReport();
	             break;
	        case 6:
	        	exit = false;
	        	System.out.println("Done...");
	        	break;
	        }


	    }
	        scanner.close();
	}
}
