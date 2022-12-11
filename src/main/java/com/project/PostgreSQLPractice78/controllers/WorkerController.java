package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.DTO.WorkerDTO;
import com.project.PostgreSQLPractice78.mainEntities.AccountInfo;
import com.project.PostgreSQLPractice78.services.WorkerRoleService;
import com.project.PostgreSQLPractice78.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@PreAuthorize("hasAnyAuthority('manager', 'admin')" )
@RequestMapping("/manageWorkers")
@Controller
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @Autowired
    WorkerRoleService workerRoleService;

    @GetMapping()
    public String manageWorkers(Model model){

        model.addAttribute("Workers", workerService.findAll());

        return "workerlist";
    }

    @GetMapping("/addingWorker")
    public String createWorker(@ModelAttribute("workerDTO") WorkerDTO workerDTO,  Model model) {

        model.addAttribute("rolesList", workerRoleService.findAllRoles());
        model.addAttribute("WorkerDTO", workerDTO);
        return "addingWorker";
    }

    @PostMapping("/addingWorker")
    public String createWorker(@ModelAttribute("workerDTO") @Valid WorkerDTO workerDTO,
                               BindingResult bindingResult,
                               String errorCode,
                               String errorExMail,
                               String errorMail,
                               Principal principal, Model model) throws ParseException {

        model.addAttribute("workerDTO", workerDTO);
        model.addAttribute("rolesList", workerRoleService.findAllRoles());

        List<String> listOfCodes = workerService.findAllWorkerCodes();
        List<String> listOfEmails = workerService.findAllEmails();


        String email = workerDTO.getWorkerEmail();
        String subemail = email.substring(email.indexOf("@") + 1);

        if (bindingResult.hasErrors() ){
            return "addingWorker";
        }
        if (listOfCodes.contains(workerDTO.getWorkerCode())) {
            errorCode = "Введите уникальное значение кода работника";
            model.addAttribute("errorCode", errorCode);
            return "addingWorker";
        }
        if (listOfEmails.contains(workerDTO.getWorkerEmail())) {
            errorExMail = "Такая почта уже существует";
            model.addAttribute("errorExMail", errorExMail);
            return "addingWorker";
        }
        if (!subemail.contains(".")){
            errorMail = "Введите домен корректно";
            model.addAttribute("errorMail", errorMail);
            return "addingWorker";
        }

        errorCode = "";
        errorExMail = "";
        errorMail = "";

        workerService.createWorker(workerDTO);

        return "redirect:/manageWorkers";
    }

}
