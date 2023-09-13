package org.replica.ecommerce.business.repos;

import org.replica.ecommerce.business.domain.Category;
import org.replica.ecommerce.business.domain.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.zimnat.lionloader.business.repos
 */

public interface CategoryRepo extends JpaRepository<Category, String> {
    public Category findByName(String name);
    public List<Category> getCategoriesByIndustry(Industry industry);
}
