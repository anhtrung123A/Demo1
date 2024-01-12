package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.repositories.PurchaseRepository;
import com.train.aimforthehead.services.PurchaseService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
}
