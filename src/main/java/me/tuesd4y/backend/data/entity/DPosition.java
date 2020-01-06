package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.Position;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DPosition implements Position {
    @ManyToOne private DVehicle vehicle;
    @Embedded private DLocation location;
    private LocalDateTime dateTime;
    @Id @GeneratedValue Long id;

    public DPosition() {
    }

    @Override
    public DVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(DVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DLocation getLocation() {
        return location;
    }

    public void setLocation(DLocation location) {
        this.location = location;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleIdentification() {
        if(vehicle != null){
            return vehicle.getId();
        } else {
            return null;
        }
    }

    @Override
    public double getLatitude() {
        return location.getLatitude();
    }

    @Override
    public double getLongitude() {
        return location.getLongitude();
    }
}
