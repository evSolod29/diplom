package com.zlin.task.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.zlin.task.models.Equipment;
import com.zlin.task.models.QEquipment;
import com.zlin.task.repositories.ComputersRepo;
import com.zlin.task.repositories.EquipmentsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentsRepo equipmentsRepo;
    @Autowired
    private ComputersRepo computersRepo;

    @GetMapping
    public String indexPage(Model model,
            @RequestParam(required = false, defaultValue = "") String search,
            @PageableDefault(size = 10, sort = {"subdivision"}, direction = Direction.DESC) Pageable pageable) {
        QEquipment qEquipment =  QEquipment.equipment;
        Page<Equipment> page;
        if(search != null && !search.isEmpty()){
            String searchs = "%" + search + "%";
            page = equipmentsRepo.findAll(qEquipment.invNo.stringValue().like(searchs)
                .or(qEquipment.subdivision.likeIgnoreCase(searchs))
                .or(qEquipment.name.likeIgnoreCase(searchs))
                .or(qEquipment.type.likeIgnoreCase(searchs))
                .or(qEquipment.movement.likeIgnoreCase(searchs))
                , pageable);
        }
        else{
            page = equipmentsRepo.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("search", search);
        model.addAttribute("url", "/equipments/");
        return "equipments/index";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("computers", computersRepo.findAll());
        return "equipments/add";
    }

    @PostMapping("/add")
    public String addPage(@RequestParam long invNo, @RequestParam String name, @RequestParam String subdivision,
            @RequestParam String commissioningDateStr, @RequestParam String movement, 
            @RequestParam String type,  @RequestParam(defaultValue = "-1") Long computerid) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date commissioningDate = simpleDateFormat.parse(commissioningDateStr);
            Equipment equipment = new Equipment();
            equipment.setInvNo(invNo);
            equipment.setName(name);
            equipment.setSubdivision(subdivision);
            equipment.setCommissioningDate(commissioningDate);
            equipment.setMovement(movement);
            equipment.setType(type);
            if(computerid >= 0){
                equipment.setComputerId(computerid);
            }
            equipmentsRepo.save(equipment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "equipments/add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable ("id") long id, Model model) {
        Equipment equipment = equipmentsRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        model.addAttribute("equipment", equipment);
        return "equipments/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ("id") long id, Model model) {
        Equipment equipment = equipmentsRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        model.addAttribute("equipment", equipment);
        model.addAttribute("computers", computersRepo.findAll());
        return "equipments/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable ("id") long id, @RequestParam long invNo, @RequestParam String name, @RequestParam String subdivision,
        @RequestParam String commissioningDateStr, @RequestParam String movement, 
        @RequestParam String type,  @RequestParam Long computerid) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date commissioningDate = simpleDateFormat.parse(commissioningDateStr);
            Equipment equipment = new Equipment();
            equipment.setId(id);
            equipment.setInvNo(invNo);
            equipment.setName(name);
            equipment.setSubdivision(subdivision);
            equipment.setCommissioningDate(commissioningDate);
            equipment.setMovement(movement);
            equipment.setType(type);
            equipment.setComputerId(computerid);
            equipmentsRepo.save(equipment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../details/"+id;
    }
}