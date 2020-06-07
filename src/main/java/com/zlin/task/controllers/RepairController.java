package com.zlin.task.controllers;

import java.util.Date;

import com.zlin.task.models.QRepair;
import com.zlin.task.models.Repair;
import com.zlin.task.models.User;
import com.zlin.task.repositories.RepairRepo;
import com.zlin.task.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    UserService usersRepo;

    @GetMapping()
    public String index(Model model,
        @RequestParam(required = false, defaultValue = "") String sometext,
        @PageableDefault(size = 10, sort = {"startDate"}, direction = Direction.DESC) Pageable pageable
    ) {
        QRepair qRepair = QRepair.repair;
        Page<Repair> page;
        if(sometext != null && !sometext.isEmpty()){
            String search = "%" + sometext + "%";
            System.out.println(search);
            page = repairRepo.findAll(
                qRepair.comp.invNo.stringValue().like(search)
                .or(qRepair.comp.subdivision.likeIgnoreCase(search))
                .or(qRepair.equipment.subdivision.likeIgnoreCase(search))
                .or(qRepair.equipment.name.likeIgnoreCase(search))
                .or(qRepair.equipment.invNo.stringValue().like(search))
                .or(qRepair.comp.name.likeIgnoreCase(search))
                .or(qRepair.users.any().lastName.concat(" " + qRepair.users.any().firstName).likeIgnoreCase(search))
                , pageable);
        }
        else{
            page = repairRepo.findAll(pageable);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Object pricipal = auth.getPrincipal();
        String user="";
        if(auth.isAuthenticated()){
            if (pricipal instanceof User) {
                user = ((User) pricipal).getId().toString();
            }
        }

        model.addAttribute("userid", user);
        model.addAttribute("users", usersRepo.allUsers());
        model.addAttribute("page", page);
        model.addAttribute("url", "/repair/");
        model.addAttribute("sometext", sometext);
        return "repair/index";
    }
    @GetMapping("/tasks")
    public String tasks(Model model,
        @AuthenticationPrincipal User user,
        @RequestParam(required = false, defaultValue = "") String sometext,
        @PageableDefault(size = 10, sort = {"startDate"}, direction = Direction.DESC) Pageable pageable
    ){
        QRepair qRepair = QRepair.repair;
        Page<Repair> page;
        if(sometext != null && !sometext.isEmpty()){
            String search = "%" + sometext + "%";
            System.out.println(search);
            page = repairRepo.findAll(qRepair.users.any().eq(user).and(
                qRepair.comp.invNo.stringValue().like(search)
                .or(qRepair.comp.subdivision.likeIgnoreCase(search))
                .or(qRepair.equipment.subdivision.likeIgnoreCase(search))
                .or(qRepair.equipment.name.likeIgnoreCase(search))
                .or(qRepair.equipment.invNo.stringValue().like(search))
                .or(qRepair.comp.name.likeIgnoreCase(search))
                ), pageable);
        }
        else{
            page = repairRepo.findAll(qRepair.users.any().eq(user), pageable);
        }
        model.addAttribute("userid", user.getId());
        model.addAttribute("users", usersRepo.allUsers());
        model.addAttribute("page", page);
        model.addAttribute("url", "/repair/tasks");
        model.addAttribute("sometext", sometext);
        return "repair/tasks";
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
    public ResponseEntity<String> addRepair(
            @RequestParam(required = false, defaultValue = "-1") long computerid,
            @RequestParam(required = false, defaultValue = "-1") long equipmentid
        ) {
        Date date = new Date(); 
        Repair repair = new Repair();
        repair.setStartDate(date);
        if(computerid > -1 ){
            repair.setComputerId(computerid);
        }
        if(equipmentid > -1){
            repair.setEquipmentId(equipmentid);
        }
        repairRepo.save(repair);
        return ResponseEntity.ok("");
    }
    
    @PostMapping("/setuser")
    public ResponseEntity<String> setUser(Model model, @RequestParam Long userid,
            @RequestParam Long repairid) {
        User user = usersRepo.findUserById(userid);
        Repair repair = repairRepo.findById(repairid).get();
        user.getRepairs().add(repair);
        repair.getUsers().add(user);
        repairRepo.save(repair);
        return ResponseEntity.ok("");
    }
}