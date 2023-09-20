package org.replica.emaze.business.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.domain.dto
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDTO {

    private String title;

    private String firstName;

    private String lastName;

    private String address;

    private String address2;

    private String email;

    private String phone;

    private String phone2;

    private String tel;

    private String qualifications;

    private String category;

    private Boolean active;

    private String experience;

    private String organization;

    private String admin;

    private String Tags;

    private String picture;
}
