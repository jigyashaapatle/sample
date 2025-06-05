package model;
import java.math.BigDecimal;
 
public class OrderItem {
    private int productId;
    private int quantity;
    private double listPrice;
    private double discount;
 
    public OrderItem(int productId, int quantity, double listPrice, double discount) {
        this.productId = productId;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
    }
 
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getListPrice() { return listPrice; }
    public double getDiscount() { return discount; }
}
