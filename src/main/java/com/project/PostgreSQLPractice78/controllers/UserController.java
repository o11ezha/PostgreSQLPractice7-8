package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.mainEntities.WorkerRole;
import com.project.PostgreSQLPractice78.repos.AccountInfoRepo;
import com.project.PostgreSQLPractice78.repos.WorkerRepo;
import com.project.PostgreSQLPractice78.services.AccountService;
import com.project.PostgreSQLPractice78.services.WorkerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AccountInfoRepo accountInfoRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private WorkerRepo workerRepo;

    @Autowired
    private WorkerRoleService workerRoleService;

    @GetMapping()
    public String userList(Model model){

        model.addAttribute("Accounts", accountService.findAll());

        return "userlist";
    }

    @GetMapping("/new")
    public String addingAccount(Model model){
        model.addAttribute("rolesList", workerRoleService.findAllRoles());
        return "addingAccount";
    }

    @Transactional
    @PostMapping("/new")
    public String addingAccount(
            @RequestParam String username,
            @RequestParam(required=false) String accountCode,
            @RequestParam String password,
            String errorWorkerCode,
            String errorUserName,
            Model model){
        model.addAttribute("rolesList", workerRoleService.findAllRoles());

        AccountInfo account = new AccountInfo();
        Worker worker = new Worker();
        List<String> workerCodes =  workerRepo.findAllWorkerCodes();

        if (!workerCodes.contains(accountCode)){
            errorWorkerCode = "Такого кода работника не найдено";
            model.addAttribute("errorWorkerCode", errorWorkerCode);
            return "addingAccount";
        }
        if (accountService.findAllLogins().contains(username)){
            errorUserName = "Такой логин уже сущсетсует: выберите другой";
            model.addAttribute("errorUserName", errorUserName);
            return "addingAccount";
        }

        worker.setWorkerCode(accountCode);

        account.setWorkerCode(worker);
        account.setLogin(username);
        account.setHashPassword(password);

        errorWorkerCode = "";
        errorUserName = "";

        accountService.createAccount(account);

        return "redirect:/users";
    }

    @GetMapping("/edit/{accountCode}")
    public String userEditForm(@PathVariable String accountCode, Model model){
        AccountInfo account = accountInfoRepo.findByworkerCode(workerRepo.findByWorkerCode(accountCode));

        String roleSelected = account.getRoleID().getRoleName().replace("_role", "");

        model.addAttribute("roleSelected", roleSelected);
        model.addAttribute("account", account);
        return "workerEdit";
    }

    @Transactional
    @PostMapping("/edit/{accountCode}")
    public  String userSave(
            @RequestParam String username,
            @PathVariable @RequestParam(required=false) String accountCode,
            @RequestParam String roles,
            @RequestParam String password){



        accountService.saveUser(username, accountCode, roles, password);
        return "redirect:/users";
    }
}
