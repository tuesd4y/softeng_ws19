package me.tuesd4y.backend.data.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import me.tuesd4y.api.entities.Route;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Table(name = "route_geojson")
@TypeDef(
        name = "jsonb-node",
        typeClass = JsonNodeBinaryType.class
)
@Entity
public class DRoute implements Route {
    @Type(type = "jsonb-node")
    @Column(columnDefinition = "jsonb")
    private JsonNode route;
    @Id @GeneratedValue
    private Long id;
    @ManyToOne private DNormalUser user;

    public DRoute() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public JsonNode getRoute() {
        return route;
    }

    public void setRoute(JsonNode route) {
        this.route = route;
    }

    @Override
    public DNormalUser getUser() {
        return user;
    }

    public void setUser(DNormalUser user) {
        this.user = user;
    }
}
