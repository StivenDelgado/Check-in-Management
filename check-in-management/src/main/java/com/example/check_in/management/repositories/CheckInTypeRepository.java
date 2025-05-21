package com.example.check_in.management.repositories;

import com.example.check_in.management.models.CheckInType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInTypeRepository extends JpaRepository<CheckInType, Long> {

}