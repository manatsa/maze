package org.replica.ecommerce.business.repos;

import org.replica.ecommerce.business.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author :: codemaster
 * created on :: 23/3/2023
 */

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
    public Customer findByEmailIsOrPhoneIs(String email, String phone);
}