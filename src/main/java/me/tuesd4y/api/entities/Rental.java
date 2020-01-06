package me.tuesd4y.api.entities;

import java.time.LocalDateTime;

public interface Rental {
    User getRenter();

    Vehicle getVehicle();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    Long getId();
}
