package org.replica.emaze.business.services.impl;

import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.Role;
import org.replica.emaze.business.repos.RoleRepo;
import org.replica.emaze.business.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DefaultUserInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserInitializer.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Default password for admin user: P@88345!

    @Override
    public void run(String... args) throws Exception {
        logger.info("Initializing default users...");

        // Create default roles if they don't exist
        Role adminRole = roleRepo.getUserRoleByName("ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setId(UUID.randomUUID().toString());
            adminRole.setName("ADMIN");
            adminRole.setDescription("Administrator role");
            adminRole.setDateCreated(new Date());
            adminRole = roleRepo.save(adminRole);
            logger.info("Created ADMIN role");
        }

        Role userRole = roleRepo.getUserRoleByName("USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setId(UUID.randomUUID().toString());
            userRole.setName("USER");
            userRole.setDescription("Regular user role");
            userRole.setDateCreated(new Date());
            userRole = roleRepo.save(userRole);
            logger.info("Created USER role");
        }

        // Create default super user
        if (userRepo.findByUserName("super", true) == null) {
            User superUser = new User();
            superUser.setId(UUID.randomUUID().toString());
            superUser.setUserName("super");
            superUser.setFirstName("Super");
            superUser.setLastName("User");
            superUser.setEmail("super@maze.com");
            superUser.setPassword(passwordEncoder.encode("P@88345!"));
            superUser.setActive(true);
            superUser.setDateCreated(new Date());
            superUser.setRoles(java.util.Collections.singleton(adminRole));
            userRepo.save(superUser);
            logger.info("Created 'super' user with password P@88345!");
        }

        // Create default admin user
        User existingAdmin = userRepo.findByUserName("admin", false); // Check regardless of active status
        if (existingAdmin == null) {
            User adminUser = new User();
            adminUser.setId(UUID.randomUUID().toString());
            adminUser.setUserName("admin");
            adminUser.setFirstName("Admin");
            adminUser.setLastName("User");
            adminUser.setEmail("admin@maze.com");
            adminUser.setPassword(passwordEncoder.encode("P@88345!"));
            adminUser.setActive(true);
            adminUser.setDateCreated(new Date());
            adminUser.setRoles(java.util.Collections.singleton(adminRole));
            userRepo.save(adminUser);
            logger.info("Created 'admin' user with password P@88345!");
        } else {
            logger.info("Admin user already exists (active={})", existingAdmin.getActive());
        }

        logger.info("Default user initialization complete.");
    }
}