package org.replica.emaze.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.replica.emaze.business.domain.enums.RecurrPeriod;

import javax.persistence.*;
import java.util.Date;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.domain
 */

@Table(name="professional_order")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalOrder extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
    private boolean homeService;
    private boolean recurring;
    @Enumerated
    private RecurrPeriod recurringPeriod;
    private Date bookingDate;
    private boolean payment;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
