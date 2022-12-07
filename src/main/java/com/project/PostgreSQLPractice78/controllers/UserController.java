package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import com.project.PostgreSQLPractice78.repos.AccountInfoRepo;
import com.project.PostgreSQLPractice78.repos.WorkerRepo;
import com.project.PostgreSQLPractice78.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountInfoRepo accountInfoRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private WorkerRepo workerRepo;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping()
    public String userList(Model model){

        model.addAttribute("Accounts", accountService.findAll());

        return "userlist";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/edit/"+"{accountCode}")
    public String userEditForm(@PathVariable String accountCode, Model model){
        AccountInfo account = accountInfoRepo.findByworkerCode(workerRepo.findByWorkerCode(accountCode));

        String roleSelected = account.getRoleID().getRoleName().replace("_role", "");

        model.addAttribute("roleSelected", roleSelected);
        model.addAttribute("account", account);
        return "workerEdit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public  String userSave(
            @RequestParam String username,
            @RequestParam String workerCode,
            @RequestParam String roles,
            @RequestParam("username") AccountInfo account,
            Model model
    ){
        accountService.saveUser(username, workerCode, roles);
        return "redirect:/user";
    }


}
