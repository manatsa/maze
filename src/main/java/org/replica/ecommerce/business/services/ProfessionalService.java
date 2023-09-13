package org.replica.ecommerce.business.services;
import org.replica.ecommerce.business.domain.Professional;
import org.replica.ecommerce.business.domain.dto.ProfessionalDTO;
import org.replica.ecommerce.business.domain.Category;
import org.replica.ecommerce.business.domain.Industry;
import org.replica.ecommerce.business.domain.User;

import java.util.List;

public interface ProfessionalService {

    public Professional save(Professional professionalDTO, User user);
    public Professional update(String id, ProfessionalDTO professionalDTO, User user);
    public  List<Professional> getAll();

    public List<Professional> findByName(String name);
    public List<Professional> getProfessionalsByCategoryAndActive(Category category);
    public List<Professional> getProfessionalsByTitleAndActive(String title);

    List<Professional> getProfessionalsByCategory(Category category);

    public List<Professional> getProfessionalsByIndustry(Industry industry);
}
