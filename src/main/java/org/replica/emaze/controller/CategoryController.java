package org.replica.emaze.controller;

import org.replica.emaze.aop.annotation.Auditor;
import org.replica.emaze.business.domain.Category;
import org.replica.emaze.business.domain.Industry;
import org.replica.emaze.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.CategoryDTO;
import org.replica.emaze.business.services.CategoryService;
import org.replica.emaze.business.services.IndustryService;
import org.replica.emaze.business.services.UserService;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 2/4/2023
 */

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    IndustryService industryService;

    @Autowired
    UserService userService;

    @Auditor
    @GetMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        List<Category> categories=categoryService.getAll();
        return ResponseEntity.ok(categories);

    }

    @Auditor
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id){
        return ResponseEntity.ok(categoryService.get(id));
    }

    @Auditor
    @GetMapping("/industry/{id}")
    public ResponseEntity<?> getByIndustry(@PathVariable("id") String industryId) throws InternalServerErrorException {
        Industry industry=industryService.findById(industryId);
        List<Category> categories=categoryService.getByIndustry(industry);
        return ResponseEntity.ok(categories);
    }


    @Auditor
    @PostMapping("/")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category= new Category();
        User user=userService.getCurrentUser();
        try{
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setIndustry(industryService.findById(categoryDTO.getIndustry()));
            category =categoryService.save(category, user);
            return  ResponseEntity.ok(categoryService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Auditor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") String id){

        try{
            User currentUser=userService.getCurrentUser();
            Category category=categoryService.update(id, categoryDTO,currentUser);
            List<Category> categories=categoryService.getAll();
            System.err.println(categories);
            return  ResponseEntity.ok(categories);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
