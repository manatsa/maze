package org.replica.ecommerce.business.repos;

import org.replica.ecommerce.business.domain.Order;
import org.replica.ecommerce.business.domain.Pricing;
import org.replica.ecommerce.business.domain.Product;
import org.replica.ecommerce.business.domain.User;
import org.replica.ecommerce.business.domain.enums.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author :: codemaster
 * created on :: 23/3/2023
 */

@Repository
public interface PricingRepo extends JpaRepository<Pricing, String> {

    @Query("Select Distinct p from Pricing p where p.period =:period order by p.endDate DESC")
    public Pricing findFirstByEndDate(@Param("period") Period period);

}