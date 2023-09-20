package org.replica.emaze.business.services;
import org.replica.emaze.business.domain.Category;
import org.replica.emaze.business.domain.Industry;
import org.replica.emaze.business.domain.Product;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public Product get(String id);
    public Product save(Product product, User user);
    public Product update(String id, ProductDTO product, User user);
    public  List<Product> getAll();

    public  List<Product> getAllForOwner(User owner);
    public Product findByName(String name);
    public List<Product> getProductsByCategory(Category category);
    public List<Product> getProductsByIndustry(Industry industry);
}
