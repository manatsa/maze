package org.replica.emaze.business.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.domain
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalOrderDTO {
    private String professionalID;
    private boolean homeService;
    private boolean recurring;
    private String recurringPeriod;
    private Date bookingDate;
    private boolean payment;
    private String customerID;
}
