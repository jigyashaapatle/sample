package service;
 
import dao.InventoryDAO;
 
public class InventoryService {
    private InventoryDAO inventoryDAO;
 
    public InventoryService() {
        this.inventoryDAO = new InventoryDAO();
    }
 
    // Business logic to check stock availability
    public String checkStockAvailability(int storeId, int productId, int requestedQuantity) {
        int availableStock = inventoryDAO.checkStock(storeId, productId);
 
        if (availableStock >= requestedQuantity) {
            return " Stock available: " + availableStock + " units.";
        } else {
            return " Insufficient stock! Available: " + availableStock + ", Requested: " + requestedQuantity;
        }
    }
}