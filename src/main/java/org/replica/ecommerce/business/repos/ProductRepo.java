package org.replica.ecommerce.business.repos;

import org.replica.ecommerce.business.domain.Category;
import org.replica.ecommerce.business.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.replica.ecommerce.business.domain.User;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.zimnat.lionloader.business.repos
 */

public interface ProductRepo extends JpaRepository<Product, String> {
    public Product findByName(String name);
    List<Product> getProductsByCategory(Category category);

    List<Product> getProductsByOwnerAndActive(User owner, boolean active);

    /*@Query("select p from Product p left join fetch p.category_id c left join fetch c.industry_id i where i = :industry")
    List<Product> getProductsInIndustry(@Param("industry") Industry industry);*/
}
