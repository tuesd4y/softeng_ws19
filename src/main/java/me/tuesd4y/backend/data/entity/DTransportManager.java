package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.TransportManager;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DTransportManager implements TransportManager {
    private String name;
    private String emailAddress;
    @Embedded private DLocation homeBase;
    @Id @GeneratedValue
    private Long id;

    public DTransportManager() {
    }

    public DLocation getHomeBase() {
        return homeBase;
    }

    public void setHomeBase(DLocation homebase) {
        this.homeBase = homebase;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
