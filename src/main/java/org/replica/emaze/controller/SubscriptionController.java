package org.replica.emaze.controller;

import org.replica.emaze.aop.annotation.Auditor;
import org.replica.emaze.business.domain.Subscription;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.SubscriptionDTO;
import org.replica.emaze.business.domain.enums.Period;
import org.replica.emaze.business.services.CategoryService;
import org.replica.emaze.business.services.SubscriptionService;
import org.replica.emaze.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 2/4/2023
 */

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Auditor
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<Subscription> subscriptions=subscriptionService.getAll();
        return ResponseEntity.ok(subscriptions);
    }

    @Auditor
    @GetMapping("/active/{id}")
    public ResponseEntity<?> getAllActiveForUser(@PathVariable("id") String id){
        if(id!=null && !id.isEmpty() && id.length()>2) {
            User subscriber = userService.get(id);
            List<Subscription> subscriptions = subscriptionService.getAllActiveForSubscriber(subscriber);
            return ResponseEntity.ok(subscriptions);
        }else{
           return  ResponseEntity.ok().build();
        }
    }

    @Auditor
    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveForSubscriber(){
            User subscriber = userService.getCurrentUser();
            List<Subscription> subscriptions = subscriptionService.getAllActiveForSubscriber(subscriber);
            return ResponseEntity.ok(subscriptions);
    }

    @Auditor
    @GetMapping("/active/all")
    public ResponseEntity<?> getAllActiveSubscriptions(){
        List<Subscription> subscriptions = subscriptionService.getAllActiveSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }


    @Auditor
    @PostMapping("/")
    public ResponseEntity<?> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {

        Subscription subscription= new Subscription();
        User user=userService.getCurrentUser();
        try{
            if(subscription!=null){
                subscription.setSubscriber(userService.get(subscriptionDTO.getSubscriber()));
                subscription.setStartDate(subscriptionDTO.getStartDate());
                subscription.setPeriod(Period.valueOf(subscriptionDTO.getPeriod()));
                subscription.setEndDate(subscriptionDTO.getEndDate());
            }
            subscription =subscriptionService.save(subscription, user);
            List<Subscription> subscriptions=subscriptionService.getAll();
            return  ResponseEntity.ok(subscriptions);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Auditor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO, @PathVariable("id") String id){
        try{
            User currentUser=userService.getCurrentUser();
            Subscription subscription=subscriptionService.update(id, subscriptionDTO,currentUser);
            return  ResponseEntity.ok(subscriptionService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
