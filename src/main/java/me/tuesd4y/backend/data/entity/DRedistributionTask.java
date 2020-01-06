package me.tuesd4y.backend.data.entity;

import me.tuesd4y.api.entities.RedistributionTask;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class DRedistributionTask implements RedistributionTask {
    @ManyToOne
    private DVehicle vehicle;
    @ManyToOne
    private DTransportManager redistributor;
    @ElementCollection
    List<DLocation> locations;
    private LocalDateTime dateTime;
    @Id
    @GeneratedValue
    private Long id;

   public DRedistributionTask() {
   }

   @Override
   public DVehicle getVehicle() {
      return vehicle;
   }

   public void setVehicle(DVehicle vehicle) {
      this.vehicle = vehicle;
   }

   @Override
   public List<DLocation> getLocations() {
      return locations;
   }

   public void setLocations(List<DLocation> locations) {
      this.locations = locations;
   }

   public DTransportManager getRedistributor() {
      return redistributor;
   }

   public void setRedistributor(DTransportManager transportManager) {
      this.redistributor = transportManager;
   }

   @Override
   public LocalDateTime getDateTime() {
      return dateTime;
   }

   public void setDateTime(LocalDateTime dateTime) {
      this.dateTime = dateTime;
   }

   @Override
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
