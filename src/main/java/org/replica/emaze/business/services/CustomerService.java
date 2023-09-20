package org.replica.emaze.business.services;
import org.replica.emaze.business.domain.Customer;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    public Customer get(String customerId);
    public Customer save(Customer customer, User user);
    public Customer update(String id, CustomerDTO customerDTO, User user);
    public  List<Customer> getAll();

    public Customer getCustomerByEmailOrPhone(String email, String phone);
}
