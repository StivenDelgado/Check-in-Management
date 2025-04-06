package com.example.check_in.management.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "check_in_type")
public class CheckInType {
    /**
     * Unique identifier for the check-in type.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_in_type_id")
    private Long checkInTypeId;    

    /**
     * Description of the check-in type (e.g., 'Entry', 'Exit', 'Lunch Break').
     */
    @Column(name = "description")
    private String description;

    /**
     * List of all check-ins associated with this type.
     * One check-in type can have many check-in records.
     */
    @OneToMany(mappedBy = "checkInType")
    private List<CheckIn> checkIns;
    
    /**
     * Timestamp when the check-in type was created.
     * Automatically set to current timestamp on creation.
     */
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /**
     * Timestamp when the check-in type was last updated.
     * Automatically updated when the record is modified.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
