package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.domain.entities.PurchaseDetailID;
import com.train.aimforthehead.repositories.PurchaseDetailRepository;
import com.train.aimforthehead.services.PurchaseDetailService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
    private PurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetailServiceImpl(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

}
