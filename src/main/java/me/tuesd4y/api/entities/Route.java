package me.tuesd4y.api.entities;

import me.tuesd4y.backend.data.entity.DLocation;

import java.util.List;

public interface Route {
    List<DLocation> getLocations();

    Long getId();
}
