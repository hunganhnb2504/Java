package com.example.jdbc;

import java.time.LocalDateTime;

public class User {

    private int id;
    private String fullName;
    private LocalDateTime birthday;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String address;
    private String resetToken;
    private LocalDateTime resetTokenExpiry;

    public User(String fullName, LocalDateTime birthday, String email, String phone, String password, String role, String address, String resetToken, LocalDateTime resetTokenExpiry) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.address = address;
        this.resetToken = resetToken;
        this.resetTokenExpiry = resetTokenExpiry;
    }

    public User(int id, String fullName, LocalDateTime birthday, String email, String phone, String password, String role, String address, String resetToken, LocalDateTime resetTokenExpiry) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.address = address;
        this.resetToken = resetToken;
        this.resetTokenExpiry = resetTokenExpiry;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getAddress() {
        return address;
    }

    public String getResetToken() {
        return resetToken;
    }

    public LocalDateTime getResetTokenExpiry() {
        return resetTokenExpiry;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", fullName=" + fullName + ", birthday=" + birthday + ", email=" + email + ", phone=" + phone + ", password=" + password + ", role=" + role + ", address=" + address + ", resetToken=" + resetToken + ", resetTokenExpiry=" + resetTokenExpiry + "]";
    }
}
