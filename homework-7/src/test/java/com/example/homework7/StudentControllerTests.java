package com.example.homework7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetStudent() throws Exception {
        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob Test"));
    }

    @Test
    public void testAddStudent() throws Exception {
        mockMvc.perform(post("/student")
                        .contentType("application/json")
                        .content("{\"id\": 6, \"name\": \"New Student\", \"matNr\": \"104567\", \"subject\": \"Mathematics\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Student"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/6"))
                .andExpect(status().isOk());
    }
}