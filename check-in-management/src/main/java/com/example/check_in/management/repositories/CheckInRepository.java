package com.example.check_in.management.repositories;

import com.example.check_in.management.models.CheckIn;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    
    @Query(value = "SELECT * FROM check_in c WHERE c.user_id = :userId AND c.created_at LIKE CONCAT(:createdAt, '%')", nativeQuery = true)
    List<CheckIn> findByUserIdAndDate(@Param("userId") Long userId, @Param("createdAt") String createdAt);

}