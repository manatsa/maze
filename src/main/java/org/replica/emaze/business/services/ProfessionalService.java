package org.replica.emaze.business.services;
import org.replica.emaze.business.domain.*;
import org.replica.emaze.business.domain.dto.ProfessionalDTO;

import java.util.List;

public interface ProfessionalService {

    public Professional get(String id);
    public Professional save(Professional professionalDTO, User user);
    public Professional update(String id, ProfessionalDTO professionalDTO, User user);
    public  List<Professional> getAll();

    public List<Professional> findByName(String name);
    public List<Professional> getProfessionalsByCategoryAndActive(Category category);
    public List<Professional> getProfessionalsByTitleAndActive(String title);

    List<Professional> getProfessionalsByCategory(Category category);

    public List<Professional> getProfessionalsByIndustry(Industry industry);
}
