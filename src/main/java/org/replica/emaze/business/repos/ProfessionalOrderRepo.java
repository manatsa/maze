package org.replica.emaze.business.repos;

import org.replica.emaze.business.domain.*;
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
public interface ProfessionalOrderRepo extends JpaRepository<ProfessionalOrder, String> {

    @Query("Select Distinct p from Professional p left join fetch p.administrator a  where a=:admin")
    public List<ProfessionalOrder> getProfessionalOrderForOwner(@Param("admin") User admin);

    @Query("Select Distinct p from ProfessionalOrder p left join fetch p.professional pr  where p.createdBy =:user")
    public List<ProfessionalOrder> getProfessionalOrderByUser(@Param("user") User owner);

    @Query("Select Distinct p from ProfessionalOrder p left join fetch p.professional pr  where p.dateCreated between :startDate and :endDate")
    public List<ProfessionalOrder> getProfessionalOrdersBetween(@Param("startDate")Date startDate, @Param("endDate") Date endDate);

    @Query("Select Distinct p from ProfessionalOrder p left join fetch p.professional pr  where pr.administrator=:owner and (p.dateCreated between :startDate and :endDate)")
    public List<ProfessionalOrder> getProfessionalOrdersForOwnerBetween(@Param("owner") User owner,@Param("startDate")Date startDate, @Param("endDate") Date endDate);

    @Query("Select Distinct p from ProfessionalOrder p  where p.professional=:professional")
    public List<ProfessionalOrder> getProfessionalOrdersByProfessional(@Param("professional") Professional professional);
}