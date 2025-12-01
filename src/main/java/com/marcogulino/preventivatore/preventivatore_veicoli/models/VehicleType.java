package com.marcogulino.preventivatore.preventivatore_veicoli.models;

import java.time.Instant;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehicle_types")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The vehicle type name must not be blank, empty or null")
    @Size(max = 100, message = "The vehicle type name cannot exceed 100 characters")
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @NotBlank(message = "The logo url must not be blank, empty or null")
    @Size(max = 255, message = "The logo URL cannot exceed 255 characters")
    @Column(name = "logo_url", length = 255, nullable = false)
    private String logoUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    // One vehicle type can have many vehicles
    @OneToMany(mappedBy = "vehicleType", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Vehicle> vehicles;
}
