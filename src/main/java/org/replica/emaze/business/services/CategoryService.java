package org.replica.emaze.business.services;

import org.replica.emaze.business.domain.Category;
import org.replica.emaze.business.domain.Industry;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public Category save(Category category, User user);

    public Category update(String id, CategoryDTO categoryDTO, User user);

    public List<Category> getAll();

    public Category get(String id);

    public List<Category> getByIndustry(Industry industry);
}
