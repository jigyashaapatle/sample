package service;
import java.util.List;
 
import dao.CustomerDAO;
import model.Customer;
 
public class CustomerService {
    private CustomerDAO customerDao;
 
    public CustomerService(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }
 
    public void addCustomer(String firstName, String lastName, String phone, String email, String street, String city, String state, String zipCode) {
        Customer customer = new Customer(0, firstName, lastName, phone, email, street, city, state, zipCode);
        customerDao.addCustomer(customer);
    }
 
    public List<Customer> searchCustomers(String keyword) {
        return customerDao.searchCustomers(keyword);
    }
}
