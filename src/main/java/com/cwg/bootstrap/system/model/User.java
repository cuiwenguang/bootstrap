package com.cwg.bootstrap.system.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class User {
    private Integer userId;
    
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, min = 2, message = "用户名长度必须在{min}~{max}之间")
    private String userName;
    
    @NotBlank(message = "密码不能为空")
    @Length(max = 20, min = 6, message = "密码长度必须在{min}~{max}之间")
    private String password;
    
    private String email;

    private String salt;

    private Boolean isAdmin;
    
    private List<Role> roles; 

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}