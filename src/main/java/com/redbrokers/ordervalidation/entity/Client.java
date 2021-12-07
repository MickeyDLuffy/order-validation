package com.redbrokers.ordervalidation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Client {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private UUID balanceId;
    private OffsetDateTime created_at;
    @NonNull
    private String email;
    private int status = 1;
    /*id UUID NOT NULL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    balance_id UUID,
    created_at TIMESTAMPTZ NOT NULL,
    email VARCHAR(100) UNIQUE,
    status int DEFAULT 1*/
}
