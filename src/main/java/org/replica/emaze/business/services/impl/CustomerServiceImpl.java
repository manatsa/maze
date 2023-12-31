package org.replica.emaze.business.services.impl;

import org.replica.emaze.business.domain.Customer;
import org.replica.emaze.business.domain.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.repos.CustomerRepo;
import org.replica.emaze.business.services.CategoryService;
import org.replica.emaze.business.services.CustomerService;
import org.replica.emaze.business.services.UserService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.services.impl
 */


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Override
    public Customer get(String customerId) {
        return customerRepo.getById(customerId);
    }

    @Transactional
    @Override
    public Customer save(Customer customer, User user) {
        if(customer!=null){
            customer.setId(UUID.randomUUID().toString());
            customer.setCreatedBy(user);
            customer.setDateCreated(new Date());

            return customerRepo.save(customer);
        }
        return null;
    }

    @Transactional
    @Override
    public Customer update(String id, CustomerDTO customerDTO, User user) {
        if(id!=null && !id.isEmpty()){
            Customer target=customerRepo.getById(id);
            if(target!=null){
                target.setFirstName(customerDTO.getFirstName());
                target.setLastName(customerDTO.getLastName());
                target.setAddress(customerDTO.getAddress());
                target.setAddress2(customerDTO.getAddress2());
                /*if(customerDTO.getPicture()!=null && customerDTO.getPicture().length()>0) {
                    target.setPicture(customerDTO.getPicture());
                }*/
                target.setEmail(customerDTO.getEmail());
                target.setPhone(customerDTO.getPhone());
                target.setPhone2(customerDTO.getPhone2());
                target.setTitle(customerDTO.getTitle());
                target.setDeliver(customerDTO.getDeliver().equalsIgnoreCase("Yes"));
                target.setRegister(customerDTO.getRegister().equalsIgnoreCase("Yes"));
                target.setModifiedBy(user);
                target.setDateModified(new Date());
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers=customerRepo.findAll();
        return customers;
    }

    @Override
    public Customer getCustomerByEmailOrPhone(String email, String phone) {
        return customerRepo.findByEmailIsOrPhoneIs(email, phone);
    }

}
