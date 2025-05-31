package com.example.check_in.management.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "check_in")
public class CheckIn {
    
    /**
     * Unique identifier for the check-in record.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_in_id")
    private Long checkInId;
    
    /**
     * Identifier of the user who made the check-in.
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Type of check-in (e.g., entry, exit, lunch break).
     * Many check-ins can be associated with one check-in type.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_in_type_id")
    private CheckInType checkInType;            

    /**
     * Start time of the check-in period.
     */
    @Column(name = "time", columnDefinition = "TIME")
    private String time;
    /**
     * Day of the week when the check-in occurred.
     */
    @Column(name = "week_day")
    private String weekDay;

    /**
     * Timestamp when the check-in record was created.
     * Automatically set to current timestamp on creation.
     */
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /**
     * Timestamp when the check-in record was last updated.
     * Automatically updated when the record is modified.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

}
