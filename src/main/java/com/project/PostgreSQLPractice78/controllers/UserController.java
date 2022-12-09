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

import javax.transaction.Transactional;

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
    @GetMapping("/edit/{accountCode}")
    public String userEditForm(@PathVariable String accountCode, Model model){
        AccountInfo account = accountInfoRepo.findByworkerCode(workerRepo.findByWorkerCode(accountCode));

        String roleSelected = account.getRoleID().getRoleName().replace("_role", "");

        model.addAttribute("roleSelected", roleSelected);
        model.addAttribute("account", account);
        return "workerEdit";
    }

    @Transactional
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/edit/{accountCode}")
    public  String userSave(
            @RequestParam String username,
            @PathVariable @RequestParam(required=false) String accountCode,
            @RequestParam String roles,
            @RequestParam String password){
        System.out.println(accountCode);

        accountService.saveUser(username, accountCode, roles, password);
        return "redirect:/user";
    }


}
