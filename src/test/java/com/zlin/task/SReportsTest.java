package com.zlin.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class SReportsTest {
    @Autowired
    public MockMvc mockMvc;

    @WithMockUser(username = "asynker29", password = "12345")
    @Test
    public void writeErrorReport() throws Exception{
        this.mockMvc.perform(post("/reports/writereport/errorreport")
            .param("id","28")
            .param("report","ff"))
            .andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/reports/"));
    }

    @WithMockUser(username = "asynker29", password = "12345")
    @Test
    public void writeSuccessReport() throws Exception{
        this.mockMvc.perform(post("/reports/writereport/successreport")
            .param("id","28")
            .param("report","ff"))
            .andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/reports/"));
    }

    // @WithMockUser(username = "test", password = "12345")
    // @Test(expected = NullPointerException.class)
    // public void writeReportAnotherUserReport() throws Exception{
    //     this.mockMvc.perform(get("/reports/writereport/28"))
    //         .andDo(print())
    //         .andExpect(status().is5xxServerError());
    // }
}