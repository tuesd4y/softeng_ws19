package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.Maintenance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DMaintenance implements Maintenance {
    @ManyToOne private DVehicle vehicle;
    private LocalDateTime startTime;
    private String problemDescription;
    private LocalDateTime endTime;
    @Id @GeneratedValue
    private Long id;

    public DMaintenance() {
    }

    public DMaintenance(DVehicle vehicle, LocalDateTime startTime, String problemDescription, LocalDateTime endTime, Long id) {
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.problemDescription = problemDescription;
        this.endTime = endTime;
        this.id = id;
    }

    public DVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(DVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
