package com.train.aimforthehead.repositories;

import com.train.aimforthehead.domain.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
    Optional<PurchaseEntity> findByuserEntity_id(int id);
    List<PurchaseEntity> findAllByuserEntity_id(int id);
}
