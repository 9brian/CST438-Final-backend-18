package com.cst438.Domain;

import jakarta.persistence.*;

@Entity
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String password;

    public User() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String email) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", name=" + name + "]";
    }
}
