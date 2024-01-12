package com.train.aimforthehead.controllers.views;

import com.train.aimforthehead.domain.entities.*;
import com.train.aimforthehead.repositories.BookRepository;
import com.train.aimforthehead.repositories.PurchaseDetailRepository;
import com.train.aimforthehead.repositories.PurchaseRepository;
import com.train.aimforthehead.services.BookService;
import com.train.aimforthehead.services.CategoryDetailService;
import com.train.aimforthehead.services.PurchaseDetailService;
import com.train.aimforthehead.services.impl.UserServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Log
@Controller
public class PurchaseDetailViewController {
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookViewController bookViewController;
    @GetMapping(path = "/cart")
    public String viewCart(Model model){
        model.addAttribute("user", userService.getInfo());
        List<PurchaseDetail> purchaseDetailList = purchaseDetailRepository.findAllBypurchase_userEntity_id(userService.getInfo().getId());
        List<PurchaseDetail> purchaseDetails = purchaseDetailList.stream().filter(pd->pd.getPurchase().getStatus().equals("waiting")).toList();
        model.addAttribute("purchaseDetails", purchaseDetails);
        double total = 0;
        for(PurchaseDetail pd : purchaseDetails){
            total+=(pd.getBook().getPrice())*pd.getQuantity();
        }
        log.info(String.valueOf(total));
        model.addAttribute("size", purchaseDetails.size());
        model.addAttribute("price", total);
        return "cart";
    }
    @PostMapping(path = "/cart")
    public String delete(@ModelAttribute("book_") int book_id, @ModelAttribute("purchase_") int purchase_id, Model model){
        PurchaseDetailID purchaseDetailID = new PurchaseDetailID();
        Optional<BookEntity> bookEntity = bookRepository.findById(book_id);
        Optional<PurchaseEntity> purchaseEntity = purchaseRepository.findById(purchase_id);
        if(bookEntity.isPresent() && purchaseEntity.isPresent()){
            purchaseDetailID.setBook(bookEntity.get());
            purchaseDetailID.setPurchase(purchaseEntity.get());
        }
        purchaseDetailRepository.deleteById(purchaseDetailID);
        model.addAttribute("user", userService.getInfo());
        List<PurchaseDetail> purchaseDetailList = purchaseDetailRepository.findAllBypurchase_userEntity_id(userService.getInfo().getId());
        model.addAttribute("purchaseDetails", purchaseDetailList);
        double total = 0;
        for(PurchaseDetail pd : purchaseDetailList){
            total+=(pd.getBook().getPrice())*pd.getQuantity();
        }
        log.info(String.valueOf(total));
        model.addAttribute("size", purchaseDetailList.size());
        model.addAttribute("price", total);
        return "cart";
    }
    @RequestMapping(path = "/buy/{book_id}")
    public String buySomething(@PathVariable("book_id") int book_id, Model model, @ModelAttribute("quantity_") int quantity){
        model.addAttribute("user", userService.getInfo());
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        List<PurchaseEntity> purchaseEntities = purchaseRepository.findAllByuserEntity_id(userService.getInfo().getId());
        List<PurchaseEntity> purchaseEntityList = purchaseEntities.stream().filter(purchaseEntity -> purchaseEntity.getStatus().equals("waiting")).toList();
        if(purchaseEntityList.isEmpty())
        {
            PurchaseEntity purchaseEntity = PurchaseEntity.builder()
                    .userEntity(userService.getInfo())
                    .date(Date.valueOf(LocalDate.now()))
                    .status("waiting")
                    .build();
            purchaseRepository.save(purchaseEntity);
            purchaseDetail.setPurchase(purchaseEntity);
        }
        else {
            purchaseDetail.setPurchase(purchaseEntityList.get(0));
        }
        if(bookRepository.findById(book_id).isPresent()){
            purchaseDetail.setBook(bookRepository.findById(book_id).get());
        }
        purchaseDetail.setQuantity(quantity);
        purchaseDetailRepository.save(purchaseDetail);
        return bookViewController.viewBookDetail(book_id, model);
    }
    @RequestMapping(path = "/checkout")
    public String checkOut(@ModelAttribute("updateEntity") int p_id, Model model){
        model.addAttribute("user", userService.getInfo());
        if(purchaseRepository.findById(p_id).isPresent()){
            PurchaseEntity purchaseEntityToUpdate = purchaseRepository.findById(p_id).get();
            List<PurchaseDetail> purchaseDetailList = purchaseDetailRepository.findAllBypurchase_id(p_id);
            double total = 0;
            for (PurchaseDetail pd: purchaseDetailList){
                total += (pd.getBook().getPrice())*pd.getQuantity();
            }
            model.addAttribute("total", total);
            purchaseEntityToUpdate.setStatus("proceed");
            purchaseRepository.save(purchaseEntityToUpdate);
            model.addAttribute("purchase", purchaseEntityToUpdate);
        }
        return "checkout";
    }
}
