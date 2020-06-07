package com.zlin.task.controllers;

import java.sql.Date;
import java.util.Calendar;

import com.zlin.task.models.QComputer;
import com.zlin.task.models.QEquipment;
import com.zlin.task.models.QRepair;
import com.zlin.task.models.User;
import com.zlin.task.repositories.ComputersRepo;
import com.zlin.task.repositories.EquipmentsRepo;
import com.zlin.task.repositories.RepairRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    RepairRepo repairsRepo;
    @Autowired
    ComputersRepo computersRepo;
    @Autowired
    EquipmentsRepo equipmentsRepo;

    @GetMapping
    public String indexPage(Model model){
        QRepair qRepair = QRepair.repair;
        QEquipment qEquipment = QEquipment.equipment;
        QComputer qComputer = QComputer.computer;

        model.addAttribute("totalComputers", computersRepo.count());
        model.addAttribute("totalEquipments", equipmentsRepo.count());
        model.addAttribute("totalRepairs", repairsRepo.count());
        Date from = new Date(System.currentTimeMillis());
        from.setDate(1);
        Calendar myCalendar = (Calendar) Calendar.getInstance().clone();
        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date to = new Date(System.currentTimeMillis());
        to.setDate(max_date);
        System.out.println(to.toString());
        System.out.println(from.toString());
        model.addAttribute("addComputersMonth", computersRepo.count(
            qComputer.commissioningDate.between(from, to)));
        model.addAttribute("addRepairsMonth", repairsRepo.count(
            qRepair.startDate.between(from, to)));
        model.addAttribute("endRepairsMonth", repairsRepo.count(
            qRepair.endDate.between(from, to)));
        model.addAttribute("addEquipmentsMonth", equipmentsRepo.count(
            qEquipment.commissioningDate.between(from, to)));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Object pricipal = auth.getPrincipal();
        String user = "";
        if(auth != null && auth.isAuthenticated() &&
        //when Anonymous Authentication is enabled
        !(auth instanceof AnonymousAuthenticationToken) ){
            if (pricipal instanceof User) {
                user = ((User) pricipal).getId().toString();
            }
            model.addAttribute("endRepairsMonthPersonal", repairsRepo.count(
                qRepair.endDate.between(from, to).and(qRepair.users.any().id.eq(Long.valueOf(user) ))));
            model.addAttribute("activeRepairsPersonal", repairsRepo.count(
                qRepair.users.any().id.eq(Long.valueOf(user))
                .and(qRepair.endDate.isNull())));
        }

        return "index";
    }

}