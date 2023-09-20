package org.replica.emaze.business.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.domain
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO{

    private String title;

    private String firstName;

    private String lastName;

    private String address;

    private String address2;

    private String email;

    private String phone;

    private String phone2;

    private String deliver;

    private String register;

    private OrderDTO order;

}
