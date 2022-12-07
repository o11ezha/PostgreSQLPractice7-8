package com.project.PostgreSQLPractice78.services;

import com.project.PostgreSQLPractice78.DTO.TaskDTO;
import com.project.PostgreSQLPractice78.mainEntities.Task;
import com.project.PostgreSQLPractice78.mainEntities.Worker;
import com.project.PostgreSQLPractice78.repos.AccountInfoRepo;
import com.project.PostgreSQLPractice78.repos.ContractRepo;
import com.project.PostgreSQLPractice78.repos.TaskRepo;
import com.project.PostgreSQLPractice78.repos.WorkerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    ContractRepo contractRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    AccountInfoRepo accountInfoRepo;

    private Task convertDTOToTask(TaskDTO taskDTO, String login) throws ParseException {
        ModelMapper model = new ModelMapper();

        model.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        if (taskDTO.getExpirationDate().isEmpty())
            taskDTO.setExpirationDate("1111-01-01");

        Task task = model.map(taskDTO, Task.class);

        if (!(taskDTO.getContractCode() == null))
            task.setContractCode(contractRepo
                    .getByContractCode(taskDTO
                    .getContractCode()));
        else task.setContractCode(null);

        Worker worker = workerRepo.findByWorkerCode(accountInfoRepo.findBylogin(login)
                .getWorkerCode().getWorkerCode());
        task.setAuthorCode(worker);

        if (taskDTO.getWorkerCode().length() == 0)
            task.setWorkerCode(null);
        else task.setWorkerCode(workerRepo
                .findByWorkerCode(taskDTO
                        .getWorkerCode())
                .getWorkerCode());

        task.setTaskPriority(taskDTO.getTaskPriority());
        task.setTaskStatus(taskDTO.getTaskStatus());
        task.setTaskDescription(taskDTO.getTaskDescription());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date expirationDate = format.parse(taskDTO.getExpirationDate());
            task.setExpirationDate(expirationDate);
        return task;
    }


    private TaskDTO convertTaskToDTO(int taskCode) {
        return modelMapper.map(taskRepo.findByTaskCode(taskCode), TaskDTO.class);
    }

    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    public void createTask(TaskDTO taskDTO, String login) throws ParseException {
        Task task = convertDTOToTask(taskDTO, login);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nullDate = format.parse("1111-01-01");

        Integer ifnullint = null;
        Date ifnulldate = null;
        if (!(task.getContractCode() == null)){
            ifnullint = task.getContractCode().getContractCode();
        }

        if (!(task.getExpirationDate().equals(nullDate))){
            ifnulldate = task.getExpirationDate();
        }


        taskRepo.addTask(task.getTaskCode(), login, task.getWorkerCode(),
                    task.getTaskPriority(), task.getTaskDescription(), null,
                    ifnullint, ifnulldate);
    }

    public List<Integer> findAllTaskCodes(){
        return taskRepo.allTaskCodes();
    }
}
