package com.redbrokers.ordervalidation.entity;

import com.redbrokers.ordervalidation.enums.PortfolioStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Portfolio {
    @Id
    private UUID id;
    private String name;

    private UUID clientId;
    private OffsetDateTime  createdTimestamp;
    @Enumerated(EnumType.STRING)
    private PortfolioStatus status;

}
