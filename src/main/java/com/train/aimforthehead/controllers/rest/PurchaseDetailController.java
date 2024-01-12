package com.train.aimforthehead.controllers.rest;

import com.train.aimforthehead.domain.dto.PurchaseDetailDto;
import com.train.aimforthehead.domain.entities.PurchaseDetail;
import com.train.aimforthehead.domain.entities.PurchaseDetailID;
import com.train.aimforthehead.domain.entities.PurchaseEntity;
import com.train.aimforthehead.mappers.Mapper;
import com.train.aimforthehead.repositories.PurchaseDetailRepository;
import com.train.aimforthehead.repositories.PurchaseRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @GetMapping(path = "/user/{id}/purchase")
    public List<PurchaseDetail> getPurchaseDetailFromUserId(@PathVariable("id") int id){
        List<PurchaseDetail> purchaseDetailList = purchaseDetailRepository.findAllBypurchase_userEntity_id(id);
        return purchaseDetailList.stream().filter(purchaseDetail -> purchaseDetail.getPurchase().getStatus().equals("waiting")).toList();
    }
    @GetMapping(path = "/purchase/{id}/count")
    @CrossOrigin
    public PurchaseInfo getPurchaseTotalItem(@PathVariable("id") int id)
    {
        List<PurchaseDetail> purchaseDetailList = purchaseDetailRepository.findAllBypurchase_id(id);
        double price = 0;
        for(PurchaseDetail purchaseDetail: purchaseDetailList){
            price += purchaseDetail.getQuantity()*purchaseDetail.getBook().getPrice();
        }
        PurchaseInfo purchaseInfo = PurchaseInfo.builder()
                .count(purchaseDetailList.size())
                .p_id(id)
                .total(price)
                .build();
        return purchaseInfo;
    }
}
