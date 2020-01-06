package me.tuesd4y.api.entities;

import java.time.LocalDateTime;

public interface Maintenance {
    Vehicle getVehicle();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    String getProblemDescription();

    Long getId();
}
