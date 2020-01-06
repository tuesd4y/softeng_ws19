package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.Route;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class DRoute implements Route {
    @ElementCollection
    private List<DLocation> locations;
    @Id @GeneratedValue
    private Long id;

    public DRoute() {
    }

    @Override
    public List<DLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<DLocation> locations) {
        this.locations = locations;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
