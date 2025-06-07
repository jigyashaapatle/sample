package service;
 
import java.util.List;
 
import dao.ProductDAO;
import model.Product;
 //My Product Service
public class ProductService {
    private ProductDAO productDao;
 
    public ProductService(ProductDAO productDao) {
        this.productDao = productDao;
    }
 
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
 
    public List<Product> getFilteredProducts(String brand, String category, Double minPrice, Double maxPrice) {
        return productDao.getFilteredProducts(brand, category, minPrice, maxPrice);
    }
}
