package org.replica.ecommerce.business.services;

import org.replica.ecommerce.business.domain.Industry;
import org.replica.ecommerce.business.domain.User;
import org.replica.ecommerce.business.domain.dto.IndustryDTO;

import java.util.List;

public interface IndustryService {

    public Industry findById(String id);
    public Industry save(Industry industry, User user);

    public Industry update(String id, IndustryDTO industryDTO, User user);

    public List<Industry> getAll();
}
