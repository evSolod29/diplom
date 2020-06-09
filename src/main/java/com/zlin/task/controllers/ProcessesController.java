package com.zlin.task.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;

import com.zlin.task.models.Computer;
import com.zlin.task.models.Equipment;
import com.zlin.task.models.Process;
import com.zlin.task.models.QProcess;
import com.zlin.task.models.Work;
import com.zlin.task.repositories.ComputersRepo;
import com.zlin.task.repositories.EquipmentsRepo;
import com.zlin.task.repositories.ProcessRepo;
import com.zlin.task.repositories.WorksRepo;
import com.zlin.task.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/processes")
public class ProcessesController {

    @Autowired
    private ProcessRepo procesesRepo;
    @Autowired
    private ComputersRepo computersRepo;
    @Autowired
    private EquipmentsRepo equipmentsRepo;
    @Autowired
    private UserService usersRepo;
    @Autowired
    private WorksRepo worksRepo;

    @GetMapping
    public String indexPage(Model model,
            @RequestParam(required = false, defaultValue = "") String search,
            @PageableDefault(size = 10, sort = {"id"}, direction = Direction.DESC) Pageable pageable) {
        QProcess qProcess =  QProcess.process;
        Page<Process> page;
        if(search != null && !search.isEmpty()){
            String searchs = "%" + search + "%";
            page = procesesRepo.findAll(qProcess.name.like(searchs)
            .or(qProcess.computer.invNo.stringValue().likeIgnoreCase(searchs))
            .or(qProcess.computer.name.likeIgnoreCase(searchs))
            .or(qProcess.customer.likeIgnoreCase(searchs))
            .or(qProcess.equipment.invNo.stringValue().likeIgnoreCase(searchs))
            .or(qProcess.equipment.name.likeIgnoreCase(searchs))
            .or(qProcess.startDate.stringValue().like(searchs))
                , pageable);
        }
        else{
            page = procesesRepo.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("search", search);
        model.addAttribute("url", "/processes/");
        return "processes/index";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("computers", computersRepo.findAll());
        return "processes/add";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPage(@RequestParam String name
    , @RequestParam String customer
    , @RequestParam(required = false, defaultValue = "-1") Long computerId
    , @RequestParam(required = false, defaultValue = "-1") Long equipmentId
    , @RequestParam(defaultValue = "1") int works) {
        Process process = new Process();
        process.setName(name);
        process.setCustomer(customer);
        process.setStartDate(new Date());
        if(computerId>-1){
            Computer computer = computersRepo.findById(computerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid computerId " + computerId));
            process.setComputer(computer);
            process.setComputerId(computerId);
        }
        else{
            Equipment equipment = equipmentsRepo.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid equipmentId " + equipmentId));
            process.setEquipment(equipment);
            process.setEquipmentId(equipmentId);
        }
        process = procesesRepo.save(process);
        return ResponseEntity.ok(process.getId().toString()+"," + works);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable ("id") long id, Model model) {
        Process process = procesesRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        
        model.addAttribute("process", process);
        return "processes/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ("id") long id, Model model) {
        Process process = procesesRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        model.addAttribute("process", process);
        model.addAttribute("computers", computersRepo.findAll());
        return "processes/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable ("id") long id, @RequestParam long invNo, @RequestParam String name, @RequestParam String subdivision,
        @RequestParam String commissioningDateStr, @RequestParam String movement, 
        @RequestParam String type,  @RequestParam Long computerid) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date commissioningDate = simpleDateFormat.parse(commissioningDateStr);
            Process process = new Process();
            
            procesesRepo.save(process);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../details/"+id;
    }

    @GetMapping("/addworks")
    public String addWorksPage(@RequestParam String id
    , @RequestParam String count, Model model) {
        model.addAttribute("users", usersRepo.allUsers());
        model.addAttribute("id", id);
        model.addAttribute("count", count);
        return "processes/addWorks";
    }

    @PostMapping("/addworks")
    public ResponseEntity<String> addWorksPage(@RequestParam String name
    , @RequestParam Long userId
    , @RequestParam Long processId
    , @RequestParam(required = false, defaultValue = "-1") Long preWorkId) {
        Work work = new Work(); 
        work.setName(name);
        work.setUser(usersRepo.findUserById(userId));
        work.setUserId(userId);
        work.setProcess(procesesRepo.findById(processId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid processId " + processId)));
        if(preWorkId>-1){
            Work temp = worksRepo.findById(preWorkId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid workId " + preWorkId));
            work.setPreWorkId(preWorkId);
        }
        work.setProcessId(processId);
        work = worksRepo.save(work);
        return ResponseEntity.ok(work.getId().toString());
    }
}