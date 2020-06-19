package com.zlin.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

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
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Проверка на существование страницы авторизации
     */
	@Test
	public void exsistLoginPage() throws Exception {
       this.mockMvc.perform(get("/login"))
       .andDo(print())
       .andExpect(status().isOk())
       .andExpect(content().string(containsString("Вход")));
    }

    /**
     * Проверка на перенаправление неавторизированных пользователей на страницу авторизации
     */
    @Test
    public void redirectUnauthUsers() throws Exception{
        this.mockMvc.perform(get("/reports/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    } 

    /**
     * Проверка работоспасобности авторизации с корректными данными
     */
    @Test
    public void AuthTestUsers() throws Exception{
        this.mockMvc.perform(formLogin().user("test").password("12345"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }
    
    /**
     * Проверка работоспасобности авторизации с не корректными данными
     */
    @Test
    public void badCredentials() throws Exception {
        this.mockMvc.perform(formLogin().user("test").password(""))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

}