package org.replica.emaze;

import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnlockAdminUser {
    
    public static void main(String[] args) {
        SpringApplication.run(UnlockAdminUser.class, args);
    }
    
    @Bean
    public CommandLineRunner unlockUser(UserRepo userRepo) {
        return args -> {
            // Find the admin user and set active to true
            User admin = userRepo.findByUserName("admin", false); // Get regardless of active status
            if (admin != null) {
                admin.setActive(true);
                userRepo.save(admin);
                System.out.println("Successfully unlocked admin user!");
                System.out.println("Admin user ID: " + admin.getId());
                System.out.println("Admin username: " + admin.getUserName());
                System.out.println("Admin active status: " + admin.getActive());
            } else {
                System.out.println("Admin user not found in database.");
            }
        };
    }
}
