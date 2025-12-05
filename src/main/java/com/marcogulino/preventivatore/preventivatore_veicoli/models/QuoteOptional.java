package com.marcogulino.preventivatore.preventivatore_veicoli.models;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quote_optional")
public class QuoteOptional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "price_at_time", precision = 8, scale = 2, nullable = false)
    private BigDecimal priceAtTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", nullable = false)
    @JsonBackReference
    private Quote quote;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "optional_id", nullable = false)
    private OptionalFeature optionalFeature;
}