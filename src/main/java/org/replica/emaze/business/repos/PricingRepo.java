package org.replica.emaze.business.repos;

import org.replica.emaze.business.domain.Pricing;
import org.replica.emaze.business.domain.enums.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author :: codemaster
 * created on :: 23/3/2023
 */

@Repository
public interface PricingRepo extends JpaRepository<Pricing, String> {

    @Query("Select Distinct p from Pricing p where p.period =:period order by p.endDate DESC")
    public Pricing findFirstByEndDate(@Param("period") Period period);

}