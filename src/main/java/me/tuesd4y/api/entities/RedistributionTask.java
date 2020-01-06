package me.tuesd4y.api.entities;

import me.tuesd4y.backend.data.entity.DLocation;

import java.time.LocalDateTime;
import java.util.List;

public interface RedistributionTask {
    Vehicle getVehicle();
    TransportManager getRedistributor();
    List<DLocation> getLocations();
    LocalDateTime getDateTime();
    Long getId();
}
