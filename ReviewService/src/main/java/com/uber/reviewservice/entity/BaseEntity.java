package com.uber.reviewservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass   // this annotation will let springboot know that we don't want separate table for BaseEntity class but the properties of BaseEntity will be included in the child classes
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {  // keeping Parent as abstract because we dont want anyone to create any object of this class

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
