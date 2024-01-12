package com.train.aimforthehead.repositories;

import com.train.aimforthehead.domain.entities.PurchaseDetail;
import com.train.aimforthehead.domain.entities.PurchaseDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, PurchaseDetailID> {
    List<PurchaseDetail> findAllBypurchase_id(int id);
    List<PurchaseDetail> findAllBypurchase_userEntity_id(int id);
}
