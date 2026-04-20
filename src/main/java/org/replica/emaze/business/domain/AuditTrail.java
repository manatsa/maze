package org.replica.emaze.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author :: codemaster
 * created on :: 24/5/2023
 * Package Name :: org.replica.emaze.business.domain
 */


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AuditTrail extends BaseEntity{

    private String username;
    @Column(name = "start_time")
    private Date start;
    @Column(name = "end_time")
    private Date end;
    private String signature;
    private String actionName;
    private String action;
}
