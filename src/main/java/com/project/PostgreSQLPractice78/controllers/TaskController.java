package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.DTO.TaskDTO;
import com.project.PostgreSQLPractice78.mainEntities.Task;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.repos.AccountInfoRepo;
import com.project.PostgreSQLPractice78.services.AccountService;
import com.project.PostgreSQLPractice78.services.ContractService;
import com.project.PostgreSQLPractice78.services.TaskService;
import com.project.PostgreSQLPractice78.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    ContractService contractService;

    @Autowired
    WorkerService workerService;

    @Autowired
    AccountService accountService;

    @Autowired
    TaskService taskService;

    @PreAuthorize("hasAnyAuthority('manager', 'admin')" )
    @GetMapping("/manageTasks")
    public String manageTasks(Principal principal,  Model model){

        Worker authorCode = accountService.findBylogin(principal.getName()).getWorkerCode();

        model.addAttribute("Tasks", taskService.findAllByAuthorCode(authorCode));
        model.addAttribute("worker", workerService);

        return "listOfTasks";
    }

    @GetMapping("/toDo")
    public String toDo(Principal principal,  Model model){

        String authorCode = accountService.findBylogin(principal.getName()).getWorkerCode().getWorkerCode();

        model.addAttribute("Tasks", taskService.findAllByWorkerCode(authorCode));
        model.addAttribute("worker", workerService);
        model.addAttribute("taskService", taskService);

        return "listToDo";
    }

    @GetMapping("/toDo/{taskCode}")
    public String finishTask(@PathVariable Integer taskCode){
        Task finishedTask = taskService.findByTaskCode(taskCode);
        taskService.finishTask(finishedTask);
        return "redirect:/toDo";
    }


    @PreAuthorize("hasAnyAuthority('manager', 'admin')" )
    @GetMapping("/manageTasks/addingTask")
    public String createTask(@ModelAttribute("taskDTO") TaskDTO taskDTO, Model model) {

        model.addAttribute("contracts", contractService.findAll());
        model.addAttribute("TaskDTO", taskDTO);
        model.addAttribute("workers", workerService.findAll());
        return "addingTask";
    }

    @PreAuthorize("hasAnyAuthority('manager', 'admin')" )
    @PostMapping("/manageTasks/addingTask")
    public String createTask(@ModelAttribute("taskDTO") @Valid TaskDTO taskDTO,
                             BindingResult bindingResult,
                             String errorTask,
                             String errorDate,
                             Principal principal, Model model) throws ParseException {

        model.addAttribute("contracts", contractService.findAll());
        model.addAttribute("TaskDTO", taskDTO);
        model.addAttribute("workers", workerService.findAll());

        List<Integer> listOfCodes = taskService.findAllTaskCodes();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        Date nowDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date expDate = format.parse(taskDTO.getExpirationDate());


        if (bindingResult.hasErrors()){
            return "addingTask";
        }

        if (listOfCodes.contains(taskDTO.getTaskCode())) {
            errorTask = "Введите уникальное значение кода задания";
            model.addAttribute("errorTask", errorTask);
            return "addingTask";
        }

        if (expDate.before(nowDate)) {
            errorDate = "Введите дату не раньше сегодняшней";
            model.addAttribute("errorDate", errorDate);
            return "addingTask";
        }
        errorTask = "";
        errorDate = "";

        taskService.createTask(taskDTO, principal.getName());

        return "redirect:/manageTasks";
    }


    @PreAuthorize("hasAnyAuthority('manager', 'admin')" )
    @GetMapping("/manageTasks/editTask/{taskCode}")
    public String editTask(@PathVariable Integer taskCode, Principal principal, Model model) throws ParseException {
        IdenticalPart( taskCode, principal, model);

        if (taskService.findByTaskCode(taskCode).getExpirationDate() != null) {
            model.addAttribute("expDate", taskService.findByTaskCode(taskCode).getExpirationDate());
        }
        return "editTask";
    }

    @PreAuthorize("hasAnyAuthority('manager', 'admin')" )
    @PostMapping("/manageTasks/editTask/{taskCode}")
    public String editTask(@PathVariable Integer taskCode,
                             @ModelAttribute("taskDTO") @Valid TaskDTO taskDTO,
                             String errorDate,
                             BindingResult bindingResult,
                             Principal principal, Model model) throws ParseException {

        IdenticalPart(taskCode, principal, model);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        Date nowDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date expDate = format.parse(taskDTO.getExpirationDate());

        if (expDate.before(nowDate)) {
            errorDate = "Введите дату не раньше сегодняшней";
            model.addAttribute("errorDate", errorDate);
            return "editTask";
        }
        errorDate = "";

        taskService.editTask(taskDTO, taskCode);
        return "redirect:/manageTasks";
    }


    public void IdenticalPart(Integer taskCode, Principal principal, Model model){
        model.addAttribute("taskCode", taskCode);
        model.addAttribute("account", accountService.findBylogin(principal.getName()));
        model.addAttribute("taskDTO", taskService.convertTaskToDTO(taskCode));
        model.addAttribute("workers", workerService.findAll());
        model.addAttribute("authors", workerService.findAllAuthors());
        model.addAttribute("contracts", contractService.findAll());
    }
}
