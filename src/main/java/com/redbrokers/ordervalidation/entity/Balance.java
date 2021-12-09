package com.redbrokers.ordervalidation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Balance {
    @Id
    private UUID id;
    private OffsetDateTime lastUpdated;
    private double balance;
}
