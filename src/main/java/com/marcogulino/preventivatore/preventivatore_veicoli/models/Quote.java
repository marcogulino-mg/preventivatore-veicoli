package com.marcogulino.preventivatore.preventivatore_veicoli.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "vehicle_year_snapshot", nullable = false)
    private Integer vehicleYearSnapshot;

    @NotNull
    @Column(name = "vehicle_engine_cc_snapshot", nullable = false)
    private Integer vehicleEngineCcSnapshot;

    @NotNull
    @Column(name = "vehicle_fuel_type_snapshot", nullable = false)
    private String vehicleFuelTypeSnapshot;

    @NotNull
    @Column(name = "base_price_snapshot", precision = 8, scale = 2, nullable = false)
    private BigDecimal basePriceSnapshot;

    @NotNull
    @Column(name = "optional_total_snapshot", precision = 8, scale = 2, nullable = false)
    private BigDecimal optionalTotalSnapshot;

    @NotNull
    @Column(name = "discount_total_snapshot", precision = 8, scale = 2, nullable = false)
    private BigDecimal discountTotalSnapshot;

    @NotNull
    @Column(name = "final_price", precision = 8, scale = 2, nullable = false)
    private BigDecimal finalPrice;

    @Lob
    @Size(max = 20000, message = "The notes must not be over 20000 characters")
    @Column(name = "description", nullable = true)
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "quote", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<QuoteOptional> optionalDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
