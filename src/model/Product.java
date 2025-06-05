package model;
 
//Display all products with brand, category, model year, and price.
public class Product {
    private int productId;
    private String productName;
    private String brand;
    private String category;
    private int modelYear;
    private double price;
 
    public Product(int productId, String productName, String brand, String category, int modelYear, double price) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.modelYear = modelYear;
        this.price = price;
    }
 
   
		 public int getProductId() {
		return productId;
	}
 
 
	public void setProductId(int productId) {
		this.productId = productId;
	}
 
 
	public String getProductName() {
		return productName;
	}
 
 
	public void setProductName(String productName) {
		this.productName = productName;
	}
 
 
	public String getBrand() {
		return brand;
	}
 
 
	public void setBrand(String brand) {
		this.brand = brand;
	}
 
 
	public String getCategory() {
		return category;
	}
 
 
	public void setCategory(String category) {
		this.category = category;
	}
 
 
	public int getModelYear() {
		return modelYear;
	}
 
 
	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}
 
 
	public double getPrice() {
		return price;
	}
 
 
	public void setPrice(double price) {
		this.price = price;
	}
 
 
		@Override
		public String toString() {
			
			return "Product [product name= " + productName +"| brand= " + brand +  "| category=" + category + "| modelYear="
					+ modelYear + "| price= " + price + "]";
			
			    
			
		}
}
 
