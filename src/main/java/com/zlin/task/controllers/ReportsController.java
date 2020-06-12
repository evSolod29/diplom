package com.zlin.task.controllers;

import com.zlin.task.models.SubprocessReport;
import com.zlin.task.models.Task;
import com.zlin.task.models.User;
import com.zlin.task.models.QSubprocessReport;
import com.zlin.task.models.StatusType;
import com.zlin.task.repositories.SubprocessReportsRepo;
import com.zlin.task.repositories.SubprocessesRepo;
import com.zlin.task.repositories.TasksRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    SubprocessReportsRepo sReportsRepo;
    @Autowired
    TasksRepo tasksRepo;
    @Autowired
    SubprocessesRepo subprocessesRepo;

    @GetMapping
    public String indexPage(Model model,
            @AuthenticationPrincipal User currentUser,
            @RequestParam(required = false, defaultValue = "") String search,
            @PageableDefault(size = 10, sort = {"task.startDate"}, direction = Direction.DESC) Pageable pageable) {
        QSubprocessReport qSubprocessReport = QSubprocessReport.subprocessReport;
        Page<SubprocessReport> page;
        if(search != null && !search.isEmpty()){
            String searchs = "%" + search + "%";
            page = sReportsRepo.findAll(qSubprocessReport.subprocess.subprocessName.eq(searchs)
            .or(qSubprocessReport.subprocess.taskDescription.likeIgnoreCase(searchs))
            .and(qSubprocessReport.userId.eq(currentUser.getId()).and(qSubprocessReport.statusType.eq(StatusType.EXECUTION)))
                , pageable);
        }
        else{
            page = sReportsRepo.findAll( qSubprocessReport.statusType.eq(StatusType.EXECUTION)
                .and(qSubprocessReport.userId.eq(currentUser.getId()))
                , pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("search", search);
        model.addAttribute("url", "/reports/");
        return "reports/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable ("id") Long id, @AuthenticationPrincipal User currentUser, Model model) {
        SubprocessReport report = sReportsRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        model.addAttribute("user", currentUser);
        model.addAttribute("report", report);
        return "reports/details";
    }

    @GetMapping("/writereport/{id}")
    public String writeReport(@PathVariable ("id") Long id, Model model, @AuthenticationPrincipal User currentUser) {
        SubprocessReport report = sReportsRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        if(report.getUser().getId() != currentUser.getId()){
            throw new IllegalArgumentException("This report must be write only by" + report.getUser().getLastName() + " " + report.getUser().getFirstName());
        }
        Iterable<SubprocessReport> tmp = sReportsRepo.findAll(QSubprocessReport.subprocessReport.taskId.eq(report.getTask().getId())
            .and(QSubprocessReport.subprocessReport.subprocess.indexNumber.eq(report.getSubprocess().getIndexNumber()-1)));
            for (SubprocessReport sr : tmp) {
                model.addAttribute("prevReport", sr);
            }
        model.addAttribute("report", report);
        return "reports/writereport";
    }

    @PostMapping("/writereport/errorreport")
    public String errorReport(@RequestParam Long id, @RequestParam String report, @AuthenticationPrincipal User currentUser, Model model) {
        SubprocessReport spreport = sReportsRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        spreport.setReport(report);
        spreport.setStatusType(StatusType.ERROR);
        Task task = spreport.getTask();
        task.setStatusType(StatusType.ERROR);
        sReportsRepo.save(spreport);
        tasksRepo.save(task);
        //model.addAttribute("success", "Отчёт успешно добавлен");
        return "redirect:/reports/";
    }

    @PostMapping("/writereport/successreport")
    public String successReport(@RequestParam Long id, @RequestParam String report, @AuthenticationPrincipal User currentUser, Model model) {
        SubprocessReport spreport = sReportsRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        spreport.setReport(report);
        spreport.setStatusType(StatusType.SUCCESS);
        sReportsRepo.save(spreport);
        Iterable<SubprocessReport> tmp = sReportsRepo.findAll(QSubprocessReport.subprocessReport.subprocess.indexNumber.eq(spreport.getSubprocess().getIndexNumber()+1)
        .and(QSubprocessReport.subprocessReport.taskId.eq(spreport.getTask().getId())));
        for (SubprocessReport spr : tmp) {
            spr.setStatusType(StatusType.EXECUTION);
        }
        sReportsRepo.saveAll(tmp);
        //model.addAttribute("success", "Отчёт успешно добавлен");
        return "redirect:/reports/";
    }

}