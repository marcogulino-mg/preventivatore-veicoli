package com.marcogulino.preventivatore.preventivatore_veicoli.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "optionals")
public class OptionalFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The optional name must not be blank or null")
    @Size(max = 100, message = "The optional name cannot exceed 100 characters")
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Size(max = 255, message = "The description cannot exceed 255 characters")
    @Column(name = "description", length = 255)
    private String description;

    @NotNull(message = "The price must not be null")
    @Column(name = "price", precision = 8, scale = 2, nullable = false)
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToMany(mappedBy = "optionals")
    @JsonIgnore
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "optionalFeature", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<QuoteOptional> quoteOptionalEntries;
}
