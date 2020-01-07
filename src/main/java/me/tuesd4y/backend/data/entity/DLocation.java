package me.tuesd4y.backend.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.tuesd4y.api.entities.Location;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;

import javax.persistence.*;

@Embeddable
public class DLocation implements Location {
    private double latitude;
    private double longitude;

    @JsonIgnore
    @Column(columnDefinition = "geometry")
    private Point<G2D> locationPoint;

    public DLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public DLocation() {
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @PrePersist
    @PreUpdate
    public void beforeSave() {
        this.locationPoint = Geometries.mkPoint(new G2D(longitude, latitude), CoordinateReferenceSystems.WGS84);
    }
}
