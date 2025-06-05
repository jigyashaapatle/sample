package service;
 
import dao.SalesReportDAO;
 
import java.util.List;
import java.util.Map;
 
public class SalesReportService {
    private SalesReportDAO reportDAO;
 
    public SalesReportService() {
        this.reportDAO = new SalesReportDAO();
    }
 
    public void generateSalesReport() {
        System.out.println("\n Total Sales Per Store:");
        Map<String, Double> storeSales = reportDAO.getTotalSalesPerStore();
        storeSales.forEach((store, sales) -> System.out.println(store + ": $" + sales));
 
        System.out.println("\n Top 5 Selling Products:");
        List<String> topProducts = reportDAO.getTopSellingProducts();
        for (int i = 0; i < topProducts.size(); i++) {
            System.out.println((i + 1) + ". " + topProducts.get(i));
        }
 
        System.out.println("\n Monthly Sales Trends:");
        Map<String, Double> monthlyTrends = reportDAO.getMonthlySalesTrends();
        monthlyTrends.forEach((month, sales) -> System.out.println(month + ": $" + sales));
    }
}
