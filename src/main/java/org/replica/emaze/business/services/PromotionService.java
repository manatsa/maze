package org.replica.emaze.business.services;

import org.replica.emaze.business.domain.Promotion;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.PromotionDTO;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 9/6/2023
 * Package Name :: org.replica.emaze.business.services
 */

public interface PromotionService {

    public List<Promotion> getAll();
    public Promotion save(Promotion setting, User user);
    public Promotion update(String id, PromotionDTO promotionDTO, User user);
    public Promotion findById(String id);
    public Promotion findByName(String name);
    public List<Promotion> findByDescription(String description);
}
