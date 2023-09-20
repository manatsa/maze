package org.replica.emaze.business.services.impl;

import org.replica.emaze.business.domain.Industry;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.IndustryDTO;
import org.replica.emaze.business.repos.IndustryRepo;
import org.replica.emaze.business.services.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.services.impl
 */

@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    private IndustryRepo industryRepo;

    @Override
    public Industry findById(String id) {
        return industryRepo.findById(id).get();
    }

    @Transactional
    @Override
    public Industry save(Industry industry, User user) {
        industry.setId(UUID.randomUUID().toString());
        industry.setCreatedBy(user);
        industry.setDateCreated(new Date());
        return industryRepo.save(industry);
    }

    @Transactional
    @Override
    public Industry update(String id, IndustryDTO industryDTO, User user) {
        if(id!=null && !id.isEmpty()){
            Industry target=industryRepo.findById(id).get();
            if(target!=null){
                target.setName(industryDTO.getName());
                target.setDescription(industryDTO.getDescription());
                target.setDateModified(new Date());
                target.setModifiedBy(user);
                return industryRepo.save(target);
            }

        }

        return null;
    }

    @Override
    public List<Industry> getAll() {
        return industryRepo.findAll();
    }
}
