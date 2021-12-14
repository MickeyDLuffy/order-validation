package com.redbrokers.ordervalidation.dto;

import com.redbrokers.ordervalidation.enums.EventType;
import lombok.Data;
import lombok.Value;

@Data
public class Report {
    private String description;
    private EventType eventType;

    public Report(String description, EventType eventType) {
        this.description = description;
        this.eventType = eventType;
    }


    @Override
    public String toString() {
        return "Report{" +
                "description=" + description +
                ", eventType=" + eventType +
                '}';
    }
}
