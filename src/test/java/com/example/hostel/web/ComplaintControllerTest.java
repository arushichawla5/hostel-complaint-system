package com.example.hostel.web;

import com.example.hostel.model.Complaint;
import com.example.hostel.repository.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ComplaintControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComplaintRepository complaintRepository;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void list_shouldRenderComplaintsPage() throws Exception {
        when(complaintRepository.findByCreatedBy("user")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/complaints"))
                .andExpect(status().isOk())
                .andExpect(view().name("complaints"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void add_shouldFailValidation_whenFieldsMissing() throws Exception {
        mockMvc.perform(post("/complaints/add")
                        .param("title", "")
                        .param("description", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("newComplaint", "title", "description"))
                .andExpect(view().name("complaints"));
    }
}


