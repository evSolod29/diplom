package com.zlin.task.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.zlin.task.models.Computer;
import com.zlin.task.models.QComputer;
import com.zlin.task.repositories.ComputersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/computers")
public class ComputersController {

    @Autowired
    private ComputersRepo computersRepo;

    @GetMapping
    public String indexPage(Model model,
            @RequestParam(required = false, defaultValue = "") String search,
            @PageableDefault(size = 10, sort = {"subdivision"}, direction = Direction.DESC) Pageable pageable) {
        QComputer qComputer = QComputer.computer;
        Page<Computer> page;
        if(search != null && !search.isEmpty()){
            String searchs = "%" + search + "%";
            page = computersRepo.findAll(qComputer.invNo.stringValue().like(searchs)
                .or(qComputer.subdivision.likeIgnoreCase(searchs))
                .or(qComputer.name.likeIgnoreCase(searchs))
                .or(qComputer.movement.likeIgnoreCase(searchs))
                , pageable);
        }
        else{
            page = computersRepo.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("search", search);
        model.addAttribute("url", "/computers/");
        return "computers/index";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        return "computers/add";
    }

    @PostMapping("/add")
    public String addPage(@RequestParam long invNo, @RequestParam String name, @RequestParam String subdivision,
            @RequestParam String commissioningDateStr, @RequestParam String movement, 
            @RequestParam String cpu,  @RequestParam String ram,  @RequestParam String rom, 
            @RequestParam String videocard, @RequestParam boolean cdRom, @RequestParam boolean buildInMonitor) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date commissioningDate = simpleDateFormat.parse(commissioningDateStr);
            Computer computer = new Computer(invNo, name, subdivision, commissioningDate, movement, cpu, ram, rom, videocard, cdRom, buildInMonitor);
            computersRepo.save(computer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "computers/add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable ("id") long id, Model model) {
        Computer computer = computersRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        model.addAttribute("computer", computer);
        return "computers/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ("id") long id, Model model) {
        Computer computer = computersRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        model.addAttribute("computer", computer);
        return "computers/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable ("id") long id, @RequestParam long invNo, @RequestParam String name, @RequestParam String subdivision,
            @RequestParam String commissioningDateStr, @RequestParam String movement, 
            @RequestParam String cpu,  @RequestParam String ram,  @RequestParam String rom, 
            @RequestParam String videocard, @RequestParam boolean cdRom, @RequestParam boolean buildInMonitor) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date commissioningDate = simpleDateFormat.parse(commissioningDateStr);
            Computer computer = new Computer(invNo, name, subdivision, commissioningDate, movement, cpu, ram, rom, videocard, cdRom, buildInMonitor);
            computer.setId(id);
            computersRepo.save(computer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../details/"+id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ("id") Long id, Model model) {
        Computer comp = computersRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        computersRepo.delete(comp);
        return "redirect: ../";
    }
}