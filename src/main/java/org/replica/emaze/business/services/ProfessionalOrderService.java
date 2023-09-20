package org.replica.emaze.business.services;

import org.replica.emaze.business.domain.Professional;
import org.replica.emaze.business.domain.ProfessionalOrder;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.ProfessionalOrderDTO;

import java.util.Date;
import java.util.List;

public interface ProfessionalOrderService {

    public ProfessionalOrder get(String orderId);
    public ProfessionalOrder save(ProfessionalOrder order, User user);
    public ProfessionalOrder update(String id, ProfessionalOrderDTO professionalOrderDTO, User user);
    public  List<ProfessionalOrder> getAll();

    public List<ProfessionalOrder> getProfessionalOrdersForOwner(User owner);

    public List<ProfessionalOrder> getProfessionalOrdersByUser(User owner);

    public List<ProfessionalOrder> getProfessionalOrdersBetween(Date startDate, Date endDate);

    public List<ProfessionalOrder> getProfessionalOrdersForOwnerBetween(User owner, Date startDate, Date endDate);

    public List<ProfessionalOrder> getProfessionalOrdersForOwnerByProfessional(Professional professional);
}
