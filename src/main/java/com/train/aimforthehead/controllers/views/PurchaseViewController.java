package com.train.aimforthehead.controllers.views;

import com.train.aimforthehead.domain.entities.CustomerEntity;
import com.train.aimforthehead.domain.entities.PurchaseEntity;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log
public class PurchaseViewController {
    @GetMapping(path = "/purchase")
    public String viewPurchase(Model model)
    {
        return "purchase";
    }
    @PostMapping(path = "/purchase")
    public String createPurchase(@ModelAttribute("customer") CustomerEntity customer, Model model)
    {
        log.info(customer.toString());
        return "purchase";
    }
}
