package com.example.fcruz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getLocation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/location?address=Dam Square Amsterdam")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formatted_address").value("Dam Square, Dam, 1012 JL Amsterdam, Netherlands"));
    }


    @Test
    public void postLocation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/location")
                .param("address","Dam Square Amsterdam")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formatted_address").value("Dam Square, Dam, 1012 JL Amsterdam, Netherlands"));
    }

    @Test
    public void getLocationWhithoutAddress() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/location")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("INVALID_REQUEST"));
    }
    @Test
    public void postLocationWhithoutAddress() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/location")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("INVALID_REQUEST"));
    }
    @Test
    public void getAnyOtherURL() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/incorrecturl")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }
    @Test
    public void postAnyOtherURL() throws Exception{
        ResultMatcher expected = MockMvcResultMatchers.jsonPath("status")
                .value("NOT_FOUND");
        mvc.perform(MockMvcRequestBuilders.post("/incorrecturl")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(expected);
    }

}
