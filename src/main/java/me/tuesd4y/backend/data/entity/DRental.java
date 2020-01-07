package me.tuesd4y.backend.data.entity;

import com.fasterxml.jackson.annotation.JsonSetter;
import me.tuesd4y.api.entities.Rental;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class DRental implements Rental  {
    @ManyToOne
    private DNormalUser renter;
    @ManyToOne
    private DVehicle vehicle;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime;
    @Id @GeneratedValue
    private Long id;

    public DRental() {
    }

    @Override
    public DNormalUser getRenter() {
        return renter;
    }

    public void setRenter(DNormalUser renter) {
        this.renter = renter;
    }

    @Override
    public DVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(DVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
