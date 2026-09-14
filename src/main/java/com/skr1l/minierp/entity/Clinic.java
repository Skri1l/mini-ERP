package com.skr1l.minierp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clinic")
@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String clinicName;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String address;
}
