package com.uber.service.entityservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NamedLocation extends BaseEntity{
    @OneToOne
    private ExactLocation exactLocation;

    private String name;
    private String zipCode;
    private String city;
    private String country;
    private String state;
}
