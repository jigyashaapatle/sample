package service;
import java.sql.Date;
import java.util.List;
 
import dao.OrderDAO;
import model.Order;
import model.OrderItem;
 
public class OrderService {
    private OrderDAO orderDao;
 
    public OrderService(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }
 
    public boolean placeOrder(int customerId, int storeId, int staffId, int orderStatus, Date requiredDate, List<OrderItem> orderItems) {
        Date orderDate = new Date(System.currentTimeMillis()); // Current date
        Order order = new Order(customerId, orderStatus, orderDate, requiredDate, storeId, staffId);
 
        return orderDao.placeOrder(order, orderItems);
    }
}
