package org.replica.ecommerce.business.repos;

import org.replica.ecommerce.business.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.replica.ecommerce.business.domain.Privilege;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 23/3/2023
 */

public interface PrivilegeRepo extends JpaRepository<Privilege, String> {


    List<Privilege> findAllByRolesIn(List<Role> role);
    Privilege getByName(String name);
}