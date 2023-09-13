package org.replica.ecommerce.business.services;

import org.replica.ecommerce.business.domain.User;
import org.replica.ecommerce.business.domain.dto.SubscriptionDTO;
import org.replica.ecommerce.business.domain.Subscription;

import java.util.List;

public interface SubscriptionService {

    public Subscription findById(String id);
    public Subscription save(Subscription industry, User user);
    public Subscription update(String id, SubscriptionDTO industryDTO, User user);
    public List<Subscription> getAll();
    public List<Subscription> getAllActive();
    public List<Subscription> getAllActiveForSubscriber(User subscriber);

    public List<Subscription> getAllActiveSubscriptions();
}
