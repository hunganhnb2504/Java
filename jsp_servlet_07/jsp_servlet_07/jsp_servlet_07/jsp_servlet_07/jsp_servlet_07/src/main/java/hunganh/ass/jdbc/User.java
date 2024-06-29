package hunganh.ass.jdbc;

public class User {

    private int id;
    private String fullName;
    private String birthday;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String address;
    private String resetToken;
    private String resetTokenExpiry;


    public User(String fullName, String birthday, String email, String phone, String password, String role, String address, String resetToken, String resetTokenExpiry) {
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

    public User(int id, String fullName, String birthday, String email, String phone, String password, String role, String address, String resetToken, String resetTokenExpiry) {
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

    // Getters and Setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getResetTokenExpiry() {
        return resetTokenExpiry;
    }

    public void setResetTokenExpiry(String resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }



    @Override
    public String toString() {
        return "User [id=" + id + ", fullName=" + fullName + ", birthday=" + birthday + ", email=" + email + ", phone=" + phone + ", password=" + password + ", role=" + role + ", address=" + address + ", resetToken=" + resetToken + ", resetTokenExpiry=" + resetTokenExpiry  + "]";
    }
}
