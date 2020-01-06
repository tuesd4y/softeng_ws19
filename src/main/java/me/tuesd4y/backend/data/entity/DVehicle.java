package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.Vehicle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class DVehicle implements Vehicle {
    private String type;
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "vehicle")
    private List<DRental> rentals;
    @OneToMany(mappedBy = "vehicle")
    private List<DMaintenance> maintenances;
    @OneToMany(mappedBy = "vehicle")
    private List<DPosition> positions;

    public DVehicle() {
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DRental> getRentals() {
        return rentals;
    }

    public void setRentals(List<DRental> rentals) {
        this.rentals = rentals;
    }

    public List<DMaintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<DMaintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public List<DPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<DPosition> positions) {
        this.positions = positions;
    }
}
