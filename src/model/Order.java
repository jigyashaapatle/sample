package model;
 
import java.sql.Date;
 
public class Order {
    private int customerId;
    private int orderStatus;
    private Date orderDate;
    private Date requiredDate;
    private int storeId;
    private int staffId;
 
    public Order(int customerId, int orderStatus, java.sql.Date orderDate, java.sql.Date requiredDate, int storeId, int staffId) {
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.storeId = storeId;
        this.staffId = staffId;
    }
 
    public int getCustomerId() { return customerId; }
    public int getOrderStatus() { return orderStatus; }
    public Date getOrderDate() { return orderDate; }
    public Date getRequiredDate() { return requiredDate; }
    public int getStoreId() { return storeId; }
    public int getStaffId() { return staffId; }
}
