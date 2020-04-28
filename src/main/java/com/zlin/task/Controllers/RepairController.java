package com.zlin.task.Controllers;

import java.util.Date;

import com.zlin.task.Models.Repair;
import com.zlin.task.Repositories.RepairRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    RepairRepo repairRepo;

    @GetMapping()
    public String index(Model model) {
        Iterable<Repair> repairs = repairRepo.findAll();
        model.addAttribute("repairs", repairs);
        return "repair/index";
    }

    @PostMapping("/endrepair")
    public ResponseEntity<String> endRepair(@RequestParam long id) {
        Date date = new Date(); 
        Repair repair = repairRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
        repair.setEndDate(date);
        repairRepo.save(repair);
        return ResponseEntity.ok("");
    }

    @PostMapping("/addrepair")
    public ResponseEntity<String> addRepair(@RequestParam long id) {
        Date date = new Date(); 
        Repair repair = new Repair(date,id);
        repairRepo.save(repair);
        return ResponseEntity.ok("");
    }
    
    
}