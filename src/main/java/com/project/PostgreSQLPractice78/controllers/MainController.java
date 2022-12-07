package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.repos.AccountInfoRepo;
import com.project.PostgreSQLPractice78.repos.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @Autowired
    private AccountInfoRepo accountInfoRepo;

    @Autowired
    private ContractRepo contractRepo;

    @GetMapping("/main")
    public String main(Model model) {

        return "main";
    }

}