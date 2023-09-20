package org.replica.emaze.business.repos;

import org.replica.emaze.business.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.replica.emaze.business.domain.Subscription;

import java.util.Date;
import java.util.List;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.repos
 */

public interface SubscriptionRepo extends JpaRepository<Subscription, String> {

    public List<Subscription> getSubscriptionBySubscriberAndEndDateIsAfter(User subscriber, Date endDate);

    public List<Subscription> getAllByEndDateIsGreaterThanEqual(Date endDate);
}
