package org.replica.emaze.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.replica.emaze.business.domain.enums.Period;

import javax.persistence.*;
import java.util.Date;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.domain
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pricing extends BaseEntity{

    @Enumerated
    private Period period;
    private String description;
    private double cost;
    private Date startDate;
    private Date endDate;

}
