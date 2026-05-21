//package com.example.hostel.model;
//
//import javax.persistence.*;
//
//@Entity
//public class Complaint {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//
//    @Column(length=2000)
//    private String description;
//
//    private String status = "OPEN";
//
//    private String username; // who created
//
//    // getters/setters
//    public Complaint(){}
//    public Long getId(){return id;}
//    public void setId(Long id){this.id = id;}
//    public String getTitle(){return title;}
//    public void setTitle(String title){this.title = title;}
//    public String getDescription(){return description;}
//    public void setDescription(String description){this.description = description;}
//    public String getStatus(){return status;}
//    public void setStatus(String status){this.status = status;}
//    public String getUsername(){return username;}
//    public void setUsername(String username){this.username = username;}
//
//    public void setCreatedBy(String name) {
//    }
//}
package com.example.hostel.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 120, message = "Title must be 3-120 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 2000, message = "Description must be 10-2000 characters")
    @Column(length = 2000)
    private String description;
    private String status = "Pending"; // Default status
    private String createdBy;
    private LocalDateTime createdAt = LocalDateTime.now();

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
