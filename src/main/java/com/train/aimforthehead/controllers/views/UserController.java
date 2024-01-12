package com.train.aimforthehead.controllers.views;

import com.train.aimforthehead.domain.entities.PurchaseEntity;
import com.train.aimforthehead.domain.entities.UserEntity;
import com.train.aimforthehead.repositories.PurchaseRepository;
import com.train.aimforthehead.repositories.UserRepository;
import com.train.aimforthehead.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.regex.Pattern;

@Controller
@Log
public class UserController {
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(path = "/login")
    public String login(){
        return "login";
    }
    @RequestMapping(path = "/home")
    public String homepage(Model model){

        model.addAttribute("user", userService.getInfo());
        log.info(String.valueOf(userService.getInfo().getId()));
        return "index";
    }
    @GetMapping(path = "/profile")
    public String profile(Model model){
        model.addAttribute("user", userService.getInfo());
        return "profile";
    }
    @PostMapping(path = "/login")
    public String logout(Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        this.logoutHandler.logout(httpServletRequest, httpServletResponse, authentication);
        return "login";
    }
    @GetMapping(path = "/registry")
    public String registryPage(){
        return "registry";
    }
    @PostMapping(path = "/registry")
    public String createAccount(@ModelAttribute("username_") String username,
                                @ModelAttribute("email_") String email,
                                @ModelAttribute("password_") String password1,
                                @ModelAttribute("password2_") String password2,
                                Model model){
        Pattern passwordPattern = Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})$");
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_]{6,12}$");
        if(usernamePattern.matcher(username).find()){
            if(userRepository.findByusername(username).isPresent()){
                model.addAttribute("message", "Account existed");
            }
            else {
                if(userRepository.findByemail(email).isPresent()){
                    model.addAttribute("message", "Your email has been used");
                }
                else {
                    if(passwordPattern.matcher(password1).find()){
                        if(password2.equals(password1)){
                            UserEntity userEntity = UserEntity.builder()
                                    .username(username)
                                    .password(passwordEncoder.encode(password1))
                                    .email(email)
                                    .role("USER")
                                    .pfp("https://www.shutterstock.com/image-vector/vector-flat-illustration-grayscale-avatar-600nw-2264922221.jpg")
                                    .build();
                            userRepository.save(userEntity);
                            model.addAttribute("messageS", "Success");
                        }
                        else{
                            model.addAttribute("message", "Passwords not match");
                        }
                    }
                    else {
                        model.addAttribute("message", "Wrong password format");
                    }
                }
            }
        }
        else {
            model.addAttribute("message", "Wrong username format");
        }
        return "registry";
    }
    @GetMapping(path = "/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminPage(Model model){
        model.addAttribute("user", userService.getInfo());
        return "admin";
    }
    @GetMapping(path = "/admin/user")
    public String getAllUser(Model model){
        model.addAttribute("user", userService.getInfo());
        List<UserEntity> userEntities = userRepository.findAll();
        model.addAttribute("users", userEntities);
        return "allUser";
    }
    @GetMapping(path = "/admin/purchase")
    public String getAllPurchase(Model model){
        model.addAttribute("user", userService.getInfo());
        List<PurchaseEntity> purchaseEntities = purchaseRepository.findAll();
        model.addAttribute("purchases", purchaseEntities);
        return "allPurchases";
    }
    @GetMapping(path = "/recovery")
    public String recoveryPage(){
        return "recovery";
    }
    @PostMapping(path = "/recovery")
    public String rememberPassword(@ModelAttribute("username_") String username,
                                @ModelAttribute("email_") String email,
                                @ModelAttribute("password_") String password1,
                                @ModelAttribute("password2_") String password2,
                                Model model) {
        Pattern passwordPattern = Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})$");
        if(userRepository.findByusername(username).isPresent()){
            if(userRepository.findByusername(username).get().getEmail().equals(email)){
                if(passwordPattern.matcher(password1).find()){
                    if(password1.equals(password2)){
                        if(passwordEncoder.matches(password1, userRepository.findByusername(username).get().getPassword())){
                            model.addAttribute("message", "Your new password can be the same as old password");
                        }
                        else {
                            model.addAttribute("messageS", "Success");
                            UserEntity userEntity = userRepository.findByusername(username).get();
                            userEntity.setPassword(passwordEncoder.encode(password1));
                            userRepository.save(userEntity);
                        }
                    }
                    else {
                        model.addAttribute("message", "Passwords not match");
                    }
                }
                else {
                    model.addAttribute("message", "Wrong password format");
                }
            }
            else {
                model.addAttribute("message", "Wrong email");
            }
        }
        else {
            model.addAttribute("message", "Wrong username");
        }
        return "recovery";
    }
}
