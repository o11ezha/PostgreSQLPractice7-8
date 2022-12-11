package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAnyAuthority('manager', 'admin')")
public class ContactsController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public String clientList(Model model){

        model.addAttribute("clients", clientService.findAllClients());
        return "listOfContacts";
    }
}
