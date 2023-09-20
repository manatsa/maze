package org.replica.emaze.business.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :: codemaster
 * created on :: 5/9/2023
 * Package Name :: org.replica.emaze.business.domain.dto
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String productID;
    private double quantity;
    private Date deliveryDate;
    private boolean payment;
}
