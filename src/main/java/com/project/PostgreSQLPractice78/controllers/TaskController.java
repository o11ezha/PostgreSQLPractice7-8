package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.DTO.TaskDTO;
import com.project.PostgreSQLPractice78.mainEntities.Task;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.services.ContractService;
import com.project.PostgreSQLPractice78.services.TaskService;
import com.project.PostgreSQLPractice78.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    ContractService contractService;

    @Autowired
    WorkerService workerService;

    @Autowired
    TaskService taskService;

    @GetMapping("/taskslist")
    public String taskList(Worker worker, Model model){

//        List<Task> tasks =  taskService.findAll();
//
//        for (Task task: tasks) {
//            task.getContractCode().getContractCode();
//
//        }

        model.addAttribute("Tasks", taskService.findAll());
        model.addAttribute("worker", workerService);

        return "listOfTasks.html";
    }


    @GetMapping("/addingTask")
    public String createTask(@ModelAttribute("taskDTO") TaskDTO taskDTO, Model model) {

        model.addAttribute("contracts", contractService.findAll());
        model.addAttribute("TaskDTO", taskDTO);
        model.addAttribute("workers", workerService.findAll());
        return "addingTask";
    }

    @PostMapping("/addingTask")
    public String createTask(@ModelAttribute("taskDTO") @Valid TaskDTO taskDTO,
                             String errorTask,
                             BindingResult bindingResult,
                             Principal principal, Model model) throws ParseException {

        List<Integer> listOfCodes = taskService.findAllTaskCodes();

        if (bindingResult.hasErrors() || listOfCodes.contains(taskDTO.getTaskCode())) {
            errorTask = "Введите уникальное значение кода задания";
            model.addAttribute("errorTask", errorTask);
            return "addingTask";
        }
        errorTask = "";

        taskService.createTask(taskDTO, principal.getName());

        return "main";
    }
}
