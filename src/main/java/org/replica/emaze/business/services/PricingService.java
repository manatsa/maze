package org.replica.emaze.business.services;

import org.replica.emaze.business.domain.Pricing;
import org.replica.emaze.business.domain.User;
import org.replica.emaze.business.domain.dto.PricingDTO;
import org.replica.emaze.business.domain.enums.Period;

import java.util.Date;
import java.util.List;

public interface PricingService {

    public Pricing get(String pricingId);
    public Pricing save(Pricing pricing, User user);
    public Pricing update(String id, PricingDTO pricingDTO, User user);
    public  List<Pricing> getAll();
    public Pricing getLatestPricingByPeriod(Period period, Date endDate);

}
