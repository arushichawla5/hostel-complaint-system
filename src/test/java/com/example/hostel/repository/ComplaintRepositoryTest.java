package com.example.hostel.repository;

import com.example.hostel.model.Complaint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ComplaintRepositoryTest {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Test
    void findByCreatedBy_returnsSavedEntity() {
        Complaint c = new Complaint();
        c.setTitle("Leakage");
        c.setDescription("There is a water leakage in room 101");
        c.setCreatedBy("alice");
        complaintRepository.save(c);

        List<Complaint> results = complaintRepository.findByCreatedBy("alice");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getTitle()).isEqualTo("Leakage");
    }
}


