package com.zlin.task.controllers;

import com.zlin.task.models.Subprocess;
import com.zlin.task.models.SubprocessReport;
import com.zlin.task.models.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zlin.task.models.QSubprocess;
import com.zlin.task.models.QSubprocessReport;
import com.zlin.task.models.QTask;
import com.zlin.task.models.StatusType;
import com.zlin.task.repositories.BusinessProcessesRepo;
import com.zlin.task.repositories.ComputersRepo;
import com.zlin.task.repositories.EquipmentsRepo;
import com.zlin.task.repositories.SubprocessReportsRepo;
import com.zlin.task.repositories.SubprocessesRepo;
import com.zlin.task.repositories.TasksRepo;
import com.zlin.task.services.UserService;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tasks/")
public class TasksController {
    @Autowired
    TasksRepo tasksRepo;

    @Autowired
    SubprocessReportsRepo sReportsRepo;

    @Autowired
    UserService userRepo;

    @Autowired
    BusinessProcessesRepo bProcessesRepo;

    @Autowired
    SubprocessesRepo subprocessesRepo;

    @Autowired
    ComputersRepo computersRepo;

    @Autowired
    EquipmentsRepo equipmentsRepo;

    /**
     * Инициализация Главная страница раздела /tasks/
     * @param model - Объекты страницы
     * @param search - (Необязательный) Значение поиска
     * @param pageable - Параметры пагинации
     * @return - Возвращает пользователю страницу со всеми бизнес-процессами
     */
    @GetMapping
    public String index(Model model
    , @RequestParam(required = false, defaultValue = "") String search
    , @PageableDefault(size = 10, sort = {"startDate"}, direction = Direction.DESC) Pageable pageable
    ) {
        QTask qTask = QTask.task;
        Page<Task> page;
        //Поиск
        if(!search.isEmpty() && search != null){
            String mod_search = search;
            page = tasksRepo.findAll(qTask.name.likeIgnoreCase(mod_search)
                .or(qTask.computer.invNo.stringValue().like(mod_search))
                .or(qTask.computer.name.likeIgnoreCase(mod_search))
                .or(qTask.equipment.invNo.stringValue().like(mod_search))
                .or(qTask.equipment.name.likeIgnoreCase(mod_search))
                , pageable);
        }
        else{
            page = tasksRepo.findAll(pageable);
        }
        model.addAttribute("search", search);
        model.addAttribute("url", "/tasks/");
        model.addAttribute("page", page);
        return "tasks/index";
    }

    /**
     * Инициализация страницы добавления
     * @param model - Объекты страницы
     * @return - возврвщает страницу добавления бизнес-процесса
     */
    @GetMapping("/add")
    public String addTask(Model model) {
        model.addAttribute("bProcesses", bProcessesRepo.findAll());
        model.addAttribute("computers", computersRepo.findAll());
        model.addAttribute("equipments", equipmentsRepo.findAll());
        model.addAttribute("users", userRepo.allUsers());
        return "tasks/add";
    }

    /**
     * Метод добавления задания
     * @param model - Объекты страницы
     * @param jsondata - данные передаваемые от клиента в формате JSON
     * @return - возвращает пользователю страницу с информацией об успешном или не успешном добавлении задания
     */
    @PostMapping("/add")
    public String add(Model model, @RequestParam String jsondata) {
        //System.out.println(jsondata + "\n\n\n");
        model.addAttribute("bProcesses", bProcessesRepo.findAll());
        model.addAttribute("computers", computersRepo.findAll());
        model.addAttribute("equipments", equipmentsRepo.findAll());
        model.addAttribute("users", userRepo.allUsers());
        List<String> errors = new ArrayList<>();
        
        JSONObject obj = (JSONObject)JSONValue.parse(jsondata);
        // System.out.println(obj.get("users"));

        Task task = new Task();
        Long tmpLong;
        String tmpString = obj.get("name").toString();
        // Проверка на наличие наименования задания   
        if(tmpString.isEmpty() || tmpString == " ")
        {
            errors.add("Поле \"Наименование бизнес-процесса\" не должно быть пустым.");
        }
        else{
            task.setName(tmpString);
        }

        // Проверка на наличие описания задания
        tmpString = obj.get("description").toString();
        if(tmpString.isEmpty() || tmpString == " ")
        {
            errors.add("Поле \"Описание бизнес-процесса\" не должно быть пустым.");
        }
        else{
            task.setDescription(tmpString);
        }

        //Проверка на наличие выбраной техники
        tmpLong = Long.valueOf(obj.get("computerId").toString());
        if(tmpLong>-1){
            task.setComputerId(tmpLong);
        }
        else{
            tmpLong = Long.valueOf(obj.get("equipmentId").toString());
            if(tmpLong>-1){
                task.setEquipmentId(tmpLong);
            }
            else{
                errors.add("Необходимо выбрать технику для задачи(компьютер или периферийное устройство).");
            }
        }

        //Проверка на наличие типа бизнес-процесса
        tmpLong = Long.valueOf(obj.get("bProcessId").toString());
        if(tmpLong>-1){
            task.setBusinessProcessId(tmpLong);
        }
        else{
            errors.add("Необходимо выбрать тип бизнес-процесса.");
        }

        //Проверка на наличие назначеных пользователей
        List<SubprocessReport> sReports = new ArrayList<SubprocessReport>();
        tmpString = obj.get("users").toString();
        if(tmpString == "{}" || tmpString.isEmpty()){
            errors.add("После выбора типа бизнес-процесса, необходимо нажать кнопку\"Назначить пользователей\" и на все подпроцессы назначить людей.");
        }
        else{
            JSONObject object = (JSONObject)obj.get("users");
            Object[] keys = object.keySet().toArray();
            for (int i = 0; i < object.size(); i++) {
                tmpLong = Long.valueOf(object.get(String.valueOf(keys[i])).toString());
                if(tmpLong>-1){
                    SubprocessReport sReport = new SubprocessReport();
                    sReport.setSubprocessId(Long.valueOf(keys[i].toString()));
                    sReport.setUserId(tmpLong);
                    sReports.add(sReport);
                    // System.out.println(Long.valueOf(keys[i].toString()));
                }
                else{
                    errors.add("Необходимо назначить пользователей на все процессы.");
                    break;
                }
            }
        }
        //Проверка наличия ошибок
        if(!errors.isEmpty())
        {
            model.addAttribute("errors", errors);
        }
        else{
            task.setStatusType(StatusType.EXECUTION);
            task.setStartDate(new Date());
            task = tasksRepo.save(task);
            for (SubprocessReport sp : sReports) {
                sp.setTaskId(task.getId());
                sp.setStatusType(StatusType.STOPPED);
                sp = sReportsRepo.save(sp);
            }
            System.out.println(task.getId());
            Iterable<SubprocessReport> tmp = sReportsRepo.findAll(QSubprocessReport.subprocessReport.taskId.eq(task.getId())
            .and(QSubprocessReport.subprocessReport.subprocess.indexNumber.eq(1)));
            for (SubprocessReport sr : tmp) {
                sr.setStatusType(StatusType.EXECUTION);
                sReportsRepo.save(sr);
            }
            model.addAttribute("success", "Успешно добавлено.");
        }
        return "tasks/add";
    }

    @GetMapping("/addusersform")
    public String addUsersForm(Model model, @RequestParam Long bProcessId){
        Iterable<Subprocess> subprocesses = subprocessesRepo
                            .findAll(QSubprocess.subprocess.businessProcessId.eq(bProcessId));
            model.addAttribute("subprocesses", subprocesses);
            model.addAttribute("users", userRepo.allUsers());
        return "tasks/addusersform";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable ("id") Long id, Model model) {
        Task task = tasksRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        System.out.println(task.getSReports().get(0).getUserId() );
        model.addAttribute("task", task);
        return "tasks/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ("id") Long id, Model model) {
        Task task = tasksRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        System.out.println(task.getSReports().get(0).getUserId() );
        model.addAttribute("task", task);
        model.addAttribute("bProcesses", bProcessesRepo.findAll());
        model.addAttribute("computers", computersRepo.findAll());
        model.addAttribute("equipments", equipmentsRepo.findAll());
        model.addAttribute("users", userRepo.allUsers());
        return "tasks/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable ("id") Long id, Model model, @RequestParam String jsondata) {
        //System.out.println(jsondata + "\n\n\n");
        model.addAttribute("bProcesses", bProcessesRepo.findAll());
        model.addAttribute("computers", computersRepo.findAll());
        model.addAttribute("equipments", equipmentsRepo.findAll());
        model.addAttribute("users", userRepo.allUsers());
        List<String> errors = new ArrayList<>();
        
        JSONObject obj = (JSONObject)JSONValue.parse(jsondata);
        // System.out.println(obj.get("users"));

        Task task = tasksRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        model.addAttribute("task", task);
        Long tmpLong;
        String tmpString = obj.get("name").toString();
        // Проверка на наличие наименования задания   
        if(tmpString.isEmpty() || tmpString == " ")
        {
            errors.add("Поле \"Наименование бизнес-процесса\" не должно быть пустым.");
        }
        else{
            task.setName(tmpString);
        }

        // Проверка на наличие описания задания
        tmpString = obj.get("description").toString();
        if(tmpString.isEmpty() || tmpString == " ")
        {
            errors.add("Поле \"Описание бизнес-процесса\" не должно быть пустым.");
        }
        else{
            task.setDescription(tmpString);
        }

        //Проверка на наличие выбраной техники
        tmpLong = Long.valueOf(obj.get("computerId").toString());
        if(tmpLong>-1){
            task.setComputerId(tmpLong);
        }
        else{
            tmpLong = Long.valueOf(obj.get("equipmentId").toString());
            if(tmpLong>-1){
                task.setEquipmentId(tmpLong);
            }
            else{
                errors.add("Необходимо выбрать технику для задачи(компьютер или периферийное устройство).");
            }
        }

        //Проверка на наличие типа бизнес-процесса
        tmpLong = Long.valueOf(obj.get("bProcessId").toString());
        if(tmpLong>-1){
            task.setBusinessProcessId(tmpLong);
        }
        else{
            errors.add("Необходимо выбрать тип бизнес-процесса.");
        }

        //Проверка на наличие назначеных пользователей
        List<SubprocessReport> sReports = new ArrayList<SubprocessReport>();
        tmpString = obj.get("users").toString();
        if(tmpString == "{}" || tmpString.isEmpty()){
            errors.add("После выбора типа бизнес-процесса, необходимо нажать кнопку\"Назначить пользователей\" и на все подпроцессы назначить людей.");
        }
        else{
            JSONObject object = (JSONObject)obj.get("users");
            Object[] keys = object.keySet().toArray();
            for (int i = 0; i < object.size(); i++) {
                tmpLong = Long.valueOf(object.get(String.valueOf(keys[i])).toString());
                if(tmpLong>-1){
                    SubprocessReport sReport = new SubprocessReport();
                    sReport.setSubprocessId(Long.valueOf(keys[i].toString()));
                    sReport.setUserId(tmpLong);
                    sReports.add(sReport);
                    // System.out.println(Long.valueOf(keys[i].toString()));
                }
                else{
                    errors.add("Необходимо назначить пользователей на все процессы.");
                    break;
                }
            }
        }
        //Проверка наличия ошибок
        if(!errors.isEmpty())
        {
            model.addAttribute("errors", errors);
        }
        else{
            task.setStatusType(StatusType.EXECUTION);
            task.setStartDate(new Date());
            Iterable<SubprocessReport> tmp = sReportsRepo.findAll(QSubprocessReport.subprocessReport.taskId.eq(task.getId()));
            sReportsRepo.deleteAll(tmp);
            task = tasksRepo.save(task);
            for (SubprocessReport sp : sReports) {
                sp.setTaskId(task.getId());
                sp.setStatusType(StatusType.STOPPED);
                sp = sReportsRepo.save(sp);
            }
            //System.out.println(task.getId());
            tmp = sReportsRepo.findAll(QSubprocessReport.subprocessReport.taskId.eq(task.getId())
            .and(QSubprocessReport.subprocessReport.subprocess.indexNumber.eq(1)));
            for (SubprocessReport sr : tmp) {
                sr.setStatusType(StatusType.EXECUTION);
                sReportsRepo.save(sr);
            }
            model.addAttribute("success", "Успешно изменено.");
        }
        return "tasks/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable ("id") Long id, Model model) {
        Task task = tasksRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
        tasksRepo.delete(task);
        return "redirect: /tasks/";
    }
}