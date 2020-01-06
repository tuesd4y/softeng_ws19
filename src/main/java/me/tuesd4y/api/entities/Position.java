package me.tuesd4y.api.entities;

import java.time.LocalDateTime;

public interface Position extends Location {
    Vehicle getVehicle();

    LocalDateTime getDateTime();

    Long getId();
}
