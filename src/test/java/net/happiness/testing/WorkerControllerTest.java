package net.happiness.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkerController.class)
class WorkerControllerTest {

    @MockBean
    private WorkerService workerService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getSumOfSalaries() throws Exception {
        Mockito.when(workerService.getSumOfSalariesOfAllWorkers()).thenReturn(100);
        mockMvc.perform(get("/api/worker/salary-sum"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(100));
        Mockito.verify(workerService, Mockito.times(1)).getSumOfSalariesOfAllWorkers();
    }

    @Test
    void hireWorker() throws Exception {
        Worker worker = new Worker(1L, "Евгений", 500);
        String workerJson = objectMapper.writeValueAsString(worker);
        mockMvc.perform(post("/api/worker/hire")
                .contentType(MediaType.APPLICATION_JSON)
                .content(workerJson))
                .andExpect(status().isCreated());
        Mockito.verify(workerService, Mockito.times(1)).hireWorker(worker);
    }

    @Test
    void getWorkerById() throws Exception {
        Worker worker = new Worker(1L, "Евгений", 500);
        Mockito.when(workerService.findWorkerById(1L)).thenReturn(worker);
        mockMvc.perform(get("/api/worker/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Евгений"))
                .andExpect(jsonPath("$.salary").value(500));
        Mockito.verify(workerService, Mockito.times(1)).findWorkerById(1L);
    }

    @Test
    void updateWorker() throws Exception {
        Worker updatedWorker = new Worker(1L, "Евгений", 500);
        String updatedWorkerJson = objectMapper.writeValueAsString(updatedWorker);
        mockMvc.perform(put("/api/worker/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedWorkerJson))
                .andExpect(status().isOk());
        Mockito.verify(workerService, Mockito.times(1)).updateWorker(updatedWorker);
    }

}
