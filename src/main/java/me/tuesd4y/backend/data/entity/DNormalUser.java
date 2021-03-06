package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.Route;
import me.tuesd4y.api.entities.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class DNormalUser implements User {
    @Id @GeneratedValue private Long id;
    private String name;
    private String emailAddress;
    @OneToMany(mappedBy = "user")
    List<DRoute> routes;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DNormalUser() {
    }

    public DNormalUser(Long id, String name, String emailAddress) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
    }


}
