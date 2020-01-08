package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.Event;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class DEvent implements Event {
    @Embedded
    private DLocation location;
    private String description;
    private String type;
    private int risk;
    private int timeLoss;
    private LocalDateTime timeStamp;
    @Id
    @GeneratedValue
    private Long id;

    public DEvent() {
    }

    public DLocation getLocation() {
        return location;
    }

    public void setLocation(DLocation location) {
        this.location = location;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    @Override
    public int getTimeLoss() {
        return timeLoss;
    }

    public void setTimeLoss(int timeLoss) {
        this.timeLoss = timeLoss;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }
}
