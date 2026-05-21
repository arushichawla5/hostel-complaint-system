//package com.example.hostel.repository;
//
//import com.example.hostel.model.Complaint;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;
//
//public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
//    List<Complaint> findByUsername(String username);
//}
package com.example.hostel.repository;

import com.example.hostel.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByCreatedBy(String createdBy);
}


