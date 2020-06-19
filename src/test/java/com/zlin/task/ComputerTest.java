package com.zlin.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import org.springframework.test.context.jdbc.Sql;
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
public class ComputerTest {
    @Autowired
    public MockMvc mockMvc;

    /**
     * Проверка на нулевой результат поиска
     */
    @Test
    public void badSearchRequest() throws Exception{
        this.mockMvc.perform(get("/computers/")
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
     * Проверка на добавление записи о компьютере
     */
    @Test
    @Sql(value = {"/delete_test_computer_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void successAddRequest() throws Exception {
        this.mockMvc.perform(post("/computers/add")
                .param("invNo", "122")
                .param("name", "a")
                .param("subdivision", "1")
                .param("commissioningDateStr", "2020-06-01")
                .param("movement", "1")
                .param("cpu", "1")
                .param("ram", "1")
                .param("rom", "1")
                .param("videocard", "1")
                .param("cdRom", "true")
                .param("buildInMonitor", "true"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    
}