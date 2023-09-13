package org.replica.ecommerce.business.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.replica.ecommerce.business.domain.Privilege;
import org.replica.ecommerce.business.services.RoleService;

/**
 * @author :: codemaster
 * created on :: 30/3/2023
 */

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PrivilegeDTO {
    private String id;
    private String name;


    @Autowired
    RoleService roleService;

    public PrivilegeDTO(String id, String name) {
        this.id = id;
        this.name=name;
    }

    public PrivilegeDTO(String name) {
        this.name=name;
    }

    public Privilege createFromDTO(){
            Privilege privilege= new Privilege();
            privilege.setId(this.id);
            privilege.setName(this.name);
            return privilege;
    }
}
