package com.marcogulino.preventivatore.preventivatore_veicoli.models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The firstname must not be blank, empty or null")
    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @NotBlank(message = "The lastname must not be blank, empty or null")
    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @NotBlank(message = "The email must not be blank, empty or null")
    @Email(message = "The email format is invalid")
    @Column(name = "email", length = 255, unique = true, nullable = false)
    private String email;

    @NotBlank(message = "The phone number must not be blank, empty or null")
    @Size(min = 7, max = 30, message = "The phone number must be between 7 and 30 characters")
    @Column(name = "phone", length = 30, nullable = false)
    private String phoneNumber;

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
}
