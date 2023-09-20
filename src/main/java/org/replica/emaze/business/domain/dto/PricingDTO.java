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
public class PricingDTO {
    private String name;
    private String description;
    private double cost;
    private Date startDate;
    private Date endDate;

}
