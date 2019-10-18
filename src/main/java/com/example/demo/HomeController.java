package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("transactions", transactionRepository.findAll());
        return "index";
    }
    @GetMapping("/userform")
    public String userForm(Model model){
        model.addAttribute("users", new User());
        return "userform";
    }
    @GetMapping("/accountform")
    public String accountForm(Model model){
        model.addAttribute("account", new Account());
        model.addAttribute("user", userRepository.findAll());
        return "accountform";
    }
    @GetMapping("/transactionform")
    public String transactionform(Model model){
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("accounts", accountRepository.findAll());
        return "transactionform";
    }

    //    mapping to create user
    @PostMapping("/processuser")
    public String processUser(@Valid User users,
                             BindingResult result){
        if(result.hasErrors()){
            return "userform";
        }
        userRepository.save(users);
        return "redirect:/";

    }
    @PostMapping("/accountform")
    public String processAccount(@Valid Account account,
                                BindingResult result){
        if(result.hasErrors()){
            return "accountform";
        }
        accountRepository.save(account);
        return "redirect:/";
    }
    @PostMapping("/createtransaction")
    public String createTransaction(@Valid Transaction transaction,
                                    BindingResult result){
        if(result.hasErrors()){
            return "transactionform";
        }
        transactionRepository.save(transaction);
        return "redirect:/";
    }




//    mapping to get user object by email and check if password matches input
//    if password does match you should return the add transaction html

//



}
