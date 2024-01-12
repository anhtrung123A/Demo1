package com.train.aimforthehead.repositories;

import com.train.aimforthehead.domain.entities.CategoryDetail;
import com.train.aimforthehead.domain.entities.CategoryDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, CategoryDetailID> {
    List<CategoryDetail> findAllBybook_id(int id);
    List<CategoryDetail> findAllBycategory_id(int id);
}
