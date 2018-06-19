package com.jpranin.calculator;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/hello"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void primeTest1() throws Exception {
        this.mockMvc.perform(get("/prime/59"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("59 is prime!")));
    }

    @Test
    public void primeTest2() throws Exception {
        this.mockMvc.perform(get("/prime/2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("2 is prime!")));
    }

    @Test
    public void primeTest3() throws Exception {
        this.mockMvc.perform(get("/prime/9"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("9 is not prime - divisible by 3")));
    }

    @Test
    public void primeTest4() throws Exception {
        this.mockMvc.perform(get("/prime/48"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("48 is not prime - divisible by 2")));
    }

    @Test
    public void primeTest5() throws Exception {
        this.mockMvc.perform(get("/prime/49"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("49 is not prime - divisible by 7")));
    }

    @Test
    public void primeTest6() throws Exception {
        this.mockMvc.perform(get("/prime/4.5"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("4.5 is not prime - it's a fraction")));
    }

    @Test
    public void addTest1() throws Exception {
        float[] nums = new float[]{1,2,3};
        this.mockMvc.perform(post("/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(nums)))
        .andExpect(status().isOk())
        .andExpect(content().string("6.0"));
    
    }
    @Test
    public void addTest2() throws Exception {
        float[] nums = new float[]{10,120,39,40,-18};
        this.mockMvc.perform(post("/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(nums)))
        .andExpect(status().isOk())
        .andExpect(content().string("191.0"));
    
    }

    @Test
    public void addTest3() throws Exception {
        float[] nums = new float[]{};
        this.mockMvc.perform(post("/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(nums)))
        .andExpect(status().isOk())
        .andExpect(content().string("0.0"));
    
    }

    @Test
    public void addTest4() throws Exception {
        float[] nums = new float[]{-1};
        this.mockMvc.perform(post("/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(nums)))
        .andExpect(status().isOk())
        .andExpect(content().string("-1.0"));
    
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  

}