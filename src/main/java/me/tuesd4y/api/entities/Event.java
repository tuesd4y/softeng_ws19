package me.tuesd4y.api.entities;

import java.time.LocalDateTime;

public interface Event extends Location{
    String getDescription();

    String getType();

    int getRisk();

    int getTimeLoss();

    LocalDateTime getTimeStamp();

    Long getId();
}
