package com.marcogulino.preventivatore.preventivatore_veicoli.models;

import java.time.Instant;
import java.util.Set;

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
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "The username must not be blank, empty or null")
    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String username;

    @Size(min = 8, message = "The password must have atleast 8 or more characters")
    @NotBlank(message = "The password must not be blank, empty or null")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @NotBlank(message = "The firstname must not be blank, empty or null")
    @Column(name = "firstname", length = 50)
    private String firstName;

    @NotBlank(message = "The lastname must not be blank, empty or null")
    @Column(name = "lastname", length = 50)
    private String lastName;

    @NotBlank(message = "The email must not be blank, empty or null")
    @Email(message = "The email format is invalid")
    @Column(name = "email", length = 255, unique = true)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    // Explanation: ManyToMany join with Role entity
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
