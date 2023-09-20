package org.replica.emaze.business.services.impl;

import org.replica.emaze.business.domain.Order;
import org.replica.emaze.business.domain.Professional;
import org.replica.emaze.business.domain.ProfessionalOrder;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.OrderDTO;
import org.replica.emaze.business.domain.dto.ProfessionalOrderDTO;
import org.replica.emaze.business.domain.enums.RecurrPeriod;
import org.replica.emaze.business.repos.OrderRepo;
import org.replica.emaze.business.repos.ProfessionalOrderRepo;
import org.replica.emaze.business.services.*;
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
public class ProfessionalOrderServiceImpl implements ProfessionalOrderService {

    @Autowired
    private ProfessionalOrderRepo professionalOrderRepo;

    @Autowired
    ProfessionalService professionalService;

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    @Override
    public ProfessionalOrder get(String orderId) {
        return professionalOrderRepo.getById(orderId);
    }

    @Transactional
    @Override
    public ProfessionalOrder save(ProfessionalOrder order, User user) {
        if(order!=null){
            order.setId(UUID.randomUUID().toString());
            order.setDateCreated(new Date());
            return professionalOrderRepo.save(order);
        }
        return null;
    }

    @Transactional
    @Override
    public ProfessionalOrder update(String id, ProfessionalOrderDTO orderDTO, User user) {
        if(id!=null && !id.isEmpty()){
            ProfessionalOrder target=professionalOrderRepo.getById(id);
            if(target!=null){
                target.setProfessional(professionalService.get(orderDTO.getProfessionalID()));
                target.setPayment(orderDTO.isPayment());
                target.setHomeService(orderDTO.isHomeService());
                target.setBookingDate(orderDTO.getBookingDate());
                target.setRecurring(orderDTO.isRecurring());
                target.setRecurringPeriod(RecurrPeriod.valueOf(orderDTO.getRecurringPeriod()));
                target.setModifiedBy(user);
                target.setDateModified(new Date());
            }
        }
        return null;
    }

    @Override
    public List<ProfessionalOrder> getAll() {
        List<ProfessionalOrder> orders=professionalOrderRepo.findAll();
        return orders;
    }

    @Override
    public List<ProfessionalOrder> getProfessionalOrdersForOwner(User owner) {
        return professionalOrderRepo.getProfessionalOrderForOwner(owner);
    }

    @Override
    public List<ProfessionalOrder> getProfessionalOrdersByUser(User owner) {
        return professionalOrderRepo.getProfessionalOrderByUser(owner);
    }

    @Override
    public List<ProfessionalOrder> getProfessionalOrdersBetween(Date startDate, Date endDate) {
        return professionalOrderRepo.getProfessionalOrdersBetween(startDate, endDate);
    }

    @Override
    public List<ProfessionalOrder> getProfessionalOrdersForOwnerBetween(User owner, Date startDate, Date endDate) {
        return professionalOrderRepo.getProfessionalOrdersForOwnerBetween(owner, startDate, endDate);
    }

    @Override
    public List<ProfessionalOrder> getProfessionalOrdersForOwnerByProfessional(Professional professional) {
        return professionalOrderRepo.getProfessionalOrdersByProfessional(professional);
    }


}
