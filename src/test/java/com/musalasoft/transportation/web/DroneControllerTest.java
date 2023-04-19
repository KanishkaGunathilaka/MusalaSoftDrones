package com.musalasoft.transportation.web;

import com.musalasoft.transportation.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DroneControllerTest extends IntegrationTest {

    @Test
    void registerDrone() throws Exception {

        mvc.perform(post("/drone/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"serialNumber\":\"23gh\",\n" +
                                "    \"model\" : \"LIGHTWEIGHT\",\n" +
                                "    \"weight\":500,\n" +
                                "    \"capacity\":50.5,\n" +
                                "    \"state\":\"LOADING\"\n" +
                                "}")
        )
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void getDrone() throws Exception {

        mvc.perform((get("/drone/{id}", 50)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber", is("Serial num 1")));
    }

    @Test
    void loadMedications() throws Exception {

        mvc.perform(post("/drone/load-medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"droneId\": 50,\n" +
                                "    \"medicationList\" : [\n" +
                                "        3\n" +
                                "    ]\n" +
                                "}")
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void getAvailableDronesForLoading() throws Exception {

        mvc.perform((get("/drone/available-drones")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id", hasItem(50)));
    }

    @Test
    void getAvailableBatteryLevel() throws Exception {

        mvc.perform((get("/drone/battery-level/{id}", 50)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.batteryLevel", is(50.5)));
    }
}