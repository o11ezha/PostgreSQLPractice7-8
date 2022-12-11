package com.project.PostgreSQLPractice78.controllers;

import com.project.PostgreSQLPractice78.repos.TaskRepo;
import com.project.PostgreSQLPractice78.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CSVController {

    @Autowired
    WorkerService workerService;

    @Autowired
    TaskRepo taskRepo;


    @GetMapping("/CSV")
    public String createCSV(Model model){

        model.addAttribute("workers", workerService.findAll());

        return "CSV";
    }

    @PostMapping("/CSV")
    public String createCSV(@RequestParam (required=false) String path,
                            @RequestParam (required=false) String workerCode,
                            @RequestParam (required=false) String fromDate,
                            @RequestParam (required=false) String toDate,
                            String errorDate,
                            String errorFromDate,
                            String errorToDate,
                            String errorWorkerCode,
                            String errorUserComp,
                            Model model) throws ParseException {

        model.addAttribute("workers", workerService.findAll());

        if (workerCode == null){
            errorWorkerCode = "Выберите работника";
            model.addAttribute("errorWorkerCode",errorWorkerCode);
            return "CSV";
        }

        model.addAttribute("workerCode", workerCode);

        if (fromDate.isEmpty()) {
            errorFromDate = "Введите дату 'До'";
            model.addAttribute("errorFromDate",errorFromDate);
            return "CSV";
        }

        if (toDate.isEmpty()) {
            errorToDate = "Введите дату 'После'";
            model.addAttribute("errorToDate",errorToDate);
            return "CSV";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date fromDateDate = format.parse(fromDate);
        Date toDateDate = format.parse(toDate);

        if ((fromDate != null) && (toDate != null) && (toDateDate.before(fromDateDate))) {
            errorDate = "Дата 'до' не может быть до даты 'с'!";
            model.addAttribute("errorDate",errorDate);
            return "CSV";
        }

        if ((path.isEmpty()) || (path == null)) {
            errorUserComp = "Введите путь";
            model.addAttribute("errorUserComp", errorUserComp);
            return "CSV";
        } else {
            File file = new File(path);
            if (!file.isDirectory()) {
                errorUserComp = "Введите путь корректно";
                return "CSV";
            } else  taskRepo.createCSVPROC(workerCode, fromDateDate, toDateDate, path,null );
        }
        errorWorkerCode = "";
        errorDate = "";
        errorUserComp = "";
        errorFromDate = "";
        errorToDate = "";




        return "redirect:/main";
    }


}
