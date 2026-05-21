//package com.example.hostel.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique=true, nullable=false)
//    private String username;
//
//    @Column(nullable=false)
//    private String password;
//
//    // Constructors, getters, setters
//    public User(){}
//    public User(String username, String password){
//        this.username = username;
//        this.password = password;
//    }
//    public Long getId(){return id;}
//    public void setId(Long id){this.id = id;}
//    public String getUsername(){return username;}
//    public void setUsername(String username){this.username = username;}
//    public String getPassword(){return password;}
//    public void setPassword(String password){this.password = password;}
//}
package com.example.hostel.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
    private String password;

    @Column(nullable = false)
    private String role = "USER"; // default role USER

    // --- Constructors ---
    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
