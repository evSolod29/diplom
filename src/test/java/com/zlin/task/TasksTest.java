package com.zlin.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksTest {
    @Autowired
    public MockMvc mockMvc;

    /**
     * Проверка на нулевой результат поиска
     */
    @Test
    public void badSearchRequest() throws Exception {
        this.mockMvc.perform(get("/tasks/")
                .param("search", "bbdfsd"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ничего не найдено")));
    }

    /**
     * Проверка на не нулевой результат поиска
     */
    @Test
    public void successSearchRequest() throws Exception {
        this.mockMvc.perform(get("/tasks/")
                .param("search", "asus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ASUS GL2799")));
    }
 
    /**
     * Проверка на некорректный ввод на странице добавления бизнес-процесса
     */
    @Test
    public void errorAddRequest() throws Exception {
        this.mockMvc.perform(post("/tasks/add")
                .param("jsondata","{\"name\":\"\",\"description\":\"\",\"computerId\":\"-1\",\"equipmentId\":\"-1\",\"bProcessId\":\"-1\",\"users\":{}}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Поле &quot;Наименование бизнес-процесса&quot; не должно быть пустым.")))
                .andExpect(content().string(containsString("Необходимо выбрать технику для задачи(компьютер или периферийное устройство).")))
                .andExpect(content().string(containsString("Необходимо выбрать тип бизнес-процесса.")));
    }
}