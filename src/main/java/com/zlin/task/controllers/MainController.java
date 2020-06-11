package com.zlin.task.controllers;

import com.zlin.task.models.QTask;
import com.zlin.task.models.StatusType;
import com.zlin.task.repositories.ComputersRepo;
import com.zlin.task.repositories.EquipmentsRepo;
import com.zlin.task.repositories.TasksRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    TasksRepo tasksRepo;

    @Autowired
    ComputersRepo computersRepo;

    @Autowired
    EquipmentsRepo equipmentsRepo;
    

    @GetMapping
    public String indexPage(Model model){
        model.addAttribute("tasks" , tasksRepo.count(QTask.task.statusType.eq(StatusType.EXECUTION))) ;
        model.addAttribute("computers" , computersRepo.count());
        model.addAttribute("equipments" , equipmentsRepo.count());
        return "index";
    }

}