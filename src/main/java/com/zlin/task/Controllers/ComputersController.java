package com.zlin.task.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zlin.task.Models.Computer;
import com.zlin.task.Repositories.ComputersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/computers")
public class ComputersController {

    @Autowired
    private ComputersRepo computersRepo;

    @GetMapping
    public String indexPage(Model model) {
        Iterable<Computer> computers = computersRepo.findAll();
        model.addAttribute("computers", computers);
        return "computers/index";
    }

    //@Async
    @PostMapping("/search")
    public ResponseEntity<String> search(@RequestParam String search, Model model) {
        try {
            //search = new String(search.getBytes("UTF-8"), "UTF-8");
            System.out.println(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterable<Computer> computers = computersRepo.findAllByAllRows(search);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String json = gson.toJson(computers);
        // System.out.println(json);
        return ResponseEntity.ok(json);
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
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String json = gson.toJson(computer);
            System.out.println(json);
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
}