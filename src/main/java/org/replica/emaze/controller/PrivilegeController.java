package org.replica.emaze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.replica.emaze.aop.annotation.Auditor;
import org.replica.emaze.business.domain.Privilege;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.PrivilegeDTO;
import org.replica.emaze.business.services.PrivilegeService;
import org.replica.emaze.business.services.UserService;

/**
 * @author :: codemaster
 * created on :: 2/4/2023
 */

@RestController
@RequestMapping("/api/privileges")
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    UserService userService;

    @Auditor
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(privilegeService.getAll());
    }


    @Auditor
    @PostMapping("/")
    public ResponseEntity<?> createRole(@RequestBody PrivilegeDTO privilegeDTO){
        Privilege privilege= new Privilege();
        privilege.setName(privilegeDTO.getName());
        User user=userService.getCurrentUser();
        try{
            privilege =privilegeService.save(privilege, user);
            return  ResponseEntity.ok(privilegeService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Auditor
    @PutMapping("/{id}")
    public ResponseEntity<?> createPrivilege(@RequestBody PrivilegeDTO privilegeDTO, @PathVariable("id") String id){

        try{
            User currentUser=userService.getCurrentUser();
            Privilege privilege=privilegeService.update(id, privilegeDTO,currentUser);
            return  ResponseEntity.ok(privilegeService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
