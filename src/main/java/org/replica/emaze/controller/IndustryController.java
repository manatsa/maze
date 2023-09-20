package org.replica.emaze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.replica.emaze.aop.annotation.Auditor;
import org.replica.emaze.business.domain.Industry;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.IndustryDTO;
import org.replica.emaze.business.services.IndustryService;
import org.replica.emaze.business.services.UserService;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 2/4/2023
 */

@RestController
@RequestMapping("/api/industry")
public class IndustryController {

    @Autowired
    IndustryService industryService;

    @Autowired
    UserService userService;

    @Auditor
    @GetMapping("/")
    public ResponseEntity<List<Industry>> getAll(){
        return ResponseEntity.ok(industryService.getAll());
    }


    @Auditor
    @PostMapping("/")
    public ResponseEntity<?> createIndustry(@RequestBody IndustryDTO industryDTO){
        Industry industry= new Industry();
        User user=userService.getCurrentUser();
        try{
            industry.setName(industryDTO.getName());
            industry.setDescription(industryDTO.getDescription());
            industry =industryService.save(industry, user);
            return  ResponseEntity.ok(industryService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Auditor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIndustry(@RequestBody IndustryDTO industryDTO, @PathVariable("id") String id){


        try{
            User currentUser=userService.getCurrentUser();
            Industry industry=industryService.update(id, industryDTO,currentUser);
            return  ResponseEntity.ok(industryService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
