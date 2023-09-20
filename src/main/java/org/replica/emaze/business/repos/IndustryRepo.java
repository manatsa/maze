package org.replica.emaze.business.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.replica.emaze.business.domain.Industry;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.replica.emaze.business.repos
 */

public interface IndustryRepo extends JpaRepository<Industry, String> {
    public Industry findByName(String name);
}
