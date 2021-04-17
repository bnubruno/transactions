package com.challenge.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.transactions.entity.OperationType;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}
